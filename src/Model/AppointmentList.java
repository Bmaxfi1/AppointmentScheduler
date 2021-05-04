package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * A list of all appointments received from the database, plus any locally added appointments.
 */
public class AppointmentList {

    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private static int currentHighestAppointmentId;

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
            if (appointment.getCustomerName().toLowerCase().contains(customerName.toLowerCase()) || appointment.getContact().getContactName().toLowerCase().contains(customerName.toLowerCase())) {
                filteredList.add(appointment);
            }
        }
        return filteredList;
    }

    public static ObservableList<String> getDifferentTypes() {
        ObservableList<String> listOfTypes = FXCollections.observableArrayList();
        listOfTypes.add("<All>");
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            boolean thisTypeAlreadyExists = false;
            for (String type:listOfTypes) {
                if (appointment.getType().equals(type)) {
                    thisTypeAlreadyExists = true;
                    break;
                }
            }
            if (!thisTypeAlreadyExists) {
                listOfTypes.add(appointment.getType());
            }

        }
        return listOfTypes;
    }

    public static ObservableList<Integer> getAppointmentTotals(int year) {
        ObservableList<Integer> appointmentTotalsByMonth= FXCollections.observableArrayList();
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getStartInstant().getYear() == year) {
                int monthNum = appointment.getStartInstant().getMonthValue();
                //this is an "enhanced switch". nice and compact.
                switch (monthNum) {
                    case 1 -> jan++;
                    case 2 -> feb++;
                    case 3 -> mar++;
                    case 4 -> apr++;
                    case 5 -> may++;
                    case 6 -> jun++;
                    case 7 -> jul++;
                    case 8 -> aug++;
                    case 9 -> sep++;
                    case 10 -> oct++;
                    case 11 -> nov++;
                    case 12 -> dec++;
                }
            }
        }
        appointmentTotalsByMonth.addAll(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
        return appointmentTotalsByMonth;
    }

    public static ObservableList<Integer> getAppointmentTotals(int year, String type) {
        ObservableList<Integer> appointmentTotalsByMonth= FXCollections.observableArrayList();
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getStartInstant().getYear() == year && appointment.getType().equals(type)) {
                int monthNum = appointment.getStartInstant().getMonthValue();
                //this is an "enhanced switch". nice and compact.
                switch (monthNum) {
                    case 1 -> jan++;
                    case 2 -> feb++;
                    case 3 -> mar++;
                    case 4 -> apr++;
                    case 5 -> may++;
                    case 6 -> jun++;
                    case 7 -> jul++;
                    case 8 -> aug++;
                    case 9 -> sep++;
                    case 10 -> oct++;
                    case 11 -> nov++;
                    case 12 -> dec++;
                }
            }
        }
        appointmentTotalsByMonth.addAll(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
        return appointmentTotalsByMonth;
    }

    public static void deleteAppointment(int idToDelete) {
        appointmentList.removeIf(appointment -> idToDelete == appointment.getAppointmentId());  //this is the first time I've used this sort of Lambda
    }

    public static int getNewAppointmentId() {
        currentHighestAppointmentId++;
        return currentHighestAppointmentId;
    }

    public static void setInitialHighestCustomerId() {
        int newAppointmentId = -1;
        for (int i = 0; i < AppointmentList.getAppointmentList().size(); i++) {
            if (newAppointmentId <= AppointmentList.getAppointmentList().get(i).getAppointmentId()) {
                newAppointmentId = AppointmentList.getAppointmentList().get(i).getAppointmentId();
            }
        }
        currentHighestAppointmentId = newAppointmentId;

    }
}

