package swastha;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HospitalSearchController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<dataClassSearchHospital, String> HospitalIDColumn;

    @FXML
    private TableColumn<dataClassSearchHospital, String> HospitalNameColumn;

    @FXML
    private TextField HospitalNameField;

    @FXML
    private TableColumn<dataClassSearchHospital, String> PhoneNumberColumn;

    @FXML
    private Button Search;

    @FXML
    private TableView<dataClassSearchHospital> TableView;

    ObservableList<dataClassSearchHospital> List;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HospitalNameColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("HospitalName"));
        PhoneNumberColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("PhoneNumber"));
        HospitalIDColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("HospitalID"));

        Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List = mySQLConnect.getDataHospitals();
                TableView.setItems(List);
            }

        });

    }

    class dataClassSearchHospital {
        String HospitalName;
        String PhoneNumber;
        String HospitalID;

        dataClassSearchHospital(String hospitalName, String hospitalID, String phoneNumber) {
            this.HospitalName = hospitalName;
            this.PhoneNumber = phoneNumber;
            this.HospitalID = hospitalID;
        }
    }

    class mySQLConnect {
        Connection conn = null;

        public static Connection connectDB() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                        "MOHAN RAO");
                JOptionPane.showMessageDialog(null, conn);
                return conn;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                return null;

            }
        }

        public static ObservableList<dataClassSearchHospital> getDataHospitals() {
            Connection conn = connectDB();
            ObservableList<dataClassSearchHospital> List = FXCollections.observableArrayList();
            try {
                PreparedStatement ps = conn
                        .prepareStatement("select hospital_name,hospital_id,ph_no from Hospital where name = ?");
                ps.setString(1, HospitalNameField.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    dataClassSearchHospital data = new dataClassSearchHospital(rs.getString(1), rs.getString(2),
                            rs.getString(3));
                    List.add(data);
                }

            } catch (Exception exception) {
                System.err.println(exception);
            }
            return List;
        }
    }

}
