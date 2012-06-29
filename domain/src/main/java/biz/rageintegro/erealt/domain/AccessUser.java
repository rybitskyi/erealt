package biz.rageintegro.erealt.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.OptimisticLock;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public class AccessUser implements Serializable {

    @NotNull
    private String name;

    private String caption;

    private String password;

    private String telephone;

    @ManyToMany(cascade = CascadeType.ALL)
    @OptimisticLock(excluded = true)
    private List<Phone> phone = new ArrayList<Phone>();

    private Boolean publicEmail;
}
