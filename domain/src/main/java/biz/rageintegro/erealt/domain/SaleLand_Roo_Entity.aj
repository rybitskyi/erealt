package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleLand;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect SaleLand_Roo_Entity {
    
    public static long SaleLand.countSaleLands() {
        Query q = entityManager().createQuery("select count(o) from SaleLand o where o.creationDate<=current_timestamp()");
        return (Long) q.getSingleResult();
    }
        
    public static Query SaleLand.findSaleLand() {
        return entityManager().createQuery("select o from SaleLand o where o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
    }

    public static List<SaleLand> SaleLand.findAllSaleLands() {
        return SaleLand.findSaleLand().getResultList();
    }
    
    public static SaleLand SaleLand.findSaleLand(Long id) {
        if (id == null) return null;
        return entityManager().find(SaleLand.class, id);
    }
    
    public static List<SaleLand> SaleLand.findSaleLandEntries(int firstResult, int maxResults) {
        return SaleLand.findSaleLand().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
