package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Region;
import java.lang.Boolean;
import java.lang.String;
import java.util.Set;

privileged aspect Street_Roo_JavaBean {
    
    public String Street.getCaption() {
        return this.caption;
    }
    
    public void Street.setCaption(String caption) {
        this.caption = caption;
    }
    
    public Set<Region> Street.getRegions() {
        return this.regions;
    }
    
    public void Street.setRegions(Set<Region> regions) {
        this.regions = regions;
    }
    
    public Boolean Street.getDeprecated() {
        return this.deprecated;
    }
    
    public void Street.setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }
    
}
