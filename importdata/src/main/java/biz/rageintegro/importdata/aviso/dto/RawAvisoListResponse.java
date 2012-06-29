package biz.rageintegro.importdata.aviso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.Set;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RawAvisoListResponse implements Serializable{

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private Set<RawItemDTO> items;
    private String url;
    private ObjectType objectType;

    @XmlElementWrapper(name = "validationResults")
    @XmlElement(name = "validationResult")
    private Set<ValidationResult> validationResults;

    public Set<RawItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<RawItemDTO> items) {
        this.items = items;
    }
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

    public Set<ValidationResult> getValidationResults() {
        return validationResults;
    }

    public void setValidationResults(Set<ValidationResult> validationResults) {
        this.validationResults = validationResults;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }
}
