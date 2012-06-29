package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect StatAbstractObject_Roo_ToString {
    
    public String StatAbstractObject.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("CreationDate: ").append(getCreationDate()).append(", ");
        sb.append("District: ").append(getDistrict()).append(", ");
        sb.append("Region: ").append(getRegion()).append(", ");
        sb.append("Street: ").append(getStreet()).append(", ");
        sb.append("StreetObjectNo: ").append(getStreetObjectNo()).append(", ");
        sb.append("Price: ").append(getPrice());
        return sb.toString();
    }
    
}
