package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseHouse;
import java.lang.Long;
import java.util.List;

privileged aspect StatLeaseHouse_Roo_Entity {
    
    public static long StatLeaseHouse.countStatLeaseHouses() {
        return (Long) entityManager().createQuery("select count(o) from StatLeaseHouse o").getSingleResult();
    }
    
    public static List<StatLeaseHouse> StatLeaseHouse.findAllStatLeaseHouses() {
        return entityManager().createQuery("select o from StatLeaseHouse o").getResultList();
    }
    
    public static StatLeaseHouse StatLeaseHouse.findStatLeaseHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(StatLeaseHouse.class, id);
    }
    
    public static List<StatLeaseHouse> StatLeaseHouse.findStatLeaseHouseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatLeaseHouse o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
