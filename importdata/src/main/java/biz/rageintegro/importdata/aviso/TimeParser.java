package biz.rageintegro.importdata.aviso;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * www.rageintegro.biz
 * User: RybitskyiI
 * Date: 13/4/2011
 */
public class TimeParser {
    private final static Logger logger = Logger.getLogger(TimeParser.class.getName());

    public static String parseTimeAsString(String value) {
        if (value == null) {
            return null;
        }

        //2011-04-11 18:22:54
        Pattern pattern = Pattern.compile("\\b[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\b");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static Date parseTime(String value) {
        value = parseTimeAsString(value);
        if (value == null) {
            return null;
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Can't parse " + value, e);
            return null;
        }
    }
}
