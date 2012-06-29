package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleFlat;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleFlatDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StatSaleFlatDataOnDemand: @Component;
    
    private Random StatSaleFlatDataOnDemand.rnd = new SecureRandom();
    
    private List<StatSaleFlat> StatSaleFlatDataOnDemand.data;
    
    public StatSaleFlat StatSaleFlatDataOnDemand.getNewTransientStatSaleFlat(int index) {
        biz.rageintegro.erealt.domain.StatSaleFlat obj = new biz.rageintegro.erealt.domain.StatSaleFlat();
        obj.setPrice(new Integer(index));
        obj.setRoomCount(null);
        obj.setStreetObjectNo("streetObjectNo_" + index);
        return obj;
    }
    
    public StatSaleFlat StatSaleFlatDataOnDemand.getSpecificStatSaleFlat(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        StatSaleFlat obj = data.get(index);
        return StatSaleFlat.findStatSaleFlat(obj.getId());
    }
    
    public StatSaleFlat StatSaleFlatDataOnDemand.getRandomStatSaleFlat() {
        init();
        StatSaleFlat obj = data.get(rnd.nextInt(data.size()));
        return StatSaleFlat.findStatSaleFlat(obj.getId());
    }
    
    public boolean StatSaleFlatDataOnDemand.modifyStatSaleFlat(StatSaleFlat obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StatSaleFlatDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlatEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StatSaleFlat' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.StatSaleFlat>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.StatSaleFlat obj = getNewTransientStatSaleFlat(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
