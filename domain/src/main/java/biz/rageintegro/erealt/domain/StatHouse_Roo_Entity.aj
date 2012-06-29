package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatHouse;
import java.lang.Long;
import java.util.List;

privileged aspect StatHouse_Roo_Entity {
    
    public static long StatHouse.countStatHouses() {
        return (Long) entityManager().createQuery("select count(o) from StatHouse o").getSingleResult();
    }
    
    public static List<StatHouse> StatHouse.findAllStatHouses() {
        return entityManager().createQuery("select o from StatHouse o").getResultList();
    }
    
    public static StatHouse StatHouse.findStatHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(StatHouse.class, id);
    }
    
    public static List<StatHouse> StatHouse.findStatHouseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatHouse o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
