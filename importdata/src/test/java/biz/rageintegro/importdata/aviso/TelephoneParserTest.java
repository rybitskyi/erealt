package biz.rageintegro.importdata.aviso;

import biz.rageintegro.erealt.domain.stub.Phone;
import junit.framework.TestCase;
import junit.framework.Assert;
 
public class TelephoneParserTest extends TestCase {

/*
    public void test1() {
        Assert.assertEquals("(097) 5197868, (063) 1431461", TelephoneParser.parseTelephone("	097-519-7868, 063-143-1461"));
    }

    public void test2() {
        Assert.assertEquals("(050) 3158510, (044) 4940999", TelephoneParser.parseTelephone("050-315-8510, 494-0999"));
    }

    public void test3() {
        Assert.assertEquals("(099) 3000070", TelephoneParser.parseTelephone("099-300-0070"));
    }

    public void test4() {
        Assert.assertEquals("(096) 4704942", TelephoneParser.parseTelephone("096 470 4942"));
    }
*/

    public void test5() {
        Assert.assertEquals("(50) 3333873, (67) 4685833", TelephoneParser.parseTelephone("(50) 3333873, моб., (67) 4685833, моб."));
    }

/*    public void test5a() {
        Assert.assertEquals("(67) 4685833", TelephoneParser.parseTelephone("(123456) 3333873, моб., (67) 4685833, моб."));
    }
*/
    public void test5b() {
        Assert.assertEquals("(044) 3333873, (67) 4685833", TelephoneParser.parseTelephone("() 3333873, моб., (67) 4685833, моб."));
    }

/*

    public void test7() {
        Assert.assertEquals("(097) 6742206", TelephoneParser.parseTelephone("0976742206"));
    }
*/

    public void test8() {
        Assert.assertEquals("(067) 5001064", TelephoneParser.parseTelephone("(067)5001064"));
    }

/*
    public void test9() {
        Assert.assertEquals("(067) 5001064", TelephoneParser.parseTelephone("067-500-10-64"));
    }
*/

    public void test10() {
        Assert.assertEquals("(050) 3532334, (044) 3317483", TelephoneParser.parseTelephone("0503532334  3317483"));
    }
/*

    public void test11() {
        Assert.assertEquals("(067) 5001064", TelephoneParser.parseTelephone("(067)500-10-64"));
    }

    public void test12() {
        Assert.assertEquals("(044) 3625185, (067) 5004756", TelephoneParser.parseTelephone("044-3625185, 067-5004756"));
    }

    public void test12a() {
        Assert.assertEquals("(044) 3625185, (067) 5004756", TelephoneParser.parseTelephone("3625185, 067-5004756"));
    }

    public void test13() {
        Assert.assertEquals("(044) 5353547, (095) 6472247", TelephoneParser.parseTelephone("535-3547, 095-647-2247"));
    }

    public void test14() {
        Assert.assertEquals("(063) 6193161", TelephoneParser.parseTelephone("0636193161"));
    }
*/

    public void test15() {
        Assert.assertEquals("(044) 5372006, (044) 2285252, (067) 4659663", TelephoneParser.parseTelephone("(044)5372006, (044)2285252, (067)4659663, Анжелика, «САФАРИ», "));
    }
/*

    public void test16() {
        Assert.assertEquals("(067) 4539575", TelephoneParser.parseTelephone("067 453 95 75"));
    }

    public void test17() {
        Assert.assertEquals("(044) 3320047", TelephoneParser.parseTelephone("044-3320047"));
    }

    public void test18() {
        Assert.assertEquals("(099) 4600002", TelephoneParser.parseTelephone("(099)46-0000-2 "));
    }

    public void test19() {
        Assert.assertEquals("(050) 8789314, (044) 2279958", TelephoneParser.parseTelephone("+38-(050)-878-93-14; \r\n +38-(044)-227-99-58."));
    }

    public void test19a() {
        Assert.assertEquals("(050) 8789314, (044) 2279958", TelephoneParser.parseTelephone("(050)-878-93-14; \r\n (044)-227-99-58."));
    }

    public void test20() {
        Assert.assertEquals("(093) 8572829, (044) 4123311", TelephoneParser.parseTelephone("0938572829 -\r\n044-4123311"));
    }

    public void test21() {
        Assert.assertEquals("(044) 5372006, (044) 2285252, (067) 4659663", TelephoneParser.parseTelephone("044)5372006, (044)2285252, (067)4659663, Анжелика, АН «САФАРИ»"));
    }

    public void test22() {
        Assert.assertEquals("(063) 8372696", TelephoneParser.parseTelephone("0-63-837-26-96"));
    }    
*/

    public void test23() {
        Assert.assertEquals("(063) 6127877, (050) 4105977", TelephoneParser.parseTelephone("(063)6127877 (050)4105977"));
    }
/*

    public void test24() {
        Assert.assertEquals("(044) 2234060, (050) 3819744", TelephoneParser.parseTelephone("0442234060,0503819744."));
    }
*/

    //todo: correct code: 44 -> 044, 67-> 067, etc
    public void test24a() {
        Assert.assertEquals("(44) 2293757, (67) 4471827", TelephoneParser.parseTelephone("(44) 2293757, (67) 4471827, моб. 63 500 у.е. 2011-11-14 13:37:06"));
    }


    public void test25() {
        Phone[] l = TelephoneParser.parseNormalizedTelephone("(044) 2234060, (050) 3819744").toArray(new Phone[] {});
    	Assert.assertEquals(2, l.length);
    	Assert.assertEquals("044", l[0].getCode());
    	Assert.assertEquals(new Integer(2234060), l[0].getPhoneNumber());
    	Assert.assertEquals("050", l[1].getCode());
    	Assert.assertEquals(new Integer(3819744), l[1].getPhoneNumber());
    }

    public void test26() {
    	Phone[] l = TelephoneParser.parseNormalizedTelephone("(044) 2234060").toArray(new Phone[] {});
    	Assert.assertEquals(1, l.length);
    	Assert.assertEquals("044", l[0].getCode());
    	Assert.assertEquals(new Integer(2234060), l[0].getPhoneNumber());
    }

    public void test30() {
        Assert.assertEquals("(50) 5376631",
                TelephoneParser.parseTelephone2("1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                        "\t\t\t\t190 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void test31() {
        Assert.assertEquals("(97) 2556760, (50) 0704858",
                TelephoneParser.parseTelephone2("\t\t\t\t\tБоголюбова ул., 1-11 этажи, 40-67 кв.м, новые кирпичные дома, круглосуточная охрана, своя инфраструктура, своя обслуживающая организация, возможна рассрочка, ст.м. \"Академгородок\" 13мин, 1000у.е./ кв.м. Тел: (97) 2556760, моб., (50) 0704858, моб.\n" +
                        "\t\t\t\t1 000 у.е.\n" +
                        "\t\t\t\t\tза кв.м.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-04 18:04:16"));
    }
    

    public void test32() {
        Assert.assertEquals("(44) 3620928, (67) 7938837",
                TelephoneParser.parseTelephone2("14/16-эт. дома, 82/42/13 кв.м, х/р, отличное состояние, испанский кафель, дубовые двери и паркет, встроенная мебель, л/з, срочно, 127000у.е., торг. Тел: (44) 3620928, (67) 7938837\n" +
				"127 000 у.е.\n" +
				"\n" + 
				"2011-02-24 15:38:11"));
    }
    
}
