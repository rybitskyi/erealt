package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseFlat;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect LeaseFlat_Roo_Entity {
    
    public static long LeaseFlat.countLeaseFlats() {
        Query q = entityManager().createQuery("select count(o) from LeaseFlat o where o.creationDate<=current_timestamp()");
        return (Long) q.getSingleResult();
    }

    public static List<LeaseFlat> LeaseFlat.findAllLeaseFlats() {
        return LeaseFlat.findLeaseFlat().getResultList();
    }
    
    public static LeaseFlat LeaseFlat.findLeaseFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(LeaseFlat.class, id);
    }
    
    public static Query LeaseFlat.findLeaseFlat() {
        Query q = entityManager().createQuery("SELECT o FROM LeaseFlat o WHERE o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
        return q;
    }
    
    public static List<LeaseFlat> LeaseFlat.findLeaseFlatEntries(int firstResult, int maxResults) {
    	return LeaseFlat.findLeaseFlat().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();    
    }    
}
