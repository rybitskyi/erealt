package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect AccessUser_Roo_ToString {
    
    public String AccessUser.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Caption: ").append(getCaption()).append(", ");
        sb.append("Password: ").append(getPassword()).append(", ");
        sb.append("Telephone: ").append(getTelephone()).append(", ");
        sb.append("Email: ").append(getEmail());
        return sb.toString();
    }
    
}
