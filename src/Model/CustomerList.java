package Model;


import java.util.List;

/**
 * A list of customers obtained from the database at startup, plus any locally added customers.
 */
public class CustomerList {

    private static List<Customer> customerList;

    /**
     *
     * @param customerToAdd
     */
    public void addCustomer(Customer customerToAdd){
        customerList.add(customerToAdd);
    }
}
