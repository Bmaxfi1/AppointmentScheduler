import Model.*;
import Controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Brandon Maxfield
 *
 * FUTURE ENHANCEMENTS
 * 1. Resizable window with element resizing.
 * 2. Timestamp on alert messages.
 * 3. Searchable contact list for adding/modifying appointments.
 * 4. Typable dates in appointment form.
 * 5. Flexible time input formats.  Current format must be 'hh:mm'.
 *
 *
 * This Application is organized based on the MVC Structure and also uses the Data Access Object Pattern (DAO) for
 * accessing the database.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //create test user
        User testUser = new User(1, "test", "test");

        //load login window
        Parent loginWindow = FXMLLoader.load(getClass().getResource("View/loginWindow.fxml"));
        primaryStage.setTitle("AppointmentScheduler");
        primaryStage.setScene(new Scene(loginWindow, 387, 253));
        primaryStage.setResizable(false);
        primaryStage.show();





    }
}
