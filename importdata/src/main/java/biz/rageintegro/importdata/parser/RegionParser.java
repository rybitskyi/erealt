package biz.rageintegro.importdata.parser;

import biz.rageintegro.erealt.domain.stub.District;
import biz.rageintegro.erealt.domain.stub.Region;
import biz.rageintegro.importdata.aviso.service.DomainServiceProxyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegionParser {
    private static final int MAX_WORDS_COUNT = 10;

    @Autowired
    private DomainServiceProxyWrapper domainService;

    private Pattern pattern = Pattern.compile("[ .,?!]+");

    public Region getRegion(String regionName, District district) {
        if (district == null) {
            return null;
        }
        String arr[] = pattern.split(regionName, MAX_WORDS_COUNT);
        Region region = null;
        for (String str : arr) {
            region = (Region) domainService.getService().findRegionByCaption(str, district.getId()).getValue();
            if (region != null) {
                return region;
            }
        }
        return region;
    }
}
