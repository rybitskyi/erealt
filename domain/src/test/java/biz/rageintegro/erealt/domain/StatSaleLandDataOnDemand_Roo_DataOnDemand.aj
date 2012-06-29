package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleLand;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleLandDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatSaleLandDataOnDemand: @Component;
    
    private Random StatSaleLandDataOnDemand.rnd = new SecureRandom();
    
    private List<StatSaleLand> StatSaleLandDataOnDemand.data;
    
    public StatSaleLand StatSaleLandDataOnDemand.getNewTransientStatSaleLand(int index) {
        biz.rageintegro.erealt.domain.StatSaleLand obj = new biz.rageintegro.erealt.domain.StatSaleLand();
        obj.setPrice(new Integer(index));
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatSaleLand StatSaleLandDataOnDemand.getSpecificStatSaleLand(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatSaleLand obj = data.get(index);
        return StatSaleLand.findStatSaleLand(obj.getId());
    }
    
    public StatSaleLand StatSaleLandDataOnDemand.getRandomStatSaleLand() {
        init();
        StatSaleLand obj = data.get(rnd.nextInt(data.size()));
        return StatSaleLand.findStatSaleLand(obj.getId());
    }
    
    public boolean StatSaleLandDataOnDemand.modifyStatSaleLand(StatSaleLand obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatSaleLandDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLandEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatSaleLand' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatSaleLand>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatSaleLand obj = getNewTransientStatSaleLand(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
