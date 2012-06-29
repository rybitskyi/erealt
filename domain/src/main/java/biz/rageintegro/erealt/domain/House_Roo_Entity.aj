package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.House;
import java.lang.Long;
import java.util.List;

privileged aspect House_Roo_Entity {
    
    public static long House.countHouses() {
        return (Long) entityManager().createQuery("select count(o) from House o").getSingleResult();
    }
    
    public static List<House> House.findAllHouses() {
        return entityManager().createQuery("select o from House o").getResultList();
    }
    
    public static House House.findHouse(Long id) {
        if (id == null) return null;
        return entityManager().find(House.class, id);
    }
    
    public static List<House> House.findHouseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from House o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
