package Model;

import java.time.LocalDateTime;


public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private LocalDateTime startInstant;
    private LocalDateTime endInstant;
    private int contactId;

    public Appointment(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String contactName,
                       String type,
                       LocalDateTime startInstant,
                       LocalDateTime endInstant,
                       int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactName = contactName;
        this.type = type;
        this.startInstant = startInstant;
        this.endInstant = endInstant;
        this.contactId = contactId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartInstant() {
        return startInstant;
    }

    public void setStartInstant(LocalDateTime startInstant) {
        this.startInstant = startInstant;
    }

    public LocalDateTime getEndInstant() {
        return endInstant;
    }

    public void setEndInstant(LocalDateTime endInstant) {
        this.endInstant = endInstant;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
