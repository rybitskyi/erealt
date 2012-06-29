package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleHouse;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;

privileged aspect SaleHouse_Roo_Entity {
    
    public static long SaleHouse.countSaleHouses() {
         Query q = entityManager().createQuery("select count(o) from SaleHouse o where o.creationDate<=current_timestamp()");
         return (Long) q.getSingleResult();
    }
    
    public static Query SaleHouse.findSaleHouse() {
        return entityManager().createQuery("select o from SaleHouse o where o.creationDate<=current_timestamp() order by o.creationDate desc, o.street.caption");
    }
    
    public static List<SaleHouse> SaleHouse.findAllSaleHouses() {
        return SaleHouse.findSaleHouse().getResultList();
    }
    
    public static SaleHouse SaleHouse.findSaleHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(SaleHouse.class, id);
    }
    
    public static List<SaleHouse> SaleHouse.findSaleHouseEntries(int firstResult, int maxResults) {
        return SaleHouse.findSaleHouse().setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
