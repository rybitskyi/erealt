package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleFlat;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect SaleFlat_Roo_Entity {
    
    public static long SaleFlat.countSaleFlats() {
        Query q = entityManager().createQuery("select count(o) from SaleFlat o where o.creationDate<=current_timestamp()");
        return (Long) q.getSingleResult();
    }

    public static List<SaleFlat> SaleFlat.findAllSaleFlats() {
        return SaleFlat.findSaleFlat().getResultList();
    }

    public static SaleFlat SaleFlat.findSaleFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(SaleFlat.class, id);
    }

    public static Query SaleFlat.findSaleFlat() {
      	return entityManager().createQuery("SELECT o FROM SaleFlat o WHERE o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
    }
    
    public static List<SaleFlat> SaleFlat.findSaleFlatEntries(int firstResult, int maxResults) {
    	return SaleFlat.findSaleFlat().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
