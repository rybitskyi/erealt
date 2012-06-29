package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.LeaseHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseHouseEntity implements AbstractObjectEntity<LeaseHouse> {

    @Autowired
    private DomainServiceProxyWrapper domainService;

    @Override
    public LeaseHouse create() {
        return new LeaseHouse();
    }

    @Override
    public void persist(LeaseHouse leaseHouse) {
        domainService.getService().persistLeaseHouse(leaseHouse);
    }

    @Override
    public void merge(LeaseHouse leaseHouse) {
        domainService.getService().mergeLeaseHouse(leaseHouse);
    }
}
