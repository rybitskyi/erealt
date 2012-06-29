package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect Street_Roo_ToString {
    
    public String Street.toString() {
        return getCaption();
    }
    
}
