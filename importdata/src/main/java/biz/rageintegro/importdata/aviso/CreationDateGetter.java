package biz.rageintegro.importdata.aviso;

import org.apache.commons.lang.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: rybitskyii
 */
public class CreationDateGetter {
    private static int FIRST_AVISO_NO = -2147467356;
    private static Date FIRST_AVISO_DATE;

    static {
        DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
        try {
            FIRST_AVISO_DATE = dfm.parse("07-01-2011");  //Must be Friday!!
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    static Date getDate(int avisoNo, int i) {
        int diff = FIRST_AVISO_NO - avisoNo;
        diff = Math.abs(diff);
        if (diff > 0) {
            diff = (diff / 2) * 7;
            if (avisoNo % 2 != 0) {
                diff = diff + 4;
                diff = diff + (i % 3);
            } else {
                diff = diff + (i % 4);
            }
        }
        Date result = new Date(FIRST_AVISO_DATE.getTime());
        return DateUtils.addDays(result, diff + 1);
    }

    public static Timestamp getCreationDate(int avisoNo, int i) {
        return new Timestamp(getDate(avisoNo, i).getTime());
    }

    public static Timestamp getCreationDate(int avisoNo, int i, int j) {
        Timestamp t = new Timestamp(getDate(avisoNo, i).getTime());
		if (j > 0 && j < 144) {
			long milliseconds = t.getTime() + (t.getNanos() / 1000000);
            t = new Timestamp(DateUtils.addMinutes(new Date(milliseconds), j * 10).getTime()); 
		}
		return t;
    }

    public static int getCurrentAvisoNo() {
        return getAvisoNo(new Date());
    }

    public static int getAvisoNo(Date date) {
        long milliseconds1 = FIRST_AVISO_DATE.getTime();
        long milliseconds2 = date.getTime();
        long diff = milliseconds2 - milliseconds1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long i = (diffDays / 7) * 2;
        int i2 = (int) (diffDays % 7);
        if (i2 == 0) {
            i = i - 1;
        }
        else if (i2 > 0) {
            if (i2 > 4) {
                i = i + 1;
            }
        }        
        return FIRST_AVISO_NO + (int) i;
    }
}