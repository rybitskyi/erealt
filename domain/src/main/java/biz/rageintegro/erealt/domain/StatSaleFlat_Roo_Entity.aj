package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleFlat;
import java.lang.Long;
import java.util.List;

privileged aspect StatSaleFlat_Roo_Entity {
    
    public static long StatSaleFlat.countStatSaleFlats() {
        return (Long) entityManager().createQuery("select count(o) from StatSaleFlat o").getSingleResult();
    }
    
    public static List<StatSaleFlat> StatSaleFlat.findAllStatSaleFlats() {
        return entityManager().createQuery("select o from StatSaleFlat o").getResultList();
    }
    
    public static StatSaleFlat StatSaleFlat.findStatSaleFlat(Long id) {
        if (id == null) return null;
        return entityManager().find(StatSaleFlat.class, id);
    }
    
    public static List<StatSaleFlat> StatSaleFlat.findStatSaleFlatEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatSaleFlat o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
