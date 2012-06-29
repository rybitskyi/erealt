package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.AbstractObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 6/8/12
 */
@ManagedBean
@RequestScoped
public class WelcomeBean {
    private List<AbstractObject> actualObjects;

    private AbstractObject selectedObject;

    public WelcomeBean() {
        actualObjects = AbstractObject.findRecentObjects();
    }

    public List<AbstractObject> getActualObjects() {
        return actualObjects;
    }

    public void setActualObjects(List<AbstractObject> actualObjects) {
        this.actualObjects = actualObjects;
    }

    public AbstractObject getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(AbstractObject selectedObject) {
        this.selectedObject = selectedObject;
    }
}
