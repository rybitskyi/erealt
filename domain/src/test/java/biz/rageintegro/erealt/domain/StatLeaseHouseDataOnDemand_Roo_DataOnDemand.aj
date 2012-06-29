package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseHouse;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatLeaseHouseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatLeaseHouseDataOnDemand: @Component;
    
    private Random StatLeaseHouseDataOnDemand.rnd = new SecureRandom();
    
    private List<StatLeaseHouse> StatLeaseHouseDataOnDemand.data;
    
    public StatLeaseHouse StatLeaseHouseDataOnDemand.getNewTransientStatLeaseHouse(int index) {
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = new biz.rageintegro.erealt.domain.StatLeaseHouse();
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatLeaseHouse StatLeaseHouseDataOnDemand.getSpecificStatLeaseHouse(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatLeaseHouse obj = data.get(index);
        return StatLeaseHouse.findStatLeaseHouse(obj.getId());
    }
    
    public StatLeaseHouse StatLeaseHouseDataOnDemand.getRandomStatLeaseHouse() {
        init();
        StatLeaseHouse obj = data.get(rnd.nextInt(data.size()));
        return StatLeaseHouse.findStatLeaseHouse(obj.getId());
    }
    
    public boolean StatLeaseHouseDataOnDemand.modifyStatLeaseHouse(StatLeaseHouse obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatLeaseHouseDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouseEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatLeaseHouse' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatLeaseHouse>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatLeaseHouse obj = getNewTransientStatLeaseHouse(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
