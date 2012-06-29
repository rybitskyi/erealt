package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Phone;

import javax.xml.bind.annotation.XmlTransient;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;

privileged aspect AccessUser_Roo_JavaBean {
    
    public String AccessUser.getName() {
        return this.name;
    }
    
    public void AccessUser.setName(String name) {
        this.name = name;
    }

    public String AccessUser.getCaption() {
        return this.caption;
    }
    
    public void AccessUser.setCaption(String caption) {
        this.caption = caption;
    }
    
    public String AccessUser.getPassword() {
        return this.password;
    }
    
    public void AccessUser.setPassword(String password) {
        this.password = password;
    }
    
    public String AccessUser.getTelephone() {
        if (this.telephone == null || this.telephone.length() == 0) {
            if (getPhone().size() > 0) {
                StringBuilder sb = new StringBuilder();
                Phone phone = getPhone().get(0);
                if (phone.getCode() != null && phone.getCode().length() > 0) {
                    sb.append("(").append(phone.getCode()).append(")");
                }
                sb.append(phone.getPhoneNumber());
                return sb.toString();
            }
        }
        return this.telephone;
    }

    @XmlTransient
    public String AccessUser.getAllTelephones() {
        if (this.telephone == null || this.telephone.length() == 0) {
            if (getPhone().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, n = getPhone().size(); i < n; i++) {
                    Phone phone = getPhone().get(i);
                    if (phone.getCode() != null && phone.getCode().length() > 0) {
                        sb.append("(").append(phone.getCode()).append(")");
                    }
                    sb.append(phone.getPhoneNumber());
                    if (i < n - 1) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
        }
        return this.telephone;
    }

    public void AccessUser.setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public List<Phone> AccessUser.getPhone() {
        return this.phone;
    }
    
    public void AccessUser.setPhone(List<Phone> phone) {
        this.phone = phone;
    }
    
    public Boolean AccessUser.getPublicEmail() {
        return this.publicEmail;
    }
    
    public void AccessUser.setPublicEmail(Boolean publicEmail) {
        this.publicEmail = publicEmail;
    }

    public String AccessUser.getEmail() {
        return this.name;
    }

    public String AccessUser.getNameFromEmail() {
        if (getEmail() != null) {
            if (getEmail().indexOf('@') > -1) {
                return getEmail().substring(0, getEmail().indexOf('@'));
            }
            else {
                return getEmail();
            }
        }
        else {
            return null;
        }
    }
}
