import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SearchDoctorController {

    @FXML
    private Button Back;

    @FXML
    private TableColumn<?, ?> DisplayDoctorName;

    @FXML
    private TableColumn<?, ?> DoctorEmail;

    @FXML
    private TextField DoctorName;

    @FXML
    private TableColumn<?, ?> HospitalID;

    @FXML
    private TableColumn<?, ?> HospitalName;

    @FXML
    private Button Search;

    @FXML
    private TableColumn<?, ?> Specialization;

}
