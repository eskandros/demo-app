package au.com.domain.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mseskander .
 */
public class Utils {

    public static Date getFormatedDate(String dateString) {
        Date date = null;
        if (dateString != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //"yyyyMMdd"
            try {
                date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateString);
            } catch (Exception ex) {
                // TODO: throw exception
            }
        }
        return date;
    }
}