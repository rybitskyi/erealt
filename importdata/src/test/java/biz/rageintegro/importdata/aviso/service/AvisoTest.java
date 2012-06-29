package biz.rageintegro.importdata.aviso.service;


import biz.rageintegro.importdata.aviso.dto.RawItemDTO;
import biz.rageintegro.importdata.aviso.service.AvisoServiceBean;
import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class AvisoTest extends TestCase {

//http://online.aviso.ua/kiev/rubrics_new.php?rid=100&rub=101&adistr=1&sortby=1&rooms1=1

    public void test1() throws IOException{
        File input = new File("C:\\Users\\yury.ribitsky\\Documents\\private\\projects\\importdata\\src\\test\\resources\\1.html"); //todo: use relative path
        Document doc = Jsoup.parse(input, "UTF-8", "http://aviso.ua/");//todo:
        AvisoServiceBean service = new AvisoServiceBean();
        Set<RawItemDTO> items = service.getRawItems(doc);
        for (RawItemDTO item : items) {
            System.out.println(item);
        }
    }
}
