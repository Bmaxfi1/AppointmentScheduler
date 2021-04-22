package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of all appointments received from the database, plus any locally added appointments.
 */
public class AppointmentList {

    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    /**
     *
     * @param appointmentToAdd the appointment to be added to the appointmentList
     */
    public static void addAppointment(Appointment appointmentToAdd){
        appointmentList.add(appointmentToAdd);
    }

    public static void addAppointmentList(ObservableList<Appointment> listToAdd) {
        for (Appointment appointment:
             listToAdd) {
            addAppointment(appointment);
        }
    }

    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
