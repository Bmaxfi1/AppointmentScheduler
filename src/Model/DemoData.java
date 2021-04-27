package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * This is just a class for loading a sample database to the application, for debugging or demonstration purposes.
 */

public abstract class DemoData {

    public static ObservableList<Customer> getDemoCustomerList() {

        Customer a = new Customer(10001,
                "USA",
                "Utah",
                "Billy Bob",
                "123 Fake Street",
                "84000",
                "8011234567");

        Customer b = new Customer(10002,
                "USA",
                "NY",
                "Snoop Dog",
                "123 phony Street",
                "14000",
                "2001234567");

        Customer c = new Customer(10003,
                "USA",
                "Alaska",
                "Corn Cob",
                "123 Hypothetical Street",
                "24000",
                "4001234567");

        Customer d = new Customer(
                10004,
                "USA",
                "Arkansas",
                "Corn Cob",
                "789 Non-Existing Road",
                "78000",
                "600-123-4567"
        );

        Customer e = new Customer(
                10005,
                "USA",
                "California",
                "Billy Bob",
                "100 pretend avenue",
                "96000",
                "7011113333"
        );

        ObservableList<Customer> demoCustomerList = FXCollections.observableArrayList();
        demoCustomerList.addAll(a, b, c, d, e);
        return demoCustomerList;

    }

    public static ObservableList<Appointment> getDemoAppointmentList(){
        ObservableList<Customer> customerList;
        customerList = Model.DemoData.getDemoCustomerList();

        Appointment a = new Appointment(100001,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.get(0).getName(),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now(),
                customerList.get(0).getCustomerId());
        Appointment b = new Appointment(100002,
                "New product offering",
                "Suggesting solutions to the client.",
                "His address",
                customerList.get(1).getName(),
                "In Person",
                LocalDateTime.now().plusDays(50),
                LocalDateTime.now().plusDays(50),
                customerList.get(1).getCustomerId());
        Appointment c = new Appointment(100003,
                "Meeting",
                "n/a",
                "Phone",
                customerList.get(2).getName(),
                "Phone",
                LocalDateTime.now().plusMinutes(11),
                LocalDateTime.now().plusMinutes(31),
                customerList.get(2).getCustomerId());
        Appointment d = new Appointment(100004,
                "Follow up about purchase",
                "need to discuss what went wrong during recent order",
                "Phone",
                customerList.get(3).getName(),
                "Phone",
                LocalDateTime.now().plusMinutes(4),
                LocalDateTime.now().plusMinutes(14),
                customerList.get(3).getCustomerId());
        Appointment e = new Appointment(100005,
                "Lunch",
                "Lunch meeting",
                "The Taco Store",
                customerList.get(0).getName(),
                "Meal",
                LocalDateTime.now().plusDays(7),
                LocalDateTime.now().plusDays(7),
                customerList.get(0).getCustomerId());


        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        appointmentList.addAll(a, b, c, d, e);
        return appointmentList;
    }

    public static ObservableList<Country> getDemoCountryList() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();

        ObservableList<String> usaDivs = FXCollections.observableArrayList();
        usaDivs.addAll("Utah", "New York", "Alabama", "Texas", "Florida");

        ObservableList<String> canadaDivs = FXCollections.observableArrayList();
        canadaDivs.addAll("Quebec", "Ontario");

        ObservableList<String> ukDivs = FXCollections.observableArrayList();
        ukDivs.addAll("Greenwich", "Brighton", "Greater London");

        ObservableList<String> mexicoDivs = FXCollections.observableArrayList();
        mexicoDivs.addAll("Baja California", "Ensenada", "Mexico City");


        Country usa = new Country(
                1,
                "USA",
                usaDivs
        );

        Country mexico = new Country(
                2,
                "Mexico",
                mexicoDivs
        );

        Country canada = new Country(
                3,
                "Canada",
                canadaDivs
        );

        Country uk = new Country(
                4,
                "UK",
                ukDivs
        );

        countryList.addAll(usa, mexico, canada, uk);
        return countryList;
    }

}
