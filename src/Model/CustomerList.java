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
     * @param customerToAdd the customer to be added to the list
     */
    public static void addCustomer(Customer customerToAdd){
        customerList.add(customerToAdd);
    }

    public static void addCustomerList(ObservableList<Customer> listToAdd) {
        for (Customer customer:
             listToAdd) {
            addCustomer(customer);
        }
    }

    public static Customer getCustomer(int index){
        return customerList.get(index);
    }

    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public static ObservableList<String> getCustomerNames(){
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customer customer : customerList)
        {
            customerNames.add(customer.getName());
        }
        return customerNames;
    }

    //public static ObservableList<String> searchResults(String searchText) { }
}
