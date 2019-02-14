package cn.jaly.utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private static final SimpleDateFormat SDF_SIMPLE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date parse(String timeStr, SimpleDateFormat format){
        Date res = null;
        if(timeStr != null && !"".equals(timeStr)){
            try{
                res = format.parse(timeStr);
            } catch(Exception ex){
                ex.printStackTrace();
                res = null;
            }
        }
        return res;
    }

    public static Date parseSimple(String timeStr){
        return parse(timeStr, SDF_SIMPLE);
    }

    public static Date parseFull(String timeStr){
        return parse(timeStr, SDF_FULL);
    }

    public static String formatSimple(Date time){
        return SDF_SIMPLE.format(time);
    }

    public static String formatFull(Date time){
        return SDF_FULL.format(time);
    }
}
