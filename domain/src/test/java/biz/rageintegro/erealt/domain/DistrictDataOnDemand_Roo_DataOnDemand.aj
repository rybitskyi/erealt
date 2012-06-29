package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DistrictDataOnDemand_Roo_DataOnDemand {
    
    declare @type: DistrictDataOnDemand: @Component;
    
    private Random DistrictDataOnDemand.rnd = new SecureRandom();
    
    private List<District> DistrictDataOnDemand.data;
    
    public District DistrictDataOnDemand.getNewTransientDistrict(int index) {
        biz.rageintegro.erealt.domain.District obj = new biz.rageintegro.erealt.domain.District();
        obj.setCaption("caption_" + index);
        return obj;
    }
    
    public District DistrictDataOnDemand.getSpecificDistrict(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        District obj = data.get(index);
        return District.findDistrict(obj.getId());
    }
    
    public District DistrictDataOnDemand.getRandomDistrict() {
        init();
        District obj = data.get(rnd.nextInt(data.size()));
        return District.findDistrict(obj.getId());
    }
    
    public boolean DistrictDataOnDemand.modifyDistrict(District obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void DistrictDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.District.findDistrictEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'District' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.District>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.District obj = getNewTransientDistrict(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
