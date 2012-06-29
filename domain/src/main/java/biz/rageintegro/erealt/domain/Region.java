package biz.rageintegro.erealt.domain;

import javax.persistence.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import biz.rageintegro.erealt.domain.District;

import java.io.Serializable;

@Entity
@RooJavaBean
@RooToString
@RooEntity
@XStreamAlias("region")
public class Region  implements Serializable {

    public Region() {
    }

    public Region(String value) {
        if (value != null && value.length() > 0 && !("-1".equals(value))) {
            setId(Long.parseLong(value));
        }
    }

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

    @NotNull
    @Size(min = 1, max = 50)
    private String caption;

    @NotNull
    @Size(min = 1, max = 50)
    private String caption2;

    @ManyToOne(targetEntity = District.class)
    @JoinColumn
    private District district;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (id != null ? !id.equals(region.id) : region.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
