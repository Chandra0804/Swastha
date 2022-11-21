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

public class PatientLoginController implements Initializable{
    
    @FXML
    private Hyperlink NoAccount;

    @FXML
    private TextField Password;

    @FXML
    private Button SignIn;

    @FXML
    private TextField Username;

    @FXML
    private Label patientLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SignIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login.logindoctor(event, Username.getText(), Password.getText());
            }
        });
    }
}
