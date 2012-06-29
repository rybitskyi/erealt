package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.erealt.domain.stub.Phone;
import biz.rageintegro.importdata.aviso.TelephoneParser;
import biz.rageintegro.importdata.aviso.dto.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Eid is the size of photo file in bytes.
 */
public abstract class AbstractRawListAdapter<T extends RawItemDTO> {
    private static String A_PREFIX = "view.php?adid=";

    private static List<String> DIV_CLASS_NAMES = new ArrayList<String>();
    static{
        DIV_CLASS_NAMES.add("ads-usual");
    }
    private Schema schema;

    @Autowired
    private ObjectTypeAdapter objectTypeAdapter;
    @Autowired
    private RawItemDTOFactory rawItemDTOFactory;

    public RawAvisoListResponse getRawItems(Document doc, File photoDir) {
        RawListAdapterContext context = new RawListAdapterContext();
        context.setDoc(doc);
        context.setPhotoDir(photoDir);
        RawAvisoListResponse response = new RawAvisoListResponse();
        Set<RawItemDTO> items = new HashSet<RawItemDTO>();
        Set<ValidationResult> validationResults = new HashSet<ValidationResult>();
        response.setItems(items);
        response.setValidationResults(validationResults);
        initPageParams(context);

        ObjectType objectType = objectTypeAdapter.getObjectType(doc);
        response.setObjectType(objectType);

        for (String divClassName : DIV_CLASS_NAMES) {
            Elements divs = doc.getElementsByClass(divClassName);
            for (Element div : divs) {
                T item = (T) rawItemDTOFactory.getInstance(objectType);
                fillRawItemDTO(item, div, context);
                try {
                    ValidationResult validationResult = validate(item);
                    if (validationResult.isEmpty()) {
                        items.add(item);
                    } else {
                        validationResults.add(validationResult);
                    }
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;
    }

    protected void fillRawItemDTO(T item, Element div, RawListAdapterContext context) {
        item.setTitle(context.getTitle());
        item.setRegionName(context.getRegionName());
        item.setEid(getEid(div));
        item.setDescription(getDescription(div));
        item.setPrice(getPrice(div));
        item.setPhotoPath(getPhotoPath(div));
        try {
            item.setPhotoFile(getPhotoFile(div, item.getEid(), context.getPhotoDir()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        item.setId(getId(item.getPhotoFile()));
        item.setDate(getDate(div));
        item.setAddress(getAddress(div));
        item.setNormalizedPhones(getNormalizedPhones(div));
        item.setPhones(getPhones(item.getNormalizedPhones()));
    }

    protected void initPageParams(RawListAdapterContext context) {
        context.setTitle(getTitle(context.getDoc()));
        context.setRegionName(getRegionName(context.getDoc()));
    }

    private Element getElementByTag(String tagName, Document doc) {
        Elements elements = doc.getElementsByTag(tagName);
        if (elements.size() == 0) {
            throw new RuntimeException("The tag '" + tagName + "' can't be found");
        } else if (elements.size() > 1) {
            throw new RuntimeException("There are more than one tag '" + tagName + "'");
        }
        return elements.get(0);
    }

    private String getTitle(Document doc) {
        Element title = getElementByTag("title", doc);
        return title.text().replaceAll("\n", " ").replaceAll("\\s+", " ");
    }

    private String getRegionName(Document doc) {
        Elements elements = doc.getElementsByAttributeValue("name", "adistr");
        if (elements.size() == 0) {
            throw new RuntimeException("The <select name='adistr'> can't be found");
        } else if (elements.size() > 1) {
            throw new RuntimeException("There are more than one <select name='adistr'>");
        }
        Element select = elements.get(0);
        Elements selectedOptionArr = select.getElementsByAttribute("selected");
        if (selectedOptionArr.size() == 0) {
            throw new RuntimeException("The <option selected> can't be found");
        } else if (selectedOptionArr.size() > 1) {
            throw new RuntimeException("There are more than one <option selected>");
        }
        return selectedOptionArr.get(0).text();
    }

    private String getDescription(Element div) {
        String value = div.text();
        value = value.replaceAll("\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d", ""); //remove date string
        String price = getPrice(div);
        if (price.length() > 0) {
            value = value.replaceAll(price, "");
        }
        return value;
    }

    private long getId(File file) {
        return file != null ? file.length() : -1;
    }

    private long getEid(Element div) {
        Elements elements = div.select("a[href^=" + A_PREFIX + "]");
        if (elements.size() == 0) {
            throw new RuntimeException("Can't parse id from " + div.text());
        }
        Element a = elements.get(0);
        String href = a.attr("href");
        return Long.parseLong(href.substring(A_PREFIX.length()));
    }

    private String getAddress(Element div) {
        Elements elements = div.select("a[href^=" + A_PREFIX + "]");
        if (elements.size() == 0) {
            throw new RuntimeException("Can't parse address from " + div.text());
        }
        for (Element el : elements) {
            Elements bList = el.getElementsByTag("b");
            if (bList.size() > 0) {
                return bList.get(0).text();
            }
        }
        return null;
    }

    private File getPhotoFile(Element div, long eid, File photoDir) throws IOException {
        if (photoDir == null) {
            return null;
        }
        String photoPath = getPhotoPath(div);
        if (photoPath == null || photoPath.isEmpty()) {
            return null;
        }
        File f = new File(photoDir, String.valueOf(eid));
        URL url = new URL(photoPath);
        FileUtils.copyURLToFile(url, f);
        return f;
    }

    private String getPhotoPath(Element div) {
        Elements elements = div.getElementsByClass("photo");
        if (elements.size() > 0) {
            Element a = elements.get(0);
            Element img = a.getElementsByTag("img").get(0);
            String value = img.attr("src");
            value = value.replaceFirst("../", "http://online.aviso.ua/");
            value = value.replaceFirst("/thumb/", "/");
            return value;
        }
        return null;
    }

    private String getPrice(Element div) {
        Elements elements = div.getElementsByClass("price");
        if (elements.size() == 0) {
            throw new RuntimeException("Can't parse price from " + div.text());
        }
        Element span = elements.get(0);
        return span.text();
    }

    private String getDateString(Element div) {
        Elements elements = div.getElementsByClass("date");
        if (elements.size() == 0) {
            throw new RuntimeException("Can't parse date from " + div.text());
        }
        Element span = elements.get(0);
        return span.text();
    }

    private Date getDate(Element div) {
        String dateValue = getDateString(div);
        if (dateValue.length() > 0) {
            String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            try {
                return sdf.parse(dateValue);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new Date();
        }
    }

    private String getNormalizedPhones(Element div) {
        String value = div.text();
        return TelephoneParser.parseTelephone(value);
    }

    private List<Phone> getPhones(String normalizedPhones) {
        List<Phone> phones = TelephoneParser.parseNormalizedTelephone(normalizedPhones);
        return phones.size() > 0 ? phones : null;
    }

    private List<ByteArrayOutputStream> schema(Class... classes) throws Exception {
        if (classes.length == 0) {
            throw new RuntimeException("There is no classes to generate schema for");
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(classes);
        final List<ByteArrayOutputStream> outs = new ArrayList<ByteArrayOutputStream>();
        jaxbContext.generateSchema(
                new SchemaOutputResolver() {
                    @Override
                    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        outs.add(out);
                        StreamResult streamResult = new StreamResult(out);
                        streamResult.setSystemId("");
                        return streamResult;
                    }
                }
        );
        return outs;
    }

    private Schema getSchema() {
        if (schema == null) {
            List<ByteArrayOutputStream> outs;
            try {
                outs = schema(RawItemDTO.class, FlatRawItemDTO.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            StreamSource[] sources = new StreamSource[outs.size()];
            for (int i = 0; i < outs.size(); i++) {
                ByteArrayOutputStream out = outs.get(i);
                sources[i] = new StreamSource(new ByteArrayInputStream(out.toByteArray()), "");
            }
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                schema = sf.newSchema(sources);
            } catch (Exception e) {
                throw new RuntimeException("Invalid schema", e);
            }
        }
        return schema;
    }

    private ValidationResult validate(final RawItemDTO item) throws JAXBException {
        final ValidationResult result = new ValidationResult();
        final List<ValidationResultItem> warnings = new ArrayList<ValidationResultItem>();
        final List<ValidationResultItem> errors = new ArrayList<ValidationResultItem>();
        final List<ValidationResultItem> fatalErrors = new ArrayList<ValidationResultItem>();
        Validator validator = getSchema().newValidator();
        validator.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void warning(SAXParseException exception) throws SAXException {
                        warnings.add(new ValidationResultItem(String.valueOf(item), exception.getMessage()));
                    }

                    @Override
                    public void error(SAXParseException exception) throws SAXException {
                        errors.add(new ValidationResultItem(String.valueOf(item), exception.getMessage()));
                    }

                    @Override
                    public void fatalError(SAXParseException exception) throws SAXException {
                        fatalErrors.add(new ValidationResultItem(String.valueOf(item), exception.getMessage()));
                    }
                }
        );
        result.setWarnings(warnings);
        result.setErrors(errors);
        result.setFatalErrors(fatalErrors);
        JAXBContext jaxbContext = JAXBContext.newInstance(RawItemDTO.class);
        JAXBSource jbSrc = new JAXBSource(jaxbContext.createMarshaller(), item);
        try {
            validator.validate(jbSrc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
