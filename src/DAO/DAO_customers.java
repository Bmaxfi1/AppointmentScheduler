package DAO;

import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_customers {
    private static final String selectAllString = "SELECT * FROM customers";

    public static String getCustomerName(int customerIdToFind) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            if (customerId == customerIdToFind) {
                return customerName;
            }
        }
        return null;
    }

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            int divisionId = rs.getInt("Division_ID");
            String phone = rs.getString("Phone");

            Customer customer = new Customer(
                    customerId,
                    DAO_countries.getCountryName(DAO_first_level_divisions.getCountryId(divisionId)),
                    DAO_first_level_divisions.getFirstLevelDivisionName(divisionId),
                    customerName,
                    address,
                    postalCode,
                    phone);
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
