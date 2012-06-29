package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect StatLand_Roo_ToString {
    
    public String StatLand.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("CreationDate: ").append(getCreationDate()).append(", ");
        sb.append("District: ").append(getDistrict()).append(", ");
        sb.append("Region: ").append(getRegion()).append(", ");
        sb.append("Street: ").append(getStreet()).append(", ");
        sb.append("StreetObjectNo: ").append(getStreetObjectNo()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Plottage: ").append(getPlottage());
        return sb.toString();
    }
    
}
