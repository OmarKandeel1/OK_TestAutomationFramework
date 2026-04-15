package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager {


   //gives date + time, useful for file names like screenshots or reports.
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }

    //gives only time, useful for quick IDs.
    public static String getSimpleTimeStamp() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }
}