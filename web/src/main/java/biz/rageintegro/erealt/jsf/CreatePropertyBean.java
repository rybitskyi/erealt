package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.Street;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 5/30/12
 */
@ManagedBean
@RequestScoped
public class CreatePropertyBean implements Serializable {
    private District district;
    private Region region;
    private List<District> districtList;
    private List<Region> regionList;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<District> getDistrictList() {
//        return districtList;//todo: districtList
        return District.findAllDistricts();
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public void handleDistrictChange() {
        if (district != null)
//            regionList = Region.findRegionsByDistrict(district.getId());
            regionList = Region.findRegionsByDistrict(1l);
        else
            regionList = Collections.emptyList();
    }
}
