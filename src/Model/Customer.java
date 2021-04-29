package Model;

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
    public int getCustomerId() {
        return customerId;
    }

    public String getCountry() {
        return country;
    }

    public String getFirstLevelDivision() {
        return firstLevelDivision;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //setters

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFirstLevelDivision(String firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //methods
    public boolean hasExistingAppointments() {
        for (Appointment appointment: AppointmentList.getAppointmentList()) {
            if (appointment.getContactId() == this.getCustomerId()) {
                return true;
            }
        }
        return false;
    }
}
