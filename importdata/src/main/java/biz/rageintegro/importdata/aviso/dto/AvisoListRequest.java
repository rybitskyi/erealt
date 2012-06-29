package biz.rageintegro.importdata.aviso.dto;

import java.io.Serializable;

public class AvisoListRequest implements Serializable{

    private String city;
    private int rid;
    private int rub;
    private int adistr;
    private RoomCount roomCount;
    private boolean onlyWithPhoto;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRub() {
        return rub;
    }

    public void setRub(int rub) {
        this.rub = rub;
    }

    public int getAdistr() {
        return adistr;
    }

    public void setAdistr(int adistr) {
        this.adistr = adistr;
    }

    public RoomCount getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(RoomCount roomCount) {
        this.roomCount = roomCount;
    }

    public boolean isOnlyWithPhoto() {
        return onlyWithPhoto;
    }

    public void setOnlyWithPhoto(boolean onlyWithPhoto) {
        this.onlyWithPhoto = onlyWithPhoto;
    }
}
