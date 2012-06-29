package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseFlat;
import java.lang.Long;
import java.util.List;

privileged aspect StatLeaseFlat_Roo_Entity {
    
    public static long StatLeaseFlat.countStatLeaseFlats() {
        return (Long) entityManager().createQuery("select count(o) from StatLeaseFlat o").getSingleResult();
    }
    
    public static List<StatLeaseFlat> StatLeaseFlat.findAllStatLeaseFlats() {
        return entityManager().createQuery("select o from StatLeaseFlat o").getResultList();
    }
    
    public static StatLeaseFlat StatLeaseFlat.findStatLeaseFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(StatLeaseFlat.class, id);
    }
    
    public static List<StatLeaseFlat> StatLeaseFlat.findStatLeaseFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatLeaseFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
