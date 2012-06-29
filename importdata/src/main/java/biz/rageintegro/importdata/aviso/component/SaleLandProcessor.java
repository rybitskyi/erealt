package biz.rageintegro.importdata.aviso.component;

import biz.rageintegro.importdata.aviso.dto.AvisoListRequest;
import biz.rageintegro.importdata.aviso.service.AvisoServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SaleLandProcessor {

    @Autowired
    private AvisoServiceBean avisoService;

    @Scheduled(cron = "0 30 8 * * *")
    public void processSaleFlat() {
        AvisoListRequest request = new AvisoListRequest();
        request.setCity("kiev"); // todo: now it's not processed
        request.setRid(180);
        request.setRub(192);
        request.setOnlyWithPhoto(true);
        for (int aDistr = 1; aDistr <= 10; aDistr++) {
            request.setAdistr(aDistr);
                avisoService.importData(request);
        }
    }
}