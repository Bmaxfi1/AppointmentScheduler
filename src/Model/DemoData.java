package Model;

import java.time.LocalDateTime;

/**
 * This is just a class for loading a sample database to the application, for debugging or demonstration purposes.
 */

public abstract class DemoData {

    public static void addDemoCustomers(CustomerList customerList) {

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

        customerList.addCustomer(a);
        customerList.addCustomer(b);
        customerList.addCustomer(c);
    }

    public static void addDemoAppointments(AppointmentList appointmentList){
        CustomerList customerList = new CustomerList();
        DemoData.addDemoCustomers(customerList);

        Appointment a = new Appointment(100001,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.getCustomer(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());
        Appointment b = new Appointment(100002,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.getCustomer(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());
        Appointment c = new Appointment(100003,
                "Meet Customer",
                "An appointment to get to know the customer",
                "Phone",
                customerList.getCustomer(0),
                "Phone",
                LocalDateTime.now(),
                LocalDateTime.now());

        appointmentList.addAppointment(a);
        appointmentList.addAppointment(b);
        appointmentList.addAppointment(c);
    }

}
