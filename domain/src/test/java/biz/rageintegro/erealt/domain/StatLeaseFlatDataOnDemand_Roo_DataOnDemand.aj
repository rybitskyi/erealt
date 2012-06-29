package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatLeaseFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatLeaseFlatDataOnDemand: @Component;
    
    private Random StatLeaseFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<StatLeaseFlat> StatLeaseFlatDataOnDemand.data;
    
    public StatLeaseFlat StatLeaseFlatDataOnDemand.getNewTransientStatLeaseFlat(int index) {
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = new biz.rageintegro.erealt.domain.StatLeaseFlat();
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatLeaseFlat StatLeaseFlatDataOnDemand.getSpecificStatLeaseFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatLeaseFlat obj = data.get(index);
        return StatLeaseFlat.findStatLeaseFlat(obj.getId());
    }
    
    public StatLeaseFlat StatLeaseFlatDataOnDemand.getRandomStatLeaseFlat() {
        init();
        StatLeaseFlat obj = data.get(rnd.nextInt(data.size()));
        return StatLeaseFlat.findStatLeaseFlat(obj.getId());
    }
    
    public boolean StatLeaseFlatDataOnDemand.modifyStatLeaseFlat(StatLeaseFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatLeaseFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlatEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatLeaseFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatLeaseFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatLeaseFlat obj = getNewTransientStatLeaseFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
