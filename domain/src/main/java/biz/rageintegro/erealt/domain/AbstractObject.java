package biz.rageintegro.erealt.domain;

import javax.persistence.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public abstract class AbstractObject implements Serializable{

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date creationDate = new Date();

    @ManyToOne(targetEntity = District.class)
    @JoinColumn
    private District district;

    @ManyToOne(targetEntity = Region.class)
    @JoinColumn
    private Region region;

    @ManyToOne(targetEntity = Street.class)
    @JoinColumn
    private Street street;

    private String streetObjectNo;

    @NotNull
    @Min(0L)
    private Integer price;

    private String clientName;

    @Size(min = 5, max = 100)
    private String telephone;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String photoExt;

    private String eid;

    @Deprecated
    private String img;

    @Autowired
    private transient PhotoManager photoManager;

    @Transient
    private transient FileDataWrapper fileDataWrapper = new FileDataWrapper();

    @Transient
    private String photo;

    @ManyToOne(targetEntity = AccessUser.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private AccessUser accessUser;    

    private String source;

    private String web;

    public String getSystemName() {
        return getSystemName(getClass());
    }

    public static String getSystemName(Class clazz) {
        return clazz.getSimpleName().toLowerCase();
    }
}
