package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.House;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public abstract class HouseFactory<T extends House> extends LandFactory<T> {

    @Override
    protected void init(T entity, CreateBean createBean) {
        super.init(entity, createBean);
        entity.setHouseArea(createBean.getHouseArea());
    }
}
