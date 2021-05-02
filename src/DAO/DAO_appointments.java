package DAO;

import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DAO_appointments {

    private static final String selectAllString = "SELECT * FROM appointments";

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            int customerId = rs.getInt("Customer_ID");
            int contactId = rs.getInt("Contact_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String type = rs.getString("Type");
            String location = rs.getString("Location");
            LocalDate startDate = rs.getDate("Start").toLocalDate();
            LocalTime startTime = rs.getTime("Start").toLocalTime();
            LocalDate endDate = rs.getDate("End").toLocalDate();
            LocalTime endTime = rs.getTime("End").toLocalTime();

            Appointment appointment = new Appointment(
                    appointmentId,
                    title,
                    description,
                    location,
                    DAO_customers.getCustomerName(customerId),
                    type,
                    LocalDateTime.of(startDate, startTime),
                    LocalDateTime.of(endDate, endTime),
                    customerId,
                    DAO_contacts.getContact(contactId));

            allAppointments.add(appointment);
        }
        return allAppointments;

    }

}
