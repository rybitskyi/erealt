package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.OperationType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
@FacesConverter(value="operationType")
public class OperationTypeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return OperationType.getInstanceByKey(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return value instanceof OperationType ? ((OperationType) value).getKey() : "";
    }

}
