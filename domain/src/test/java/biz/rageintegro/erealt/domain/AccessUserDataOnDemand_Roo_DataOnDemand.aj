package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AccessUser;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AccessUserDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AccessUserDataOnDemand: @Component;
    
    private Random AccessUserDataOnDemand.rnd = new SecureRandom();
    
    private List<AccessUser> AccessUserDataOnDemand.data;
    
    public AccessUser AccessUserDataOnDemand.getNewTransientAccessUser(int index) {
        biz.rageintegro.erealt.domain.AccessUser obj = new biz.rageintegro.erealt.domain.AccessUser();
        obj.setName("name_" + index);
        obj.setPassword("password_" + index);
        return obj;
    }
    
    public AccessUser AccessUserDataOnDemand.getSpecificAccessUser(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        AccessUser obj = data.get(index);
        return AccessUser.findAccessUser(obj.getId());
    }
    
    public AccessUser AccessUserDataOnDemand.getRandomAccessUser() {
        init();
        AccessUser obj = data.get(rnd.nextInt(data.size()));
        return AccessUser.findAccessUser(obj.getId());
    }
    
    public boolean AccessUserDataOnDemand.modifyAccessUser(AccessUser obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void AccessUserDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = biz.rageintegro.erealt.domain.AccessUser.findAccessUserEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'AccessUser' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<biz.rageintegro.erealt.domain.AccessUser>();
        for (int i = 0; i < 10; i++) {
            biz.rageintegro.erealt.domain.AccessUser obj = getNewTransientAccessUser(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
