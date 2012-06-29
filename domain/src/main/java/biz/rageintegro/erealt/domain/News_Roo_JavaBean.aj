package biz.rageintegro.erealt.domain;

import java.lang.String;
import java.util.Date;

privileged aspect News_Roo_JavaBean {
    
    public Date News.getCreationDate() {
        return this.creationDate;
    }
    
    public void News.setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public String News.getCaption() {
        return this.caption;
    }
    
    public void News.setCaption(String caption) {
        this.caption = caption;
    }
    
    public String News.getSourceref() {
        return this.sourceref;
    }
    
    public void News.setSourceref(String sourceref) {
        this.sourceref = sourceref;
    }
    
}
