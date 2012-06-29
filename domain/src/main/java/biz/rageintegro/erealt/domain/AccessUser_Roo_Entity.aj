package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AccessUser;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import java.util.Set;
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

privileged aspect AccessUser_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager AccessUser.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long AccessUser.id;
    
    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer AccessUser.version;
    
    public Long AccessUser.getId() {
        return this.id;
    }
    
    public void AccessUser.setId(Long id) {
        this.id = id;
    }
    
    public Integer AccessUser.getVersion() {
        return this.version;
    }
    
    public void AccessUser.setVersion(Integer version) {
        this.version = version;
    }
    
    private void AccessUser.checkCaption() {
        if (this.caption == null || this.caption.length() == 0) {
            if (this.getEmail() != null && this.getEmail().indexOf('@') > -1) {
                this.caption = this.getEmail().substring(0, this.getEmail().indexOf('@'));
            }
            else {
                this.caption = this.getEmail();
            }
        }
    }

    @Transactional
    public void AccessUser.persist() {
        checkCaption();
        if (this.entityManager == null) this.entityManager = entityManager();        
        processPhone();
        this.entityManager.persist(this);
    }

    private void AccessUser.processPhone() {
        for (int i = 0; i < getPhone().size(); i++) {
            Phone p = getPhone().get(i);
            Phone realPhone = Phone.findPhone(p.getCode(), p.getPhoneNumber());
            if (realPhone != null) {
                getPhone().set(i, realPhone); // reuse existed telephone, do not create new one
            }
        }
    }

    @Transactional
    public void AccessUser.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AccessUser attached = this.entityManager.find(AccessUser.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void AccessUser.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void AccessUser.merge() {
        checkCaption();
        if (this.entityManager == null) this.entityManager = entityManager();
        processPhone();
        AccessUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager AccessUser.entityManager() {
        EntityManager em = new AccessUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long AccessUser.countAccessUsers() {
        return (Long) entityManager().createQuery("select count(o) from AccessUser o").getSingleResult();
    }
    
    public static List<AccessUser> AccessUser.findAllAccessUsers() {
        return entityManager().createQuery("select o from AccessUser o").getResultList();
    }
    
    public static AccessUser AccessUser.findAccessUser(Long id) {
        if (id == null) return null;
        return entityManager().find(AccessUser.class, id);
    }

    public static AccessUser AccessUser.findAccessUserByEmail(String email) {
    	return AccessUser.findAccessUserByName(email);
    }

    public static AccessUser AccessUser.findAccessUserByPhone(Phone phone) {
    	if (phone == null || phone.getCode() == null || phone.getPhoneNumber() == null)  {
            return null;
        }
        Query q = entityManager().createQuery("select au from AccessUser au join au.phone p where p.code = :code and p.phoneNumber = :phoneNumber");
        q.setParameter("code", phone.getCode());
        q.setParameter("phoneNumber", phone.getPhoneNumber());
        List<AccessUser> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }

    public static AccessUser AccessUser.findAccessUserByName(String name) {
        if (name == null) return null;
        Query q = entityManager().createQuery("select o from AccessUser o where o.name=:name");
        q.setParameter("name", name);
        List<AccessUser> l = q.getResultList();
        if (l.size() == 0) {
            return null;
        }
        else if (l.size() == 1) {
            return l.get(0);
        }
        else {
            //todo: log about duplicated names
        	return l.get(0);
        }
    }

    public static boolean AccessUser.existRegisteredUser(String name) {
        if (name == null) return false;
        Query q = entityManager().createQuery("select count(o) from AccessUser o where o.name=:name and password is not null");
        q.setParameter("name", name);
        Long count = (Long) q.getSingleResult();
        if (count > 1) {
        	//todo: log about duplicated names
        }
        return count >= 1;
    }

    public static List<AccessUser> AccessUser.findAccessUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from AccessUser o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public List<AbstractObject> AccessUser.findAllUserObjects() {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.accessUser.name=:name");
        q.setParameter("name", this.getName());
        return q.getResultList();
    }

}
