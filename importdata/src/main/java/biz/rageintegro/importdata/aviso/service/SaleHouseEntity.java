package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.SaleHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleHouseEntity implements AbstractObjectEntity<SaleHouse> {

    @Autowired
    private DomainServiceProxyWrapper domainService;

    @Override
    public SaleHouse create() {
        return new SaleHouse();
    }

    @Override
    public void persist(SaleHouse saleHouse) {
        domainService.getService().persistSaleHouse(saleHouse);
    }

    @Override
    public void merge(SaleHouse saleHouse) {
        domainService.getService().mergeSaleHouse(saleHouse);
    }
}
