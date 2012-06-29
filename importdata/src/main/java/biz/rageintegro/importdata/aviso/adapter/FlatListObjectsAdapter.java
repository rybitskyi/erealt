package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.erealt.domain.stub.*;
import biz.rageintegro.importdata.aviso.AvisoUtils;
import biz.rageintegro.importdata.aviso.dto.FlatRawItemDTO;
import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import org.springframework.stereotype.Component;

@Component
public class FlatListObjectsAdapter<T extends Flat, RawT extends FlatRawItemDTO> extends AbstractListObjectsAdapter<T, RawT>{
    private RoomCountAdapter roomCountAdapter;

    @Override
    public void fillObject(T object, RawT item) {
        super.fillObject(object, item);
        object.setBuildFloor(AvisoUtils.getBuildFloor(item.getDescription()));
        object.setFlatArea(AvisoUtils.getFlatArea(item.getDescription()));
        object.setFlatFloor(AvisoUtils.getFlatFloor(item.getDescription()));
        object.setRoomCount(getRoomCountAdapter().getRoomCount(item.getRoomCount()));
    }

    private RoomCountAdapter getRoomCountAdapter() {
        if (roomCountAdapter == null) {
            roomCountAdapter = new RoomCountAdapter();
        }
        return roomCountAdapter;
    }
}
