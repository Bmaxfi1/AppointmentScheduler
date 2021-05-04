package Model;

import java.time.LocalDateTime;

/**
 * The Appointment class defines an appointment instance.  This class relies heavily on other classes to function.
 */

public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String customerName;
    private String type;
    private LocalDateTime startInstant;
    private LocalDateTime endInstant;
    private int customerId;
    private Contact contact;

    public Appointment(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String customerName,
                       String type,
                       LocalDateTime startInstant,
                       LocalDateTime endInstant,
                       int customerId,
                        Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerName = customerName;
        this.type = type;
        this.startInstant = startInstant;
        this.endInstant = endInstant;
        this.customerId = customerId;
        this.contact = contact;
    }

    /**
     *
     * @return the id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     *
     * @param appointmentId the id to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName the customer name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return the start time and date
     */
    public LocalDateTime getStartInstant() {
        return startInstant;
    }

    /**
     *
     * @param startInstant the start time and date to set
     */
    public void setStartInstant(LocalDateTime startInstant) {
        this.startInstant = startInstant;
    }

    /**
     *
     * @return the end time and date
     */
    public LocalDateTime getEndInstant() {
        return endInstant;
    }

    /**
     *
     * @param endInstant the end time and date to set
     */
    public void setEndInstant(LocalDateTime endInstant) {
        this.endInstant = endInstant;
    }

    /**
     *
     * @return the customerID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId the customerID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     *
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
