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
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DAOImpl_customers implements DAO_customers {
    private static final String selectAllString = "SELECT * FROM customers";
    private static final String addString = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?, NULL, ?, ?)";
    private static final String modifyString = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
    private static final String deleteString = "DELETE FROM customers WHERE Customer_ID = ?";

    @Override
    public String getCustomerName(int customerIdToFind) throws SQLException {
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

    @Override
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

    @Override
    public int addCustomer(Customer customerToAdd) throws SQLException {
        DAO_first_level_divisions fldDao = new DAOImpl_first_level_divisions();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, addString);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, customerToAdd.getName());
        ps.setString(2, customerToAdd.getAddress());
        ps.setString(3, customerToAdd.getPostalCode());
        ps.setString(4, customerToAdd.getPhoneNumber());
        ps.setString(5, LocalDateTime.now(ZoneId.of("UTC")).toString());
        ps.setString(6, "admin");
        ps.setString(7, "admin");
        ps.setString(8, String.valueOf(fldDao.getFirstLevelDivisionId(customerToAdd.getFirstLevelDivision())));
        ps.execute();
        System.out.println(ps.getUpdateCount() + " row(s) affected.");

        //return the generated Id
        int generatedId = -1;

        DBQuery.setPreparedStatement(connection, "SELECT Customer_ID FROM customers WHERE Customer_ID = @@Identity");
        PreparedStatement ps2 = DBQuery.getPreparedStatement();
        ps2.execute();
        ResultSet rs = ps2.getResultSet();
        if (rs.next()) {
            generatedId = rs.getInt("Customer_ID");
        }

        System.out.println(generatedId);
        return generatedId;

    }

    @Override
    public void modifyCustomer(Customer customerToModify) throws SQLException {
        DAO_first_level_divisions fldDao = new DAOImpl_first_level_divisions();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, modifyString);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, customerToModify.getName());
        ps.setString(2, customerToModify.getAddress());
        ps.setString(3, customerToModify.getPostalCode());
        ps.setString(4, customerToModify.getPhoneNumber());
        ps.setString(5, "admin");
        ps.setString(6, String.valueOf(fldDao.getFirstLevelDivisionId(customerToModify.getFirstLevelDivision())));
        ps.setString(7, String.valueOf(customerToModify.getCustomerId()));
        ps.execute();
        System.out.println(ps.getUpdateCount() + " row(s) affected.");

    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, deleteString);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, String.valueOf(customerId));

        ps.execute();
        if (ps.getUpdateCount() > 0) {
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        } else {
            System.out.println("no rows affected.");
        }
    }

}

