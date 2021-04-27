package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Locale;

/**
 * The list of customers.
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
    /*
    public static Customer lookupCustomer(int customerId) {
        int customerIndex = -1;
        for (Customer customer:customerList) {
            if(
                    customerId == customer.getCustomerId())
            {
                customerIndex = customerList.indexOf(customer);
            }
        }
        return customerList.get(customerIndex);
    }
    */

    public static ObservableList<Customer> lookupCustomer(int customerId) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for (Customer customer: customerList) {
            String customerIdAsString = String.valueOf(customerId);
            String existingIdAsString = String.valueOf(customer.getCustomerId());
            if (customerIdAsString.contains(existingIdAsString)) {
                filteredList.add(customer);
            }
        }
        return filteredList;
    }

    public static ObservableList<Customer> lookupCustomer(String searchText) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for(Customer customer: customerList) {
            if(
                    customer.getName().toLowerCase().contains(searchText.toLowerCase())
            )
                filteredList.add(customer);
        }
        return filteredList;
    }
}
