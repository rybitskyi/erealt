package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.District;
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

privileged aspect District_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager District.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long District.id;
    
    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer District.version;
    
    public Long District.getId() {
        return this.id;
    }
    
    public void District.setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean District.equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District district = (District) o;

        if (id != null ? !id.equals(district.id) : district.id != null) return false;

        return true;
    }

    @Override
    public int District.hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Integer District.getVersion() {
        return this.version;
    }
    
    public void District.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void District.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void District.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            District attached = this.entityManager.find(District.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void District.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void District.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        District merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager District.entityManager() {
        EntityManager em = new District().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long District.countDistricts() {
        return (Long) entityManager().createQuery("select count(o) from District o").getSingleResult();
    }
    
    public static List<District> District.findAllDistricts() {
        return entityManager().createQuery("select o from District o").getResultList();
    }
    
    public static District District.findDistrict(Long id) {
        if (id == null) return null;
        return entityManager().find(District.class, id);
    }
    
    public static District District.findDistrictByCaption(String caption) {
        if (caption == null) return null;
        Query q = entityManager().createQuery("select o from District o where caption=:caption");
		q.setParameter("caption", caption);
		List<District> l = q.getResultList();
		return l.size() > 0 ? l.get(0) : null;
    }

    public static District District.findDistrictByTranslitCaption(String value) {
        if (value == null) return null;
        Query q = entityManager().createQuery("select o from District o where caption2=:caption");
		q.setParameter("caption", value);
		List<District> l = q.getResultList();
		return l.size() > 0 ? l.get(0) : null;
    }

    public static List<District> District.findDistrictEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from District o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
