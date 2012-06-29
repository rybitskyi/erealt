package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.District;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * User: yury.ribitsky
 * Date: 6/15/12
 */
@FacesConverter(value="district")
public class DistrictConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return District.findDistrict(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return value instanceof District ? ((District) value).getId().toString() : "";
    }
}
