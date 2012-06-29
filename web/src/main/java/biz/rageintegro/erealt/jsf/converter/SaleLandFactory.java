package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.SaleLand;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public class SaleLandFactory extends LandFactory<SaleLand>{

    private static SaleLandFactory INSTANCE;

    public static SaleLandFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SaleLandFactory();
        }
        return INSTANCE;
    }

    @Override
    public SaleLand createEntity(CreateBean createBean) {
        SaleLand result = new SaleLand();
        init(result, createBean);
        return result;
    }
}
