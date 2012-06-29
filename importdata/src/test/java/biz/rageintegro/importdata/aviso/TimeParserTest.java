package biz.rageintegro.importdata.aviso;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * www.rageintegro.biz
 * User: RybitskyiI
 * Date: 13/4/2011
 */
public class TimeParserTest extends TestCase {

    public void test1() {
        assertEquals("2011-04-11 18:22:54", TimeParser.parseTimeAsString("1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                "\t\t\t\t190 000 у.е.\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void test2() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = dfm.parse("2011-04-11 18:22:54");
        assertEquals(d, TimeParser.parseTime("1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                "\t\t\t\t190 000 у.е.\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t2011-04-11 18:22:54"));
    }
}
