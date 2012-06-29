package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Flat;
import java.lang.Long;
import java.util.List;

privileged aspect Flat_Roo_Entity {
    
    public static long Flat.countFlats() {
        return (Long) entityManager().createQuery("select count(o) from Flat o").getSingleResult();
    }
    
    public static List<Flat> Flat.findAllFlats() {
        return entityManager().createQuery("select o from Flat o").getResultList();
    }
    
    public static Flat Flat.findFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(Flat.class, id);
    }
    
    public static List<Flat> Flat.findFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Flat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
