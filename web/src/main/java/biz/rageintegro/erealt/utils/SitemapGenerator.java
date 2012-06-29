package biz.rageintegro.erealt.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import org.w3c.dom.*;
import org.apache.commons.lang.time.DateUtils;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.TranslitConverter;
import biz.rageintegro.erealt.domain.AbstractObject;


/**
 * User: RybitskyiI
 * Date: 17/2/2011
 */
public class SitemapGenerator {
    private File file;
    private Document doc;
    private Element root;
    private List<String> rubrics;

    public static final String BASE_URL = "http://www.erealt.com.ua/";
    public static final String NAMESPACE = "http://www.sitemaps.org/schemas/sitemap/0.9";

    public List<String> getRubrics() {
        return rubrics;
    }

    public void setRubrics(List<String> rubrics) {
        this.rubrics = rubrics;
    }

    public void run() {
        System.out.println("Start SitemapGenerator" + new Date()); // todo: delete!!

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();

            doc = impl.createDocument(NAMESPACE, "urlset", null);
            root = doc.getDocumentElement();
            root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:schemaLocation", "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");

            fill();

            // transform the Document into a String
            DOMSource domSource = new DOMSource(doc);
            StreamResult result = new StreamResult(getFile());
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected List<Url> getUrlList() {
        List<Url> l = new ArrayList<Url>();
        String[] rubrics = new String[]{"saleflat", "leaseflat", "salehouse", "leasehouse", "saleland"/*, "salenewflat"*/};
        for (String s : rubrics) {
            l.add(new AdvertRubricUrl(s));
        }

        List<Region> regions = Region.findAllRegions();
        for (String s : rubrics) {
            for (Region r : regions) {
                l.add(new RegionFilterUrl(s + "/" + r.getDistrict().getTranslitCaption() + "/" + r.getTranslitCaption()));
            }
        }

        Date dueDate = DateUtils.addMonths(new Date(), -2);
        for (AbstractObject ao : AbstractObject.findManualEntries(dueDate, 0, 500)) {
            l.add(new ManualPropertyUrl("property/" + ao.getExtId(), ao.getCreationDate()));
        }

        for (AbstractObject ao : AbstractObject.findSourceEntries(dueDate, "aviso", 0, 9000)) {
            l.add(new SourcePropertyUrl("property/" + ao.getExtId(), ao.getCreationDate()));
        }

        return l;
    }


    private void fill() {
        for (Url url : getUrlList()) {
            addUrl(url);
        }
    }

    private Element createElement(String qualifiedName) {
        return doc.createElementNS(NAMESPACE, qualifiedName);
    }

    private void addUrl(Url url) {
        Element el = createElement("url");
        url.saveToElement(el);
        root.appendChild(el);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private class Url {
        private String loc;
        private Date lastmod;
        private double priority;
        private CHANGEFREQ changefreq;

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public void setLastmod(Date lastmod) {
            this.lastmod = lastmod;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }

        public void setChangefreq(CHANGEFREQ changefreq) {
            this.changefreq = changefreq;
        }

        public String getLoc() {
            return BASE_URL + loc;
        }

        public String getLastmod() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

            TimeZone tz = TimeZone.getTimeZone("UTC");

            df.setTimeZone(tz);

            String output = df.format(lastmod);

            int inset0 = 9;
            int inset1 = 6;

            String s0 = output.substring(0, output.length() - inset0);
            String s1 = output.substring(output.length() - inset1, output.length());

            String result = s0 + s1;

            result = result.replaceAll("UTC", "+00:00");
            return result;
        }

        public String getPriority() {
            return String.valueOf(priority);
        }

        public String getChangefreq() {
            return changefreq.toString();
        }

        public void saveToElement(Element el) {
            Element loc = createElement("loc");
            loc.setTextContent(getLoc());
            el.appendChild(loc);

            Element lastmod = createElement("lastmod");
            lastmod.setTextContent(getLastmod());
            el.appendChild(lastmod);

            Element priority = createElement("priority");
            priority.setTextContent(getPriority());
            el.appendChild(priority);

            Element changefreq = createElement("changefreq");
            changefreq.setTextContent(getChangefreq());
            el.appendChild(changefreq);
        }
    }

    private class AdvertRubricUrl extends Url {

        public AdvertRubricUrl(String rubricName) {
            setLoc(rubricName);
            setLastmod(new Date());
            setPriority(0.2);
            setChangefreq(CHANGEFREQ.HOURLY);
        }
    }

    private class RegionFilterUrl extends Url {

        public RegionFilterUrl(String rubricName) {
            setLoc(rubricName);
            setLastmod(new Date());
            setPriority(0.3);
            setChangefreq(CHANGEFREQ.HOURLY);
        }
    }

    private class ManualPropertyUrl extends Url {

        public ManualPropertyUrl(String rubricName, Date lastmod) {
            setLoc(rubricName);
            setLastmod(lastmod);
            setPriority(1.0);
            setChangefreq(CHANGEFREQ.WEEKLY);
        }
    }

    private class SourcePropertyUrl extends Url {

        public SourcePropertyUrl(String rubricName, Date lastmod) {
            setLoc(rubricName);
            setLastmod(lastmod);
            setPriority(0.2);
            setChangefreq(CHANGEFREQ.WEEKLY);
        }
    }

    private enum CHANGEFREQ {
        ALWAYS, HOURLY, WEEKLY;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
