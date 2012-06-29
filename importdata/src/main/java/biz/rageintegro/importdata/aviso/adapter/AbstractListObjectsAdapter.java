package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.erealt.domain.service.DomainServiceProxy;
import biz.rageintegro.erealt.domain.stub.*;
import biz.rageintegro.importdata.aviso.AvisoUtils;
import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import biz.rageintegro.importdata.parser.AddressParser;
import biz.rageintegro.importdata.parser.AddressParserImpl;
import biz.rageintegro.importdata.parser.RegionParser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractListObjectsAdapter<T extends AbstractObject, RawT extends RawItemDTO> {
    private static String DISTRICT_KIEV = "Киев";
    private DomainServiceProxy domainServiceProxy;
    @Autowired
    private RegionParser regionParser;

    private Map<Long, AddressParser> addressParserMap; // todo: add singleton!

    public void fillObject(T object, RawT item) {
        if (object == null) {
            throw new IllegalArgumentException("Argument object can't be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("Argument item can't be null");
        }
        {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(item.getDate());
            try {
                object.setCreationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            } catch (DatatypeConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        object.setDistrict(getDistrict(item.getTitle()));
        object.setRegion(regionParser.getRegion(item.getRegionName(), object.getDistrict()));
        object.setStreet(getStreet(item.getDescription(), object.getRegion()));
        if (object.getStreet() != null) {
            object.setStreetObjectNo(AvisoUtils.getStreetObjectNo(item.getDescription(), object.getStreet().getCaption()));
        }
        object.setDescription(item.getDescription());
        object.setEid(String.valueOf(item.getId()));
        object.setPrice(AvisoUtils.getPriceNormal(item.getPrice()));
        object.setAccessUser(getAccessUser(item));
//        result.setEmail(todo);
        object.setImg(item.getPhotoPath());
        object.setPhotoExt(item.getPhotoExt());
//        result.setWeb(todo);
        object.setSource("aviso");
        object.setEid(String.valueOf(item.getId()));
        if (item.getPhotoFile() != null) {
            object.setPhoto(item.getPhotoFile().getAbsolutePath());
        }
    }

    private AddressParser getAddressParser(Region region) {
        if (region == null) {
            return null;
        }
        if (addressParserMap == null) {
            addressParserMap = new HashMap<Long, AddressParser>();
        }
        AddressParser parser = addressParserMap.get(region.getId());
        if (parser == null) {
            parser = new AddressParserImpl(region);
            addressParserMap.put(region.getId(), parser);
        }
        return parser;
    }

    private DomainService getDomainService() {
        if (domainServiceProxy == null) {
            domainServiceProxy = new DomainServiceProxy();
        }
        return domainServiceProxy.getDomainService();
    }

    private District getDistrict(String title) {
        return (District) getDomainService().findDistrictByCaption(getDistrictName(title)).getValue();
    }

    private String getDistrictName(String title) { //todo: extract other districts
        if (title.contains(DISTRICT_KIEV)) {
            return DISTRICT_KIEV;
        }
        throw new RuntimeException("Can't parse districtName from title '" + title + "'");
    }

    private Street getStreet(String description, Region region) {
        if (description == null || description.isEmpty() || region == null) {
            return null;
        }
        return getAddressParser(region).parseStreet(description);
    }

    private AccessUser getAccessUser(RawItemDTO item) {
        AccessUser accessUser = new AccessUser();
        accessUser.setName(item.getNormalizedPhones());
        for (Phone p : item.getPhones()) {
            accessUser.getPhone().add(p);
        }
        return accessUser;
    }
}
