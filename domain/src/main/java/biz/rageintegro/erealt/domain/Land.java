package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AbstractObject;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findLandsByDistrictAndRegionAndPriceBetween" })
public abstract class Land extends AbstractObject {

    @Min(0L)
    @Max(100L)
    private Integer plottage;

    @Override
    public Map<String, String> getQuickInfo() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (getPlottage() != null) {
            map.put(String.valueOf(getPlottage()), "label.plottagePostfix");
        }
        return map;
    }

    @Override
    public List<QuickInfo> getQuickInfoList() {
        List<QuickInfo> list = new ArrayList<QuickInfo>();
        if (getPlottage() != null) {
            list.add(new QuickInfo("label_plottagePostfix", String.valueOf(getPlottage())));
        }
        return list;
    }
}
