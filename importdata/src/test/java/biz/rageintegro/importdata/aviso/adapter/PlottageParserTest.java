package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.parser.PlottageParser;
import junit.framework.TestCase;

/**
 * S&T Ukraine (www.snt.ua)
 * User: RybitskyiI
 * Date: 16/6/2011
 */
public class PlottageParserTest extends TestCase {
    private PlottageParser parser = new PlottageParser();

    public void test1() {
        float plottage = (float) 4.0;
        assertEquals(plottage, parser.parsePlottage(
                "дом 60 кв.м, участок 4 сотки, все коммуникации, лес, транспортная развязка, 102000у.е., торг. Тел: (96) 4387487, моб., (63) 7194480, моб., Виктория, (44) 3600458"));
    }

    public void test2() {
        float plottage = 6;
        assertEquals(plottage, parser.parsePlottage(
                "Теремки-1, 6 соток, акт, дом 7х8, все коммуникации городские, сад, 135000у.е. Тел: (50) 6824226, моб."));
    }

    public void test3() {
        float plottage = 6;
        assertEquals(plottage, parser.parsePlottage(
                "ст.м. \"Голосеевская\", 1/2 дома, 73 кв.м, 3 комнаты, кухня, с/у совм., тел., все коммун., гараж, погреб, уч. 6 сот., приватиз., 155000у.е., торг. Тел: (67) 7630707, моб., Марина Сергеевна"));
    }

    public void test4() {
        float plottage = (float) 12.6;
        assertEquals(plottage, parser.parsePlottage(
                "усадьба 12.6 сот., 2-эт. кирпичный дом, 140 кв.м, 2 кухни, 2 с/у, все коммуникации, гараж, сад, рядом лес, озера, живописное место, 550000у.е., торг. Тел: (63) 6089364, моб., (66) 1718223, моб."));
    }

    public void test5() {
        float plottage = (float) 18.5;
        assertEquals(plottage, parser.parsePlottage(
                "дом кирпичный, 150 кв.м, 3 уровня, 5 комнат, все городские коммуникации, видовой участок 18.5 соток, сад, цена договорная. Тел: (67) 2939145, моб., (44) 3534748"));
    }

    public void test6() {
        float plottage = 12;
        assertEquals(plottage, parser.parsePlottage(
                "коттедж, продажа двух коттеджей 450кв.м на участке 11соток и 650кв.м - 12 соток, разная степень готовности, коммуникации рядом, 8км - \"Дворец Украина\". Тел: (44) 2276696, (67) 4662594"));
    }

    public void test7() {
        float plottage = 10;
        assertEquals(plottage, parser.parsePlottage(
                "Чапаевка, Бродовская ул., дом 300 кв.м, 80% готовности, 3 этажа, 9 комнат, каминный зал, кабинет, 2 с/у, хозблок, электричество в доме, газ и канализация 5м от дома, участок 10 соток, у озера, цена договорная. Тел: (67) 5364171, моб."));
    }

    public void test8() {
        float plottage = 10;
        assertEquals(plottage, parser.parsePlottage(
                "Чапаевка, Бродовская ул., дом 300 кв.м, 80% готовности, 3 этажа, 9 комнат, каминный зал, кабинет, 2 с/у, хозблок, электричество в доме, газ и канализация 5м от дома, участок 10+10 соток, у озера, цена договорная. Тел: (67) 5364171, моб."));
    }    
}
