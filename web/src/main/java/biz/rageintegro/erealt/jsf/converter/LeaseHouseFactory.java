package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.LeaseHouse;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public class LeaseHouseFactory extends HouseFactory<LeaseHouse>{

    private static LeaseHouseFactory INSTANCE;

    public static LeaseHouseFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeaseHouseFactory();
        }
        return INSTANCE;
    }

    @Override
    public LeaseHouse createEntity(CreateBean createBean) {
        LeaseHouse result = new LeaseHouse();
        init(result, createBean);
        return result;
    }
}
