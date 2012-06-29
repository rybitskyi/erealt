package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleNewFlat;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect SaleNewFlat_Roo_Entity {
    
    public static long SaleNewFlat.countSaleNewFlats() {
        Query q = entityManager().createQuery("select count(o) from SaleNewFlat o whereand o.creationDate<=current_timestamp()");
        return (Long) q.getSingleResult();
    }
    
    public static Query SaleNewFlat.findSaleNewFlat() {
      	return entityManager().createQuery("SELECT o FROM SaleNewFlat o WHERE o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
    }

    public static List<SaleNewFlat> SaleNewFlat.findAllSaleNewFlats() {
        return SaleNewFlat.findSaleNewFlat().getResultList();
    }
    
    public static SaleNewFlat SaleNewFlat.findSaleNewFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(SaleNewFlat.class, id);
    }
    
    public static List<SaleNewFlat> SaleNewFlat.findSaleNewFlatEntries(int firstResult, int maxResults, District district, Region region, RoomCountType roomCount) {
        return SaleNewFlat.findSaleNewFlat().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }    
}
