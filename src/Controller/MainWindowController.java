package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class MainWindowController {

    //all appointments table
    @FXML
    private TableView<Appointment> allAppointmentsTable;

    //all customers table
    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private ScrollPane alertScrollPane;

    @FXML
    private VBox alertMessages;

    @FXML
    private Label initialAlertMessage;

    //init optional
    public void initialize(){
        addMessage("Successfully created MainWindowController instance");

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


    }

    public void addMessage(String message){
        Label messageToAddLabel = new Label(message);
        System.out.println(alertMessages);
        alertMessages.getChildren().add(messageToAddLabel);
    }
}
