package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleLand;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleLandDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleLandDataOnDemand: @Component;
    
    private Random SaleLandDataOnDemand.rnd = new SecureRandom();
    
    private List<SaleLand> SaleLandDataOnDemand.data;
    
    public SaleLand SaleLandDataOnDemand.getNewTransientSaleLand(int index) {
        biz.rageintegro.erealt.domain.SaleLand obj = new biz.rageintegro.erealt.domain.SaleLand();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public SaleLand SaleLandDataOnDemand.getSpecificSaleLand(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        SaleLand obj = data.get(index);
        return SaleLand.findSaleLand(obj.getId());
    }
    
    public SaleLand SaleLandDataOnDemand.getRandomSaleLand() {
        init();
        SaleLand obj = data.get(rnd.nextInt(data.size()));
        return SaleLand.findSaleLand(obj.getId());
    }
    
    public boolean SaleLandDataOnDemand.modifySaleLand(SaleLand obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void SaleLandDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.SaleLand.findSaleLandEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'SaleLand' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.SaleLand>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.SaleLand obj = getNewTransientSaleLand(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
