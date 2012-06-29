package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.erealt.domain.stub.House;
import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import biz.rageintegro.importdata.aviso.parser.AreaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseListObjectsAdapter extends LandListObjectsAdapter<House, RawItemDTO> {

    @Autowired
    private AreaParser areaParser;

    @Override
    public void fillObject(House object, RawItemDTO item) {
        super.fillObject(object, item);
        object.setHouseArea(areaParser.parseArea(item.getDescription()));
    }
}
