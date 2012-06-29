package biz.rageintegro.erealt.domain;

import java.lang.Integer;

privileged aspect Land_Roo_JavaBean {
    
    public Integer Land.getPlottage() {
        return this.plottage;
    }
    
    public void Land.setPlottage(Integer plottage) {
        this.plottage = plottage;
    }
    
}
