package ypx.com.androidbend.utils;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by S01 on 2017/5/6.
 */

public class TimeUtils {
    public static String getTime() {
        String time = String.valueOf(new Date().getTime());

        return time.substring(0, time.length() - 3);
    }


    public static String getTimekehu() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String ee = dff.format(new Date());
        return ee;
    }
}
