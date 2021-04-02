package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is just a class for loading a sample database to the application, for debugging or demonstration purposes.
 */

public abstract class DemoData {

    public static List<Customer> getDemoCustomerList() {
        List<Customer> demoCustomerList = new ArrayList<>();
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

        demoCustomerList.add(a);
        demoCustomerList.add(b);
        demoCustomerList.add(c);

        return demoCustomerList;
    }

    public static List<Appointment> getAppointmentList(){
        List<Appointment> appointmentList = new ArrayList<>();
        Appointment a = new Appointment(100001,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                getDemoCustomerList().get(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());
        Appointment b = new Appointment(100002,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                getDemoCustomerList().get(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());
        Appointment c = new Appointment(100003,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                getDemoCustomerList().get(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());

        appointmentList.add(a);
        appointmentList.add(b);
        appointmentList.add(c);

        return appointmentList;
    }
}
