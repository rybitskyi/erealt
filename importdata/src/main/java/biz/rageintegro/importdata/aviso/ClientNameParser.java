package biz.rageintegro.importdata.aviso;

import biz.rageintegro.erealt.domain.service.DomainServiceProxy;
import biz.rageintegro.erealt.domain.stub.DomainService;
import biz.rageintegro.erealt.domain.stub.HumanName;

import java.util.regex.Pattern;

/**
 * User: rybitskyii
 */
public class ClientNameParser {
    //todo: optimize performance! It should be to slowly
    private DomainServiceProxy domainServiceProxy;
    private Pattern pattern = Pattern.compile("[ .,?!]+");
    public void ClientNameParser() {
    }

    public String parseClientName(String value) {
        if (value == null) {
            return null;
        }
        value = TelephoneParser.getAfterTelValue(value);
        String arr[] = pattern.split(value);
        for (String str : arr) {
            HumanName humanName = (HumanName) getDomainService().findHumanNameByCaption(str).getValue();
            if (humanName != null) {
                return humanName.getCaption();
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
