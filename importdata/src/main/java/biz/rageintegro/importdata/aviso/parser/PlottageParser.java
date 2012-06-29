package biz.rageintegro.importdata.aviso.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * User: RybitskyiI
 * Date: 16/6/2011
 */
@Component
public class PlottageParser {

    static String FLOAT_REGEXP = "\\d+\\.?\\d*";

    public Float parsePlottage(String value) {
        Float result = parse(value, "участок " + FLOAT_REGEXP + " сотки");
        if (result == null) {
            result = parse(value, "участок " + FLOAT_REGEXP + " соток");
        }
        if (result == null) {
            result = parse(value, "уч. " + FLOAT_REGEXP + " сот.");
        }
        if (result == null) {
            result = parse(value, FLOAT_REGEXP + " соток");
        }
        if (result == null) {
            result = parse(value, FLOAT_REGEXP + " сот.");
        }
        return result;
    }

    private Float parse(String value, String regexp) {
        Matcher matcher = Pattern.compile(regexp).matcher(value);
        if (matcher.find()) {
            Matcher matcher2 = Pattern.compile(FLOAT_REGEXP).matcher(matcher.group());
            matcher2.find();
            return Float.parseFloat(matcher2.group());
        }
        return null;
    }

    public Integer parseIntPlottage(String value) {
        Float result = parsePlottage(value);
        return result != null ? (int) result.longValue() : null;
    }
}
