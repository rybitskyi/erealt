package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLand;
import java.lang.Long;
import java.util.List;

privileged aspect StatLand_Roo_Entity {
    
    public static long StatLand.countStatLands() {
        return (Long) entityManager().createQuery("select count(o) from StatLand o").getSingleResult();
    }
    
    public static List<StatLand> StatLand.findAllStatLands() {
        return entityManager().createQuery("select o from StatLand o").getResultList();
    }
    
    public static StatLand StatLand.findStatLand(Long id) {
        if (id == null) return null;
        return entityManager().find(StatLand.class, id);
    }
    
    public static List<StatLand> StatLand.findStatLandEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatLand o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
