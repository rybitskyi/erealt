package biz.rageintegro.erealt.web;

import java.beans.PropertyEditorSupport;

/**
* User: rybitskyii
*/
public class StringNullableEditor extends PropertyEditorSupport {

    public String getAsText() {
        return (getValue() == null ? "" : String.valueOf(getValue()));
    }

    public void setAsText(final String text) throws IllegalArgumentException {
        if (text != null && text.length() == 0) {
            setValue(null);
        } else if ("all".equals(text)) {
        	setValue(null);
        } 
        else {
            setValue(text);
        }
    }
}
