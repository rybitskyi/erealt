package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Region;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RegionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: RegionDataOnDemand: @Component;
    
    private Random RegionDataOnDemand.rnd = new SecureRandom();
    
    private List<Region> RegionDataOnDemand.data;
    
    public Region RegionDataOnDemand.getNewTransientRegion(int index) {
        biz.rageintegro.erealt.domain.Region obj = new biz.rageintegro.erealt.domain.Region();
        obj.setCaption("caption_" + index);
        return obj;
    }
    
    public Region RegionDataOnDemand.getSpecificRegion(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Region obj = data.get(index);
        return Region.findRegion(obj.getId());
    }
    
    public Region RegionDataOnDemand.getRandomRegion() {
        init();
        Region obj = data.get(rnd.nextInt(data.size()));
        return Region.findRegion(obj.getId());
    }
    
    public boolean RegionDataOnDemand.modifyRegion(Region obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void RegionDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.Region.findRegionEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Region' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.Region>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.Region obj = getNewTransientRegion(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
