package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleNewFlat;
import java.lang.Long;
import java.util.List;

privileged aspect StatSaleNewFlat_Roo_Entity {
    
    public static long StatSaleNewFlat.countStatSaleNewFlats() {
        return (Long) entityManager().createQuery("select count(o) from StatSaleNewFlat o").getSingleResult();
    }
    
    public static List<StatSaleNewFlat> StatSaleNewFlat.findAllStatSaleNewFlats() {
        return entityManager().createQuery("select o from StatSaleNewFlat o").getResultList();
    }
    
    public static StatSaleNewFlat StatSaleNewFlat.findStatSaleNewFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(StatSaleNewFlat.class, id);
    }
    
    public static List<StatSaleNewFlat> StatSaleNewFlat.findStatSaleNewFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatSaleNewFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
