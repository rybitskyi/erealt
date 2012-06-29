package biz.rageintegro.erealt.domain.ws;

import biz.rageintegro.erealt.domain.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DomainService {

    @WebMethod
    @WebResult(name = "district")
    Holder<District> findDistrictByCaption(
            @WebParam(name = "caption") String caption);

    @WebMethod
    @WebResult(name = "region")
    Holder<Region> findRegionByCaption(
            @WebParam(name = "caption") String caption,
            @WebParam(name = "districtId") long districtId);

    @WebMethod
    @WebResult(name = "street")
    Holder<Street> findStreetByCaption(
            @WebParam(name = "caption") String caption,
            @WebParam(name = "regionId") long regionId);

    @WebMethod
    @WebResult(name = "streets")
    StreetListResponse findStreetsByRegion(
            @WebParam(name = "regionId") long regionId);

    @WebMethod
    void persistSaleFlat(
            @WebParam(name = "saleFlat") SaleFlat saleFlat);

    @WebMethod
    void mergeSaleFlat(
            @WebParam(name = "saleFlat") SaleFlat saleFlat);

    @WebMethod
    void persistLeaseFlat(
            @WebParam(name = "leaseFlat") LeaseFlat leaseFlat);

    @WebMethod
    void mergeLeaseFlat(
            @WebParam(name = "leaseHouse") LeaseFlat leaseFlat);

    @WebMethod
    void persistSaleLand(
            @WebParam(name = "saleLand") SaleLand saleLand);

    @WebMethod
    void mergeSaleLand(
            @WebParam(name = "saleLand") SaleLand saleLand);

    @WebMethod
    void persistSaleHouse(
            @WebParam(name = "saleHouse") SaleHouse saleHouse);

    @WebMethod
    void mergeSaleHouse(
            @WebParam(name = "saleHouse") SaleHouse saleHouse);

    @WebMethod
    void persistLeaseHouse(
            @WebParam(name = "leaseHouse") LeaseHouse leaseHouse);

    @WebMethod
    void mergeLeaseHouse(
            @WebParam(name = "leaseHouse") LeaseHouse leaseHouse);

    @WebMethod
    @WebResult(name = "humanName")
    Holder<HumanName> findHumanNameByCaption(
            @WebParam(name = "caption") String caption);

    @WebMethod
    @WebResult(name = "existEntry")
    Boolean existAbstractObjectByEidAndSource(
            @WebParam(name = "eid") String eid,
            @WebParam(name = "source") String source);

    @WebMethod
    @WebResult(name = "abstractObject")
    Holder<AbstractObject> findAbstractObjectByStreetIdAndEidAndSource(
            @WebParam(name = "streetId") long stretId,
            @WebParam(name = "eid") String eid,
            @WebParam(name = "source") String source);

    @WebMethod
    @WebResult(name = "accessUser")
    Holder<AccessUser> findAccessUserByPhone(
            @WebParam(name = "phone") Phone phone);

    @WebMethod
    @WebResult(name = "phone")
    Holder<Phone> findPhone(
            @WebParam(name = "code") String code,
            @WebParam(name = "phoneNumber") int phoneNumber);

    @WebMethod
    void mergeAccessUser(
            @WebParam(name = "accessUser") AccessUser accessUser);

    @WebMethod
    void persistAccessUser(
            @WebParam(name = "accessUser") AccessUser accessUser);
}
