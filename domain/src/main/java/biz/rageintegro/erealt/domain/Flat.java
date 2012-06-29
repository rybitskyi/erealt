package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AbstractObject;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import biz.rageintegro.erealt.reference.RoomCountType;
import javax.validation.constraints.NotNull;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findFlatsByDistrictAndRegionAndRoomCountAndPriceBetween" })
public abstract class Flat extends AbstractObject {

    @NotNull
    @Enumerated
    private RoomCountType roomCount;

    @Pattern(regexp = "([0-9]+/[0-9]+/[0-9]+)?")
    private String flatArea;

    @Min(0L)
    @Max(100L)
    private Integer flatFloor;

    @Min(0L)
    @Max(100L)
    private Integer buildFloor;

    public Map<String, String> getQuickInfo() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (getRoomCount() != null) {
            map.put(getRoomCount().getCaption(), "label.roomCountPostfix");
        }
        if (getFlatArea() != null) {
            map.put(getFlatArea(), "label.flatAreaPostfix");
        }
        if (getFlatFloor() != null && getBuildFloor() != null) {
            map.put(getFlatFloor() +  "\\" + getBuildFloor(), "label.flatFloorPostfix");
        }
        return map;
    }

    @Override
    public List<QuickInfo> getQuickInfoList() {
        List<QuickInfo> list = new ArrayList<QuickInfo>();
        if (getRoomCount() != null) {
            list.add(new QuickInfo("label_roomCountPostfix", getRoomCount().getCaption()));
        }
        if (getFlatArea() != null) {
            list.add(new QuickInfo("label_flatAreaPostfix", getFlatArea()));
        }
        if (getFlatFloor() != null && getBuildFloor() != null) {
            list.add(new QuickInfo("label_flatFloorPostfix", getFlatFloor() + "\\" + getBuildFloor()));
        }
        return list;
    }
}
