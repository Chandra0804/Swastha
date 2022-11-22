import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchDoctor implements Initializable {

    @FXML
    private TableView<Doctor> Search;

    @FXML
    private TableColumn<Doctor, String> HospitalID;

    @FXML
    private TableColumn<Doctor, String> Name;

    @FXML
    private Label DoctorSearch;

    @FXML
    private TableColumn<Doctor, String> Specialization;

    @FXML
    private Button Back;

    // ObservableList<Doctor> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(new PropertyValueFactory<Doctor, String>("Name"));
        HospitalID.setCellValueFactory(new PropertyValueFactory<Doctor, String>("HospitalID"));
        Specialization.setCellValueFactory(new PropertyValueFactory<Doctor, String>("Specialization"));
        
        Back.setOnAction(
            new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    try {
                        DBUtilities.changeScene(event, "PatientHome.fxml");
                    } 
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            });

        // Search.setItems(list);
    }

}
