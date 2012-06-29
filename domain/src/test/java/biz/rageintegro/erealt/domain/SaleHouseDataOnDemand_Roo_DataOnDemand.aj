package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleHouse;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleHouseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleHouseDataOnDemand: @Component;
    
    private Random SaleHouseDataOnDemand.rnd = new SecureRandom();
    
    private List<SaleHouse> SaleHouseDataOnDemand.data;
    
    public SaleHouse SaleHouseDataOnDemand.getNewTransientSaleHouse(int index) {
        biz.rageintegro.erealt.domain.SaleHouse obj = new biz.rageintegro.erealt.domain.SaleHouse();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public SaleHouse SaleHouseDataOnDemand.getSpecificSaleHouse(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        SaleHouse obj = data.get(index);
        return SaleHouse.findSaleHouse(obj.getId());
    }
    
    public SaleHouse SaleHouseDataOnDemand.getRandomSaleHouse() {
        init();
        SaleHouse obj = data.get(rnd.nextInt(data.size()));
        return SaleHouse.findSaleHouse(obj.getId());
    }
    
    public boolean SaleHouseDataOnDemand.modifySaleHouse(SaleHouse obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void SaleHouseDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouseEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'SaleHouse' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.SaleHouse>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.SaleHouse obj = getNewTransientSaleHouse(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
