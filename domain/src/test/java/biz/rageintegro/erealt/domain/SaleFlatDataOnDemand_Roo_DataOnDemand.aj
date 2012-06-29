package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleFlatDataOnDemand: @Component;
    
    private Random SaleFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<SaleFlat> SaleFlatDataOnDemand.data;
    
    public SaleFlat SaleFlatDataOnDemand.getNewTransientSaleFlat(int index) {
        biz.rageintegro.erealt.domain.SaleFlat obj = new biz.rageintegro.erealt.domain.SaleFlat();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPhotoExt("photoExt_" + index);
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public SaleFlat SaleFlatDataOnDemand.getSpecificSaleFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        SaleFlat obj = data.get(index);
        return SaleFlat.findSaleFlat(obj.getId());
    }
    
    public SaleFlat SaleFlatDataOnDemand.getRandomSaleFlat() {
        init();
        SaleFlat obj = data.get(rnd.nextInt(data.size()));
        return SaleFlat.findSaleFlat(obj.getId());
    }
    
    public boolean SaleFlatDataOnDemand.modifySaleFlat(SaleFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void SaleFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlatEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'SaleFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.SaleFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.SaleFlat obj = getNewTransientSaleFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
