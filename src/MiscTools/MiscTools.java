package MiscTools;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MiscTools {

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static String getAmOrPm(LocalTime timeToCheck) {
        if (timeToCheck.getHour() >= 12) {
            return "PM";
        } else {
            return "AM";
        }
    }

    public static boolean isOutsideBusinessHours(LocalDateTime startTimeToCheck, LocalDateTime endTimeToCheck) {
        String date_format = "M-dd-yyyy hh:mm a";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(date_format);

        ZoneId myZone = ZoneId.systemDefault();
        ZoneId EST = ZoneId.of("America/New_York");

        ZonedDateTime startTimeToCheckLocalZone = startTimeToCheck.atZone(myZone);
        ZonedDateTime startTimeToCheckEST = startTimeToCheckLocalZone.withZoneSameInstant(EST);

        ZonedDateTime endTimeToCheckLocalZone = endTimeToCheck.atZone(myZone);
        ZonedDateTime endTimeToCheckEST = endTimeToCheckLocalZone.withZoneSameInstant(EST);

        //System.out.println("start time in current time zone is: " + startTimeToCheckLocalZone.format(format));
        //System.out.println("start time in EST is: " + startTimeToCheckEST.format(format));
        //System.out.println("end time in current time zone is: " + endTimeToCheckLocalZone.format(format));
        //System.out.println("end time in EST is: " + endTimeToCheckEST.format(format));

        LocalTime startOfBusinessHours =  LocalTime.of(8,0);
        LocalTime endOfBusinessHours = LocalTime.of(22, 0);

        return startTimeToCheckEST.toLocalTime().isBefore(startOfBusinessHours) ||
                startTimeToCheckEST.toLocalTime().isAfter(endOfBusinessHours) ||
                endTimeToCheckEST.toLocalTime().isBefore(startOfBusinessHours) ||
                endTimeToCheckEST.toLocalTime().isAfter(endOfBusinessHours) ||
                !startTimeToCheckEST.toLocalDate().toString().equals(endTimeToCheckEST.toLocalDate().toString());
    }
}
