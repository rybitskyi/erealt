package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.ObjectType;
import biz.rageintegro.importdata.aviso.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectEntityFactory {
    @Autowired
    private SaleFlatEntity saleFlatEntity;
    @Autowired
    private LeaseFlatEntity leaseFlatEntity;
    @Autowired
    private SaleHouseEntity saleHouseEntity;
    @Autowired
    private SaleLandEntity saleLandEntity;
    @Autowired
    private LeaseHouseEntity leaseHouseEntity;

    public AbstractObjectEntity getEntity(ObjectType type) {
        switch (type) {
            case SALE_FLAT:
                return saleFlatEntity;
            case LEASE_FLAT:
                return leaseFlatEntity;
            case SALE_HOUSE:
                return saleHouseEntity;
            case LEASE_HOUSE:
                return leaseHouseEntity;
            case SALE_LAND:
                return saleLandEntity;
        }
        return null;
    }
}
