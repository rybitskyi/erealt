package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.LeaseFlat;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public class LeaseFlatFactory extends FlatFactory<LeaseFlat>{

    private static LeaseFlatFactory INSTANCE;

    public static LeaseFlatFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeaseFlatFactory();
        }
        return INSTANCE;
    }

    @Override
    public LeaseFlat createEntity(CreateBean createBean) {
        LeaseFlat result = new LeaseFlat();
        init(result, createBean);
        return result;
    }
}
