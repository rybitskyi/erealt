package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.House;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findLeaseHousesByRegion" })
public class LeaseHouse extends House {
}
