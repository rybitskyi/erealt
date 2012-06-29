package biz.rageintegro.importdata.aviso;

import biz.rageintegro.erealt.domain.stub.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * User: rybitskyii
 */
public class TelephoneParser {
    private void TelephoneParser() {
    }

    public static String parseTelephone(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll(" +", " ").trim();
        value = value.replaceAll("-\r\n", ",").trim();
        //0503532334 3317483
        Matcher matcher = Pattern.compile("\\d{10} \\d{7}").matcher(value);
        if (matcher.find()) {
            int i = matcher.group().indexOf(' ');
            return "(" + matcher.group().substring(0, 3) + ") " +
                    matcher.group().substring(3, i) + ", (044) " + matcher.group().substring(i + 1);
        }
        //(063)6127877 (050)4105977
        matcher = Pattern.compile("\\(\\d{3}\\)\\d{7} \\(\\d{3}\\)\\d{7}").matcher(value);
        if (matcher.find()) {
            return matcher.group().replaceAll(" ", ", ").replaceAll("\\)", ") ");
        }
        StringBuffer sb = new StringBuffer();
        String[] arr = value.trim().split("[,;]");
        for (String str : arr) {
            String item = parseTelephoneItem(str);
            if (item != null) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(item);
            }
        }
        return sb.toString();
    }

    public static String TEL = "Тел:";
    public static String REG_EXP = "\\(\\d{2,5}\\) \\d{5,7}";

    public static String getAfterTelValue(String value) {
        if (value == null) {
            return null;
        }
        int i = value.indexOf(TEL);
        if (i < 0) {
            return null;
        } else {
            return value.substring(i + TEL.length());
        }
    }

    public static String parseTelephone2(String value) {
        if (value == null) {
            return null;
        }
        value = getAfterTelValue(value);
        if (value == null) {
            return null;
        }		
        StringBuffer sb = new StringBuffer();
        //Тел: (97) 2556760, моб., (50) 0704858, моб.
        Matcher matcher = Pattern.compile(REG_EXP).matcher(value);
        while (matcher.find()) {
            String str = matcher.group();
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }

        return sb.toString();
    }

    private static String parseTelephoneItem(String value) {
        value = value.replaceAll("\\(\\)", "");
        value = value.replaceAll(" ", "");
        value = value.trim();
//(067)5001064
        Matcher matcher = Pattern.compile("\\(\\d{1,5}\\)\\d{7}").matcher(value);
        if (matcher.find()) {
            int i = matcher.group().indexOf(')');
            return matcher.group().substring(0, i + 1) + " " + matcher.group().substring(i + 1);
        }
/*
        //(050)-878-93-14
        matcher = Pattern.compile("\\(\\d{1,5}\\)-\\d{1,6}-\\d{1,6}-\\d{1,6}").matcher(value);
        if (matcher.find()) {
            int i = matcher.group().indexOf(')');
            return matcher.group().substring(0, i + 1) + " " + matcher.group().substring(i + 1).replaceAll("-", "");
        }
        //(067)500-10-64, (099)46-0000-2
        matcher = Pattern.compile("\\(\\d{1,5}\\)\\d{1,6}-\\d{1,6}-\\d{1,6}").matcher(value);
        if (matcher.find()) {
            int i = matcher.group().indexOf(')');
            return matcher.group().substring(0, i + 1) + " " + matcher.group().substring(i + 1).replaceAll("-", "");
        }
        //044)5372006
        matcher = Pattern.compile("^\\d{1,5}\\)\\d{7}").matcher(value);
        if (matcher.find()) {
            int i = matcher.group().indexOf(')');
            return "(" + matcher.group().substring(0, i + 1) + " " + matcher.group().substring(i + 1).replaceAll("-", "");
        }
        //0-63-837-26-96
        matcher = Pattern.compile("^\\d-\\d{2}-\\d{1,5}-\\d{1,5}-\\d{1,5}").matcher(value);
        if (matcher.find()) {
            String arr2[] = matcher.group().split("-");
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (String str : arr2) {
                if (sb.length() == 0) {
                    sb.append("(" + str);
                } else if (i == 1) {
                    sb.append(str).append(") ");
                } else {
                    sb.append(str);
                }
                i++;
            }
            return sb.toString();
        }
        //067-500-10-64
        matcher = Pattern.compile("\\d{1,5}-\\d{1,5}-\\d{1,5}-\\d{1,5}").matcher(value);
        if (matcher.find()) {
            String arr2[] = matcher.group().split("-");
            StringBuffer sb = new StringBuffer();
            for (String str : arr2) {
                if (sb.length() == 0) {
                    sb.append("(" + str + ") ");
                } else {
                    sb.append(str);
                }
            }
            return sb.toString();
        }
        //099-300-0070
        matcher = Pattern.compile("\\d{1,5}-\\d{1,6}-\\d{1,6}").matcher(value);
        if (matcher.find()) {
            String arr2[] = matcher.group().split("-");
            StringBuffer sb = new StringBuffer();
            for (String str : arr2) {
                if (sb.length() == 0) {
                    sb.append("(" + str + ") ");
                } else {
                    sb.append(str);
                }
            }
            return sb.toString();
        }
        //067-5004756
        matcher = Pattern.compile("\\d{1,5}-\\d{7}").matcher(value);
        if (matcher.find()) {
            String arr2[] = matcher.group().split("-");
            StringBuffer sb = new StringBuffer();
            for (String str : arr2) {
                if (sb.length() == 0) {
                    sb.append("(" + str + ") ");
                } else {
                    sb.append(str);
                }
            }
            return sb.toString();
        }
        //0976742206
        if (value.length() >= 10) {
            matcher = Pattern.compile("\\d{10}").matcher(value);
            if (matcher.find()) {
                return "(" + matcher.group().substring(0, 3) + ") " + matcher.group().substring(3);
            }
        }
*/
        //6742206
        if (value.length() >= 7) {
            matcher = Pattern.compile("\\d{7}").matcher(value);
            if (matcher.find()) {
                return "(044) " + matcher.group();
            }
        }
/*
        //494-0999
        if (value.length() == 8) {
            matcher = Pattern.compile("\\d{1,6}-\\d{1,6}").matcher(value);
            if (matcher.find()) {
                return "(044) " + matcher.group().replaceFirst("-", "");
            }
        }
*/
        return null;
    }

    public static List<Phone> parseNormalizedTelephone(String value) {
        List<Phone> result = new ArrayList<Phone>();
        Matcher matcher = Pattern.compile("\\(\\d+\\) \\d+").matcher(value);
        while (matcher.find()) {
            int i = matcher.group().indexOf(')');
            String code = matcher.group().substring(1, i);
            int number = Integer.parseInt(matcher.group().substring(i + 2));
            Phone phone = new Phone();
            phone.setCode(code);
            phone.setPhoneNumber(number);
            result.add(phone);
        }
        return result;
    }
}
