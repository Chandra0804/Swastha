import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DoctorLoginController implements Initializable{

    @FXML
    private TextField Email;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField Password;

    @FXML
    private Hyperlink CreateAccountLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtilities.logindoctor(event, Email.getText(), Password.getText());
            }
        });
        
    }
}
