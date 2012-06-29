package biz.rageintegro.erealt.domain;

import java.lang.Integer;
import java.lang.String;

privileged aspect Phone_Roo_JavaBean {
    
    public String Phone.getCode() {
        return this.code;
    }
    
    public void Phone.setCode(String code) {
        this.code = code;
    }
    
    public Integer Phone.getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void Phone.setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
