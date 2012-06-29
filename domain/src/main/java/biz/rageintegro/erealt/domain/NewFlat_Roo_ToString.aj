package biz.rageintegro.erealt.domain;

import java.lang.String;

privileged aspect NewFlat_Roo_ToString {
    
    public String NewFlat.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("CreationDate: ").append(getCreationDate()).append(", ");
        sb.append("District: ").append(getDistrict()).append(", ");
        sb.append("Region: ").append(getRegion()).append(", ");
        sb.append("Street: ").append(getStreet()).append(", ");
        sb.append("StreetObjectNo: ").append(getStreetObjectNo()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("ClientName: ").append(getClientName()).append(", ");
        sb.append("Telephone: ").append(getTelephone()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("RoomCount: ").append(getRoomCount()).append(", ");
        sb.append("FlatArea: ").append(getFlatArea()).append(", ");
        sb.append("FlatFloor: ").append(getFlatFloor()).append(", ");
        sb.append("BuildFloor: ").append(getBuildFloor());
        return sb.toString();
    }
    
}
