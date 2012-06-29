package biz.rageintegro.importdata.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import biz.rageintegro.erealt.domain.service.DomainServiceProxy;
import biz.rageintegro.erealt.domain.stub.*;

/**
 * For peformance reason new parser instance must be created for each region.<br/>
 * As though all streets are stored im memory<br/>
 */

public class AddressParserImpl implements AddressParser {

    private static final int MAX_WORDS_COUNT = 30;
    private static final int MAX_WORD_LENGTH = 50;

    private Region region;
    private Pattern pattern = Pattern.compile("[ .,?!]+");
    protected Map<String, Street> streets;
    private DomainServiceProxy domainServiceProxy;

    /**
     * Street Parser. <br/>
     * Works by the following steps:<br/>
     * 1) District and Region must be determined<br/>
     * 2) All streets of region are stored in hashSet as interned strings<br/>
     * 3) Checked all words by " " for street in dictionary
     *
     * @param region
     */
    public AddressParserImpl(Region region) {
        if (region == null || region.getCaption().isEmpty()) {
            throw new RuntimeException("Argument region must be set");
        }
        this.region = region;
    }

    private Map<String, Street> getStreets() {
        if (streets == null) {
            streets = new HashMap<String, Street>();
            for (Street street : getDomainService().findStreetsByRegion(region.getId()).getList()) {
                streets.put(street.getCaption().intern(), street);
            }
        }
        return streets;
    }

    @Override
    public Street parseStreet(String description) {
        if (description == null || description.isEmpty()) {
            return null;
        }
        String arr[] = pattern.split(description, MAX_WORDS_COUNT);
        for (String str : arr) {
            if (str.length() > MAX_WORD_LENGTH) {
                str = str.substring(0, MAX_WORD_LENGTH - 1); // prevent to intern long words
            }
            String strIntern = str.intern();
            for (String street : getStreets().keySet()) {
                if (strIntern == street) {  // street must be interned
                    return streets.get(strIntern);
                }
            }
        }
        return null;
    }

    private DomainService getDomainService() {
        if (domainServiceProxy == null) {
            domainServiceProxy = new DomainServiceProxy();
        }
        return domainServiceProxy.getDomainService();
    }
}
