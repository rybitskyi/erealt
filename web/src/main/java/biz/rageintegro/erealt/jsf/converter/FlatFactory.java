package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.Flat;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public abstract class FlatFactory<T extends Flat> extends EntityFactory<T> {

    @Override
    protected void init(T entity, CreateBean createBean) {
        super.init(entity, createBean);
        entity.setRoomCount(createBean.getRoomCount());
        entity.setFlatArea(createBean.getFlatArea());
        entity.setFlatFloor(createBean.getFlatFloor());
        entity.setBuildFloor(createBean.getFlatBuildFloor());
    }
}
