package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.LeaseFlat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseFlatEntity implements AbstractObjectEntity<LeaseFlat> {

    @Autowired
    private DomainServiceProxyWrapper domainService;

    @Override
    public LeaseFlat create() {
        return new LeaseFlat();
    }

    @Override
    public void persist(LeaseFlat leaseFlat) {
        domainService.getService().persistLeaseFlat(leaseFlat);
    }

    @Override
    public void merge(LeaseFlat leaseFlat) {
        domainService.getService().mergeLeaseFlat(leaseFlat);
    }
}
