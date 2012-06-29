package biz.rageintegro.importdata.aviso.dto;

import biz.rageintegro.erealt.domain.stub.AbstractObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.Set;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class AvisoListResponse implements Serializable{
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private Set<AbstractObject> items;
    private String url;
	
    public Set<AbstractObject> getItems() {
        return items;
    }

    public void setItems(Set<AbstractObject> items) {
        this.items = items;
    }
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
