package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListObjectsAdapterFactory {
    @Autowired
    private FlatListObjectsAdapter flatAdapter;
    @Autowired
    private SaleLandListObjectsAdapter landAdapter;
    @Autowired
    private HouseListObjectsAdapter houseAdapter;

    public AbstractListObjectsAdapter getAdapter(ObjectType objectType) {
        switch (objectType) {
            case SALE_FLAT:
                return flatAdapter;
            case LEASE_FLAT:
                return flatAdapter;
            case SALE_HOUSE:
                return houseAdapter;
            case LEASE_HOUSE:
                return houseAdapter;
            case SALE_LAND:
                return landAdapter;
        }
        return null;
    }
}
