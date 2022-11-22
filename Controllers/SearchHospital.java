import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchHospital implements Initializable{

    @FXML
    private Button Back;

    @FXML
    private TableColumn<Hospital, String> Contact;

    @FXML
    private TableColumn<Hospital, String> Name;

    @FXML
    private Label HospitalSearch;

    @FXML
    private TableView<Hospital> Search;

    // ObservableList<Hospital> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(new PropertyValueFactory<Hospital, String>("Hospital_Name"));
        Contact.setCellValueFactory(new PropertyValueFactory<Hospital, String>("ph_no"));

        Back.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

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
    }
}
