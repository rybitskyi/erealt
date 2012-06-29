package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleHouse;
import java.lang.Long;
import java.util.List;

privileged aspect StatSaleHouse_Roo_Entity {
    
    public static long StatSaleHouse.countStatSaleHouses() {
        return (Long) entityManager().createQuery("select count(o) from StatSaleHouse o").getSingleResult();
    }
    
    public static List<StatSaleHouse> StatSaleHouse.findAllStatSaleHouses() {
        return entityManager().createQuery("select o from StatSaleHouse o").getResultList();
    }
    
    public static StatSaleHouse StatSaleHouse.findStatSaleHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(StatSaleHouse.class, id);
    }
    
    public static List<StatSaleHouse> StatSaleHouse.findStatSaleHouseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatSaleHouse o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
