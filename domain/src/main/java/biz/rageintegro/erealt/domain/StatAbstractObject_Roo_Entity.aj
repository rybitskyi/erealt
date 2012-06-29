package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatAbstractObject;
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
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatAbstractObject_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager StatAbstractObject.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StatAbstractObject.id;
    
    @Version
    @Column(name = "version")
    private Integer StatAbstractObject.version;
    
    public Long StatAbstractObject.getId() {
        return this.id;
    }
    
    public void StatAbstractObject.setId(Long id) {
        this.id = id;
    }
    
    public Integer StatAbstractObject.getVersion() {
        return this.version;
    }
    
    public void StatAbstractObject.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void StatAbstractObject.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void StatAbstractObject.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            StatAbstractObject attached = this.entityManager.find(StatAbstractObject.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void StatAbstractObject.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void StatAbstractObject.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        StatAbstractObject merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager StatAbstractObject.entityManager() {
        EntityManager em = new StatAbstractObject(){
        }.entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long StatAbstractObject.countStatAbstractObjects() {
        return (Long) entityManager().createQuery("select count(o) from StatAbstractObject o").getSingleResult();
    }
    
    public static List<StatAbstractObject> StatAbstractObject.findAllStatAbstractObjects() {
        return entityManager().createQuery("select o from StatAbstractObject o").getResultList();
    }
    
    public static StatAbstractObject StatAbstractObject.findStatAbstractObject(Long id) {
        if (id == null) return null;
        return entityManager().find(StatAbstractObject.class, id);
    }
    
    public static List<StatAbstractObject> StatAbstractObject.findStatAbstractObjectEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from StatAbstractObject o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
