package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.reference.RoomCountType;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
@FacesConverter(value="roomCountTypeConverter")
public class RoomCountTypeConverter extends EnumConverter {
    public RoomCountTypeConverter() {
        super(RoomCountType.class);
    }
}
