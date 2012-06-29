package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatFlat;
import java.lang.Long;
import java.util.List;

privileged aspect StatFlat_Roo_Entity {
    
    public static long StatFlat.countStatFlats() {
        return (Long) entityManager().createQuery("select count(o) from StatFlat o").getSingleResult();
    }
    
    public static List<StatFlat> StatFlat.findAllStatFlats() {
        return entityManager().createQuery("select o from StatFlat o").getResultList();
    }
    
    public static StatFlat StatFlat.findStatFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(StatFlat.class, id);
    }
    
    public static List<StatFlat> StatFlat.findStatFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
