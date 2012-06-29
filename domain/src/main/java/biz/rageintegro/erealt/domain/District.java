package biz.rageintegro.erealt.domain;

import javax.persistence.Entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@Entity
@RooJavaBean
@RooToString
@RooEntity
@XStreamAlias("district")
public class District  implements Serializable {

    @NotNull
    @Size(min = 1, max = 50)
    private String caption;

    @NotNull
    @Size(min = 1, max = 50)
    private String caption2;
}
