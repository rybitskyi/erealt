package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Phone;

import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.transaction.annotation.Transactional;

privileged aspect Phone_Roo_Entity {

    @PersistenceContext
    transient EntityManager Phone.entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long Phone.id;

    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer Phone.version;

    public Long Phone.getId() {
        return this.id;
    }

    public void Phone.setId(Long id) {
        this.id = id;
    }

    public Integer Phone.getVersion() {
        return this.version;
    }

    public void Phone.setVersion(Integer version) {
        this.version = version;
    }

    @Transactional
    public void Phone.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void Phone.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Phone attached = this.entityManager.find(Phone.class, this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void Phone.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void Phone.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Phone merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }

    public static final EntityManager Phone.entityManager() {
        EntityManager em = new Phone().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long Phone.countPhones() {
        return (Long) entityManager().createQuery("select count(o) from Phone o").getSingleResult();
    }

    public static List<Phone> Phone.findAllPhones() {
        return entityManager().createQuery("select o from Phone o").getResultList();
    }

    public static Phone Phone.findPhone(Long id) {
        if (id == null) return null;
        return entityManager().find(Phone.class, id);
    }

    public static Phone Phone.findPhone(String code, int phoneNumber) {
        if (code == null) return null;
        Query q = entityManager().createQuery("select p from Phone p where p.code = :code and p.phoneNumber = :phoneNumber");
        q.setParameter("code", code);
        q.setParameter("phoneNumber", phoneNumber);
        List<Phone> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }

    public static List<Phone> Phone.findPhoneEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Phone o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

}
