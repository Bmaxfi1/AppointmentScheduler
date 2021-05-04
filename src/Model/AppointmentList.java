package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * A list of appointments.
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

    /**
     *
     * @param listToAdd the list that will be added to appointmentList
     */
    public static void addAppointmentList(ObservableList<Appointment> listToAdd) {
        for (Appointment appointment:
             listToAdd) {
            addAppointment(appointment);
        }
    }

    /**
     *
     * @return the static appointmentList
     */
    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    /**
     *
     * @param searchText the id of the search query
     * @return the appointment(s) matching the parameter
     */
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

    /**
     *
     * @param name the customer/contact name to search for
     * @return the results of the search
     */
    public static ObservableList<Appointment> lookupAppointment(String name) {
        ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
        for (Appointment appointment:appointmentList){
            if (appointment.getCustomerName().toLowerCase().contains(name.toLowerCase()) || appointment.getContact().getContactName().toLowerCase().contains(name.toLowerCase())) {
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

    /**
     *
     * @param year the year to collect totals from
     * @return the total appointments for the year, in a list and totalled by month.
     */
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

    /**
     *
     * @param year the year to collect totals from
     * @param type the type of appointment to return
     * @return the total appointments for the year, in a list and totalled by month.
     */
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

    /**
     * **Lambda Here**
     * The lambda shown here is a shorthand version of a conditional remove operation
     *
     * @param idToDelete the id of the appointment to delete
     */
    public static void deleteAppointment(int idToDelete) {
        appointmentList.removeIf(appointment -> idToDelete == appointment.getAppointmentId());  //this is the first time I've used this sort of Lambda
    }

    /**
     *
     * @return a new unique appointmentId, used only in demo mode.
     */
    public static int getNewAppointmentId() {
        currentHighestAppointmentId++;
        return currentHighestAppointmentId;
    }

    /**
     * determines current highest appointmentId and sets it to the static member
     */
    public static void setInitialHighestAppointmentId() {
        int newAppointmentId = -1;
        for (int i = 0; i < AppointmentList.getAppointmentList().size(); i++) {
            if (newAppointmentId <= AppointmentList.getAppointmentList().get(i).getAppointmentId()) {
                newAppointmentId = AppointmentList.getAppointmentList().get(i).getAppointmentId();
            }
        }
        currentHighestAppointmentId = newAppointmentId;

    }
}

