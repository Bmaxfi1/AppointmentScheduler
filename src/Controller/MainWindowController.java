package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class MainWindowController {

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

    //other
    @FXML
    private ScrollPane alertScrollPane;

    @FXML
    private VBox alertMessages;

    @FXML
    private Label initialAlertMessage;

    //init
    public void initialize(){
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
        ObservableList<Customer> customerList;
        ObservableList<Appointment> appointmentList;

        //load demo data into lists
        customerList = Model.DemoData.getDemoCustomerList();
        appointmentList = Model.DemoData.getDemoAppointmentList();

        //TODO load database data into lists
        //
        //

        //Insert the lists into the tables
        allCustomersTable.setItems(customerList);
        allAppointmentsTable.setItems(appointmentList);

        addMessage("MainWindowController instantiated");
        System.out.println("Successfully completed MainWindowController instance");
    }

    public void addMessage(String message){
        Label messageToAddLabel = new Label(message);
        System.out.println(alertMessages);
        alertMessages.getChildren().add(messageToAddLabel);
    }
}
