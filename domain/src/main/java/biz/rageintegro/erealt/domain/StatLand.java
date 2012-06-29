package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatAbstractObject;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public abstract class StatLand extends StatAbstractObject {

    @Min(0L)
    @Max(100L)
    private Integer plottage;
}
