package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Integer;
import java.lang.String;

privileged aspect Flat_Roo_JavaBean {
    
    public RoomCountType Flat.getRoomCount() {
        return this.roomCount;
    }
    
    public void Flat.setRoomCount(RoomCountType roomCount) {
        this.roomCount = roomCount;
    }
    
    public String Flat.getFlatArea() {
        return this.flatArea;
    }
    
    public void Flat.setFlatArea(String flatArea) {
        this.flatArea = flatArea;
    }
    
    public Integer Flat.getFlatFloor() {
        return this.flatFloor;
    }
    
    public void Flat.setFlatFloor(Integer flatFloor) {
        this.flatFloor = flatFloor;
    }
    
    public Integer Flat.getBuildFloor() {
        return this.buildFloor;
    }
    
    public void Flat.setBuildFloor(Integer buildFloor) {
        this.buildFloor = buildFloor;
    }
    
}
