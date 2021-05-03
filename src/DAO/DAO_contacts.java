package DAO;

import Model.Contact;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO_contacts {
    public Contact getContact(int contactIdToFind) throws SQLException;
    public ObservableList<Contact> getAllContacts() throws SQLException;

}
