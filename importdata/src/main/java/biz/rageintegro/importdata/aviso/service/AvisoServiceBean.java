package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.AbstractObject;
import biz.rageintegro.erealt.domain.stub.Flat;
import biz.rageintegro.importdata.aviso.adapter.*;
import biz.rageintegro.importdata.aviso.dto.*;
import biz.rageintegro.importdata.parser.RegionParser;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class AvisoServiceBean {
    @Value("#{importdata.testMode}")
    private Boolean testMode;
    @Value("#{importdata.photoDir}")
    private String photoDirPath;

    @Value("#{importdata.testInputFileName}")
    private String testInputFileName;

    @Autowired
    private DomainServiceProxyWrapper domainService;
    @Autowired
    private ObjectTypeAdapter objectTypeAdapter;
    @Autowired
    private RawListObjectsAdapterFactory rawListAdapterFactory;
    @Autowired
    private ObjectEntityFactory objectEntityFactory;
    @Autowired
    private ListObjectsAdapterFactory listObjectsAdapterFactory;

    private DataBean getData(Document doc, File photoDir) {
        DataBean result = new DataBean();
        result.items = new HashSet<AbstractObject>();
        ObjectType objectType = objectTypeAdapter.getObjectType(doc);
        result.objectType = objectType;
        for (RawItemDTO rawItem : rawListAdapterFactory.getAdapter(objectType).getRawItems(doc, photoDir).getItems()) {
            AbstractObject object = objectEntityFactory.getEntity(result.objectType).create();
            listObjectsAdapterFactory.getAdapter(result.objectType).fillObject(object, rawItem);
            result.items.add(object);
        }
        return result;
    }

    private Document getTestDoc() {
        String str = "/" + testInputFileName;
        InputStream in = this.getClass().getResourceAsStream(str);
        if (in == null) {
            throw new NullPointerException("Resource " + str + " can't be found on classpath");
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            return Jsoup.parse(writer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getUrl(AvisoListRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://online.aviso.ua");
        sb.append("/kiev"); //todo: use another city
        sb.append("/rubrics_new.php"); //todo: change if necessary
        sb.append("?rid=").append(request.getRid());
        sb.append("&rub=").append(request.getRub());
        sb.append("&adistr=").append(request.getAdistr());
        sb.append("&sortby=1");
        if (request.isOnlyWithPhoto()) {
            sb.append("&fonly=on");
        }
        if (request.getRoomCount() != null) {
            sb.append("&rooms");
            switch (request.getRoomCount()) {
                case ONE:
                    sb.append("1=1");
                    break;
                case TWO:
                    sb.append("2=2");
                    break;
                case THREE:
                    sb.append("3=3");
                    break;
                case FOUR:
                    sb.append("4=4");
                    break;
            }
        }
        return sb.toString();
    }

    private AbstractObject loaded(AbstractObject object) {
        if (object.getEid() == null || object.getSource() == null) {
            throw new RuntimeException("Fields eid and source must be filled up");
        }
        return (AbstractObject) domainService.getService().findAbstractObjectByStreetIdAndEidAndSource(
                object.getStreet().getId(), object.getEid(), object.getSource()).getValue();
    }

    private boolean isValid(AbstractObject object) {
        boolean valid = object.getCreationDate() != null
                && object.getDistrict() != null
                && object.getRegion() != null
                && object.getStreet() != null
                && object.getAccessUser() != null
                && object.getSource() != null && object.getEid() != null //required fields for aviso
                ;
        if (object instanceof Flat) {
            valid = valid && object.getStreetObjectNo() != null;
        }
        return valid;
    }

    public RawAvisoListResponse getRawList(AvisoListRequest request) {
        Document doc = getDocument(request);
        ObjectType objectType = objectTypeAdapter.getObjectType(doc);
        RawAvisoListResponse result = rawListAdapterFactory.getAdapter(objectType).getRawItems(doc, null);
        result.setUrl(getUrl(request));
        return result;
    }

    public AvisoListResponse prepareImportData(AvisoListRequest request) {
        AvisoListResponse result = new AvisoListResponse();
        Set<AbstractObject> items = getData(getDocument(request), null).items;
        result.setItems(items);
        result.setUrl(getUrl(request));
        return result;
    }

    public AvisoListResponse importData(AvisoListRequest request) {
//todo: find object be descripiton and update if changed. Not to create new one as now
//todo: use batch-processing
//todo: add map to not process the objects with same eid
//todo: use prepareImportData. rawListAdapter.setPhotoDir(photoDir) must be deleted
        AvisoListResponse result = new AvisoListResponse();
        File photoDir = null;
        try {
            photoDir = new File(photoDirPath, "aviso-work-photo" + new Date().getTime());
            DataBean dataBean = getData(getDocument(request), photoDir);
            result.setItems(new HashSet<AbstractObject>());
            result.setUrl(getUrl(request));
            AbstractObjectEntity entity = objectEntityFactory.getEntity(dataBean.objectType);
            try {
                FileUtils.forceMkdir(photoDir);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            for (AbstractObject object : dataBean.items) {
                if (isValid(object)) {
                    result.getItems().add(object);
                    AbstractObject ao = loaded(object);
                    if (ao == null) {
                        entity.persist(object);
                    } else {
                        object.setId(ao.getId());
                        entity.merge(object);
                    }
                }
                //todo: log skipped records
            }
        } finally {
            if (photoDir != null) {
                FileUtils.deleteQuietly(photoDir);
            }
        }
        return result;
    }

    private Document getDocument(AvisoListRequest request) {
        if (!testMode) {//live-mode
            try {
                return createDoc(getUrl(request));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else { //test-mode
            return getTestDoc();
        }
    }

    private Document createDoc(String url) throws IOException {
        return Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();
    }

    /**
     * Test mode method
     *
     * @return
     */
    Set<RawItemDTO> getRawItems(Document doc) {
        ObjectType objectType = objectTypeAdapter.getObjectType(doc);
        return rawListAdapterFactory.getAdapter(objectType).getRawItems(doc, null).getItems();
    }

    private static class DataBean {
        private ObjectType objectType;
        private Set<AbstractObject> items;
    }
}
