package DAO;

import Model.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO_customers {
    public ObservableList<Customer> getAllCustomers() throws SQLException;
}
