package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Street;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StreetDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StreetDataOnDemand: @Component;
    
    private Random StreetDataOnDemand.rnd = new SecureRandom();
    
    private List<Street> StreetDataOnDemand.data;
    
    public Street StreetDataOnDemand.getNewTransientStreet(int index) {
        biz.rageintegro.erealt.domain.Street obj = new biz.rageintegro.erealt.domain.Street();
        obj.setCaption("caption_" + index);
        obj.setDeprecated(new Boolean(true));
        return obj;
    }
    
    public Street StreetDataOnDemand.getSpecificStreet(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Street obj = data.get(index);
        return Street.findStreet(obj.getId());
    }
    
    public Street StreetDataOnDemand.getRandomStreet() {
        init();
        Street obj = data.get(rnd.nextInt(data.size()));
        return Street.findStreet(obj.getId());
    }
    
    public boolean StreetDataOnDemand.modifyStreet(Street obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void StreetDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.Street.findStreetEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Street' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.Street>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.Street obj = getNewTransientStreet(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
