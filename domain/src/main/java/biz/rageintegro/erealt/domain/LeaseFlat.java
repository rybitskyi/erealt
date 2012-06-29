package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Flat;
import javax.persistence.Entity;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findLeaseFlatsByRegionAndRoomCount" })
public class LeaseFlat extends Flat {
}
