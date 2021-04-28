package MiscTools;

import Model.Appointment;
import Model.AppointmentList;
import Model.Customer;

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
        ZoneId myZone = ZoneId.systemDefault();
        ZoneId EST = ZoneId.of("America/New_York");

        ZonedDateTime startTimeToCheckLocalZone = startTimeToCheck.atZone(myZone);
        ZonedDateTime startTimeToCheckEST = startTimeToCheckLocalZone.withZoneSameInstant(EST);

        ZonedDateTime endTimeToCheckLocalZone = endTimeToCheck.atZone(myZone);
        ZonedDateTime endTimeToCheckEST = endTimeToCheckLocalZone.withZoneSameInstant(EST);

        LocalTime startOfBusinessHours =  LocalTime.of(8,0);
        LocalTime endOfBusinessHours = LocalTime.of(22, 0);

        return startTimeToCheckEST.toLocalTime().isBefore(startOfBusinessHours) ||
                startTimeToCheckEST.toLocalTime().isAfter(endOfBusinessHours) ||
                endTimeToCheckEST.toLocalTime().isBefore(startOfBusinessHours) ||
                endTimeToCheckEST.toLocalTime().isAfter(endOfBusinessHours) ||
                !startTimeToCheckEST.toLocalDate().toString().equals(endTimeToCheckEST.toLocalDate().toString());
    }

    public static boolean appointmentOverlaps(int customerId, LocalDateTime start, LocalDateTime end) {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (customerId == appointment.getContactId()) {
                if (appointment.getStartInstant().isAfter(start) && appointment.getEndInstant().isBefore(end)) {
                    return true;
                }
            }
        }
        return false;
    }

    //when modifying an appointment, it is important to ignore the appointment we are changing.
    public static boolean appointmentOverlaps(int customerId, LocalDateTime start, LocalDateTime end, int appointmentIdToDisregard) {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getAppointmentId() != appointmentIdToDisregard) {
                if (customerId == appointment.getContactId()) {
                    if (appointment.getStartInstant().isAfter(start) && appointment.getEndInstant().isBefore(end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
