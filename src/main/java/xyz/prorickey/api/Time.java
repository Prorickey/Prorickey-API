package xyz.prorickey.api;

import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class Time {

    public static long getUnix() {
        return Instant.now().getEpochSecond();
    }

    public static String getBetterTimeFromLongs(long largerNumber, long smallerNumber, boolean aFewSeconds) {

        String time = "null";
        Map<TimeUnit, Long> timeSince = getDateDifference(new Date(smallerNumber), new Date(largerNumber));

        if(Math.round(timeSince.get(TimeUnit.DAYS)) / 365 >= 1) {
            time = Math.round(timeSince.get(TimeUnit.DAYS)/365) + ((Math.round(timeSince.get(TimeUnit.DAYS)) / 365 == 1) ? " year" : " years");
        }else if(Math.round(timeSince.get(TimeUnit.DAYS)) / 31 >= 1) {
            time = Math.round(timeSince.get(TimeUnit.DAYS)/31) + ((Math.round(timeSince.get(TimeUnit.DAYS)) / 31 == 1) ? " month" : " months");
        }else if(Math.round(timeSince.get(TimeUnit.DAYS)) / 7 >= 1) {
            time = Math.round(timeSince.get(TimeUnit.DAYS)/7) + ((Math.round(timeSince.get(TimeUnit.DAYS)) / 7 == 1) ? " week" : " weeks");
        }else if(timeSince.get(TimeUnit.DAYS) != 0) {
            time = timeSince.get(TimeUnit.DAYS) + ((timeSince.get(TimeUnit.DAYS) == 1) ? " day" : " days");
        }else if(timeSince.get(TimeUnit.HOURS) != 0) {
            time = timeSince.get(TimeUnit.HOURS) + ((timeSince.get(TimeUnit.HOURS) == 1) ? " hour" : " hours");
        }else if(timeSince.get(TimeUnit.MINUTES) >= 5) {
            time = timeSince.get(TimeUnit.MINUTES) + ((timeSince.get(TimeUnit.MINUTES) == 1) ? " minute" : " minutes");
        }

        if(timeSince.get(TimeUnit.DAYS) < 1 && timeSince.get(TimeUnit.HOURS) < 1) {
            if(aFewSeconds) {
                if(timeSince.get(TimeUnit.MINUTES) < 5 && timeSince.get(TimeUnit.MINUTES) > 0) {
                    time = "a few minutes";
                }else if(timeSince.get(TimeUnit.MINUTES) < 1) {
                    time = "a few seconds";
                }
            }else{
                if(timeSince.get(TimeUnit.MINUTES) > 0) {
                    time = timeSince.get(TimeUnit.MINUTES) + ((timeSince.get(TimeUnit.MINUTES) == 1) ? " minute" : " minutes");
                }else if(timeSince.get(TimeUnit.SECONDS) >= 0) {
                    time = timeSince.get(TimeUnit.SECONDS) + ((timeSince.get(TimeUnit.SECONDS) == 1) ? " second" : " seconds");
                }
            }
        }

        return time;
    }

    public static Map<TimeUnit, Long> getDateDifference(Date date1, Date date2) {

        long diffInSeconds = date2.getTime() - date1.getTime();

        //create the list
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        //create the result map of TimeUnit and difference
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
        long secondsRest = diffInSeconds;

        for ( TimeUnit unit : units ) {

            //calculate difference in millisecond
            long diff = unit.convert(secondsRest,TimeUnit.SECONDS);
            long diffInSecondsForUnit = unit.toSeconds(diff);
            secondsRest = secondsRest - diffInSecondsForUnit;

            //put the result in the map
            result.put(unit,diff);
        }

        return result;
    }

    public static String getFormattedDate(String pattern) {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        return ft.format(dNow);
    }

}
