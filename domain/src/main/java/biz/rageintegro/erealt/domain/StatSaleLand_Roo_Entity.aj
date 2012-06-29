package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleLand;
import java.lang.Long;
import java.util.List;

privileged aspect StatSaleLand_Roo_Entity {
    
    public static long StatSaleLand.countStatSaleLands() {
        return (Long) entityManager().createQuery("select count(o) from StatSaleLand o").getSingleResult();
    }
    
    public static List<StatSaleLand> StatSaleLand.findAllStatSaleLands() {
        return entityManager().createQuery("select o from StatSaleLand o").getResultList();
    }
    
    public static StatSaleLand StatSaleLand.findStatSaleLand(Long id) {
        if (id == null) return null;
        return entityManager().find(StatSaleLand.class, id);
    }
    
    public static List<StatSaleLand> StatSaleLand.findStatSaleLandEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatSaleLand o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
