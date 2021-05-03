package DAOImpl;

import DAO.DAO_contacts;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl_contacts implements DAO_contacts {
    private static final String selectAllString = "SELECT * FROM contacts";

    public Contact getContact(int contactIdToFind) throws SQLException {
        Contact contact;

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            if (contactId == contactIdToFind) {
                contact = new Contact(contactId, contactName, email);
                return contact;
            }
        }
        return null;
    }

    public ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contact contact = new Contact(contactId, contactName, email);
            allContacts.add(contact);
        }
        return allContacts;
    }

}
