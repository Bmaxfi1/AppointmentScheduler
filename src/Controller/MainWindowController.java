package Controller;

import DAO.DAO_appointments;
import DAO.DAO_contacts;
import DAO.DAO_countries;
import DAO.DAO_customers;
import DAOImpl.DAOImpl_appointments;
import DAOImpl.DAOImpl_contacts;
import DAOImpl.DAOImpl_countries;
import DAOImpl.DAOImpl_customers;
import DBConnectionClasses.DBConnection;
import MiscTools.MiscTools;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;


public class MainWindowController {

    //This section is for linking the FXML file to this controller class
    //Appointments tab
    @FXML
    private TabPane appointmentsTabPane;
    @FXML
    private Tab allAppointmentsTab;

    //Customers tab
    @FXML
    private TabPane customersTabPane;
    @FXML
    private Tab allCustomersTab;
    @FXML
    private Tab modifyCustomerTab;

    //all appointments tab
    @FXML
    private ComboBox<String> viewSelector;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label filterSelectionLabel;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchBox;

    //all customers tab
    @FXML
    private Button modifyCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private TextField customerSearchBox;

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
    private TableColumn<Appointment, String> customerColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endColumn;
    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;
    @FXML
    private TableColumn<Appointment, Contact> contactColumn;

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
    private ComboBox<String> customerSelector;
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
    private ComboBox<String> contactSelector;
    @FXML
    private TextField contactIdField;
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
    private Label customerErrorMessage;
    @FXML
    private Label startErrorMessage;
    @FXML
    private Label endErrorMessage;
    @FXML
    private Label contactErrorMessage;

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
    private ComboBox<String> mCustomerSelector;
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
    private TextField mCustomerIdField;
    @FXML
    private ComboBox<String> mContactSelector;
    @FXML
    private TextField mContactIdField;
    @FXML
    private Button mSaveButton;
    @FXML
    private Label mTitleErrorMessage;
    @FXML
    private Label mDescriptionErrorMessage;
    @FXML
    private Label mLocationErrorMessage;
    @FXML
    private Label mCustomerErrorMessage;
    @FXML
    private Label mTypeErrorMessage;
    @FXML
    private Label mStartErrorMessage;
    @FXML
    private Label mEndErrorMessage;
    @FXML
    private Label mContactErrorMessage;
    @FXML
    private Button mCancelButton;

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

    //modify customer form
    @FXML
    private TextField mcCustomerIdField;
    @FXML
    private TextField mcNameField;
    @FXML
    private TextField mcPhoneNumberField;
    @FXML
    private TextField mcAddressField;
    @FXML
    private TextField mcPostalCodeField;
    @FXML
    private ComboBox<String> mcCountryField;
    @FXML
    private ComboBox<String> mcFirstLevelDivisionField;
    @FXML
    private Label mcNameErrorMessage;
    @FXML
    private Label mcPhoneNumberErrorMessage;
    @FXML
    private Label mcAddressErrorMessage;
    @FXML
    private Label mcPostalCodeErrorMessage;
    @FXML
    private Label mcCountryErrorMessage;
    @FXML
    private Label mcFirstLevelDivisionErrorMessage;
    @FXML
    private Button mcSaveButton;
    @FXML
    private Button mcCancelButton;

    //reports
    @FXML
    private ComboBox<String> rtaTypeSelector;
    @FXML
    private TextField rtaYearTextField;
    @FXML
    private Button rtaRetrieveButton;
    @FXML
    private Label janResults;
    @FXML
    private Label febResults;
    @FXML
    private Label marResults;
    @FXML
    private Label aprResults;
    @FXML
    private Label mayResults;
    @FXML
    private Label junResults;
    @FXML
    private Label julResults;
    @FXML
    private Label augResults;
    @FXML
    private Label sepResults;
    @FXML
    private Label octResults;
    @FXML
    private Label novResults;
    @FXML
    private Label decResults;

    @FXML
    private ComboBox<Country> tccCountrySelector;
    @FXML
    private ScrollPane tccResultScrollPane;
    @FXML
    private VBox tccResultsVBox;

    //other
    @FXML
    private ScrollPane alertScrollPane;
    @FXML
    private VBox alertMessages;
    @FXML
    private Label initialAlertMessage;

    //init
    public void initialize() throws SQLException {
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
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startInstant"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endInstant"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        contactColumn.setCellFactory(tc -> new TableCell<Appointment, Contact>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(contact.getContactName());
                }
            }
        });

        //customer table
        customerIdColumn2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        firstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));


        if (DBConnection.getConnection() == null) {
            //load demo data into lists
            System.out.println("Could not connect to database.  Loading demo data into lists.");
            CustomerList.addCustomerList(DemoData.getDemoCustomerList());
            ContactList.addContactList(DemoData.getDemoContactList());
            AppointmentList.addAppointmentList(DemoData.getDemoAppointmentList());
            CountryList.addCountryList(DemoData.getDemoCountryList());
        }
        else {
            //load database data into lists
            System.out.println("Loading database items into lists.");

            DAO_countries countriesDao=new DAOImpl_countries();
            DAO_customers customersDao = new DAOImpl_customers();
            DAO_contacts contactsDao = new DAOImpl_contacts();
            DAO_appointments appointmentsDao = new DAOImpl_appointments();

            CustomerList.addCustomerList(customersDao.getAllCustomers());
            ContactList.addContactList(contactsDao.getAllContacts());
            AppointmentList.addAppointmentList(appointmentsDao.getAllAppointments());
            CountryList.addCountryList(countriesDao.getAllCountries());
        }

        //Check to see if there is an upcoming appointment within 15 minutes.
        boolean upcomingAppointment = false;
        for (Appointment appointment : AppointmentList.getAppointmentList()) {
            if (appointment.getStartInstant().minusMinutes(15).isBefore(LocalDateTime.now()) && appointment.getStartInstant().isAfter(LocalDateTime.now())) {
                upcomingAppointment = true;
                addMessage("There is an upcoming appointment with " + appointment.getCustomerName() + " at " + appointment.getStartInstant().format(DateTimeFormatter.ofPattern("hh:mm a")) + ". (Appointment Id: " + appointment.getAppointmentId() + ")", RED);
            }
        }
        if (!upcomingAppointment) {
            addMessage("There are no upcoming appointments within 15 minutes.", BLACK);
        }

        //Insert the lists into the tables
        allCustomersTable.setItems(CustomerList.getCustomerList());
        allAppointmentsTable.setItems(AppointmentList.getAppointmentList());

        //Format the appointment table time columns
        //lambda addition
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, MM/dd/yyyy");
        startColumn.setCellFactory(tc -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(dateTime));
                }
            }
        });
        //lambda addition
        endColumn.setCellFactory(tc -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(dateTime));
                }
            }
        });

        //report misc
        rtaYearTextField.setText(String.valueOf(LocalDateTime.now().getYear()));

        //AM or PM Selectors
        ObservableList<String> amPm = FXCollections.observableArrayList("AM", "PM");
        startAmOrPm.setItems(amPm);
        endAmOrPm.setItems(amPm);
        mStartAmOrPm.setItems(amPm);
        mEndAmOrPm.setItems(amPm);

        //viewSelection button
        ObservableList<String> weekOrMonth = FXCollections.observableArrayList("Week of", "Month of", "All");
        viewSelector.setItems(weekOrMonth);
        viewSelector.setOnAction(e -> {
            if (viewSelector.getValue().equals("Week of")) {
                datePicker.setPromptText("Select a week ->");
                datePicker.setDisable(false);
                datePicker.setValue(null);
            }
            if (viewSelector.getValue().equals("Month of")) {
                datePicker.setPromptText("Select a month ->");
                datePicker.setDisable(false);
                datePicker.setValue(null);
            }
            if (viewSelector.getValue().equals("All")) {
                datePicker.setPromptText("<- pick one");
                datePicker.setValue(null);
                datePicker.setDisable(true);
                allAppointmentsTable.setItems(AppointmentList.getAppointmentList());
                filterSelectionLabel.setText("Showing all appointments");
            }
        });

        //appointment datePicker - used to filter appointments
        datePicker.setOnAction(e -> {
            if (viewSelector.getValue().equals("Week of") && datePicker.getValue() != null) {
                filterSelectionLabel.setText("Showing appointments in week " + datePicker.getValue().get(WEEK_OF_WEEK_BASED_YEAR) + " of " + datePicker.getValue().getYear());
                //filtering mechanism
                ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
                for (Appointment appointment : AppointmentList.getAppointmentList()) {
                    if (appointment.getStartInstant().get(WEEK_OF_WEEK_BASED_YEAR) == datePicker.getValue().get(WEEK_OF_WEEK_BASED_YEAR)) {
                        filteredList.add(appointment);
                    }
                }
                allAppointmentsTable.setItems(filteredList);
            }
            if (viewSelector.getValue().equals("Month of") && datePicker.getValue() != null) {
                filterSelectionLabel.setText("Showing appointments in " + datePicker.getValue().getMonth() + " of " + datePicker.getValue().getYear());
                //filtering mechanism
                ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
                for (Appointment appointment : AppointmentList.getAppointmentList()) {
                    if (appointment.getStartInstant().getMonth() == datePicker.getValue().getMonth() && appointment.getStartInstant().getYear() == datePicker.getValue().getYear()) {
                        filteredList.add(appointment);
                    }
                }
                allAppointmentsTable.setItems(filteredList);
            }
        });

        //appointment searchBox
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchBox.getText().equals("")) {
                allAppointmentsTable.setItems(AppointmentList.getAppointmentList());
                filterSelectionLabel.setText("Showing all appointments");
            } else if (MiscTools.isInteger(searchBox.getText())) {
                try {
                    ObservableList<Appointment> searchResults = FXCollections.observableArrayList();
                    searchResults.addAll(AppointmentList.lookupAppointment(Integer.parseInt(newValue)));
                    for (Appointment appointment : AppointmentList.getAppointmentList())
                        if (appointment.getCustomerId() == Integer.parseInt(searchBox.getText())) {
                            searchResults.add(appointment);
                        }
                    for (Appointment appointment : AppointmentList.getAppointmentList()) {
                        if (appointment.getContact().getContactId() == Integer.parseInt(searchBox.getText())) {
                            searchResults.add(appointment);
                        }
                    }
                    allAppointmentsTable.setItems(searchResults);
                } catch (NumberFormatException numberFormatException) {
                    addMessage("The number you entered is too long to be an Id.", BLACK);
                }
                filterSelectionLabel.setText("Showing search by Id results");
            } else {
                allAppointmentsTable.setItems(AppointmentList.lookupAppointment(newValue));
                filterSelectionLabel.setText("Showing search by name results");
            }
            if (allAppointmentsTable.getItems().isEmpty()) {
                allAppointmentsTable.setItems(AppointmentList.getAppointmentList());
                filterSelectionLabel.setText("Showing all appointments");
            }
        });

        //Customer search box
        customerSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (customerSearchBox.getText().equals("")) {
                        allCustomersTable.setItems(CustomerList.getCustomerList());
                    } else if (MiscTools.isInteger(customerSearchBox.getText())) {
                        try {
                            allCustomersTable.setItems(CustomerList.lookupCustomer(Integer.parseInt(newValue)));
                        } catch (NumberFormatException numberFormatException) {
                            addMessage("The number you entered is too long to be an Id.", BLACK);
                        }
                    } else {
                        allCustomersTable.setItems(CustomerList.lookupCustomer(newValue));
                    }
                    if (allCustomersTable.getItems().isEmpty()) {
                        allCustomersTable.setItems(CustomerList.getCustomerList());
                    }
                }
        );

        //Customer Drop Down Menus
        customerSelector.setItems(CustomerList.getCustomerNames());
        mCustomerSelector.setItems(CustomerList.getCustomerNames());

        //Contact Drop Down Menus
        contactSelector.setItems(ContactList.getContactNames());
        mContactSelector.setItems(ContactList.getContactNames());

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
        mcCountryField.setItems(CountryList.getCountryNames());
        mcCountryField.setOnAction(e -> {
            String selectedCountry = mcCountryField.getValue();
            for (Country country : CountryList.getCountryList()) {
                if (country.getCountryName().equals(selectedCountry)) {
                    mcFirstLevelDivisionField.setItems(country.getFirstLevelDivisions());
                }
            }
        });
        tccCountrySelector.setItems(CountryList.getCountryList());
        tccCountrySelector.setOnAction(e -> {
            tccResultsVBox.getChildren().clear();

            ObservableList<ObservableList<String>> listOfFLDAndTotal = CustomerList.getCustomersByCountry(tccCountrySelector.getValue());
            ObservableList<String> listOfFLDs = listOfFLDAndTotal.get(0);
            ObservableList<String> listOfTotals = listOfFLDAndTotal.get(1);


            for (int i = 0; i < listOfFLDs.size(); i++) {
                //this "region" fills the void space in an hbox for alignment purposes.
                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);

                Label fldLabel = new Label(listOfFLDs.get(i));
                Label totalLabel = new Label(listOfTotals.get(i));

                HBox lineItem = new HBox(fldLabel, region, totalLabel);


                tccResultsVBox.getChildren().add(lineItem);
            }

        });

        //Appointment Type Drop Down Menus
        rtaTypeSelector.setItems(AppointmentList.getDifferentTypes());

        //New Appointment tab - Customer Selector Value Change event - used to put the customer id into its field
        customerSelector.setOnAction(e -> {

            if (customerSelector.getSelectionModel().getSelectedItem() != null) {
                ObservableList<Customer> listOfClientsSharingName = FXCollections.observableArrayList();
                String nameOfSelectedCustomer = customerSelector.getValue();
                for (Customer customer : CustomerList.getCustomerList()) {
                    if (nameOfSelectedCustomer.equals(customer.getName())) {
                        listOfClientsSharingName.add(customer);
                    }
                }
                if (listOfClientsSharingName.size() == 1) {
                    customerIdField.setText(String.valueOf(listOfClientsSharingName.get(0).getCustomerId()));
                }
                //handle matching customer names
                if (listOfClientsSharingName.size() > 1) {
                    Stage multiCustomerSelectorStage = new Stage();
                    multiCustomerSelectorStage.initModality(Modality.APPLICATION_MODAL);
                    multiCustomerSelectorStage.setResizable(false);
                    multiCustomerSelectorStage.setTitle("Multiple customers found");
                    Label infoLabel = new Label("There are multiple customers that share that name.  Which do you mean?");
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
                            multiCustomerSelectorStage.close();
                        } else {
                            multiCustomerSelectorStage.close();
                            addMessage("No customer selected.  Please try again.", RED);
                            customerSelector.getSelectionModel().clearSelection();
                            customerIdField.setText("");
                        }
                    });
                    cancelButton.setOnAction(e2 -> {
                        multiCustomerSelectorStage.close();
                        customerSelector.getSelectionModel().clearSelection();
                        customerIdField.setText("");
                    });
                    HBox buttonRow = new HBox(10);
                    VBox layout = new VBox(10);
                    buttonRow.getChildren().addAll(confirmButton, cancelButton);
                    layout.getChildren().addAll(infoLabel, duplicatesTable, buttonRow);
                    layout.setPadding(new Insets(20));
                    Scene multiCustomerSelectorScene = new Scene(layout, 570, 300);
                    multiCustomerSelectorStage.setScene(multiCustomerSelectorScene);
                    multiCustomerSelectorStage.show();
                }
            }
        });

        //New Appointment tab - Contact Selector Value Change event - used to put the contact id into its field
        contactSelector.setOnAction(e -> {

            if (contactSelector.getSelectionModel().getSelectedItem() != null) {
                ObservableList<Contact> listOfContactsSharingName = FXCollections.observableArrayList();
                String nameOfSelectedContact = contactSelector.getValue();
                for (Contact contact : ContactList.getContactList()) {
                    if (nameOfSelectedContact.equals(contact.getContactName())) {
                        listOfContactsSharingName.add(contact);
                    }
                }
                if (listOfContactsSharingName.size() == 1) {
                    contactIdField.setText(String.valueOf(listOfContactsSharingName.get(0).getContactId()));
                }
                //handle matching customer names
                if (listOfContactsSharingName.size() > 1) {
                    Stage multiContactSelectorStage = new Stage();
                    multiContactSelectorStage.initModality(Modality.APPLICATION_MODAL);
                    multiContactSelectorStage.setResizable(false);
                    multiContactSelectorStage.setTitle("Multiple contacts found");
                    Label infoLabel = new Label("There are multiple contacts that share that name.  Which do you mean?");
                    TableView<Contact> duplicatesTable = new TableView<>();
                    TableColumn<Contact, String> multiContactNameColumn = new TableColumn<>("Name");
                    TableColumn<Contact, Integer> multiContactIdColumn = new TableColumn<>("Id");
                    TableColumn<Contact, String> multiContactAddressColumn = new TableColumn<>("E-mail");
                    multiContactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                    multiContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
                    multiContactAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                    duplicatesTable.getColumns().addAll(multiContactNameColumn, multiContactIdColumn, multiContactAddressColumn);
                    duplicatesTable.setItems(listOfContactsSharingName);
                    Button confirmButton = new Button("Confirm");
                    Button cancelButton = new Button("Cancel");
                    confirmButton.setOnAction(e2 -> {
                        if (duplicatesTable.getSelectionModel().getSelectedItem() != null) {
                            contactIdField.setText(String.valueOf(duplicatesTable.getSelectionModel().getSelectedItem().getContactId()));
                            multiContactSelectorStage.close();
                        } else {
                            multiContactSelectorStage.close();
                            addMessage("No contact selected.  Please try again.", RED);
                            contactSelector.getSelectionModel().clearSelection();
                            contactIdField.setText("");
                        }
                    });
                    cancelButton.setOnAction(e2 -> {
                        multiContactSelectorStage.close();
                        contactSelector.getSelectionModel().clearSelection();
                        contactIdField.setText("");
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

        //Modify Appointment tab - Customer selector value change event
        mCustomerSelector.setOnAction(e -> {
            if (mCustomerSelector.getSelectionModel().getSelectedItem() != null && !modifyAppointmentTab.isDisabled()) {
                ObservableList<Customer> listOfClientsSharingName = FXCollections.observableArrayList();
                String nameOfSelectedCustomer = mCustomerSelector.getValue();
                for (Customer customer : CustomerList.getCustomerList()) {
                    if (nameOfSelectedCustomer.equals(customer.getName())) {
                        listOfClientsSharingName.add(customer);
                    }
                }
                if (listOfClientsSharingName.size() == 1) {
                    mCustomerIdField.setText(String.valueOf(listOfClientsSharingName.get(0).getCustomerId()));
                }
                //handle matching customer names
                if (listOfClientsSharingName.size() > 1) {
                    Stage multiCustomerSelectorStage = new Stage();
                    multiCustomerSelectorStage.initModality(Modality.APPLICATION_MODAL);
                    multiCustomerSelectorStage.setResizable(false);
                    multiCustomerSelectorStage.setTitle("Multiple customers found");
                    Label infoLabel = new Label("There are multiple customers that share that name.  Which do you mean?");
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
                            mCustomerIdField.setText(String.valueOf(duplicatesTable.getSelectionModel().getSelectedItem().getCustomerId()));
                            multiCustomerSelectorStage.close();
                        } else {
                            multiCustomerSelectorStage.close();
                            addMessage("No customer selected.  Please try again.", RED);
                            mCustomerSelector.getSelectionModel().clearSelection();
                            mCustomerIdField.setText("");
                        }
                    });
                    cancelButton.setOnAction(e2 -> {
                        multiCustomerSelectorStage.close();
                        mCustomerSelector.getSelectionModel().clearSelection();
                        mCustomerIdField.setText("");
                    });
                    HBox buttonRow = new HBox(10);
                    VBox layout = new VBox(10);
                    buttonRow.getChildren().addAll(confirmButton, cancelButton);
                    layout.getChildren().addAll(infoLabel, duplicatesTable, buttonRow);
                    layout.setPadding(new Insets(20));
                    Scene multiCustomerSelectorScene = new Scene(layout, 570, 300);
                    multiCustomerSelectorStage.setScene(multiCustomerSelectorScene);
                    multiCustomerSelectorStage.show();
                }
            }
        });

        //Modify Appointment tab - Contact selector value change event
        mContactSelector.setOnAction(e -> {

            if (mContactSelector.getSelectionModel().getSelectedItem() != null) {
                ObservableList<Contact> listOfContactsSharingName = FXCollections.observableArrayList();
                String nameOfSelectedContact = mContactSelector.getValue();
                for (Contact contact : ContactList.getContactList()) {
                    if (nameOfSelectedContact.equals(contact.getContactName())) {
                        listOfContactsSharingName.add(contact);
                    }
                }
                if (listOfContactsSharingName.size() == 1) {
                    mContactIdField.setText(String.valueOf(listOfContactsSharingName.get(0).getContactId()));
                }
                //handle matching customer names
                if (listOfContactsSharingName.size() > 1) {
                    Stage multiContactSelectorStage = new Stage();
                    multiContactSelectorStage.initModality(Modality.APPLICATION_MODAL);
                    multiContactSelectorStage.setResizable(false);
                    multiContactSelectorStage.setTitle("Multiple contacts found");
                    Label infoLabel = new Label("There are multiple contacts that share that name.  Which do you mean?");
                    TableView<Contact> duplicatesTable = new TableView<>();
                    TableColumn<Contact, String> multiContactNameColumn = new TableColumn<>("Name");
                    TableColumn<Contact, Integer> multiContactIdColumn = new TableColumn<>("Id");
                    TableColumn<Contact, String> multiContactAddressColumn = new TableColumn<>("E-mail");
                    multiContactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                    multiContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
                    multiContactAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                    duplicatesTable.getColumns().addAll(multiContactNameColumn, multiContactIdColumn, multiContactAddressColumn);
                    duplicatesTable.setItems(listOfContactsSharingName);
                    Button confirmButton = new Button("Confirm");
                    Button cancelButton = new Button("Cancel");
                    confirmButton.setOnAction(e2 -> {
                        if (duplicatesTable.getSelectionModel().getSelectedItem() != null) {
                            mContactIdField.setText(String.valueOf(duplicatesTable.getSelectionModel().getSelectedItem().getContactId()));
                            multiContactSelectorStage.close();
                        } else {
                            multiContactSelectorStage.close();
                            addMessage("No contact selected.  Please try again.", RED);
                            mContactSelector.getSelectionModel().clearSelection();
                            mContactIdField.setText("");
                        }
                    });
                    cancelButton.setOnAction(e2 -> {
                        multiContactSelectorStage.close();
                        mContactSelector.getSelectionModel().clearSelection();
                        mContactIdField.setText("");
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

        //New Appointment tab - Save Button
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

                if (customerSelector.getValue() != null) {
                    customerErrorMessage.setText("");
                } else {
                    validationError = true;
                    customerErrorMessage.setText("No customer selected.");
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
                    contactErrorMessage.setText("");
                } else {
                    validationError = true;
                    contactErrorMessage.setText("Please try re-selecting the customer.");
                }

                if (startDateTime != null && endDateTime != null) {
                    if (startDateTime.isAfter(endDateTime)) {
                        validationError = true;
                        startErrorMessage.setText("Start time cannot be after end time.");
                        endErrorMessage.setText("End time cannot be before start time.");
                    } else {
                        if (MiscTools.isOutsideBusinessHours(startDateTime, endDateTime)) {
                            validationError = true;
                            startErrorMessage.setText("That time/date is outside of the standard business hours.");
                            endErrorMessage.setText("That time/date is outside of the standard business hours.");
                        } else {
                            if (MiscTools.appointmentOverlaps(Integer.parseInt(customerIdField.getText()), startDateTime, endDateTime)) {
                                validationError = true;
                                startErrorMessage.setText("This appointment time overlaps with an existing appointment time.");
                                endErrorMessage.setText("This appointment time overlaps with an existing appointment time.");
                            } else {
                                startErrorMessage.setText("");
                                endErrorMessage.setText("");
                            }
                        }
                    }
                }

                if (contactSelector.getValue() != null) {
                    contactErrorMessage.setText("");
                } else {
                    validationError = true;
                    contactErrorMessage.setText("No contact selected.");
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
                            this.customerSelector.getValue(),
                            this.typeField.getText(),
                            startDateTime,
                            endDateTime,
                            Integer.parseInt(this.customerIdField.getText()),
                            ContactList.getContact(Integer.parseInt(this.contactIdField.getText()))
                    );
                    AppointmentList.addAppointment(appointmentToAdd);
                    System.out.println("appointment saved");
                    addMessage("appointment " + appointmentId + " saved.", BLACK);

                    //reset all fields to be empty
                    titleField.setText("");
                    descriptionField.setText("");
                    locationField.setText("");
                    customerSelector.setValue("");
                    typeField.setText("");
                    startDateField.setValue(null);
                    startTimeField.setText("");
                    startAmOrPm.getSelectionModel().clearSelection();
                    endDateField.setValue(null);
                    endTimeField.setText("");
                    endAmOrPm.getSelectionModel().clearSelection();
                    customerIdField.setText("");
                    contactSelector.setValue("");
                    contactIdField.setText("");
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
            }

        });

        //All Appointments tab - Modify Button
        modifyButton.setOnAction(e -> {
            if (allAppointmentsTable.getSelectionModel().getSelectedItem() != null && modifyAppointmentTab.isDisabled()) {
                Appointment selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
                DateTimeFormatter.ofPattern("hh:mm a");
                appointmentsTabPane.getSelectionModel().select(modifyAppointmentTab);
                mIdField.setText(String.valueOf(selectedAppointment.getAppointmentId()));
                mTitleField.setText(selectedAppointment.getTitle());
                mDescriptionField.setText(selectedAppointment.getDescription());
                mLocationField.setText(selectedAppointment.getLocation());
                mCustomerSelector.setValue(selectedAppointment.getCustomerName());
                mTypeField.setText(selectedAppointment.getType());
                mStartDateField.setValue(selectedAppointment.getStartInstant().toLocalDate());
                mStartTimeField.setText(LocalTime.parse(selectedAppointment.getStartInstant().format(DateTimeFormatter.ofPattern("hh:mm"))).toString());
                mStartAmOrPm.setValue(MiscTools.getAmOrPm(selectedAppointment.getStartInstant().toLocalTime()));
                mEndDateField.setValue(selectedAppointment.getEndInstant().toLocalDate());
                mEndTimeField.setText(LocalTime.parse(selectedAppointment.getEndInstant().format(DateTimeFormatter.ofPattern("hh:mm"))).toString());
                mEndAmOrPm.setValue(MiscTools.getAmOrPm(selectedAppointment.getEndInstant().toLocalTime()));
                mCustomerIdField.setText(String.valueOf(selectedAppointment.getCustomerId()));
                mContactSelector.setValue(selectedAppointment.getContact().getContactName());
                mContactIdField.setText(String.valueOf(selectedAppointment.getContact().getContactId()));
                modifyAppointmentTab.setDisable(false);
            } else if (allAppointmentsTable.getSelectionModel().getSelectedItem() != null && !modifyAppointmentTab.isDisabled()) {
                addMessage("There is already an appointment modification in progress.  Please save or cancel the pending changes on the 'Modify Appointment' tab.", RED);
            } else if (allAppointmentsTable.getSelectionModel().getSelectedItem() == null) {
                addMessage("Please select an appointment to change.", BLACK);
            }
        });

        //All Appointments tab - Delete Button
        deleteButton.setOnAction(e -> {
            if (allAppointmentsTable.getSelectionModel().getSelectedItem() != null) {
                Stage confirmWindow = new Stage();
                confirmWindow.initModality(Modality.APPLICATION_MODAL);
                confirmWindow.setResizable(false);
                confirmWindow.setTitle("Delete Confirmation");
                Label infoLabel = new Label("Delete appointment # " + allAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId() + " with " + allAppointmentsTable.getSelectionModel().getSelectedItem().getCustomerName() + "?");
                Button confirmButton = new Button("Confirm");
                Button cancelButton = new Button("Cancel");
                confirmButton.setOnAction(e2 -> {
                    int appointmentToDelete = allAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId();
                    addMessage("Appointment # " + allAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId() + " with " + allAppointmentsTable.getSelectionModel().getSelectedItem().getCustomerName() + " deleted.", BLACK);
                    if (!modifyAppointmentTab.isDisabled()) {
                        if (Integer.parseInt(mIdField.getText()) == allAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId()) {
                            modifyAppointmentTab.setDisable(true);
                        }
                    }
                    AppointmentList.deleteAppointment(appointmentToDelete);
                    confirmWindow.close();
                });
                cancelButton.setOnAction(e2 -> {
                    addMessage("Delete canceled.  No changes made.", BLACK);
                    confirmWindow.close();
                });
                HBox buttonRow = new HBox(10);
                buttonRow.setAlignment(Pos.CENTER);
                VBox layout = new VBox(10);
                layout.setAlignment(Pos.CENTER);
                buttonRow.getChildren().addAll(confirmButton, cancelButton);
                layout.getChildren().addAll(infoLabel, buttonRow);
                layout.setPadding(new Insets(20));
                Scene multiCustomerSelectorScene = new Scene(layout, 300, 150);
                confirmWindow.setScene(multiCustomerSelectorScene);
                confirmWindow.show();
            } else if (allAppointmentsTable.getSelectionModel().getSelectedItem() == null) {
                addMessage("Please select an appointment to delete.", BLACK);
            }

        });

        //Modify Appointment Tab - Save Button
        mSaveButton.setOnAction(e -> {
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

                if (mTitleField.getLength() > 50 || mTitleField.getLength() < 1) {
                    validationError = true;
                    mTitleErrorMessage.setText("Title character length must be between 1-50.");
                } else {
                    mTitleErrorMessage.setText("");
                }

                if (mDescriptionField.getLength() > 50 || mDescriptionField.getLength() < 1) {
                    validationError = true;
                    mDescriptionErrorMessage.setText("Description character length must be between 1-50.");
                } else {
                    mDescriptionErrorMessage.setText("");
                }

                if (mLocationField.getLength() > 50 || mLocationField.getLength() < 1) {
                    validationError = true;
                    mLocationErrorMessage.setText("Location character length must be between 1-50.");
                } else {
                    mLocationErrorMessage.setText("");
                }

                if (mCustomerSelector.getValue() != null) {
                    mCustomerErrorMessage.setText("");
                } else {
                    validationError = true;
                    mCustomerErrorMessage.setText("No customer selected.");
                }

                if (mTypeField.getLength() > 50 || mTypeField.getLength() < 1) {
                    validationError = true;
                    mTypeErrorMessage.setText("Type character length must be between 1-50.");
                } else {
                    mTypeErrorMessage.setText("");
                }

                if (mStartDateField.getValue() != null && mStartTimeField.getText() != null && mStartAmOrPm.getValue() != null) {
                    startDate = mStartDateField.getValue();
                    String startTimeString = mStartTimeField.getText() + " " + mStartAmOrPm.getValue();
                    System.out.println("startTimeString is " + startTimeString);
                    startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault()));
                    startDateTime = LocalDateTime.of(startDate, startTime);
                    System.out.println("start time is " + startTime);
                    mStartErrorMessage.setText("");
                } else {
                    validationError = true;
                    mStartErrorMessage.setText("Please enter a starting time/date.");
                }

                if (mEndDateField.getValue() != null && mEndTimeField.getText() != null && mEndAmOrPm.getValue() != null) {
                    endDate = mEndDateField.getValue();
                    String endTimeString = mEndTimeField.getText() + " " + mEndAmOrPm.getValue();
                    System.out.println("endTimeString is " + endTimeString);
                    endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault()));
                    endDateTime = LocalDateTime.of(endDate, endTime);
                    System.out.println("end time is " + endTime);
                    mEndErrorMessage.setText("");
                } else {
                    validationError = true;
                    mEndErrorMessage.setText("Please enter an ending time/date.");
                }

                if (mCustomerIdField.getText() != null) {
                    mContactErrorMessage.setText("");
                } else {
                    validationError = true;
                    mContactErrorMessage.setText("Please try re-selecting the customer.");
                }

                if (startDateTime != null && endDateTime != null) {
                    if (startDateTime.isAfter(endDateTime)) {
                        validationError = true;
                        mStartErrorMessage.setText("Start time cannot be after end time.");
                        mEndErrorMessage.setText("End time cannot be before start time.");
                    } else {
                        if (MiscTools.isOutsideBusinessHours(startDateTime, endDateTime)) {
                            validationError = true;
                            mStartErrorMessage.setText("That time/date is outside of the standard business hours.");
                            mEndErrorMessage.setText("That time/date is outside of the standard business hours.");
                        } else {
                            if (MiscTools.appointmentOverlaps(Integer.parseInt(mCustomerIdField.getText()), startDateTime, endDateTime, Integer.parseInt(mIdField.getText()))) {
                                validationError = true;
                                mStartErrorMessage.setText("This appointment time overlaps with an existing appointment time.");
                                mEndErrorMessage.setText("This appointment time overlaps with an existing appointment time.");
                            } else {
                                mStartErrorMessage.setText("");
                                mEndErrorMessage.setText("");
                            }
                        }
                    }
                }

                if (mContactSelector.getValue() != null) {
                    mContactErrorMessage.setText("");
                } else {
                    validationError = true;
                    mContactErrorMessage.setText("No customer selected.");
                }


                //validation complete, time to change the appointment
                if (!validationError) {
                    Appointment appointmentToChange = AppointmentList.lookupAppointment(Integer.parseInt(mIdField.getText())).get(0);
                    appointmentToChange.setTitle(this.mTitleField.getText());
                    appointmentToChange.setDescription(mDescriptionField.getText());
                    appointmentToChange.setLocation(mLocationField.getText());
                    appointmentToChange.setCustomerName(mCustomerSelector.getValue());
                    appointmentToChange.setType(mTypeField.getText());
                    appointmentToChange.setStartInstant(startDateTime);
                    appointmentToChange.setEndInstant(endDateTime);
                    appointmentToChange.setCustomerId(Integer.parseInt(mCustomerIdField.getText()));
                    appointmentToChange.setContact(ContactList.getContact(Integer.parseInt(mContactIdField.getText())));
                    allAppointmentsTable.refresh();
                    System.out.println("appointment changes saved");
                    addMessage("Appointment modification to " + Integer.parseInt(this.mIdField.getText()) + " saved.", BLACK);
                    appointmentsTabPane.getSelectionModel().select(allAppointmentsTab);
                    modifyAppointmentTab.setDisable(true);

                } else {
                    addMessage("Appointment changes NOT saved.  Check your entries on the 'Modify Appointment' tab.", RED);
                }
            } catch (DateTimeException dateTimeException) {
                mStartErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                mEndErrorMessage.setText("Please use the proper 12-hour time format '00:00'.");
                System.out.println("dateTimeException during 'add Appointment' save.");
                addMessage("Appointment change was NOT saved.  Exception occurred.", RED);
            } catch (NullPointerException nullPointerException) {
                System.out.println("nullPointerException during 'modify Appointment' save.");
                addMessage("Appointment change was NOT saved.  Exception occurred.", RED);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("indexOutOfBoundsException during 'modify Appointment' save.");
                addMessage("Appointment change was NOT saved.  This appointment no longer exists.", RED);
                appointmentsTabPane.getSelectionModel().select(allAppointmentsTab);
                modifyAppointmentTab.setDisable(true);
            } catch (Exception exception) {
                System.out.println("Unknown exception during 'modify Appointment' save.");
                System.out.println(exception);
                addMessage("Appointment change was NOT saved.  Exception occurred.", RED);
            }
        });

        //Modify Appointment Tab - Cancel Button
        mCancelButton.setOnAction(e -> {
            appointmentsTabPane.getSelectionModel().select(allAppointmentsTab);
            modifyAppointmentTab.setDisable(true);
            addMessage("Appointment modification canceled.", BLACK);
        });

        //All Customers Tab - Modify Button
        modifyCustomerButton.setOnAction(e -> {
            if (allCustomersTable.getSelectionModel().getSelectedItem() != null && modifyCustomerTab.isDisabled()) {
                Customer selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();
                modifyCustomerTab.setDisable(false);
                customersTabPane.getSelectionModel().select(modifyCustomerTab);
                mcCustomerIdField.setText(String.valueOf(selectedCustomer.getCustomerId()));
                mcNameField.setText(selectedCustomer.getName());
                mcPhoneNumberField.setText(selectedCustomer.getPhoneNumber());
                mcAddressField.setText(selectedCustomer.getAddress());
                mcPostalCodeField.setText(selectedCustomer.getPostalCode());
                mcCountryField.setValue(selectedCustomer.getCountry());
                mcFirstLevelDivisionField.setValue(selectedCustomer.getFirstLevelDivision());
            } else if (allCustomersTable.getSelectionModel().getSelectedItem() != null && !modifyCustomerTab.isDisabled()) {
                addMessage("There is already a customer modification in progress.  Please save or cancel the pending changes on the 'Modify Customer' tab.", RED);
            } else if (allCustomersTable.getSelectionModel().getSelectedItem() == null) {
                addMessage("Please select a customer to change.", BLACK);
            }
        });

        //All Customers Tab - Delete Button
        deleteCustomerButton.setOnAction(e -> {
            if (allCustomersTable.getSelectionModel().getSelectedItem() != null) {
                if (allCustomersTable.getSelectionModel().getSelectedItem().hasExistingAppointments()) {
                    addMessage("Customer # " + allCustomersTable.getSelectionModel().getSelectedItem().getCustomerId() + ", " + allCustomersTable.getSelectionModel().getSelectedItem().getName() + ", cannot be deleted without first deleting all of the customer's existing appointments.", RED);
                } else {
                    Stage confirmWindow = new Stage();
                    confirmWindow.initModality(Modality.APPLICATION_MODAL);
                    confirmWindow.setResizable(false);
                    confirmWindow.setTitle("Delete Confirmation");
                    Label infoLabel = new Label("Delete customer # " + allCustomersTable.getSelectionModel().getSelectedItem().getCustomerId() + " , " + allCustomersTable.getSelectionModel().getSelectedItem().getName() + "?");
                    Button confirmButton = new Button("Confirm");
                    Button cancelButton = new Button("Cancel");
                    confirmButton.setOnAction(e2 -> {
                        int customerToDelete = allCustomersTable.getSelectionModel().getSelectedItem().getCustomerId();
                        addMessage("Customer # " + allCustomersTable.getSelectionModel().getSelectedItem().getCustomerId() + ", " + allCustomersTable.getSelectionModel().getSelectedItem().getName() + ", deleted.", BLACK);
                        if (!modifyCustomerTab.isDisabled()) {
                            if (Integer.parseInt(mcCustomerIdField.getText()) == allCustomersTable.getSelectionModel().getSelectedItem().getCustomerId()) {
                                modifyCustomerTab.setDisable(true);
                            }
                        }
                        CustomerList.deleteCustomer(customerToDelete);
                        customerSelector.setItems(CustomerList.getCustomerNames());
                        mCustomerSelector.setItems(CustomerList.getCustomerNames());
                        confirmWindow.close();
                    });
                    cancelButton.setOnAction(e2 -> {
                        addMessage("Delete canceled.  No changes made.", BLACK);
                        confirmWindow.close();
                    });
                    HBox buttonRow = new HBox(10);
                    buttonRow.setAlignment(Pos.CENTER);
                    VBox layout = new VBox(10);
                    layout.setAlignment(Pos.CENTER);
                    buttonRow.getChildren().addAll(confirmButton, cancelButton);
                    layout.getChildren().addAll(infoLabel, buttonRow);
                    layout.setPadding(new Insets(20));
                    Scene multiCustomerSelectorScene = new Scene(layout, 300, 150);
                    confirmWindow.setScene(multiCustomerSelectorScene);
                    confirmWindow.show();
                }
            } else if (allCustomersTable.getSelectionModel().getSelectedItem() == null) {
                addMessage("Please select a customer to delete.", BLACK);
            }

        });

        //New Customer Tab - Save Button
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
                    customerSelector.setItems(CustomerList.getCustomerNames());
                    mCustomerSelector.setItems(CustomerList.getCustomerNames());
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

        //Modify Customer Tab - Save Button
        mcSaveButton.setOnAction(e -> {
            //declare local variables
            boolean validationError = false;

            //validation
            try {
                if (mcNameField.getText().length() > 50 || mcNameField.getText().length() < 1) {
                    validationError = true;
                    mcNameErrorMessage.setText("Name length must be between 1-50.");
                } else {
                    mcNameErrorMessage.setText("");
                }

                if (mcPhoneNumberField.getText().length() > 50 || mcPhoneNumberField.getText().length() < 1) {
                    validationError = true;
                    mcPhoneNumberErrorMessage.setText("Phone length must be between 1-50.");
                } else {
                    mcPhoneNumberErrorMessage.setText("");
                }

                if (mcAddressField.getText().length() > 100 || mcAddressField.getText().length() < 1) {
                    validationError = true;
                    mcAddressErrorMessage.setText("Address length must be between 1-100.");
                } else {
                    mcAddressErrorMessage.setText("");
                }

                if (mcPostalCodeField.getText().length() > 50 || mcPostalCodeField.getText().length() < 1) {
                    validationError = true;
                    mcPostalCodeErrorMessage.setText("Postal Code length must be between 1-50.");
                } else {
                    mcPostalCodeErrorMessage.setText("");
                }

                if (mcCountryField.getValue() == null) {
                    validationError = true;
                    mcCountryErrorMessage.setText("Please select a country.");
                } else {
                    mcCountryErrorMessage.setText("");
                }

                if (mcFirstLevelDivisionField.getValue() == null) {
                    validationError = true;
                    mcFirstLevelDivisionErrorMessage.setText("Please select a region.");
                } else {
                    mcFirstLevelDivisionErrorMessage.setText("");
                }
                if (!validationError) {
                    Customer customerToChange = CustomerList.lookupCustomer(Integer.parseInt(mcCustomerIdField.getText())).get(0);
                    if (customerToChange.hasExistingAppointments()) {
                        for (Appointment appointment : AppointmentList.getAppointmentList()) {
                            if (customerToChange.getCustomerId() == appointment.getCustomerId()) {
                                appointment.setCustomerName(mcNameField.getText());
                            }
                        }
                    }

                    customerToChange.setName(mcNameField.getText());
                    customerToChange.setAddress(mcAddressField.getText());
                    customerToChange.setPhoneNumber(mcPhoneNumberField.getText());
                    customerToChange.setPostalCode(mcPostalCodeField.getText());
                    customerToChange.setCountry(mcCountryField.getValue());
                    customerToChange.setFirstLevelDivision(mcFirstLevelDivisionField.getValue());
                    for (Appointment appointment : AppointmentList.getAppointmentList()) {
                        if (customerToChange.getCustomerId() == appointment.getCustomerId()) ;
                    }


                    allCustomersTable.refresh();
                    allAppointmentsTable.refresh();
                    customerSelector.setItems(CustomerList.getCustomerNames());
                    mCustomerSelector.setItems(CustomerList.getCustomerNames());
                    addMessage("Customer '" + mcNameField.getText() + "' was modified successfully." + " (id: " + mcCustomerIdField.getText() + ")", BLACK);
                    customersTabPane.getSelectionModel().select(allCustomersTab);
                    modifyCustomerTab.setDisable(true);
                } else {
                    addMessage("New customer was NOT added.  Please check your entries.", RED);
                }

            } catch (Exception exception) {
                System.out.println("Exception " + exception + " during save new customer.");
                addMessage("New customer was NOT added.  Exception occurred.", RED);
            }
        });

        //Modify Customer Tab - Cancel Button
        mcCancelButton.setOnAction(e -> {
            customersTabPane.getSelectionModel().select(allCustomersTab);
            modifyCustomerTab.setDisable(true);
            addMessage("Customer modification canceled.", BLACK);
        });

        //Reports Tab - Refresh Button
        rtaRetrieveButton.setOnAction(e -> {

            try {
                if (rtaTypeSelector.getValue() != null && rtaYearTextField.getText() != null) {
                    String selectedType = rtaTypeSelector.getValue();
                    int selectedYear = Integer.parseInt(rtaYearTextField.getText());
                    ObservableList<Integer> allAppointmentsForYear = FXCollections.observableArrayList();
                    if (selectedType.equals("<All>")) {
                        allAppointmentsForYear = AppointmentList.getAppointmentTotals(selectedYear);
                    } else {
                        allAppointmentsForYear = AppointmentList.getAppointmentTotals(selectedYear, selectedType);
                    }
                    janResults.setText(String.valueOf(allAppointmentsForYear.get(0)));
                    febResults.setText(String.valueOf(allAppointmentsForYear.get(1)));
                    marResults.setText(String.valueOf(allAppointmentsForYear.get(2)));
                    aprResults.setText(String.valueOf(allAppointmentsForYear.get(3)));
                    mayResults.setText(String.valueOf(allAppointmentsForYear.get(4)));
                    junResults.setText(String.valueOf(allAppointmentsForYear.get(5)));
                    julResults.setText(String.valueOf(allAppointmentsForYear.get(6)));
                    augResults.setText(String.valueOf(allAppointmentsForYear.get(7)));
                    sepResults.setText(String.valueOf(allAppointmentsForYear.get(8)));
                    octResults.setText(String.valueOf(allAppointmentsForYear.get(9)));
                    novResults.setText(String.valueOf(allAppointmentsForYear.get(10)));
                    decResults.setText(String.valueOf(allAppointmentsForYear.get(11)));
                }
                else {
                    addMessage("Please select an appointment type and enter a valid 4-digit year number.", BLACK);
                }
            } catch (NumberFormatException numberFormatException) {
                addMessage("Please select an appointment type and enter a valid 4-digit year number.", BLACK);
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
