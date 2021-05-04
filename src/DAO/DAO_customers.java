package DAO;

import Model.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * The DAO Interface for customers
 */
public interface DAO_customers {
    public String getCustomerName(int customerIdToFind) throws SQLException;
    public ObservableList<Customer> getAllCustomers() throws SQLException;
    public int addCustomer(Customer customerToAdd) throws SQLException;
    public void modifyCustomer(Customer customerToModify) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
}
