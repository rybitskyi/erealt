package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.AbstractObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 6/11/12
 */
@ManagedBean
@RequestScoped
public class PropertyBean {
    private AbstractObject entity;
    private List<String> images;

    public PropertyBean() {
        entity = (AbstractObject) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("object");
    }

    public AbstractObject getEntity() {
        return entity;
    }

    public void setEntity(AbstractObject entity) {
        this.entity = entity;
    }

    public List<String> getImages() {
        images = new ArrayList<String>();
        images.add(entity.getPhotoUrl());
        return images;
    }
}
