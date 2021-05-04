package DAO;

import Model.Contact;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * The DAO Interface for contacts
 */
public interface DAO_contacts {
    public Contact getContact(int contactIdToFind) throws SQLException;
    public ObservableList<Contact> getAllContacts() throws SQLException;

}
