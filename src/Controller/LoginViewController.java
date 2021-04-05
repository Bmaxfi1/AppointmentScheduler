package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;


//As a reminder, the FXML file is pointing to this controller class.

public class LoginViewController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label timezoneLabel;


    //This is the initialization method, and it is optional.  It is automatically called after the FXML file is loaded.
    //I'm including it here as a reminder to myself that it is something I can use.
    public void initialize(){
    //initialization here
    }


    //Event Listeners - Note that the listener is linked to the view by the FXML file itself.
    public void loginButtonListener() throws Exception{
        System.out.println("login button clicked");
        //TODO user login validation
        try {
            if (userIdField.getText().equals("test") && passwordField.getText().equals("test")) {

                //set a pointer to the current stage
                Stage thisStage = (Stage) loginButton.getScene().getWindow();

                //get the new scene from the fxml file and set it to the stage
                Parent mainScene = FXMLLoader.load(getClass().getResource("../View/mainWindow.fxml"));
                thisStage.setScene(new Scene(mainScene, 734, 500));

            }
            else {
                System.out.println("invalid credentials");
                errorLabel.setText("Invalid credentials.  Check your user ID and password.");
            }
        } catch (Exception exception){
            System.out.println(exception);
        }

    }



}
