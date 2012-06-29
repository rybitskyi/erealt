package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.service.DomainServiceProxy;
import biz.rageintegro.erealt.domain.stub.DomainService;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceProxyWrapper {
    private DomainServiceProxy domainServiceProxy;

    public DomainServiceProxyWrapper() {
        domainServiceProxy = new DomainServiceProxy();
    }

    public DomainService getService() {
        return domainServiceProxy.getDomainService();
    }
}
