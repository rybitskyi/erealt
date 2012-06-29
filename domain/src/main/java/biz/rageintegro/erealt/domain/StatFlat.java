package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatAbstractObject;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import biz.rageintegro.erealt.reference.RoomCountType;
import javax.validation.constraints.NotNull;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public abstract class StatFlat extends StatAbstractObject {

    @NotNull
    @Enumerated
    private RoomCountType roomCount;

    @Pattern(regexp = "([0-9]+/[0-9]+/[0-9]+)?")
    private String flatArea;

    @Min(0L)
    @Max(100L)
    private Integer flatFloor;

    @Min(0L)
    @Max(100L)
    private Integer buildFloor;
}
