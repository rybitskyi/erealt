package biz.rageintegro.erealt.domain;

import java.lang.Integer;

privileged aspect StatLand_Roo_JavaBean {
    
    public Integer StatLand.getPlottage() {
        return this.plottage;
    }
    
    public void StatLand.setPlottage(Integer plottage) {
        this.plottage = plottage;
    }
    
}
