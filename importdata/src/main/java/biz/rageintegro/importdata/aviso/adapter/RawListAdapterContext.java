package biz.rageintegro.importdata.aviso.adapter;

import org.jsoup.nodes.Document;

import javax.xml.validation.Schema;
import java.io.File;

public class RawListAdapterContext {
    Document doc;
    private String title;
    private String regionName;
    private File photoDir;

    public File getPhotoDir() {
        return photoDir;
    }

    public void setPhotoDir(File photoDir) {
        this.photoDir = photoDir;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTitle() {
        return title;
    }

    public String getRegionName() {
        return regionName;
    }
}
