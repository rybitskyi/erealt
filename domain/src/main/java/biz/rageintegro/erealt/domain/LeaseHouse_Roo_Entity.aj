package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseHouse;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect LeaseHouse_Roo_Entity {
    
    public static long LeaseHouse.countLeaseHouses() {
        Query q = entityManager().createQuery("select count(o) from LeaseHouse o where o.creationDate<=current_timestamp()");
        return (Long) q.getSingleResult();
    }
    
    public static Query LeaseHouse.findLeaseHouse() {
        return entityManager().createQuery("select o from LeaseHouse o where o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
    }

    public static List<LeaseHouse> LeaseHouse.findAllLeaseHouses() {
        return LeaseHouse.findLeaseHouse().getResultList();
    }
    
    public static LeaseHouse LeaseHouse.findLeaseHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(LeaseHouse.class, id);
    }
    
    public static List<LeaseHouse> LeaseHouse.findLeaseHouseEntries(int firstResult, int maxResults) {
    	return LeaseHouse.findLeaseHouse().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }    
}
