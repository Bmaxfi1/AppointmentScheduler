package Model;

/**
 * Tho Contact class defines a contact.  A contact is an individual who may be scheduled with a customer in an
 * appointment.
 */

public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     *
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
