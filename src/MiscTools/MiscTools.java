package MiscTools;

import Model.Appointment;
import Model.AppointmentList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * The MiscTools Class holds several general static methods that are used throughout the app
 */
public abstract class MiscTools {

    /**
     *
     * @param str the string to check
     * @return true if the parameter is an integer
     */
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

    /**
     *
     * @param timeToCheck the time to check
     * @return string containing AM or PM
     */
    public static String getAmOrPm(LocalTime timeToCheck) {
        if (timeToCheck.getHour() >= 12) {
            return "PM";
        } else {
            return "AM";
        }
    }

    /**
     *
     * @param startTimeToCheck the start time
     * @param endTimeToCheck the end time
     * @return true if the business is closed between the start time and end time
     */
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

    /**
     *
     * @param customerId the customer to check for overlapping appointments
     * @param start the start time
     * @param end the end time
     * @return true if the customer has an appointment between the start time and end time
     */
    public static boolean appointmentOverlaps(int customerId, LocalDateTime start, LocalDateTime end) {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (customerId == appointment.getCustomerId()) {
                if (appointment.getStartInstant().isAfter(start) && appointment.getEndInstant().isBefore(end)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * When modifying an appointment, it is important to ignore the appointment we are changing. This overloaded method
     * takes into account an existing appointment.
     * @param customerId the int of the customer to check for overlapping appointments
     * @param start the start time
     * @param end the end time
     * @param appointmentIdToDisregard the appointment that is being modified
     * @return true if the customer has an appointment between the start time and end time, ignoring a certain appointment
     */
    public static boolean appointmentOverlaps(int customerId, LocalDateTime start, LocalDateTime end, int appointmentIdToDisregard) {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getAppointmentId() != appointmentIdToDisregard) {
                if (customerId == appointment.getCustomerId()) {
                    if (appointment.getStartInstant().isAfter(start) && appointment.getEndInstant().isBefore(end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param loginDetails a string containing details to be logged
     * @throws IOException
     */
    public static void recordLoginToFile(String loginDetails) throws IOException {
        Files.writeString(
                Path.of(System.getProperty("java.io.tmpdir"), "login_activity.txt"),
                loginDetails + System.lineSeparator(),
                CREATE, APPEND
        );
    }
    }


