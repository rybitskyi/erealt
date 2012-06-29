package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.importdata.aviso.dto.AvisoListRequest;
import biz.rageintegro.importdata.aviso.dto.AvisoListResponse;
import biz.rageintegro.importdata.aviso.dto.RawAvisoListResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface AvisoService{

    @WebMethod
    @WebResult(name = "rawList")
    RawAvisoListResponse getRawList(
             @WebParam(name = "request") AvisoListRequest request);

	@WebMethod
    @WebResult(name = "listItems")
    AvisoListResponse prepareImportData(
            @WebParam(name = "request") AvisoListRequest request);

    @WebMethod
    @WebResult(name = "listObjects")
    AvisoListResponse importData(
            @WebParam(name = "request") AvisoListRequest request);
}