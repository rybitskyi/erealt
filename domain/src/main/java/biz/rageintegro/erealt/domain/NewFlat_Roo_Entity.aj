package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.NewFlat;
import java.lang.Long;
import java.util.List;

privileged aspect NewFlat_Roo_Entity {
    
    public static long NewFlat.countNewFlats() {
        return (Long) entityManager().createQuery("select count(o) from NewFlat o").getSingleResult();
    }
    
    public static List<NewFlat> NewFlat.findAllNewFlats() {
        return entityManager().createQuery("select o from NewFlat o").getResultList();
    }
    
    public static NewFlat NewFlat.findNewFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(NewFlat.class, id);
    }
    
    public static List<NewFlat> NewFlat.findNewFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from NewFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
