package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect District_Roo_JavaBean {
    
    public String District.getCaption() {
        return this.caption;
    }
    
    public void District.setCaption(String caption) {
        this.caption = caption;
    }
	
	public String District.getTranslitCaption() {	
		return TranslitConverter.translit(getCaption());
	}    
    
    public String District.getCaption2() {
        return this.caption2;
    }
    
    public void District.setCaption2(String caption2) {
        this.caption2 = caption2;
    }
}
