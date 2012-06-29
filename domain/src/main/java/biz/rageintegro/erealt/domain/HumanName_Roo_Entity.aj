package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.HumanName;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Query;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.transaction.annotation.Transactional;

privileged aspect HumanName_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager HumanName.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long HumanName.id;
    
    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer HumanName.version;
    
    public Long HumanName.getId() {
        return this.id;
    }
    
    public void HumanName.setId(Long id) {
        this.id = id;
    }
    
    public Integer HumanName.getVersion() {
        return this.version;
    }
    
    public void HumanName.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void HumanName.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void HumanName.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            HumanName attached = this.entityManager.find(HumanName.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void HumanName.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void HumanName.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        HumanName merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager HumanName.entityManager() {
        EntityManager em = new HumanName().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long HumanName.countHumanNames() {
        return (Long) entityManager().createQuery("select count(o) from HumanName o").getSingleResult();
    }
    
    public static List<HumanName> HumanName.findAllHumanNames() {
        return entityManager().createQuery("select o from HumanName o").getResultList();
    }
    
    public static HumanName HumanName.findHumanName(Long id) {
        if (id == null) return null;
        return entityManager().find(HumanName.class, id);
    }

    public static HumanName HumanName.findHumanName(String caption) {
        if (caption == null) return null;
        Query q = entityManager().createQuery("select o from HumanName o where lower(caption)=:caption");
        q.setParameter("caption", caption.trim().toLowerCase());
        List<HumanName> l = q.getResultList();
        if (l.size() == 0) {
            return null;
        }
        else {
            return l.get(0); 
        }
    }

    public static List<HumanName> HumanName.findHumanNameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from HumanName o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
