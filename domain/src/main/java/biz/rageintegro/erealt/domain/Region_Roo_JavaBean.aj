package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
import java.lang.String;

privileged aspect Region_Roo_JavaBean {
    
    public String Region.getCaption() {
        return this.caption;
    }
    
    public void Region.setCaption(String caption) {
        this.caption = caption;
    }
	
	public String Region.getTranslitCaption() {	
		return TranslitConverter.translit(getCaption());
	}    
    
    public String Region.getCaption2() {
        return this.caption2;
    }
    
    public void Region.setCaption2(String caption2) {
        this.caption2 = caption2;
    }
    
    public District Region.getDistrict() {
        return this.district;
    }
    
    public void Region.setDistrict(District district) {
        this.district = district;
    }
    
}
