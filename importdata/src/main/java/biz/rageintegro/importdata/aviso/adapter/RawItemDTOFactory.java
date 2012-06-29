package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.FlatRawItemDTO;
import biz.rageintegro.importdata.aviso.dto.ObjectType;
import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import org.springframework.stereotype.Component;

import javax.xml.validation.Schema;
import java.util.Map;

@Component
public class RawItemDTOFactory {

    public RawItemDTO getInstance(ObjectType objectType) {
        switch (objectType) {
            case SALE_FLAT:
                return new FlatRawItemDTO();
            case LEASE_FLAT:
                return new FlatRawItemDTO();
            default:
                return new RawItemDTO();
        }
    }
}
