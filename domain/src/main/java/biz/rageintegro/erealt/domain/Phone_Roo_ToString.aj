package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect Phone_Roo_ToString {
    
    public String Phone.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(getCode()).append(") ");
        sb.append(getPhoneNumber());
        return sb.toString();
    }
    
}
