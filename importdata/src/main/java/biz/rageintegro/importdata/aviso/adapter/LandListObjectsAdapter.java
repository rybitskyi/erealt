package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.erealt.domain.stub.Land;
import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import biz.rageintegro.importdata.aviso.parser.PlottageParser;
import org.springframework.beans.factory.annotation.Autowired;

public class LandListObjectsAdapter<T extends Land, RawT extends RawItemDTO> extends AbstractListObjectsAdapter<T, RawT>{

    @Autowired
    private PlottageParser plottageParser;

    @Override
    public void fillObject(T object, RawT item) {
        super.fillObject(object, item);
        object.setPlottage(plottageParser.parseIntPlottage(item.getDescription()));
    }
}
