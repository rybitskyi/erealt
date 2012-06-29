package biz.rageintegro.importdata.aviso;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: rybitskyii
 * Date: 17.10.2010
 * Time: 8:02:42
 * To change this template use File | Settings | File Templates.
 */
public class CreationDateGetterTest extends TestCase {

/*todo: use date, not id
    public void test0() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("9-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467780, 0));
    }

    public void test1() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("13-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467779, 0));
    }

    public void test1a() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("14-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467779, 1));
    }

    public void test1b() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("15-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467779, 2));
    }

    public void test1c() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("13-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467779, 3));
    }

    public void test1d() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("14-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467779, 4));
    }

    public void test2() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("16-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467778, 0));
    }

    public void test2a() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("17-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467778, 1));
    }

    public void test2b() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("18-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467778, 2));
    }

    public void test2c() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("19-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467778, 3));
    }

    public void test2d() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("16-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467778, 4));
    }

    public void test3() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("20-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467777, 0));
    }

    public void test4() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("23-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467776, 0));
    }

    public void test4a() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("24-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467776, 1));
    }

    public void test4b() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("25-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467776, 2));
    }

    public void test4c() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("26-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467776, 3));
    }

    public void test4d() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("23-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467776, 4));
    }

    public void test5() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("27-10-2010");
        assertEquals(d, CreationDateGetter.getDate(-2147467775, 0));
    }

    //-2147467775
    public void test9() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("27-10-2010");
        assertEquals(-2147467775, CreationDateGetter.getAvisoNo(d));
    }

    public void test10() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("28-10-2010");
        assertEquals(-2147467775, CreationDateGetter.getAvisoNo(d));
    }

    public void test11() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("29-10-2010");
        assertEquals(-2147467775, CreationDateGetter.getAvisoNo(d));
    }

    //-2147467774
    public void test12() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("30-10-2010");
        assertEquals(-2147467774, CreationDateGetter.getAvisoNo(d));
    }

    public void test13() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("31-10-2010");
        assertEquals(-2147467774, CreationDateGetter.getAvisoNo(d));
    }

    public void test14() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("1-11-2010");
        assertEquals(-2147467774, CreationDateGetter.getAvisoNo(d));
    }

    public void test15() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("2-11-2010");
        assertEquals(-2147467774, CreationDateGetter.getAvisoNo(d));
    }

    //-2147467773
    public void test16() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("3-11-2010");
        assertEquals(-2147467773, CreationDateGetter.getAvisoNo(d));
    }

    public void test17() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("4-11-2010");
        assertEquals(-2147467773, CreationDateGetter.getAvisoNo(d));
    }

    public void test18() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("5-11-2010");
        assertEquals(-2147467773, CreationDateGetter.getAvisoNo(d));
    }

    public void test19() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("6-11-2010");
        assertEquals(-2147467772, CreationDateGetter.getAvisoNo(d));
    }

    public void test20() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("7-11-2010");
        assertEquals(-2147467772, CreationDateGetter.getAvisoNo(d));
    }
*/
    public void test10() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("12-01-2011");
        assertEquals(-2147467355, CreationDateGetter.getAvisoNo(d));
    }

    public void test1() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("12-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467355, 0));
    }

    public void test1a() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("13-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467355, 1));
    }

    public void test1b() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("14-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467355, 2));
    }

    public void test1c() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("12-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467355, 3));
    }

    public void test1d() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("13-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467355, 4));
    }

    public void test21() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("15-01-2011");
        assertEquals(-2147467354, CreationDateGetter.getAvisoNo(d));
    }

    public void test22() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("15-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467354, 0));
    }

    public void test2a() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("16-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467354, 1));
    }

    public void test2b() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("17-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467354, 2));
    }

    public void test2c() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("18-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467354, 3));
    }

    public void test2d() throws ParseException {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dfm.parse("15-1-2011");
        assertEquals(d, CreationDateGetter.getDate(-2147467354, 4));
    }		

    public void test3_1() throws ParseException {
        assertEquals("2011-01-15 00:10:00.0", CreationDateGetter. getCreationDate(-2147467354, 4, 1).toString());
    }

    public void test3_2() throws ParseException {
        assertEquals("2011-01-15 23:20:00.0", CreationDateGetter. getCreationDate(-2147467354, 4, 140).toString());
    }
}
