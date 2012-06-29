package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LeaseFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LeaseFlatDataOnDemand: @Component;
    
    private Random LeaseFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<LeaseFlat> LeaseFlatDataOnDemand.data;
    
    public LeaseFlat LeaseFlatDataOnDemand.getNewTransientLeaseFlat(int index) {
        biz.rageintegro.erealt.domain.LeaseFlat obj = new biz.rageintegro.erealt.domain.LeaseFlat();
        obj.setClientName("clientName_" + index);
        obj.setEmail("email_" + index);
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        obj.setTelephone("telephone_" + index);
        return obj;
    }
    
    public LeaseFlat LeaseFlatDataOnDemand.getSpecificLeaseFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        LeaseFlat obj = data.get(index);
        return LeaseFlat.findLeaseFlat(obj.getId());
    }
    
    public LeaseFlat LeaseFlatDataOnDemand.getRandomLeaseFlat() {
        init();
        LeaseFlat obj = data.get(rnd.nextInt(data.size()));
        return LeaseFlat.findLeaseFlat(obj.getId());
    }
    
    public boolean LeaseFlatDataOnDemand.modifyLeaseFlat(LeaseFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void LeaseFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlatEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'LeaseFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.LeaseFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.LeaseFlat obj = getNewTransientLeaseFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
