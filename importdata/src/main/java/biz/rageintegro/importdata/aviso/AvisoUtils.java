package biz.rageintegro.importdata.aviso;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rybitskyii
 */
public class AvisoUtils {
    private final static Logger logger = Logger.getLogger(AvisoUtils.class.getName());

    private static Properties PROPERTIES = new Properties();

    static {
        try {
            InputStream in = AvisoUtils.class.getResourceAsStream("/utils.properties");
            if (in == null) {
                throw new NullPointerException("Resource utils.properties can't be found on classpath");
            }
            PROPERTIES.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Can't load utils.properties");
        }
    }

    private AvisoUtils() {
    }

    public static Info getStreetInfo(String str) {
        String regexp = (String) PROPERTIES.get("street.info.regexp");
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regexp).matcher(str);
        if (matcher.find()) {
            //street Name
            String[] arr = str.substring(0, matcher.end()).split(",");
            String s1 = arr[arr.length - 1];
            Info result = new Info();
            result.setStart(str.indexOf(s1));
            result.setEnd(matcher.end());
            return result;
        }
        return null;
    }

    public static String getStreet(String str) {
        return getStreet(str, 2);
    }

    public static String getStreet(String str, long district) {
        if (str == null) {
            return null;
        }
        Info info = getStreetInfo(str);
        if (info != null) {
            String s1 = str.substring(info.getStart(), info.getEnd());
            s1 = s1.replaceAll(" " + PROPERTIES.get("str") + "\\.", "").replaceAll(" " + PROPERTIES.get("prosp") + "\\.",
                    " " + PROPERTIES.get("pr") + ".").trim();
            String result = removeNumberFromStreet(s1);
            if (logger.isLoggable(Level.FINE)) {
                logger.fine("input=" + str + " result = " + result);
            }
            return result;
        } else {
            return null;
        }
    }

    public static String getStreetObjectNo(String str, String streetName) {
        if (str == null || streetName == null) {
            return null;
        }
        String[] arr = str.split("[ .,?!]+");
        String regexp = "\\d+([а-яА-Я])?";
        String regexpReservedWords = "ул|просп|бульв|наб|пер|пл|шос|дор|спуск|проезд|тупик|линия|майдан|аллея";
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (streetName.equals(s) && i + 1 < arr.length) {
                String str2 = arr[i + 1];
                if (Pattern.compile(regexpReservedWords).matcher(str2).find() && i + 2 < arr.length) {
                    str2 = arr[i + 2];
                }
                Matcher matcher = Pattern.compile(regexp).matcher(str2);
                if (matcher.find()) {
                    return str2;
                }
            }
        }
        return null;
    }

    private static String removeNumberFromStreet(String str) {
        String[] arr = str.split("[ .,?!]+");
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            String regexp = "\\d+";
            Matcher matcher = Pattern.compile(regexp).matcher(s);
            if (matcher.find()) {
                if (s.length() <= matcher.end() || s.charAt(matcher.end()) != '-') {
                    continue;
                }
            }
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(s);
        }
        return sb.toString();
    }

    private static Info getBuildNoInfo(String str) {
        String regexp = (String) PROPERTIES.get("buildNo.info.regexp");
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regexp).matcher(str);
        if (matcher.find()) {
            String buildNoRaw = str.substring(matcher.end());
            String str2 = buildNoRaw;
            String[] arr = str2.split(",");
            if (arr.length > 0) {
                str2 = arr[0];
            }
            str2 = str2.trim();
            java.util.regex.Matcher matcher2 = java.util.regex.Pattern.compile("^\\d{1,3}(([" + PROPERTIES.get("a.ya.regexp") + "a-zA-Z]{1})|(/\\d)*)(\\s|\n|,|$)").matcher(str2);
            if (matcher2.find()) {
                String s = matcher2.group();
                if (s.charAt(s.length() - 1) == '\n') {
                    s = s.substring(0, s.length() - 1);
                }

                String post = str2.indexOf(s) > -1 ? str2.substring(str2.indexOf(s) + s.length()) : "";
                post = post.trim();
                //kv.m=кв.м sot=сот doma=дома
                if (post.startsWith((String) PROPERTIES.get("kv.m")) || post.startsWith((String) PROPERTIES.get("sot")) ||
                        post.startsWith((String) PROPERTIES.get("doma"))) {
                    return null;
                }
                Info result = new Info();
                result.setStart(matcher.end() + result.getStart() + buildNoRaw.indexOf(s));
                result.setEnd(matcher.end() + result.getEnd() + buildNoRaw.indexOf(s) + s.length());
                return result;
            }
        }
        return null;
    }

    public static String getBuildNo(String str) {
        Info info = getBuildNoInfo(str);
        return info != null ? str.substring(info.getStart(), info.getEnd()) : null;
    }

    public static String getDescription(String str) {
        Info info = getBuildNoInfo(str);
        String result = str;
        if (info != null) {
            result = postProcessDescription(result, str, info);
        } else {
            info = getStreetInfo(str);
            result = postProcessDescription(result, str, info);
        }
        return result;
    }

    private static String postProcessDescription(String result, String str, Info info) {
        if (info != null) {
            result = str.substring(info.getEnd());
            if (result != null && result.length() > 0) {
                if (result.charAt(0) == ',') {
                    return result.substring(1).trim();
                }
            }
        }
        return result;
    }

    private static Map<String, String> streetSynonyms = new HashMap<String, String>();

    static {
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.1.key"), (String) PROPERTIES.get("streetSynonym.1.value"));
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.2.key"), (String) PROPERTIES.get("streetSynonym.2.value"));
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.3.key"), (String) PROPERTIES.get("streetSynonym.3.value"));
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.4.key"), (String) PROPERTIES.get("streetSynonym.4.value"));
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.5.key"), (String) PROPERTIES.get("streetSynonym.5.value"));
        streetSynonyms.put((String) PROPERTIES.get("streetSynonym.6.key"), (String) PROPERTIES.get("streetSynonym.6.value"));
    }

    public static class Info {
        private int start;
        private int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static int getPrice(String str) {
        Pattern pattern = Pattern.compile(PROPERTIES.getProperty("price.regexp"));
        Matcher matcher = pattern.matcher(str);
        String result = "0";
        if (matcher.find()) {
            result = matcher.group();
            result = result.replaceAll(PROPERTIES.getProperty("tis.regexp"), "000");
        }
        try {
            result = result.replaceAll(PROPERTIES.getProperty("price.regexp2"), "");
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return (int) 0;
        }
    }

    public static int getPriceNormal(String str) {
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        try {
            if (sb.length() > 0) {
                return Integer.parseInt(sb.toString());
            } else {
                return 0;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getClientName(String str) {
        if (str == null) {
            return null;
        }
        String[] arr = str.split(",");
        if (arr.length < 1) {
            return null;
        }
        String result = arr[arr.length - 1];
        if (result.contains(PROPERTIES.getProperty("mob") + ".")) {
            return null;
        }
        Matcher matcher = Pattern.compile("\\d+").matcher(result);
        if (matcher.find()) {
            return null;
        } else {
            result = result.replaceAll("\"", " ").trim();
            if ("".equals(result)) {
                result = null;
            }
            if (result != null && result.contains(PROPERTIES.getProperty("AN"))) {
                return result;
            } else {
                return new ClientNameParser().parseClientName(result);
            }
        }
    }

    private static Integer getBuildFlatFloor(String value, int index) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[\\d]+/[\\d]+");
        java.util.regex.Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String[] arr = matcher.group().split("/");
            if (value.charAt(matcher.end()) == '/') {
                return null;
            }
            try {
                return new Integer(arr[index]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }

    public static Integer getBuildFloor(String value) {
        return getBuildFlatFloor(value, 1);
    }

    public static Integer getFlatFloor(String value) {
        return getBuildFlatFloor(value, 0);
    }

    public static String getFlatArea(String value) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(PROPERTIES.getProperty("flatArea.regexp"));
        java.util.regex.Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String result = matcher.group();
            if (result.endsWith(PROPERTIES.getProperty("kv.m"))) {
                result = result.substring(0, result.length() - 5);
            }
            return result;
        }
        return null;
    }

    @Deprecated
    public static String getTelephone(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("\\(\\d+\\) \\d+");

        String[] arr = str.split(",");
        for (String s : arr) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }
}
