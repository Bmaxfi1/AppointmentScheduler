package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * A list of customers obtained from the database at startup, plus any locally added customers.
 */
public class CustomerList {

    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     *
     * @param customerToAdd
     */
    public void addCustomer(Customer customerToAdd){
        customerList.add(customerToAdd);
    }

    public Customer getCustomer(int index){
        return customerList.get(index);
    }
}
