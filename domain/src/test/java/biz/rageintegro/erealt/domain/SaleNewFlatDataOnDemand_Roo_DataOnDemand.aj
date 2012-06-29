package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleNewFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleNewFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleNewFlatDataOnDemand: @Component;
    
    private Random SaleNewFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<SaleNewFlat> SaleNewFlatDataOnDemand.data;
    
    public SaleNewFlat SaleNewFlatDataOnDemand.getNewTransientSaleNewFlat(int index) {
        biz.rageintegro.erealt.domain.SaleNewFlat obj = new biz.rageintegro.erealt.domain.SaleNewFlat();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public SaleNewFlat SaleNewFlatDataOnDemand.getSpecificSaleNewFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        SaleNewFlat obj = data.get(index);
        return SaleNewFlat.findSaleNewFlat(obj.getId());
    }
    
    public SaleNewFlat SaleNewFlatDataOnDemand.getRandomSaleNewFlat() {
        init();
        SaleNewFlat obj = data.get(rnd.nextInt(data.size()));
        return SaleNewFlat.findSaleNewFlat(obj.getId());
    }
    
    public boolean SaleNewFlatDataOnDemand.modifySaleNewFlat(SaleNewFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void SaleNewFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlatEntries(0, 10, null, null, null);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'SaleNewFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.SaleNewFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.SaleNewFlat obj = getNewTransientSaleNewFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
