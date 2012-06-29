package biz.rageintegro.erealt.domain;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import biz.rageintegro.erealt.domain.District;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.Street;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public abstract class StatAbstractObject {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date creationDate;

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
}
