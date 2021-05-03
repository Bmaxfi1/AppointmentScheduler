package DAOImpl;

import DAO.DAO_countries;
import DAO.DAO_customers;
import DAO.DAO_first_level_divisions;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl_customers implements DAO_customers {
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

    public ObservableList<Customer> getAllCustomers() throws SQLException {
        DAO_countries countriesDao = new DAOImpl_countries();
        DAO_first_level_divisions fldDao = new DAOImpl_first_level_divisions();

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
                    countriesDao.getCountryName(fldDao.getCountryId(divisionId)),
                    fldDao.getFirstLevelDivisionName(divisionId),
                    customerName,
                    address,
                    postalCode,
                    phone);
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
