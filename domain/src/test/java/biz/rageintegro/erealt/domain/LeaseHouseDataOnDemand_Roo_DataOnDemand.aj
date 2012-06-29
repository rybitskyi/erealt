package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseHouse;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LeaseHouseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LeaseHouseDataOnDemand: @Component;
    
    private Random LeaseHouseDataOnDemand.rnd = new SecureRandom();
    
    private List<LeaseHouse> LeaseHouseDataOnDemand.data;
    
    public LeaseHouse LeaseHouseDataOnDemand.getNewTransientLeaseHouse(int index) {
        biz.rageintegro.erealt.domain.LeaseHouse obj = new biz.rageintegro.erealt.domain.LeaseHouse();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public LeaseHouse LeaseHouseDataOnDemand.getSpecificLeaseHouse(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        LeaseHouse obj = data.get(index);
        return LeaseHouse.findLeaseHouse(obj.getId());
    }
    
    public LeaseHouse LeaseHouseDataOnDemand.getRandomLeaseHouse() {
        init();
        LeaseHouse obj = data.get(rnd.nextInt(data.size()));
        return LeaseHouse.findLeaseHouse(obj.getId());
    }
    
    public boolean LeaseHouseDataOnDemand.modifyLeaseHouse(LeaseHouse obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void LeaseHouseDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouseEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'LeaseHouse' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.LeaseHouse>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.LeaseHouse obj = getNewTransientLeaseHouse(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
