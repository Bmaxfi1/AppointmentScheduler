package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;


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
    @FXML
    private Label customerIdErrorMessage;

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
    private ComboBox<String> mContactSelector;
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

    //new customer form
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private ComboBox<String> countrySelector;
    @FXML
    private ComboBox<String> firstLevelDivisionSelector;
    @FXML
    private Button customerSaveButton;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label phoneNumberErrorLabel;
    @FXML
    private Label addressErrorLabel;
    @FXML
    private Label postalCodeErrorLabel;
    @FXML
    private Label countryErrorLabel;
    @FXML
    private Label firstLevelDivisionErrorLabel;

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
        ZoneId userTimeZone = ZoneId.systemDefault();
        String startupMessage = "Welcome!  Alerts will be displayed in this window.  All times are in local time(Your timezone is: " + userTimeZone.toString() + ").";
        addMessage(startupMessage, BLACK);

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


        //build the model lists of customers and appointments
        CustomerList customerList = new CustomerList();
        AppointmentList appointmentList = new AppointmentList();
        CountryList countryList = new CountryList();


        //load demo data into lists
        CustomerList.addCustomerList(Model.DemoData.getDemoCustomerList());
        AppointmentList.addAppointmentList(Model.DemoData.getDemoAppointmentList());
        CountryList.addCountryList(Model.DemoData.getDemoCountryList());

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
        mStartAmOrPm.setItems(amPm);
        mEndAmOrPm.setItems(amPm);

        //Contact Drop Down Menus
        contactSelector.setItems(CustomerList.getCustomerNames());
        mContactSelector.setItems(CustomerList.getCustomerNames());

        //Country Drop Down Menus
        countrySelector.setItems(CountryList.getCountryNames());
        countrySelector.setOnAction(e -> {
            String selectedCountry = countrySelector.getValue();
            for (Country country : CountryList.getCountryList()) {
                if (country.getCountryName().equals(selectedCountry)) {
                    firstLevelDivisionSelector.setItems(country.getFirstLevelDivisions());
                }
            }
        });

        //New Appointment Contact Selector Value Change event - used to put the customer id into its field
        contactSelector.setOnAction(e -> {

            if (contactSelector.getSelectionModel().getSelectedItem() != null) {
                ObservableList<Customer> listOfClientsSharingName = FXCollections.observableArrayList();
                String nameOfSelectedContact = contactSelector.getValue();
                for (Customer customer : CustomerList.getCustomerList()) {
                    if (nameOfSelectedContact.equals(customer.getName())) {
                        listOfClientsSharingName.add(customer);
                    }
                }
                if (listOfClientsSharingName.size() == 1) {
                    customerIdField.setText(String.valueOf(listOfClientsSharingName.get(0).getCustomerId()));
                }
                //handle matching customer names
                if (listOfClientsSharingName.size() > 1) {
                    Stage multiContactSelectorStage = new Stage();
                    multiContactSelectorStage.initModality(Modality.APPLICATION_MODAL);
                    multiContactSelectorStage.setResizable(false);
                    multiContactSelectorStage.setTitle("Multiple contacts found");
                    Label infoLabel = new Label("There are multiple contacts that share that name.  Which do you mean?");
                    TableView<Customer> duplicatesTable = new TableView<>();
                    TableColumn<Customer, String> multiCustomerNameColumn = new TableColumn<>("Name");
                    TableColumn<Customer, Integer> multiCustomerIdColumn = new TableColumn<>("Id");
                    TableColumn<Customer, String> multiCustomerAddressColumn = new TableColumn<>("Address");
                    TableColumn<Customer, String> multiCustomerFLDColumn = new TableColumn<>("First-Level Division");
                    TableColumn<Customer, String> multiCustomerCountryColumn = new TableColumn<>("Country");
                    TableColumn<Customer, String> multiCustomerPhoneColumn = new TableColumn<>("Phone");
                    multiCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    multiCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
                    multiCustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
                    multiCustomerFLDColumn.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
                    multiCustomerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
                    multiCustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                    duplicatesTable.getColumns().addAll(multiCustomerNameColumn, multiCustomerIdColumn, multiCustomerAddressColumn, multiCustomerFLDColumn, multiCustomerCountryColumn, multiCustomerPhoneColumn);
                    duplicatesTable.setItems(listOfClientsSharingName);
                    Button confirmButton = new Button("Confirm");
                    Button cancelButton = new Button("Cancel");
                    confirmButton.setOnAction(e2 -> {
                        if (duplicatesTable.getSelectionModel().getSelectedItem() != null) {
                            customerIdField.setText(String.valueOf(duplicatesTable.getSelectionModel().getSelectedItem().getCustomerId()));
                            multiContactSelectorStage.close();
                        } else {
                            multiContactSelectorStage.close();
                            addMessage("No contact selected.  Please try again.", RED);
                            contactSelector.getSelectionModel().clearSelection();
                            customerIdField.setText("");
                        }
                    });
                    cancelButton.setOnAction(e2 -> {
                        multiContactSelectorStage.close();
                        contactSelector.getSelectionModel().clearSelection();
                        customerIdField.setText("");
                    });
                    HBox buttonRow = new HBox(10);
                    VBox layout = new VBox(10);
                    buttonRow.getChildren().addAll(confirmButton, cancelButton);
                    layout.getChildren().addAll(infoLabel, duplicatesTable, buttonRow);
                    layout.setPadding(new Insets(20));
                    Scene multiContactSelectorScene = new Scene(layout, 570, 300);
                    multiContactSelectorStage.setScene(multiContactSelectorScene);
                    multiContactSelectorStage.show();
                }
            }
        });

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
                    contactErrorMessage.setText("");

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

                if (customerIdField.getText() != null) {
                    customerIdErrorMessage.setText("");
                } else {
                    validationError = true;
                    customerIdErrorMessage.setText("Please try re-selecting the contact.");
                }

                if (startDateTime != null && endDateTime != null) {
                    if (startDateTime.isAfter(endDateTime)) {
                        validationError = true;
                        startErrorMessage.setText("Start time cannot be after end time.");
                        endErrorMessage.setText("End time cannot be before start time.");
                    } else {
                        startErrorMessage.setText("");
                        endErrorMessage.setText("");
                    }
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
                            Integer.parseInt(this.customerIdField.getText())
                    );
                    AppointmentList.addAppointment(appointmentToAdd);
                    System.out.println("appointment saved");
                    addMessage("appointment " + appointmentId + " saved.", BLACK);

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
                    customerIdField.setText("");
                } else {
                    addMessage("Appointment was NOT saved.  Check your entries on the 'Add Appointment' page.", RED);
                }
            } catch (DateTimeException dateTimeException) {
                startErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                endErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                System.out.println("dateTimeException during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.", RED);
            } catch (NullPointerException nullPointerException) {
                System.out.println("nullPointerException during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.", RED);
            } catch (Exception exception) {
                System.out.println("Unknown exception during 'add Appointment' save.");
                addMessage("Appointment was NOT saved.  Exception occurred.", RED);
            } finally {
                System.out.println("startDate is " + startDate);
                System.out.println("startTime is " + startTime);
                System.out.println("startDateTime is " + startDateTime);

            }

        });

        //New Customer Save Button
        customerSaveButton.setOnAction(e -> {
            //declare local variables
            boolean validationError = false;

            //validation
            try {
                if (nameField.getText().length() > 50 || nameField.getText().length() < 1) {
                    validationError = true;
                    nameErrorLabel.setText("Name length must be between 1-50.");
                } else {
                    nameErrorLabel.setText("");
                }

                if (phoneNumberField.getText().length() > 50 || phoneNumberField.getText().length() < 1) {
                    validationError = true;
                    phoneNumberErrorLabel.setText("Phone length must be between 1-50.");
                } else {
                    phoneNumberErrorLabel.setText("");
                }

                if (addressField.getText().length() > 100 || addressField.getText().length() < 1) {
                    validationError = true;
                    addressErrorLabel.setText("Address length must be between 1-100.");
                } else {
                    addressErrorLabel.setText("");
                }

                if (postalCodeField.getText().length() > 50 || postalCodeField.getText().length() < 1) {
                    validationError = true;
                    postalCodeErrorLabel.setText("Postal Code length must be between 1-50.");
                } else {
                    postalCodeErrorLabel.setText("");
                }

                if (countrySelector.getValue() == null) {
                    validationError = true;
                    countryErrorLabel.setText("Please select a country.");
                } else {
                    countryErrorLabel.setText("");
                }

                if (firstLevelDivisionSelector.getValue() == null) {
                    validationError = true;
                    firstLevelDivisionErrorLabel.setText("Please select a region.");
                } else {
                    firstLevelDivisionErrorLabel.setText("");
                }
                int newCustomerId = -1;
                if (!validationError) {
                    for (int i = 0; i < CustomerList.getCustomerList().size(); i++) {
                        if (newCustomerId <= CustomerList.getCustomerList().get(i).getCustomerId()) {
                            newCustomerId = CustomerList.getCustomerList().get(i).getCustomerId() + 1;
                        }
                    }

                    Customer customerToAdd = new Customer(
                            newCustomerId,
                            countrySelector.getValue(),
                            firstLevelDivisionSelector.getValue(),
                            nameField.getText(),
                            addressField.getText(),
                            postalCodeField.getText(),
                            phoneNumberField.getText()
                    );
                    CustomerList.addCustomer(customerToAdd);
                    addMessage("New customer '" + nameField.getText() + "' was added successfully." + " (id: " + newCustomerId + ")", BLACK);

                    //empty out all the fields
                    nameField.setText("");
                    phoneNumberField.setText("");
                    addressField.setText("");
                    postalCodeField.setText("");
                    countrySelector.getSelectionModel().clearSelection();
                    firstLevelDivisionSelector.getSelectionModel().clearSelection();
                } else {
                    addMessage("New customer was NOT added.  Please check your entries.", RED);
                }

            } catch (Exception exception) {
                System.out.println("Exception " + exception + " during save new customer.");
                addMessage("New customer was NOT added.  Exception occurred.", RED);
            }
        });
    }

    //methods
    public void addMessage(String message, Paint color) {
        Label messageToAddLabel = new Label(message);
        Label currentTimeLabel = new Label();
        DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        LocalTime currentTime = LocalTime.now();
        String currentTimeString = "";
        currentTimeString = currentTime.format(timestampFormatter);
        currentTimeLabel.setText(currentTimeString + "     ");

        HBox fullMessage = new HBox(currentTimeLabel, messageToAddLabel);

        messageToAddLabel.setTextFill(color);
        System.out.println("alertMessage added");
        alertMessages.getChildren().add(fullMessage);
        alertScrollPane.vvalueProperty().bind(alertMessages.heightProperty()); //this causes the scrollbar to snap to bottom
    }
}
