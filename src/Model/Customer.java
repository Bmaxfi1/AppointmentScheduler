package Model;

public class Customer {

    //members
    private int customerId;
    private String country;
    private String firstLevelDivision;
    private String name;
    private String address;
    private int postalCode;
    private int phoneNumber;

    //constructor
    public Customer(int customerId, String country, String firstLevelDivision, String name, String address, int postalCode, int phoneNumber) {
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

    public int getPostalCode() {
        return postalCode;
    }

    public int getPhoneNumber() {
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

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
