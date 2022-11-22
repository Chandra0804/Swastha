package swastha;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditPatientController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private Button back;

    @FXML
    private TextField height;

    @FXML
    private TextField password;

    @FXML
    private TextField pincode;

    @FXML
    private Button save;

    @FXML
    private TextField username;

    @FXML
    private TextField weight;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (height != null) {
                    try {
                        DBUtilities.editDoctor(username.getText(), password.getText(), "height",
                                height.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (address != null) {
                    try {
                        DBUtilities.editDoctor(username.getText(), password.getText(), "Address",
                                address.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (weight != null) {
                    try {
                        DBUtilities.editDoctor(username.getText(), password.getText(), "weight",
                                weight.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pincode != null) {
                    try {
                        DBUtilities.editDoctor(username.getText(), password.getText(), "Pincode",
                                pincode.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Updated!");
                alert.show();

            }

        });

        back.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            DBUtilities.changeScene(event, "DoctorHome.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

}
