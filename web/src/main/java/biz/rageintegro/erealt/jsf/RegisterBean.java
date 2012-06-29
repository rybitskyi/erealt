package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.AbstractObject;
import biz.rageintegro.erealt.domain.AccessUser;
import biz.rageintegro.erealt.domain.Phone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 6/19/12
 */
@ManagedBean
@ViewScoped
public class RegisterBean {
    private AccessUser accessUser;
    private String phone;

    @PostConstruct
    public void init() {
        accessUser = new AccessUser();
    }

    public AccessUser getAccessUser() {
        return accessUser;
    }

    public void setAccessUser(AccessUser accessUser) {
        this.accessUser = accessUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void create(ActionEvent actionEvent) {

        //todo: validate entered email
        //todo: process phones.
        //todo: It can be more than one phone
        //todo: send notification email

/*
        List<Phone> phones = new ArrayList<Phone>();
        phones.add(getPhone());
        accessUser.setPhone(phones);
*/


        //todo: add field 'confirmed' to accessUser table
        if (AccessUser.existRegisteredUser(accessUser.getName())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User is already exists",
                    "User is already exists."));
            return ;
        }
        AccessUser oldUser = AccessUser.findAccessUserByName(accessUser.getName());
        if (oldUser != null) {
            oldUser.setCaption(accessUser.getCaption());
            oldUser.setPassword(accessUser.getPassword());
            oldUser.setTelephone(accessUser.getTelephone());
            oldUser.setVersion(1);  //todo: check it
            oldUser.merge();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User is merged"));
        }
        else {
            accessUser.persist();
            accessUser.setTelephone(getPhone()); //todo: use phone list instead of it
            accessUser.persist();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New user added successfully"));
        }
    }
}
