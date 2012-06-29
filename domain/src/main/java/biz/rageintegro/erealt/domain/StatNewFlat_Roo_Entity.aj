package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatNewFlat;
import java.lang.Long;
import java.util.List;

privileged aspect StatNewFlat_Roo_Entity {
    
    public static long StatNewFlat.countStatNewFlats() {
        return (Long) entityManager().createQuery("select count(o) from StatNewFlat o").getSingleResult();
    }
    
    public static List<StatNewFlat> StatNewFlat.findAllStatNewFlats() {
        return entityManager().createQuery("select o from StatNewFlat o").getResultList();
    }
    
    public static StatNewFlat StatNewFlat.findStatNewFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(StatNewFlat.class, id);
    }
    
    public static List<StatNewFlat> StatNewFlat.findStatNewFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatNewFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
