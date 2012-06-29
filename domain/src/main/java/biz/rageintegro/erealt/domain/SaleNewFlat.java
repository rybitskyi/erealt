package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.NewFlat;
import javax.persistence.Entity;

import biz.rageintegro.erealt.reference.RoomCountType;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findSaleNewFlatsByRegionAndRoomCount" })
public class SaleNewFlat extends NewFlat {

}
