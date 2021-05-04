package DAO;

import Model.Appointment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * The DAO Interface for appointments
 */
public interface DAO_appointments {
    public ObservableList<Appointment> getAllAppointments() throws SQLException;
    public int addAppointment(Appointment appointmentToAdd) throws SQLException;
    public void deleteAppointment(int appointmentId) throws SQLException;
    public void modifyAppointment(Appointment appointmentToModify) throws SQLException;

}
