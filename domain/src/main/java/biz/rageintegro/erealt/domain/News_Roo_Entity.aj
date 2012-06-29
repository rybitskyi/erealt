package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.News;
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

privileged aspect News_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager News.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long News.id;
    
    @Version
    @Column(name = "version")
    private Integer News.version;
    
    public Long News.getId() {
        return this.id;
    }
    
    public void News.setId(Long id) {
        this.id = id;
    }
    
    public Integer News.getVersion() {
        return this.version;
    }
    
    public void News.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void News.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void News.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            News attached = this.entityManager.find(News.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void News.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void News.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        News merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager News.entityManager() {
        EntityManager em = new News().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long News.countNews() {
        return (Long) entityManager().createQuery("select count(o) from News o").getSingleResult();
    }
    
    public static List<News> News.findAllNews() {
        return entityManager().createQuery("select o from News o order by creationDate desc").getResultList();
    }
    
    public static News News.findNews(Long id) {
        if (id == null) return null;
        return entityManager().find(News.class, id);
    }
    
    public static List<News> News.findNewsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from News o order by creationDate desc").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
