package biz.rageintegro.importdata.aviso.component;

import biz.rageintegro.importdata.aviso.dto.AvisoListRequest;
import biz.rageintegro.importdata.aviso.dto.RoomCount;
import biz.rageintegro.importdata.aviso.service.AvisoServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SaleFlatProcessor {

    @Autowired
    private AvisoServiceBean avisoService;

    @Scheduled(cron = "0 0 7 * * *")
    public void processSaleFlat() {
        AvisoListRequest request = new AvisoListRequest();
        request.setCity("kiev"); // todo: now it's not processed
        request.setRid(100);
        request.setRub(101);
        request.setOnlyWithPhoto(true);
        for (int aDistr = 1; aDistr <= 10; aDistr++) {
            request.setAdistr(aDistr);
            for (RoomCount roomCount : RoomCount.values()) {
                request.setRoomCount(roomCount);
                avisoService.importData(request);
            }
        }
    }
}