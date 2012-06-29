package biz.rageintegro.erealt.domain.ws;

import biz.rageintegro.erealt.domain.*;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import java.util.List;

@WebService(endpointInterface = "biz.rageintegro.erealt.domain.ws.DomainService")
public class DomainServiceImpl implements DomainService {

    @Override
    public Holder<District> findDistrictByCaption(String caption) {
        Holder<District> holder = new Holder<District>();
        holder.value = District.findDistrictByCaption(caption);
        return holder;
    }

    @Override
    public Holder<Region> findRegionByCaption(String caption, long districtId) {
        Holder<Region> holder = new Holder<Region>();
        holder.value = Region.findRegionByCaption(caption, districtId);
        return holder;
    }

    @Override
    public Holder<Street> findStreetByCaption(String caption, long regionId) {
        Holder<Street> holder = new Holder<Street>();
        holder.value = Street.findStreetByCaption(caption, regionId);
        return holder;
    }

    @Override
    public StreetListResponse findStreetsByRegion(long regionId) {
        StreetListResponse response = new StreetListResponse();
        response.setList(Street.findStreetsByRegion(regionId, false));
        return response;
    }

    @Override
    public void persistSaleFlat(SaleFlat saleFlat) {
        saleFlat.persist();
    }

    @Override
    public void persistLeaseFlat(LeaseFlat leaseFlat) {
        leaseFlat.persist();
    }

    @Override
    public void mergeSaleFlat(SaleFlat saleFlat) {
        mergeAbstractObject(saleFlat);
    }

    @Override
    public void mergeLeaseFlat(LeaseFlat leaseFlat) {
        mergeAbstractObject(leaseFlat);
    }

    @Override
    public void persistSaleLand(SaleLand saleLand) {
        saleLand.persist();
    }

    @Override
    public void mergeSaleLand(SaleLand saleLand) {
        mergeAbstractObject(saleLand);
    }

    @Override
    public void persistSaleHouse(SaleHouse saleHouse) {
        saleHouse.persist();
    }

    @Override
    public void mergeSaleHouse(SaleHouse saleHouse) {
        mergeAbstractObject(saleHouse);
    }

    @Override
    public void persistLeaseHouse(LeaseHouse leaseHouse) {
        mergeAbstractObject(leaseHouse);
    }

    @Override
    public void mergeLeaseHouse(LeaseHouse leaseHouse) {
        mergeAbstractObject(leaseHouse);
    }

    private void mergeAbstractObject(AbstractObject abstractObject) {
        if (abstractObject.getId() == null) {
            throw new RuntimeException("Field id must be exist");
        }
        AbstractObject realAO = AbstractObject.findAbstractObject(abstractObject.getId());
        if (abstractObject.getCreationDate().getTime() > realAO.getCreationDate().getTime()) {
            realAO.setCreationDate(abstractObject.getCreationDate());
        }
        realAO.setPrice(abstractObject.getPrice());
        realAO.setDescription(abstractObject.getDescription());
        realAO.setStreetObjectNo(abstractObject.getStreetObjectNo());
        //todo: set phones also
        realAO.merge();
    }

    @Override
    public Holder<HumanName> findHumanNameByCaption(String caption) {
        Holder<HumanName> holder = new Holder<HumanName>();
        holder.value = HumanName.findHumanName(caption);
        return holder;
    }

    @Override
    public Boolean existAbstractObjectByEidAndSource(String eid, String source) {
        return AbstractObject.existEntryByEidAndSource(eid, source);
    }

    @Override
    public Holder<AbstractObject> findAbstractObjectByStreetIdAndEidAndSource(long streetId, String eid, String source) {
        Holder<AbstractObject> holder = new Holder<AbstractObject>();
        holder.value = AbstractObject.findEntryByStreetIdAndEidAndSource(streetId, eid, source);
        return holder;
    }

    @Override
    public Holder<AccessUser> findAccessUserByPhone(Phone phone) {
        Holder<AccessUser> holder = new Holder<AccessUser>();
        holder.value = AccessUser.findAccessUserByPhone(phone);
        return holder;
    }

    @Override
    public Holder<Phone> findPhone(String code, int phoneNumber) {
        Holder<Phone> holder = new Holder<Phone>();
        holder.value = Phone.findPhone(code, phoneNumber);
        return holder;
    }

    @Override
    public void mergeAccessUser(AccessUser accessUser) {
        accessUser.merge();
    }

    @Override
    public void persistAccessUser(AccessUser accessUser) {
        accessUser.persist();
    }
}
