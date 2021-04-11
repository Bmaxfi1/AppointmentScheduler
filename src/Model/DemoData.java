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
                84000,
                8011234567L);

        Customer b = new Customer(10002,
                "USA",
                "NY",
                "Snoop Dog",
                "123 phony Street",
                14000,
                2001234567L);

        Customer c = new Customer(10003,
                "USA",
                "Alaska",
                "Corn cob",
                "123 Hypothetical Street",
                24000,
                4001234567L);

        ObservableList<Customer> demoCustomerList = FXCollections.observableArrayList();
        demoCustomerList.addAll(a, b, c);
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
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.get(0).getName(),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now(),
                customerList.get(0).getCustomerId());
        Appointment c = new Appointment(100003,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.get(0).getName(),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now(),
                customerList.get(0).getCustomerId());

        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        appointmentList.addAll(a, b, c);
        return appointmentList;
    }

}
