package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of all appointments received from the database, plus any locally added appointments.
 */
public class AppointmentList {

    private static List<Appointment> appointmentList;

    /**
     *
     * @param appointmentToAdd
     */
    public void addAppointment(Appointment appointmentToAdd){
        appointmentList.add(appointmentToAdd);
    }
}
