import Model.Appointment;
import Model.AppointmentList;
import Model.CustomerList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author Brandon Maxfield
 *
 * This Application is organized based on the MVC Structure and also uses the Data Access Object Pattern (DAO) for
 * accessing the database.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //load window
        Parent root = FXMLLoader.load(getClass().getResource("View/mainWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();

        //build the lists
        CustomerList customerList = new CustomerList();
        AppointmentList appointmentList = new AppointmentList();

        //load the demo lists

    }
}
