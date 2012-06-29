package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleNewFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleNewFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatSaleNewFlatDataOnDemand: @Component;
    
    private Random StatSaleNewFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<StatSaleNewFlat> StatSaleNewFlatDataOnDemand.data;
    
    public StatSaleNewFlat StatSaleNewFlatDataOnDemand.getNewTransientStatSaleNewFlat(int index) {
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = new biz.rageintegro.erealt.domain.StatSaleNewFlat();
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatSaleNewFlat StatSaleNewFlatDataOnDemand.getSpecificStatSaleNewFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatSaleNewFlat obj = data.get(index);
        return StatSaleNewFlat.findStatSaleNewFlat(obj.getId());
    }
    
    public StatSaleNewFlat StatSaleNewFlatDataOnDemand.getRandomStatSaleNewFlat() {
        init();
        StatSaleNewFlat obj = data.get(rnd.nextInt(data.size()));
        return StatSaleNewFlat.findStatSaleNewFlat(obj.getId());
    }
    
    public boolean StatSaleNewFlatDataOnDemand.modifyStatSaleNewFlat(StatSaleNewFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatSaleNewFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlatEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatSaleNewFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatSaleNewFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatSaleNewFlat obj = getNewTransientStatSaleNewFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
