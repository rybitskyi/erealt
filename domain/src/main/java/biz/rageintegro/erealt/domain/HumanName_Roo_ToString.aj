package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect HumanName_Roo_ToString {
    
    public String HumanName.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Caption: ").append(getCaption()).append(", ");
        sb.append("Male: ").append(getMale());
        return sb.toString();
    }
    
}
