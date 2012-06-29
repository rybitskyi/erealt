package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.SaleLand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleLandEntity implements AbstractObjectEntity<SaleLand> {

    @Autowired
    private DomainServiceProxyWrapper domainService;

    @Override
    public SaleLand create() {
        return new SaleLand();
    }

    @Override
    public void persist(SaleLand saleLand) {
        domainService.getService().persistSaleLand(saleLand);
    }

    @Override
    public void merge(SaleLand saleLand) {
        domainService.getService().mergeSaleLand(saleLand);
    }
}
