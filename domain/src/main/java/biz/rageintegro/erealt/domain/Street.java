package biz.rageintegro.erealt.domain;

import javax.persistence.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import biz.rageintegro.erealt.domain.Region;
import java.util.HashSet;

@Entity
@RooJavaBean
@RooToString
@RooEntity
@XStreamAlias("street")
public class Street  implements Serializable {

    @NotNull
    @Size(min = 1, max = 50)
    private String caption;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Region> regions = new HashSet<Region>();

    private Boolean deprecated;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Street street = (Street) o;

        if (id != null ? !id.equals(street.id) : street.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
