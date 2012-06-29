package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.SaleFlat;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SaleFlatEntity implements AbstractObjectEntity<SaleFlat> {

    @Autowired
    private DomainServiceProxyWrapper domainService;

    @Override
    public SaleFlat create() {
        return new SaleFlat();
    }

    @Override
    public void persist(SaleFlat saleFlat) {
        domainService.getService().persistSaleFlat(saleFlat);
    }

    @Override
    public void merge(SaleFlat saleFlat) {
        domainService.getService().mergeSaleFlat(saleFlat);
    }
}
