package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Region;
import java.lang.SuppressWarnings;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect SaleLand_Roo_Finder {
    
	public static Query SaleLand.findSaleLandsBySearchCriteria(SearchCriteria searchCriteria) {
        EntityManager em = SaleLand.entityManager();
        StringBuffer sb = new StringBuffer("SELECT o ");
        sb.append(" FROM SaleLand o WHERE ");
        if (searchCriteria.getDistrict() != null) {
        	sb.append(" district=:district ");
        }
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
        if (!searchCriteria.isEmpty()) {
            sb.append(" and ");
        }
        sb.append(" o.creationDate<=current_timestamp() ");
        sb.append(" order by o.creationDate desc, o.street.caption ");
        Query q = em.createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
        	q.setParameter("district", searchCriteria.getDistrict());
        }
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
    
    public static long SaleLand.findSaleLandsBySearchCriteriaCount(SearchCriteria searchCriteria) {
        EntityManager em = SaleLand.entityManager();
        StringBuffer sb = new StringBuffer("SELECT count(o) ");
        sb.append(" FROM SaleLand o WHERE ");
        if (searchCriteria.getDistrict() != null) {
        	sb.append(" district=:district ");
        }
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
        if (!searchCriteria.isEmpty()) {
            sb.append(" and ");
        }
        sb.append(" o.creationDate<=current_timestamp() ");
        Query q = em.createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
        	q.setParameter("district", searchCriteria.getDistrict());
        }
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
