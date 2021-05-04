package Model;

/**
 * The Customer class defines a customer object.  Customers need to have a Country assigned to them, and depends on the
 * Country class because of this.
 */
public class Customer {

    //members
    private int customerId;
    private String country;
    private String firstLevelDivision;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;

    //constructor
    public Customer(int customerId, String country, String firstLevelDivision, String name, String address, String postalCode, String phoneNumber) {
        this.customerId = customerId;
        this.country = country;
        this.firstLevelDivision = firstLevelDivision;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    //getters

    /**
     *
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return the first level division(state, province, region, etc.)
     */
    public String getFirstLevelDivision() {
        return firstLevelDivision;
    }

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //setters
    /**
     *
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @param firstLevelDivision the first level division to set
     */
    public void setFirstLevelDivision(String firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    /**
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param postalCode the postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //methods

    /**
     *
     * @return true if the customer has any appointments in the static AppointmentList.appointmentList
     */
    public boolean hasExistingAppointments() {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getCustomerId() == this.getCustomerId()) {
                return true;
            }
        }
        return false;
    }
}
