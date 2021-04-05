package Model;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of all appointments received from the database, plus any locally added appointments.
 */
public class AppointmentList {

    private static List<Appointment> appointmentList = FXCollections.observableArrayList();

    /**
     *
     * @param appointmentToAdd
     */
    public void addAppointment(Appointment appointmentToAdd){
        appointmentList.add(appointmentToAdd);
    }
}
