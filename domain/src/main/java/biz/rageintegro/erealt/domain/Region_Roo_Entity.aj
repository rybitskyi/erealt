package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Region;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.transaction.annotation.Transactional;

privileged aspect Region_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager Region.entityManager;

    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer Region.version;

    public Integer Region.getVersion() {
        return this.version;
    }
    
    public void Region.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Region.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Region.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Region attached = this.entityManager.find(Region.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Region.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Region.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Region merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager Region.entityManager() {
        EntityManager em = new Region().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Region.countRegions() {
        return (Long) entityManager().createQuery("select count(o) from Region o").getSingleResult();
    }
    
    public static List<Region> Region.findAllRegions() {
        return entityManager().createQuery("select o from Region o order by o.caption").getResultList();
    }

    public static List<Region> Region.findRegionsByDistrict(Long districtId) {
        return entityManager().createQuery("select r from Region r where r.district.id=:district order by r.caption").setParameter("district", districtId).getResultList();
    }

    public static Region Region.findRegion(Long id) {
        if (id == null) return null;
        return entityManager().find(Region.class, id);
    }
    	    
    public static Region Region.findRegionByTranslitCaption(String district, String region) {
        if (district == null || region == null) return null;
        Query q = entityManager().createQuery("select r from Region r where r.caption2=:region and r.district.caption2=:district");
		q.setParameter("district", district);
		q.setParameter("region", region);
		List<Region> l = q.getResultList();
		return l.size() > 0 ? l.get(0) : null;
    }

    public static Region Region.findRegionByCaption(String region, Long districtId) {
        if (districtId == null || region == null) return null;
        Query q = entityManager().createQuery("select r from Region r where r.caption=:region and r.district.id=:districtId");
		q.setParameter("districtId", districtId);
		q.setParameter("region", region);
		List<Region> l = q.getResultList();
		return l.size() > 0 ? l.get(0) : null;
    }

	public static List<Region> Region.findRegionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Region o order by o.caption").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
