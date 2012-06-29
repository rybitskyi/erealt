package biz.rageintegro.importdata.aviso.dto;

import biz.rageintegro.erealt.domain.stub.Phone;

import javax.xml.bind.annotation.*;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "flatRawItem")
@XmlType(name = "flatRawItemType")
public class FlatRawItemDTO extends RawItemDTO {
    @XmlElement(required = true)
    private Integer roomCount;

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("roomCount=").append(roomCount)/*.append(", ")*/;
        return sb.toString();
    }
}
