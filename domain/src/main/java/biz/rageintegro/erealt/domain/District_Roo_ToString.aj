package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect District_Roo_ToString {
    
    public String District.toString() {
        return getCaption();
    }
    
}
