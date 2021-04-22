package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class MainWindowController {

    //This section is for linking the FXML file to this controller class
    //all appointments table
    @FXML
    private TableView<Appointment> allAppointmentsTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> contactColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;

    //all customers table
    @FXML
    private TableView<Customer> allCustomersTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn2;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, Long> phoneNumberColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, Integer> postalCodeColumn;
    @FXML
    private TableColumn<Customer, String> firstLevelDivisionColumn;
    @FXML
    private TableColumn<Customer, String> countryColumn;

    //New Appointment Form
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<String> contactSelector;
    @FXML
    private TextField typeField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private ChoiceBox<String> startAmOrPm;
    @FXML
    private ChoiceBox<String> endAmOrPm;
    @FXML
    private TextField customerIdField;
    @FXML
    private Button saveButton;
    @FXML
    private Label titleErrorMessage;
    @FXML
    private Label descriptionErrorMessage;
    @FXML
    private Label locationErrorMessage;
    @FXML
    private Label typeErrorMessage;
    @FXML
    private Label contactErrorMessage;
    @FXML
    private Label startErrorMessage;
    @FXML
    private Label endErrorMessage;

    //Modify Appointment Form
    @FXML
    private Tab modifyAppointmentTab;
    @FXML
    private TextField mIdField;
    @FXML
    private TextField mTitleField;
    @FXML
    private TextField mDescriptionField;
    @FXML
    private TextField mLocationField;
    @FXML
    private ComboBox<Customer> mContactSelector;
    @FXML
    private TextField mTypeField;
    @FXML
    private DatePicker mStartDateField;
    @FXML
    private DatePicker mEndDateField;
    @FXML
    private TextField mStartTimeField;
    @FXML
    private TextField mEndTimeField;
    @FXML
    private ChoiceBox<String> mStartAmOrPm;
    @FXML
    private ChoiceBox<String> mEndAmOrPm;
    @FXML
    private Button mSaveButton;
    @FXML
    private Label mTitleErrorMessage;
    @FXML
    private Label mDescriptionErrorMessage;
    @FXML
    private Label mLocationErrorMessage;
    @FXML
    private Label mContactErrorMessage;
    @FXML
    private Label mTypeErrorMessage;
    @FXML
    private Label mStartErrorMessage;
    @FXML
    private Label mEndErrorMessage;

    //other
    @FXML
    private ScrollPane alertScrollPane;
    @FXML
    private VBox alertMessages;
    @FXML
    private Label initialAlertMessage;

    //init
    public void initialize() {
        System.out.println("Successfully began MainWindowController instantiation");

        //Create links between table columns and model
        //appointment table
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startInstant"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endInstant"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        //customer table
        customerIdColumn2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        firstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));


        //build the lists of customers and appointments
        CustomerList customerList = new CustomerList();
        AppointmentList appointmentList = new AppointmentList();


        //load demo data into lists
        CustomerList.addCustomerList(Model.DemoData.getDemoCustomerList());
        AppointmentList.addAppointmentList(Model.DemoData.getDemoAppointmentList());


        //TODO load database data into lists
        //
        //

        //Insert the lists into the tables
        allCustomersTable.setItems(CustomerList.getCustomerList());
        allAppointmentsTable.setItems(AppointmentList.getAppointmentList());

        //AM or PM Selectors
        ObservableList<String> amPm = FXCollections.observableArrayList("AM", "PM");
        startAmOrPm.setItems(amPm);
        endAmOrPm.setItems(amPm);

        //Contact Drop Down Menu
        contactSelector.setItems(CustomerList.getCustomerNames());

        //New Appointment Save Button
        saveButton.setOnAction(e -> {
            //declare needed variables
            LocalTime startTime = null;
            LocalDate startDate = null;
            LocalDateTime startDateTime = null;
            LocalTime endTime = null;
            LocalDate endDate = null;
            LocalDateTime endDateTime = null;

            //validation section
            try {
                boolean validationError = false;

                if (titleField.getLength() > 50 || titleField.getLength() < 1) {
                    validationError = true;
                    titleErrorMessage.setText("Title character length must be between 1-50.");
                } else {
                    titleErrorMessage.setText("");
                }

                if (descriptionField.getLength() > 50 || descriptionField.getLength() < 1) {
                    validationError = true;
                    descriptionErrorMessage.setText("Description character length must be between 1-50.");
                } else {
                    descriptionErrorMessage.setText("");
                }

                if (locationField.getLength() > 50 || locationField.getLength() < 1) {
                    validationError = true;
                    locationErrorMessage.setText("Location character length must be between 1-50.");
                } else {
                    locationErrorMessage.setText("");
                }

                if (contactSelector.getValue() != null) {
                    boolean foundACustomer = false;
                    for (int i = 0; i < CustomerList.getCustomerNames().size(); i++) {
                        if (contactSelector.getValue().equalsIgnoreCase(CustomerList.getCustomerNames().get(i))) {
                            foundACustomer = true;
                        }
                    }
                    if (!foundACustomer) {
                        validationError = true;
                        contactErrorMessage.setText("That contact does not exist.");
                    } else {
                        contactErrorMessage.setText("");
                    }
                } else {
                    validationError = true;
                    contactErrorMessage.setText("No contact selected.");
                }

                if (typeField.getLength() > 50 || typeField.getLength() < 1) {
                    validationError = true;
                    typeErrorMessage.setText("Type character length must be between 1-50.");
                } else {
                    typeErrorMessage.setText("");
                }

                if (startDateField.getValue() != null && startTimeField.getText() != null && startAmOrPm.getValue() != null) {
                    startDate = startDateField.getValue();
                    String startTimeString = startTimeField.getText() + " " + startAmOrPm.getValue();
                    System.out.println("startTimeString is " + startTimeString);
                    startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault()));
                    startDateTime = LocalDateTime.of(startDate, startTime);
                    System.out.println("start time is " + startTime);
                    startErrorMessage.setText("");
                } else {
                    validationError = true;
                    startErrorMessage.setText("Please enter a starting time/date.");
                }

                if (endDateField.getValue() != null && endTimeField.getText() != null && endAmOrPm.getValue() != null) {
                    endDate = endDateField.getValue();
                    String endTimeString = endTimeField.getText() + " " + endAmOrPm.getValue();
                    System.out.println("endTimeString is " + endTimeString);
                    endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault()));
                    endDateTime = LocalDateTime.of(endDate, endTime);
                    System.out.println("end time is " + endTime);
                    endErrorMessage.setText("");
                } else {
                    validationError = true;
                    endErrorMessage.setText("Please enter an ending time/date.");
                }

                //validation complete, time to add the appointment
                int appointmentId = -1;
                if (!validationError) {
                    //generate an appointment id
                    for (int i = 0; i < AppointmentList.getAppointmentList().size(); i++) {
                        if (appointmentId <= AppointmentList.getAppointmentList().get(i).getAppointmentId()) {
                            appointmentId = AppointmentList.getAppointmentList().get(i).getAppointmentId() + 1;
                        }
                    }
                    Appointment appointmentToAdd = new Appointment(
                            appointmentId,
                            this.titleField.getText(),
                            this.descriptionField.getText(),
                            this.locationField.getText(),
                            this.contactSelector.getValue(),
                            this.typeField.getText(),
                            startDateTime,
                            endDateTime,
                            -1  //todo add customer id generator
                    );
                    AppointmentList.addAppointment(appointmentToAdd);
                    System.out.println("appointment saved");
                    addMessage("appointment " + appointmentId + " saved.");

                    //reset all fields to be empty
                    titleField.setText("");
                    descriptionField.setText("");
                    locationField.setText("");
                    contactSelector.setValue("");
                    typeField.setText("");
                    startDateField.setValue(null);
                    startTimeField.setText("");
                    startAmOrPm.getSelectionModel().clearSelection();
                    endDateField.setValue(null);
                    endTimeField.setText("");
                    endAmOrPm.getSelectionModel().clearSelection();
                } else {
                    addMessage("Appointment was NOT saved.  Check your entries on the 'Add Appointment' page.");
                }
            } catch (DateTimeException dateTimeException) {
                startErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                endErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                System.out.println("dateTimeException during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.");
            } catch (NullPointerException nullPointerException) {
                System.out.println("nullPointerException during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.");
            } catch (Exception exception) {
                System.out.println("Unknown exception during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.");
            } finally {
                System.out.println("startDate is " + startDate);
                System.out.println("startTime is " + startTime);
                System.out.println("startDateTime is " + startDateTime);

            }

        });
        addMessage("MainWindowController instantiated");
        System.out.println("Successfully completed MainWindowController instance");
    }

    //methods
    public void addMessage(String message) {
        Label messageToAddLabel = new Label(message);
        System.out.println("alertMessage added");
        alertMessages.getChildren().add(messageToAddLabel);
    }
}
