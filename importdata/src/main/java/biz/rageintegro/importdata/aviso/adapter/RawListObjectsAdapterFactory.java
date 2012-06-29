package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RawListObjectsAdapterFactory {
    @Autowired
    private DefaultRawListAdapter defaultAdapter;
    @Autowired
    private FlatRawListAdapter flatAdapter;

    public AbstractRawListAdapter getAdapter(ObjectType objectType) {
        switch (objectType) {
            case SALE_FLAT:
                return flatAdapter;
            case LEASE_FLAT:
                return flatAdapter;
            default:
                return defaultAdapter;
        }
    }
}
