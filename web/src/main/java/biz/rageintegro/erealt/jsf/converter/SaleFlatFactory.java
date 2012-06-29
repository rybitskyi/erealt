package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.Flat;
import biz.rageintegro.erealt.domain.SaleFlat;
import biz.rageintegro.erealt.jsf.CreateBean;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public class SaleFlatFactory extends FlatFactory<SaleFlat>{

    private static SaleFlatFactory INSTANCE;

    public static SaleFlatFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SaleFlatFactory();
        }
        return INSTANCE;
    }

    @Override
    public SaleFlat createEntity(CreateBean createBean) {
        SaleFlat result = new SaleFlat();
        init(result, createBean);
        return result;
    }
}
