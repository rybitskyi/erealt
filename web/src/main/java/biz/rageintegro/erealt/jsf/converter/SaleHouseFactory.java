package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.SaleHouse;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public class SaleHouseFactory extends HouseFactory<SaleHouse>{

    private static SaleHouseFactory INSTANCE;

    public static SaleHouseFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SaleHouseFactory();
        }
        return INSTANCE;
    }

    @Override
    public SaleHouse createEntity(CreateBean createBean) {
        SaleHouse result = new SaleHouse();
        init(result, createBean);
        return result;
    }
}
