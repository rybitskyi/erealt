package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.importdata.aviso.dto.AvisoListRequest;
import biz.rageintegro.importdata.aviso.dto.AvisoListResponse;
import biz.rageintegro.importdata.aviso.dto.RawAvisoListResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "biz.rageintegro.importdata.aviso.service.AvisoService")
public class AvisoServiceImpl implements AvisoService {
    private AvisoServiceBean service;

    public AvisoServiceImpl() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/META-INF/spring/applicationContext.xml"); //get Spring context

        service = (AvisoServiceBean) ctx.getBean("avisoServiceBean");
    }

    @Override
    public RawAvisoListResponse getRawList(@WebParam(name = "request") AvisoListRequest request) {
        return service.getRawList(request);
    }

    @Override
    public AvisoListResponse prepareImportData(@WebParam(name = "request") AvisoListRequest request) {
        return service.prepareImportData(request);
    }

    @Override
    public AvisoListResponse importData(@WebParam(name = "request") AvisoListRequest request) {
        return service.importData(request);
    }
}