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
    private static int currentHighestCustomerId;

    /**
     * @param customerToAdd the customer to be added to the list
     */
    public static void addCustomer(Customer customerToAdd) {
        customerList.add(customerToAdd);
    }

    public static void addCustomerList(ObservableList<Customer> listToAdd) {
        for (Customer customer :
                listToAdd) {
            addCustomer(customer);
        }
    }

    public static Customer getCustomer(int index) {
        return customerList.get(index);
    }

    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public static ObservableList<String> getCustomerNames() {
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            customerNames.add(customer.getName());
        }
        return customerNames;
    }

    public static ObservableList<Customer> lookupCustomer(int customerId) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
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
        for (Customer customer : customerList) {
            if (
                    customer.getName().toLowerCase().contains(searchText.toLowerCase())
            )
                filteredList.add(customer);
        }
        return filteredList;
    }

    public static void deleteCustomer(int idToDelete) {
        customerList.removeIf(customer -> idToDelete == customer.getCustomerId());  //this is the first time I've used this sort of Lambda
    }

    public static ObservableList<ObservableList<String>> getCustomersByCountry(Country country) {
        ObservableList<ObservableList<String>> listOfFLDAndTotal = FXCollections.observableArrayList();
        ObservableList<String> firstLevelDivisions = country.getFirstLevelDivisions();
        ObservableList<Integer> totalsByDivision = FXCollections.observableArrayList();
        for (String fld : firstLevelDivisions) {
            totalsByDivision.add(0);
        }
        for (Customer customer : CustomerList.getCustomerList()) {
            if (customer.getCountry().equals(country.getCountryName())) {
                for (int i = 0; i < firstLevelDivisions.size(); i++) {
                    if (firstLevelDivisions.get(i).equals(customer.getFirstLevelDivision())) {
                        int totalToIncrease = totalsByDivision.get(i);
                        totalToIncrease++;
                        totalsByDivision.set(i, totalToIncrease);
                    }
                }
            }
        }
        ObservableList<String> totalsByDivisionAsString = FXCollections.observableArrayList();
        for (int n : totalsByDivision) {
            totalsByDivisionAsString.add(String.valueOf(n));
        }
        listOfFLDAndTotal.addAll(firstLevelDivisions, totalsByDivisionAsString);
        return listOfFLDAndTotal;
    }


    public static int getNewCustomerId() {
        currentHighestCustomerId++;
        return currentHighestCustomerId;
    }

    public static void setInitialHighestCustomerId() {
        int newCustomerId = -1;
        for (int i = 0; i < CustomerList.getCustomerList().size(); i++) {
            if (newCustomerId <= CustomerList.getCustomerList().get(i).getCustomerId()) {
                newCustomerId = CustomerList.getCustomerList().get(i).getCustomerId();
            }
        }
        currentHighestCustomerId = newCustomerId;
    }
}
