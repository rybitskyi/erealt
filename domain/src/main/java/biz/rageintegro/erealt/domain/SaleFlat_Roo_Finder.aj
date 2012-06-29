package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.SearchCriteria;
import biz.rageintegro.erealt.reference.RoomCountType;

import java.lang.Integer;
import java.lang.SuppressWarnings;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect SaleFlat_Roo_Finder {
    
    public static Query SaleFlat.findSaleFlatsBySearchCriteria(SearchCriteria searchCriteria) {
        EntityManager em = SaleFlat.entityManager();
        StringBuffer sb = new StringBuffer("SELECT o ");
        sb.append(" FROM SaleFlat o WHERE ");
        if (searchCriteria.getDistrict() != null) {
        	sb.append(" district=:district ");
        }
        if (searchCriteria.getRegion() != null) {
        	sb.append(" and  o.region = :region");
        }
        if (searchCriteria.getRoomCount() != null) {
        	sb.append(" and  o.roomCount = :roomCount");
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
        logger.debug("findSaleFlatsBySearchCriteria sql = " + sb.toString());
        Query q = em.createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
        	q.setParameter("district", searchCriteria.getDistrict());
        }
        if (searchCriteria.getRegion() != null) {
        	q.setParameter("region", searchCriteria.getRegion());
        }
        if (searchCriteria.getRoomCount() != null) {
        	q.setParameter("roomCount", searchCriteria.getRoomCount());
        }
        if (searchCriteria.getMinPrice() != null) {
        	q.setParameter("minPrice", searchCriteria.getMinPrice());
        }
        if (searchCriteria.getMaxPrice() != null) {
        	q.setParameter("maxPrice", searchCriteria.getMaxPrice());
        }
        return q;        
    }
    
    public static long SaleFlat.findSaleFlatsBySearchCriteriaCount(SearchCriteria searchCriteria) {
        EntityManager em = SaleFlat.entityManager();
        StringBuffer sb = new StringBuffer("SELECT count(o) ");
        sb.append(" FROM SaleFlat o WHERE ");
        if (searchCriteria.getDistrict() != null) {
        	sb.append(" district=:district ");
        }
        if (searchCriteria.getRegion() != null) {
        	sb.append(" and  o.region = :region");
        }
        if (searchCriteria.getRoomCount() != null) {
        	sb.append(" and  o.roomCount = :roomCount");
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
        sb.append("o.creationDate<=current_timestamp() ");
logger.debug("findSaleFlatsBySearchCriteriaCount sql = " + sb.toString());
        Query q = em.createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
        	q.setParameter("district", searchCriteria.getDistrict());
        }
        if (searchCriteria.getRegion() != null) {
        	q.setParameter("region", searchCriteria.getRegion());
        }
        if (searchCriteria.getRoomCount() != null) {
        	q.setParameter("roomCount", searchCriteria.getRoomCount());
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
