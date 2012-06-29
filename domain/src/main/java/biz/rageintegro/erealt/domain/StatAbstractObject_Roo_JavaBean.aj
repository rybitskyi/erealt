package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.Street;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

privileged aspect StatAbstractObject_Roo_JavaBean {
    
    public Date StatAbstractObject.getCreationDate() {
        return this.creationDate;
    }
    
    public void StatAbstractObject.setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public District StatAbstractObject.getDistrict() {
        return this.district;
    }
    
    public void StatAbstractObject.setDistrict(District district) {
        this.district = district;
    }
    
    public Region StatAbstractObject.getRegion() {
        return this.region;
    }
    
    public void StatAbstractObject.setRegion(Region region) {
        this.region = region;
    }
    
    public Street StatAbstractObject.getStreet() {
        return this.street;
    }
    
    public void StatAbstractObject.setStreet(Street street) {
        this.street = street;
    }
    
    public String StatAbstractObject.getStreetObjectNo() {
        return this.streetObjectNo;
    }
    
    public void StatAbstractObject.setStreetObjectNo(String streetObjectNo) {
        this.streetObjectNo = streetObjectNo;
    }
    
    public Integer StatAbstractObject.getPrice() {
        return this.price;
    }
    
    public void StatAbstractObject.setPrice(Integer price) {
        this.price = price;
    }
    
}
