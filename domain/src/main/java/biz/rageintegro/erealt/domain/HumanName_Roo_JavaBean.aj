package biz.rageintegro.erealt.domain;

import java.lang.Boolean;
import java.lang.String;

privileged aspect HumanName_Roo_JavaBean {
    
    public String HumanName.getCaption() {
        return this.caption;
    }
    
    public void HumanName.setCaption(String caption) {
        this.caption = caption;
    }
    
    public Boolean HumanName.getMale() {
        return this.male;
    }
    
    public void HumanName.setMale(Boolean male) {
        this.male = male;
    }
    
}
