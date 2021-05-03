package DAOImpl;

import DAO.DAO_appointments;
import DAO.DAO_contacts;
import DAO.DAO_customers;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;
import java.util.TimeZone;

public class DAOImpl_appointments implements DAO_appointments {

    private static final String selectAllString = "SELECT * FROM appointments";
    private static final String addAppointmentString = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?, ?, ?)";
    private static final String deleteAppointmentString = "DELETE FROM appointments WHERE Appointment_Id = ?";
    private static final String modifyAppointmentString = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

    private static final ZoneId myZone = TimeZone.getDefault().toZoneId();
    private static final ZoneId UTC = ZoneId.of("UTC");

    @Override
    public ObservableList<Appointment> getAllAppointments() throws SQLException {
        DAO_contacts contactsDao = new DAOImpl_contacts();
        DAO_customers customersDao = new DAOImpl_customers();

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

            LocalDateTime startInstant = LocalDateTime.of(startDate, startTime);
            LocalDateTime endInstant = LocalDateTime.of(endDate, endTime);

            //time conversion

            //adjusts for DST if applicable.
            boolean isDST = TimeZone.getDefault().inDaylightTime(Date.valueOf(LocalDate.now()));
            if (isDST) {
                startInstant = startInstant.plusHours(1);
                endInstant = endInstant.plusHours(1);
            }

            ZonedDateTime utcStart = startInstant.atZone(UTC);
            ZonedDateTime utcEnd = endInstant.atZone(UTC);

            ZonedDateTime convertedStart = utcStart.withZoneSameLocal(myZone);
            ZonedDateTime convertedEnd = utcEnd.withZoneSameLocal(myZone);

            LocalDateTime myZoneStart = convertedStart.toLocalDateTime();
            LocalDateTime myZoneEnd = convertedEnd.toLocalDateTime();


            Appointment appointment = new Appointment(
                    appointmentId,
                    title,
                    description,
                    location,
                    customersDao.getCustomerName(customerId),
                    type,
                    myZoneStart,
                    myZoneEnd,
                    customerId,
                    contactsDao.getContact(contactId));

            allAppointments.add(appointment);
        }
        return allAppointments;

    }

    @Override
    public void addAppointment(Appointment appointmentToAdd) throws SQLException {

        LocalDateTime startInstant = appointmentToAdd.getStartInstant();
        LocalDateTime endInstant = appointmentToAdd.getEndInstant();

        ZonedDateTime originalStart = startInstant.atZone(myZone);
        ZonedDateTime originalEnd = endInstant.atZone(myZone);

        ZonedDateTime convertedStart = originalStart.withZoneSameInstant(UTC);
        ZonedDateTime convertedEnd = originalEnd.withZoneSameInstant(UTC);

        LocalDateTime UTCZoneStart = convertedStart.toLocalDateTime();
        LocalDateTime UTCZoneEnd = convertedEnd.toLocalDateTime();



        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, addAppointmentString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, appointmentToAdd.getTitle()); //remember, values start at one, not zero.
        ps.setString(2, appointmentToAdd.getDescription());
        ps.setString(3, appointmentToAdd.getLocation());
        ps.setString(4, appointmentToAdd.getType());
        ps.setString(5, UTCZoneStart.toString());
        ps.setString(6, UTCZoneEnd.toString());
        ps.setString(7, LocalDateTime.now(ZoneId.of("UTC")).toString());
        ps.setString(8, "admin");
        ps.setString(9, "admin");
        ps.setString(10, String.valueOf(appointmentToAdd.getCustomerId()));
        ps.setString(11, String.valueOf(appointmentToAdd.getContact().getContactId()));
        ps.setString(12, "1");
        ps.execute();
        System.out.println(ps.getUpdateCount() + " row(s) affected.");

    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, deleteAppointmentString);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, String.valueOf(appointmentId));

        ps.execute();
        if (ps.getUpdateCount() > 0) {
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        } else {
            System.out.println("no rows affected.");
        }
    }

    @Override
    public void modifyAppointment(Appointment appointmentToModify) throws SQLException {
        LocalDateTime startInstant = appointmentToModify.getStartInstant();
        LocalDateTime endInstant = appointmentToModify.getEndInstant();

        ZonedDateTime originalStart = startInstant.atZone(myZone);
        ZonedDateTime originalEnd = endInstant.atZone(myZone);

        ZonedDateTime convertedStart = originalStart.withZoneSameInstant(UTC);
        ZonedDateTime convertedEnd = originalEnd.withZoneSameInstant(UTC);

        LocalDateTime UTCZoneStart = convertedStart.toLocalDateTime();
        LocalDateTime UTCZoneEnd = convertedEnd.toLocalDateTime();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, modifyAppointmentString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, appointmentToModify.getTitle()); //remember, values start at one, not zero.
        ps.setString(2, appointmentToModify.getDescription());
        ps.setString(3, appointmentToModify.getLocation());
        ps.setString(4, appointmentToModify.getType());
        ps.setString(5, UTCZoneStart.toString());
        ps.setString(6, UTCZoneEnd.toString());
        ps.setString(7, "admin");
        ps.setString(8, String.valueOf(appointmentToModify.getCustomerId()));
        ps.setString(9, "1");
        ps.setString(10, String.valueOf(appointmentToModify.getContact().getContactId()));
        ps.setString(11, String.valueOf(appointmentToModify.getCustomerId()));
        ps.execute();
        System.out.println(ps.getUpdateCount() + "row(s) affected.");
    }
}
