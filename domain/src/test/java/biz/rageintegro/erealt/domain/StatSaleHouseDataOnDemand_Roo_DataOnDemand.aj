package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleHouse;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleHouseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatSaleHouseDataOnDemand: @Component;
    
    private Random StatSaleHouseDataOnDemand.rnd = new SecureRandom();
    
    private List<StatSaleHouse> StatSaleHouseDataOnDemand.data;
    
    public StatSaleHouse StatSaleHouseDataOnDemand.getNewTransientStatSaleHouse(int index) {
        biz.rageintegro.erealt.domain.StatSaleHouse obj = new biz.rageintegro.erealt.domain.StatSaleHouse();
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatSaleHouse StatSaleHouseDataOnDemand.getSpecificStatSaleHouse(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatSaleHouse obj = data.get(index);
        return StatSaleHouse.findStatSaleHouse(obj.getId());
    }
    
    public StatSaleHouse StatSaleHouseDataOnDemand.getRandomStatSaleHouse() {
        init();
        StatSaleHouse obj = data.get(rnd.nextInt(data.size()));
        return StatSaleHouse.findStatSaleHouse(obj.getId());
    }
    
    public boolean StatSaleHouseDataOnDemand.modifyStatSaleHouse(StatSaleHouse obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatSaleHouseDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouseEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatSaleHouse' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatSaleHouse>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatSaleHouse obj = getNewTransientStatSaleHouse(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
