package biz.rageintegro.erealt.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.io.FileUtils;

privileged aspect AbstractObject_Roo_Entity {

    @PersistenceContext
    transient EntityManager AbstractObject.entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement(name = "id")
    private Long AbstractObject.id;

    @Version
    @Column(name = "version")
    @XmlElement(name = "version")
    private Integer AbstractObject.version;

    public Long AbstractObject.getId() {
        return this.id;
    }

    public void AbstractObject.setId(Long id) {
        this.id = id;
    }

    public Integer AbstractObject.getVersion() {
        return this.version;
    }

    public void AbstractObject.setVersion(Integer version) {
        this.version = version;
    }

    @Transactional
    public void AbstractObject.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.creationDate == null) this.creationDate = new Date();
        processAccessUser();
        this.entityManager.persist(this);
        try {
            processImage();
        } catch (IOException e) {
            throw new RuntimeException("Can't process image from path " + getImg() + " for id =" + getId(), e);
        }
    }

    private void AbstractObject.processImage() throws IOException {
        if (getId() <= 0) {
            throw new IllegalStateException("Field id must be initiated ");
        }
        if (getPhoto() != null) {
            FileUtils.moveFile(new File(getPhoto()), photoManager.getOriginalPhotoFile(this));
            photoManager.createPhotoFile(this);
            photoManager.createThumbnail(this);
        } else if (getFileDataWrapper().getFileData() != null && !getFileDataWrapper().getFileData().isEmpty()) {
            photoManager.setPhotoExt(this);
            getFileDataWrapper().getFileData().transferTo(photoManager.getOriginalPhotoFile(this));
            photoManager.createPhotoFile(this);
            photoManager.createThumbnail(this);
        } else if (getFileDataWrapper().getContents() != null && getFileDataWrapper().getFileExtension() != null) {
            setPhotoExt(getFileDataWrapper().getFileExtension());
            FileOutputStream fos = new FileOutputStream(photoManager.getOriginalPhotoFile(this));
            fos.write(getFileDataWrapper().getContents());
            photoManager.createPhotoFile(this);
            photoManager.createThumbnail(this);
        }
    }

    private void AbstractObject.processAccessUser() {
        if (getAccessUser() != null) {
            if (getAccessUser().getId() == null) {
                for (Phone phone : getAccessUser().getPhone()) {
                    AccessUser accessUser = AccessUser.findAccessUserByPhone(phone);
                    if (accessUser != null) {
                        accessUser.setPhone(this.getAccessUser().getPhone());
                        setAccessUser(accessUser);
                        break;
                    }
                }
            }
            processPhone();
        }
    }

    private void AbstractObject.processPhone() {
        for (int i = 0; i < getAccessUser().getPhone().size(); i++) {
            Phone p = getAccessUser().getPhone().get(i);
            if (p.getId() == null) {
                Phone realPhone = Phone.findPhone(p.getCode(), p.getPhoneNumber());
                if (realPhone != null) {
                    getAccessUser().getPhone().set(i, realPhone); // reuse existed telephone, do not create new one
                }
            }
        }
    }

    @Transactional
    public void AbstractObject.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AbstractObject attached = this.entityManager.find(AbstractObject.class, this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void AbstractObject.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void AbstractObject.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        processAccessUser();
        AbstractObject merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }

    public static final EntityManager AbstractObject.entityManager() {
        EntityManager em = new AbstractObject() {
        }.entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long AbstractObject.countAbstractObjects() {
        return (Long) entityManager().createQuery("select count(o) from AbstractObject o").getSingleResult();
    }

    public static List<AbstractObject> AbstractObject.findAllAbstractObjects() {
        return entityManager().createQuery("select o from AbstractObject o").getResultList();
    }

    public static Query AbstractObject.findAbstractObjects(SearchCriteria searchCriteria) {
        StringBuilder sb = new StringBuilder();
        if (searchCriteria.getDistrict() != null) {
            addIdParameter(sb, "district");
        }
        if (searchCriteria.getRegion() != null) {
            addIdParameter(sb, "region");
        }
        String str = "SELECT o FROM " + (searchCriteria.getOperationType() != null ?
                searchCriteria.getOperationType().getKey() : "AbstractObject") + " o ";
        if (sb.length() > 0) {
            str = str + " WHERE ";
        }
        sb.insert(0, str);
        //todo: WHERE o.creationDate<=current_timestamp()
        sb.append(" order by o.creationDate desc, o.street.caption");

        Query q = entityManager().createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
            q.setParameter("district", searchCriteria.getDistrict());
        }
        if (searchCriteria.getRegion() != null) {
            q.setParameter("region", searchCriteria.getRegion());
        }
        return q;
    }

    public static long AbstractObject.countAbstractObjects(SearchCriteria searchCriteria) {
        StringBuilder sb = new StringBuilder();
        if (searchCriteria.getDistrict() != null) {
            addIdParameter(sb, "district");
        }
        if (searchCriteria.getRegion() != null) {
            addIdParameter(sb, "region");
        }
        String str = "SELECT count(o) FROM " + (searchCriteria.getOperationType() != null ?
                searchCriteria.getOperationType().getKey() : "AbstractObject") + " o ";
        if (sb.length() > 0) {
            str = str + " WHERE ";
        }
        sb.insert(0, str);
        //todo: WHERE o.creationDate<=current_timestamp()

        Query q = entityManager().createQuery(sb.toString());
        if (searchCriteria.getDistrict() != null) {
            q.setParameter("district", searchCriteria.getDistrict());
        }
        if (searchCriteria.getRegion() != null) {
            q.setParameter("region", searchCriteria.getRegion());
        }
        return (Long) q.getSingleResult();
    }

    private static void addIdParameter(StringBuilder sb, String param) {
        if (sb.length() > 0) {
            sb.append(" and ");
        }
        sb.append(" o.").append(param).append(" = :").append(param);
    }

    public static AbstractObject AbstractObject.findAbstractObject(Long id) {
        if (id == null) return null;
        return entityManager().find(AbstractObject.class, id);
    }

    public static List<AbstractObject> AbstractObject.findAbstractObjectEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() order by o.creationDate desc").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findManualEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() and source is null order by o.creationDate desc").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findManualEntries(Date dueDate, int firstResult, int maxResults) {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() and o.creationDate >= :duedate and source is null order by o.creationDate desc");
        q.setParameter("duedate", dueDate);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findSourceEntries(String source, int firstResult, int maxResults) {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() and source = :source order by o.creationDate desc");
        q.setParameter("source", source);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findSourceEntriesOnlyPhoto(String source, int firstResult, int maxResults) {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() and source = :source and o.photoExt is not null order by o.creationDate desc");
        q.setParameter("source", source);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findSourceEntries(Date dueDate, String source, int firstResult, int maxResults) {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.creationDate<=current_timestamp() and o.creationDate >= :duedate and source = :source order by o.creationDate desc");
        q.setParameter("source", source);
        q.setParameter("duedate", dueDate);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AbstractObject> AbstractObject.findRecentObjects() {
        List<AbstractObject> l1 = findManualEntries(0, 5);
        List<AbstractObject> l2 = findSourceEntriesOnlyPhoto("aviso", 0, 10);
        l1.addAll(l2);
        return l1;
    }

    public static boolean AbstractObject.existEntryByEidAndSource(String eid, String source) {
        Query q = entityManager().createQuery("select count(o) from AbstractObject o where o.source = :source and o.eid = :eid");
        q.setParameter("eid", eid);
        q.setParameter("source", source);
        long count = (Long) q.getSingleResult();
        return count > 0;
    }

    public static AbstractObject AbstractObject.findEntryByStreetIdAndEidAndSource(long streetId, String eid, String source) {
        Query q = entityManager().createQuery("select o from AbstractObject o where o.source = :source and o.street.id=:streetId and o.eid = :eid");
        q.setParameter("streetId", streetId);
        q.setParameter("eid", eid);
        q.setParameter("source", source);
        List<AbstractObject> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }
}
