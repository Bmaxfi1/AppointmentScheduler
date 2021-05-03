package DAO;

import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Customer;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO_customers {
    public String getCustomerName(int customerIdToFind) throws SQLException;
    public ObservableList<Customer> getAllCustomers() throws SQLException;
    public void addCustomer(Customer customerToAdd) throws SQLException;
    public void modifyCustomer(Customer customerToModify) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
}
