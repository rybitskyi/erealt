package biz.rageintegro.importdata.aviso;

import junit.framework.TestCase;
import junit.framework.Assert;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: rybitskyii
 * Date: 20.09.2010
 * Time: 16:57:36
 * To change this template use File | Settings | File Templates.
 */
public class RegexpTest extends TestCase {

    private final String EMAIL_REGEXP = "([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)?";
//    private final String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//    private final String EMAIL_REGEXP = "\\\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\n" + ".[A-Za-z]{2,4}";
    private final String FLAT_AREA = "([0-9]+/[0-9]+/[0-9]+)?";

    public void test1() {  // this will fail
        Assert.assertTrue(Pattern.matches(EMAIL_REGEXP, "balex@vmware.com"));
    }

    public void test2() {  // this will pass
        Assert.assertTrue(Pattern.matches(EMAIL_REGEXP, "balex@vmware.com"));
    }

    public void test2a() {  // this will pass
        Assert.assertTrue(Pattern.matches(EMAIL_REGEXP, ""));
    }

    public void test3() {  // this will pass
        Assert.assertTrue(Pattern.matches("[0-9]+/[0-9]+/[0-9]+", "1/5/7"));
    }

    public void test4() {  // this will pass
        Assert.assertTrue(Pattern.matches("[0-9]+/[0-9]+/[0-9]+", "13/5/7"));
    }

    public void test5() {  // this will pass
        Assert.assertFalse(Pattern.matches("[0-9]+/[0-9]+/[0-9]+", "/5/7"));
    }

    public void test6() {  // this will pass
        Assert.assertFalse(Pattern.matches("[0-9]+/[0-9]+/[0-9]+", "7"));
    }

    public void test7() {  // this will pass
        Assert.assertTrue(Pattern.matches(FLAT_AREA, ""));
    }

    public void test8() {
        Assert.assertEquals(56000, getPrice("Воскресенка, Стальского ул., 6а, 3/9-эт. пан. дома, 32/16/8 кв.м, с/у разд.,\n" +
                "                                            балк. застекл., жилое состояние, окна во двор, свободна, 56000у.е."));
    }

    public void test9() {
        Assert.assertEquals(0, getPrice("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test9a() {
        Assert.assertEquals(70000, getPrice("Заболотного вул., 12, 4/16кер/бет, 38/18.6/8.5, жилий стан, с/в-роздільний, імпортний кахель; розвинена інфраструктура, зручне транспортне сполучення, поруч ТРЦ\"Магелан\", ринок, \"Феофанія\", дитячі садочки, школи, зелена зона відпочинку, 70000у.о., торг. "));
    }

    public void test9b() {
        Assert.assertEquals(70000, getPrice("Заболотного вул., 12, 4/16кер/бет, 38/18.6/8.5, жилий стан, с/в-роздільний, імпортний кахель; розвинена інфраструктура, зручне транспортне сполучення, поруч ТРЦ\"Магелан\", ринок, \"Феофанія\", дитячі садочки, школи, зелена зона відпочинку, 70000у.е, торг. "));
    }

    public void test9c() {
        Assert.assertEquals(70000, getPrice("Заболотного вул., 12, 4/16кер/бет, 38/18.6/8.5, жилий стан, с/в-роздільний, імпортний кахель; розвинена інфраструктура, зручне транспортне сполучення, поруч ТРЦ\"Магелан\", ринок, \"Феофанія\", дитячі садочки, школи, зелена зона відпочинку, 70000у.о, торг. "));
    }

    public void test9d() {
        Assert.assertEquals(6000, getPrice("Автовокзал, Науки просп., 6/9-эт. дома, 40 кв.м, ламинат, евроремонт, студио, есть все. Зелкальный потолок, ванная с гидромассажем. 2 телевизора - в том числе - в ванной., 6000грн/мес.\n" +
                "Тел.: \t(67) 4012536, АН \"Житло-Всесвит\""));
    }

    public void test9e() {
        Assert.assertEquals(4000, getPrice("Автовокзал, Науки просп., новострой, 4/20эт, 51/25/12 кв.м, и 40-летия Октября просп., сталинка, 60 кв.м, обе квартиры после евроремонта, новая мебель, быттехника, в т.ч. стиральная автомат, кондиционер, встроенная кухня, парковка, 4000грн.\n" +
                "Тел.: \t(44) 2285628"));
    }

    public void test10() {
        Assert.assertEquals(new Integer(3), getBuildFloor("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test11() {
        Assert.assertEquals(new Integer(5), getFlatFloor("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test10b() {
        Assert.assertEquals(new Integer(2), getBuildFloor("Ентузіастів вул., 9/2 2/9-пов. кер.-бет. буд., \"чешка\", 31.5/18/6, 2\n" +
                "                                            квартири в одному будинку, жилий стан, еркер, арка на балкон, 10хв - метро,\n" +
                "                                            чистий під\"їзд, затишний двір, БТІ - готово, 60000-59000у.о., торг, або\n" +
                "                                            обміняю на двокімн.кв. з доплатою, в цьому районі. Терміново!"));
    }

    public void test11b() {
        Assert.assertEquals(new Integer(9), getFlatFloor("Ентузіастів вул., 9/2 2/9-пов. кер.-бет. буд., \"чешка\", 31.5/18/6, 2\n" +
                "                                            квартири в одному будинку, жилий стан, еркер, арка на балкон, 10хв - метро,\n" +
                "                                            чистий під\"їзд, затишний двір, БТІ - готово, 60000-59000у.о., торг, або\n" +
                "                                            обміняю на двокімн.кв. з доплатою, в цьому районі. Терміново!"));
    }

    public void test12() {
        Assert.assertEquals("60/29/12", getFlatSquare("Гагарина просп. 10/2, 3/5-эт. кирп. дома, 60/29/12 кв.м, h=3м, евроремонт,\n" +
                "                                            встроенная мебель, бытовая техника, сигнализация, видеодомофон, без\n" +
                "                                            перепланировок, заходи и живи. Супер! ."));
    }

    public void test15() {
        Assert.assertEquals("28.8/14.8/4", getFlatSquare("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28.8/14.8/4 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test16() {
        Assert.assertEquals("28.8/14.8/4.1", getFlatSquare("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28.8/14.8/4.1 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test17() {
        Assert.assertEquals("28/14.8/4.1", getFlatSquare("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28/14.8/4.1 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test18() {
        Assert.assertEquals("28.8/14/4.1", getFlatSquare("Гашека бульв. 10\n" +
                "                                            4/5-эт. дома, 28.8/14/4.1 кв.м, аккуратная, просторная, светлая, уютная,\n" +
                "                                            паркет, кафель, Ленинграадская пл., хорошая траснпортная развязка, 45000у.е."));
    }

    public void test19() {
        Assert.assertEquals(new Integer(6), getPlottage("Автовокзал,    Цимбалов Яр (начало), 6.3 соток, рельефный участок с возможностью увеличения, старый дом, капитальный гараж, централизированная вода, канализация, асфальт, 185000у.е., торг. "));
    }

    public void test20() {
        Assert.assertEquals(new Integer(8), getPlottage("Автовокзал, Уральская ул., дом 80 кв.м, жилой, коммуникации, 8 соток, прямоугольный, фасадный, асфальтированный подъезд, сад, артезианская вода, прекрасное солнечное место, 340000у.е., торг."));
    }

    private int getPrice(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+((у.е(\\.)?)|(у.о(\\.)?)|(грн/мес(\\.)?)|(грн(\\.)?))");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        String result = "0";
        if (matcher.find()) {
            result = matcher.group();
            int ln;
            if (str.contains("грн/мес")) {
                ln = 7;
            } else {
                ln = 3;
            }
            int endIndex = result.endsWith(".") ? result.length() - ln - 1 : result.length() - ln;
            if (endIndex > -1) {
                result = result.substring(0, endIndex);
            }
            else {
                System.out.println("Price Error: str=" + str);
            }
        }
        try {
            return new Integer(result);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            return new Integer(0);
        }
    }

    private Integer getBuildFloor(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+/[\\d]+(-эт.|-пов.)");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String result = matcher.group();
            if (result.endsWith("-эт.")) {
                result = result.substring(0, result.length() - 4);
            } else if (result.endsWith("-пов.")) {
                result = result.substring(0, result.length() - 5);
            }
            String[] arr = result.split("/");
            return new Integer(arr[0]);
        }
        return null;
    }

    private Integer getPlottage(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+(\\.\\d+)?\\sсоток");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String result = matcher.group();
            if (result.endsWith("соток")) {
                result = result.substring(0, result.length() - 6);
            }
            try {
                return new Integer(new Double(result).intValue());
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private Integer getFlatFloor(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+/[\\d]+(-эт.|-пов.)");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String result = matcher.group();
            if (result.endsWith("-эт.")) {
                result = result.substring(0, result.length() - 4);
            } else if (result.endsWith("-пов.")) {
                result = result.substring(0, result.length() - 5);
            }
            String[] arr = result.split("/");
            return new Integer(arr[1]);
        }
        return null;
    }

    private String getFlatSquare(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+(.[\\d])*/[\\d]+(.[\\d])*/[\\d]+(.[\\d])* кв.м");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String result = matcher.group();
            if (result.endsWith("кв.м")) {
                result = result.substring(0, result.length() - 5);
            }
            return result;
        }
        return null;
    }
}
