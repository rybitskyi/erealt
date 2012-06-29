package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import java.lang.Integer;
import java.lang.SuppressWarnings;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect Land_Roo_Finder {
    
    public static Query Land.findLandsBySearchCriteria(SearchCriteria searchCriteria) {
        EntityManager em = Land.entityManager();
        StringBuffer sb = new StringBuffer("SELECT o ");
        sb.append(" FROM Land o WHERE ");
        sb.append(" district=:district ");
        if (searchCriteria.getRegion() != null) {
        	sb.append(" and  o.region = :region");
        }
        if (searchCriteria.getMinPrice() != null && searchCriteria.getMaxPrice() != null) {
        	sb.append(" AND o.price BETWEEN :minPrice AND :maxPrice ");
        }
        else if (searchCriteria.getMinPrice() != null) {
        	sb.append(" AND o.price >= :minPrice ");
        }
        else if (searchCriteria.getMaxPrice() != null) {
        	sb.append(" AND o.price <= :maxPrice ");
        }
        sb.append(" and o.creationDate<=current_timestamp() ");
        sb.append(" order by o.creationDate desc, o.street.caption ");
        Query q = em.createQuery(sb.toString());
        q.setParameter("district", searchCriteria.getDistrict());
        if (searchCriteria.getRegion() != null) {
        	q.setParameter("region", searchCriteria.getRegion());
        }
        if (searchCriteria.getMinPrice() != null) {
        	q.setParameter("minPrice", searchCriteria.getMinPrice());
        }
        if (searchCriteria.getMaxPrice() != null) {
        	q.setParameter("maxPrice", searchCriteria.getMaxPrice());
        }
        return q;        
    }
    
    public static long Land.findLandsBySearchCriteriaCount(SearchCriteria searchCriteria) {
        EntityManager em = Land.entityManager();
        StringBuffer sb = new StringBuffer("SELECT count(o) ");
        sb.append(" FROM Land o WHERE ");
        sb.append(" district=:district ");
        if (searchCriteria.getRegion() != null) {
        	sb.append(" and  o.region = :region");
        }
        if (searchCriteria.getMinPrice() != null && searchCriteria.getMaxPrice() != null) {
        	sb.append(" AND o.price BETWEEN :minPrice AND :maxPrice ");
        }
        else if (searchCriteria.getMinPrice() != null) {
        	sb.append(" AND o.price >= :minPrice ");
        }
        else if (searchCriteria.getMaxPrice() != null) {
        	sb.append(" AND o.price <= :maxPrice ");
        }
        sb.append(" and o.creationDate<=current_timestamp() ");
        Query q = em.createQuery(sb.toString());
        q.setParameter("district", searchCriteria.getDistrict());
        if (searchCriteria.getRegion() != null) {
        	q.setParameter("region", searchCriteria.getRegion());
        }
        if (searchCriteria.getMinPrice() != null) {
        	q.setParameter("minPrice", searchCriteria.getMinPrice());
        }
        if (searchCriteria.getMaxPrice() != null) {
        	q.setParameter("maxPrice", searchCriteria.getMaxPrice());
        }
        return (Long) q.getSingleResult();        
    }        
}
