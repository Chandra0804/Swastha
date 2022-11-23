import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SearchHospitalController {

    @FXML
    private Button Back;

    @FXML
    private TableColumn<?, ?> DisplayHospitalName;

    @FXML
    private TableColumn<?, ?> HospitalID;

    @FXML
    private TextField HospitalName;

    @FXML
    private TableColumn<?, ?> PhoneNumber;

    @FXML
    private Button Search;

}
