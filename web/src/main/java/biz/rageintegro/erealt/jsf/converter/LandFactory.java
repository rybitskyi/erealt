package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.Land;
import biz.rageintegro.erealt.domain.SaleFlat;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public abstract class LandFactory<T extends Land> extends EntityFactory<T>{

    @Override
    protected void init(T entity, CreateBean createBean) {
        super.init(entity, createBean);
        entity.setPlottage(createBean.getPlottage());
    }
}
