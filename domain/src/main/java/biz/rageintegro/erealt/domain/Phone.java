package biz.rageintegro.erealt.domain;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public class Phone implements Serializable {

    @NotNull
    @Size(min = 1, max = 6)
    private String code;

    @NotNull
    @Min(1L)
    @Max(9999999L)
    private Integer phoneNumber;
}
