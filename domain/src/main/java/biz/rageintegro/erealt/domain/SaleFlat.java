package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.Flat;

import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findSaleFlatsByRegionAndRoomCount" })
public class SaleFlat extends Flat {
    private final static Logger logger = Logger.getLogger(SaleFlat.class);
}
