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

    public static ObservableList<Appointment> lookupAppointment(int searchText){
        ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
        for (Appointment appointment: appointmentList) {
            String searchedIdAsString = String.valueOf(searchText);
            String existingIdAsString = String.valueOf(appointment.getAppointmentId());
            if (searchedIdAsString.contains(existingIdAsString)) {
                filteredList.add(appointment);
            }
        }
        return filteredList;
    }

    public static ObservableList<Appointment> lookupAppointment(String customerName) {
        ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
        for (Appointment appointment:appointmentList){
            if (appointment.getContactName().toLowerCase().contains(customerName.toLowerCase())) {
                filteredList.add(appointment);
            }
        }
        return filteredList;
    }

    public static void deleteAppointment(int idToDelete) {
        appointmentList.removeIf(appointment -> idToDelete == appointment.getAppointmentId());  //this is the first time I've used this sort of Lambda
    }

}

