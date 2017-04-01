package hello.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Oriol on 01/04/2017.
 */
public class DateUtils {
    private DateUtils() {}

    public static Date addDays(Date baseDate, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return calendar.getTime();
    }
}
