package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


//As a reminder, the FXML file is pointing to this controller class.

public class LoginWindowController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label timezoneHeaderLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label timezoneLabel;


    //This is the initialization method, and it is optional.  It is automatically called after the FXML file is loaded.
    //I'm including it here as a reminder to myself that it is something I can use.
    public void initialize(){
    //initialization here
        //Set the language
        String userLocale = Locale.getDefault().toString();
        System.out.println(userLocale);
        System.out.println("user language was detected as " + Locale.getDefault().getDisplayLanguage());

        ResourceBundle labels = ResourceBundle.getBundle("Internationalization.ResourceBundle", Locale.getDefault());

        timezoneHeaderLabel.setText(labels.getString("timezoneHeaderLabel"));
        userIdLabel.setText(labels.getString("userIdLabel"));
        passwordLabel.setText(labels.getString("passwordLabel"));
        loginButton.setText(labels.getString("loginButton"));

        //Set the local time zone
        timezoneLabel.setText(ZoneId.systemDefault().toString());
    }

    //Event Listeners - Note that the listener is linked to the view by the FXML file itself.
    public void loginButtonListener() throws Exception{
        System.out.println("login button clicked");
        ResourceBundle labels = ResourceBundle.getBundle("Internationalization.ResourceBundle", Locale.getDefault());

        //login validation with actual users is beyond the scope of this project.
        try {
            if (userIdField.getText().equals("test") && passwordField.getText().equals("test")) {

                //set a pointer to the current stage
                Stage thisStage = (Stage) loginButton.getScene().getWindow();

                //get the new scene from the fxml file and set it to the stage
                System.out.println("about to load mainWindow.fxml");
                Parent mainScene = FXMLLoader.load(getClass().getResource("../View/mainWindow.fxml"));
                System.out.println("Successfully loaded mainWindow.fxml");
                System.out.println("about to set the scene to mainWindow.fxml");
                thisStage.setScene(new Scene(mainScene, 923, 505));
                System.out.println("Successfully set the scene to mainWindow.fxml");

                //create the main window controller
                MainWindowController mainWindowController = new MainWindowController();

            }
            else {
                System.out.println("invalid credentials");
                errorLabel.setText(labels.getString("errorLabel"));
            }
        } catch (Exception exception){
            System.out.println(exception);
        }

    }



}
