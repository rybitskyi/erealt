package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Land;
import java.lang.Long;
import java.util.List;

privileged aspect Land_Roo_Entity {
    
    public static long Land.countLands() {
        return (Long) entityManager().createQuery("select count(o) from Land o").getSingleResult();
    }
    
    public static List<Land> Land.findAllLands() {
        return entityManager().createQuery("select o from Land o").getResultList();
    }
    
    public static Land Land.findLand(Long id) {
        if (id == null) return null;
        return entityManager().find(Land.class, id);
    }
    
    public static List<Land> Land.findLandEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Land o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
