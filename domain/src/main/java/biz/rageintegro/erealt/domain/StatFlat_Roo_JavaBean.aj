package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Integer;
import java.lang.String;

privileged aspect StatFlat_Roo_JavaBean {
    
    public RoomCountType StatFlat.getRoomCount() {
        return this.roomCount;
    }
    
    public void StatFlat.setRoomCount(RoomCountType roomCount) {
        this.roomCount = roomCount;
    }
    
    public String StatFlat.getFlatArea() {
        return this.flatArea;
    }
    
    public void StatFlat.setFlatArea(String flatArea) {
        this.flatArea = flatArea;
    }
    
    public Integer StatFlat.getFlatFloor() {
        return this.flatFloor;
    }
    
    public void StatFlat.setFlatFloor(Integer flatFloor) {
        this.flatFloor = flatFloor;
    }
    
    public Integer StatFlat.getBuildFloor() {
        return this.buildFloor;
    }
    
    public void StatFlat.setBuildFloor(Integer buildFloor) {
        this.buildFloor = buildFloor;
    }
    
}
