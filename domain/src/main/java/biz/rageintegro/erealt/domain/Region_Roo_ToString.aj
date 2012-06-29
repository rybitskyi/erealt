package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect Region_Roo_ToString {
    
    public String Region.toString() {
        return getCaption();
    }
    
}
