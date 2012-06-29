package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Street;
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
import javax.persistence.Query;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.transaction.annotation.Transactional;

privileged aspect Street_Roo_Entity {

    @PersistenceContext
    transient EntityManager Street.entityManager;

    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer Street.version;

    public Integer Street.getVersion() {
        return this.version;
    }

    public void Street.setVersion(Integer version) {
        this.version = version;
    }

    @Transactional
    public void Street.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void Street.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Street attached = this.entityManager.find(Street.class, this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void Street.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void Street.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Street merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }

    public static final EntityManager Street.entityManager() {
        EntityManager em = new Street().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long Street.countStreets() {
        return (Long) entityManager().createQuery("select count(o) from Street o").getSingleResult();
    }

    public static List<Street> Street.findAllStreets() {
        return entityManager().createQuery("select o from Street o").getResultList();
    }

    public static List<Street> Street.findStreetsByRegion(Long regionId) {
        return findStreetsByRegion(regionId, true);
    }

    public static List<Street> Street.findStreetsByRegion(Long regionId, boolean sort) {
        StringBuilder sb = new StringBuilder();
        sb.append("select o from Street o where :region in elements (o.regions) ");
        if (sort) {
            sb.append(" order by o.caption");
        }
        return entityManager().createQuery(sb.toString()).setParameter("region", regionId).getResultList();
    }

    public static Street Street.findStreetByCaption(String streetCaption, Long regionId) {
        if (regionId == null || streetCaption == null) return null;
        Query q = entityManager().createQuery("select s from Street s where s.caption=:caption and :region in elements (s.regions)");
        q.setParameter("region", regionId);
        q.setParameter("caption", streetCaption);
        List<Street> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }

    public static Street Street.findStreet(Long id) {
        if (id == null) return null;
        return entityManager().find(Street.class, id);
    }

    public static List<Street> Street.findStreetEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Street o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

}
