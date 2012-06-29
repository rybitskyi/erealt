package biz.rageintegro.importdata.aviso;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AvisoUtilsTest extends TestCase {

    public void test1() {
        Assert.assertEquals("Стальского", AvisoUtils.getStreet("Воскресенка, Стальского ул., 6а, 3/9-эт. пан. дома, 32/16/8 кв.м, с/у разд.,\n" +
                "                                            балк. застекл., жилое состояние, окна во двор, свободна, 56000у.е."));
    }

    public void test1a() {
        Assert.assertEquals("6а", AvisoUtils.getBuildNo("Воскресенка, Стальского ул., 6а, 3/9-эт. пан. дома, 32/16/8 кв.м, с/у разд.,\n" +
                "                                            балк. застекл., жилое состояние, окна во двор, свободна, 56000у.е."));
    }

    public void test1b() {
        Assert.assertEquals("3/9-эт. пан. дома, 32/16/8 кв.м, с/у разд.",
                AvisoUtils.getDescription("Воскресенка, Стальского ул., 6а, 3/9-эт. пан. дома, 32/16/8 кв.м, с/у разд."));
    }

    public void test2() {
        Assert.assertEquals("Гагарина пр.", AvisoUtils.getStreet("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test2a() {
        Assert.assertEquals("10/2", AvisoUtils.getBuildNo("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test3() {
        Assert.assertEquals("Гагарина пр.", AvisoUtils.getStreet("Гагарина просп., 3а, 5/5 кирпич, h=2.75м, свободна, 32/18/7, хорошее\n" +
                "                                            состояние, паркет, стеклопакеты, договорная. Срочно."));
    }

    public void test3a() {
        Assert.assertEquals("3а", AvisoUtils.getBuildNo("Гагарина просп., 3а, 5/5 кирпич, h=2.75м, свободна, 32/18/7, хорошее\n" +
                "                                            состояние, паркет, стеклопакеты, договорная. Срочно."));
    }

    public void test4() {
        Assert.assertEquals("Гашека бульв.", AvisoUtils.getStreet("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28.8/14.8/4 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test4a() {
        Assert.assertEquals("10", AvisoUtils.getBuildNo("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28.8/14.8/4 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test4b() {
        Assert.assertEquals(null, AvisoUtils.getBuildNo("Оболонь, Богатырская ул., 10мин до ст.м. \"Героев Днепра\", дом двухэтажный, 290 кв.м, электричество, стеклопакеты, вода, газ, под отделку, асфальт, участок 10 соток, озеро 2мин, 165000у.е., торг."));
    }

    public void test4c() {
        Assert.assertEquals(null, AvisoUtils.getBuildNo("Голосеево, Крымская ул., трехэтажный VIP-коттедж 2005г., 620/360 кв.м, кирпичный, гараж, фонтан, домик охраны, участок 5 соток, приватиз., возможность выкупа соседнего участка, в рассрочку, хорошее ограждение, тихо, цена договорная."));
    }

    public void test4d() {
        Assert.assertEquals(null, AvisoUtils.getBuildNo("Караваевы Дачи, Зеленая ул., 1/2 дома, 68 кв.м, 3 комнаты, все коммуникации, участок 3 соток, сарай, тихое место, асфальтированный подъезд, приватиз., 72000у.е., торг."));
    }

    public void test4e() {
        Assert.assertEquals(null, AvisoUtils.getBuildNo("Батыева Гора, Громадская ул., 350 кв.м, 100% готовности, дизайнерский ремонт 2010 года, мебель, бытовая техника, 9 соток, ландшафтный дизайн, вид на Киев, документы готовы, 1200000у.е."));
    }

    public void test4f() {
        Assert.assertEquals("350 кв.м, 100% готовности, дизайнерский ремонт 2010 года, мебель, бытовая техника, 9 соток, ландшафтный дизайн, вид на Киев, документы готовы, 1200000у.е.",
                AvisoUtils.getDescription("Батыева Гора, Громадская ул., 350 кв.м, 100% готовности, дизайнерский ремонт 2010 года, мебель, бытовая техника, 9 соток, ландшафтный дизайн, вид на Киев, документы готовы, 1200000у.е."));
    }

    public void test5() {
        Assert.assertEquals("Двинская", AvisoUtils.getStreet("Двинская ул. 1\n" +
                "                                            8/9-эт. кирп. дома, 21/12/5 кв.м, аккуратная, аккуратная, бронедвери,\n" +
                "                                            домофон, кирпичный дом, документы готовы, \"чистая\" продажа, 38000у.е."));
    }

    public void test5_0() {
        Assert.assertEquals("1", AvisoUtils.getBuildNo("Двинская ул. 1\n" +
                "                                            8/9-эт. кирп. дома, 21/12/5 кв.м, аккуратная, аккуратная, бронедвери,\n" +
                "                                            домофон, кирпичный дом, документы готовы, \"чистая\" продажа, 38000у.е."));
    }

    public void test5a() {
        Assert.assertEquals("8/9-эт. кирп. дома, 21/12/5 кв.м, аккуратная, аккуратная, бронедвери",
                AvisoUtils.getDescription("Двинская ул. 1 " +
                        "8/9-эт. кирп. дома, 21/12/5 кв.м, аккуратная, аккуратная, бронедвери"));
    }

    public void test6() {
        Assert.assertEquals("Уральская", AvisoUtils.getStreet("Автовокзал, Уральская ул., дом 80 кв.м, жилой, коммуникации, 8 соток, прямоугольный, фасадный, асфальтированный подъезд, сад, артезианская вода, прекрасное солнечное место, 340000у.е., торг. "));
    }

    public void test6_0() {
        Assert.assertEquals(null, AvisoUtils.getBuildNo("Автовокзал, Уральская ул., дом 80 кв.м, жилой, коммуникации, 8 соток, прямоугольный, фасадный, асфальтированный подъезд, сад, артезианская вода, прекрасное солнечное место, 340000у.е., торг. "));
    }

    public void test6a() {
        Assert.assertEquals("дом 80 кв.м, жилой, коммуникации, 8 соток, прямоугольный, фасадный, асфальтированный подъезд, сад, артезианская вода, прекрасное солнечное место, 340000у.е., торг.",
                AvisoUtils.getDescription("Автовокзал, Уральская ул., дом 80 кв.м, жилой, коммуникации, 8 соток, прямоугольный, фасадный, асфальтированный подъезд, сад, артезианская вода, прекрасное солнечное место, 340000у.е., торг. "));
    }

/*todo:
    public void testClientName1() {
        Assert.assertEquals("Рідний дім", AvisoUtils.getClientName("(44) 3620981, (44) 2464146, \"Рідний дім\""));
    }
*/

    public void testClientName2() {
        Assert.assertNull(AvisoUtils.getClientName("(44) 3620981, (44) 2464146 \"Рідний дім\""));
    }

/*todo:
    public void testClientName3() {
        Assert.assertEquals("Денис", AvisoUtils.getClientName("(67) 4478121, моб., Денис"));
    }
*/

    public void testClientName4() {
        Assert.assertNull(AvisoUtils.getClientName("(67) 4478121, моб."));
    }

    public void testClientName5() {
        Assert.assertNull(AvisoUtils.getClientName("(67) 4478121, моб.,"));
    }

    public void testClientName5a() {
        Assert.assertNull(AvisoUtils.getClientName("(67) 4478121, 555,"));
    }

    public void testClientName5b() {
        Assert.assertNull(AvisoUtils.getClientName("(67) 4478121, ,"));
    }

    public void testClientName6() {
        Assert.assertEquals("АН Золотые Ворота", AvisoUtils.getClientName("(44) 2008150, (44) 3602601, АН\"Золотые Ворота\""));
    }

    public void testFlatArea1() {
        Assert.assertEquals("97/46/12",
                AvisoUtils.getFlatArea("\t\t\t\t\t1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                        "\t\t\t\t190 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testFlatArea2() {
        Assert.assertEquals("31/17.4/5.2",
                AvisoUtils.getFlatArea("\t\t\t\t\t4/9-эт. кирп. дома, 31/17.4/5.2, с/у совм., балк. застекл., паркет, бронедверь, жилое состояние, без опекунского, инфраструктура, \"чистая\" продажа, метро 5мин, 60000у.е. Тел: (44) 3316273, (67) 5085249, моб.\n" +
                        "\t\t\t\t60 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testFlatArea3() {
        Assert.assertEquals("40/20/10",
                AvisoUtils.getFlatArea("- 13мин, Софиевская Борщаговка, Боголюбова ул., 4, 40/20/10, новые кирпичный дома, продажа от застройщика, 1000у.е./ кв.м, рассрочка до 2 лет. Тел: (97) 2556760, моб., (50) 0704858, моб.\n" +
                        "\t\t\t\t1 000 у.е.\n" +
                        "\t\t\t\t\tза кв.м.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-03-03 16:51:19"));
    }

    public void testBuildFloor1() {
        Assert.assertEquals(new Integer(18),
                AvisoUtils.getBuildFloor("\t\t\t\t\t1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                        "\t\t\t\t190 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testBuildFloor2() {
        Assert.assertEquals(new Integer(9),
                AvisoUtils.getBuildFloor("\t\t\t\t\t4/9-эт. кирп. дома, 31/17.4/5.2, с/у совм., балк. застекл., паркет, бронедверь, жилое состояние, без опекунского, инфраструктура, \"чистая\" продажа, метро 5мин, 60000у.е. Тел: (44) 3316273, (67) 5085249, моб.\n" +
                        "\t\t\t\t60 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testBuildFloor3() {
        Assert.assertNull(AvisoUtils.getBuildFloor("\t\t\t\t\t- 13мин, Софиевская Борщаговка, Боголюбова ул., 4, 40/20/10, новые кирпичный дома, продажа от застройщика, 1000у.е./ кв.м, рассрочка до 2 лет. Тел: (97) 2556760, моб., (50) 0704858, моб.\n" +
                "\t\t\t\t1 000 у.е.\n" +
                "\t\t\t\t\tза кв.м.\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t2011-03-03 16:51:19"));
    }

    public void testFlatFloor1() {
        Assert.assertEquals(new Integer(1),
                AvisoUtils.getFlatFloor("\t\t\t\t\t1/18, 97/46/12, АППС-люкс (2007г.), нормальное состояние, ст/пак, 2 л/з. Паркинг, школа, детский сад, м.Минская-2мин. Без комиссии. 190000у.е. Тел: (50) 5376631, моб.\n" +
                        "\t\t\t\t190 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testFlatFloor2() {
        Assert.assertEquals(new Integer(4),
                AvisoUtils.getFlatFloor("\t\t\t\t\t4/9-эт. кирп. дома, 31/17.4/5.2, с/у совм., балк. застекл., паркет, бронедверь, жилое состояние, без опекунского, инфраструктура, \"чистая\" продажа, метро 5мин, 60000у.е. Тел: (44) 3316273, (67) 5085249, моб.\n" +
                        "\t\t\t\t60 000 у.е.\n" +
                        "\t\t\t\t\n" +
                        "\t\t\t\t2011-04-11 18:22:54"));
    }

    public void testFlatFloor3() {
        Assert.assertNull(AvisoUtils.getFlatFloor("\t\t\t\t\t- 13мин, Софиевская Борщаговка, Боголюбова ул., 4, 40/20/10, новые кирпичный дома, продажа от застройщика, 1000у.е./ кв.м, рассрочка до 2 лет. Тел: (97) 2556760, моб., (50) 0704858, моб.\n" +
                "\t\t\t\t1 000 у.е.\n" +
                "\t\t\t\t\tза кв.м.\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t2011-03-03 16:51:19"));
    }

    public void testStreet1() {
        Assert.assertEquals("Бальзака", AvisoUtils.getStreet("Бальзака 54, "));
    }

    public void testStreet2() {
        Assert.assertEquals("2-я линия", AvisoUtils.getStreet("2-я линия"));
    }

    public void testStreetObjectNo1() {
        Assert.assertEquals("24", AvisoUtils.getStreetObjectNo("Продам  квартиру, 76000.00 грн. Расположение: Киев, Голосеевский,  ул. Григоренко. Продам 1-к.квартиру, пр-т Григоренко, 24, 7/16эт., кирпичный спецпроект 2004г.п., 46/18/8,7, коридор 9кв.м, строительный ремонт, с/у разд., балкон, лоджия застеклена, консьерж, рядом метро, супермаркеты, садики, школы, парк, первичная чистая продажа, срочно, 76000у.е., 0994568421, 0635782609 Татьяна\n" +
                "                 Тел: (99) 4568421", "Григоренко"));
    }

    public void testStreetObjectNo2() {
        Assert.assertEquals("3", AvisoUtils.getStreetObjectNo("Драгомирова ул., 3, ", "Драгомирова"));
    }

    public void testStreetObjectNo3() {
        Assert.assertEquals("8", AvisoUtils.getStreetObjectNo("Лихачева бульв., 8, 5/5,", "Лихачева"));
    }

    public void testStreetObjectNo4() {
        Assert.assertEquals("10", AvisoUtils.getStreetObjectNo("Печерский спуск 10, ", "Печерский"));
    }
}
