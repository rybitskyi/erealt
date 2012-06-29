package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect News_Roo_ToString {
    
    public String News.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("CreationDate: ").append(getCreationDate()).append(", ");
        sb.append("Caption: ").append(getCaption()).append(", ");
        sb.append("Sourceref: ").append(getSourceref());
        return sb.toString();
    }
    
}
