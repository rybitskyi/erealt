package biz.rageintegro.importdata.aviso.adapter;


import biz.rageintegro.erealt.domain.stub.RoomCountType;

public class RoomCountAdapter {

    public RoomCountType getRoomCount(Integer roomCount) {
        if (roomCount == 1) {
            return RoomCountType.ONE;
        } else if (roomCount == 2) {
            return RoomCountType.TWO;
        } else if (roomCount == 3) {
            return RoomCountType.THREE;
        } else if (roomCount == 4) {
            return RoomCountType.FOUR;
        } else if (roomCount > 4) {
            return RoomCountType.MORE_THAN_FOUR;
        }
        return null;
    }
}
