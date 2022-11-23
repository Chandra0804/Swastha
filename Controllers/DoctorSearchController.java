package swastha;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
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
    private Button Back;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> DisplayDoctorName;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> DoctorEmail;

    @FXML
    private TextField DoctorName;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> HospitalID;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> HospitalName;

    @FXML
    private Button Search;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> Specialization;

    @FXML
    private TableView<dataClassSearchDoctor> TableView;

    ObservableList<dataClassSearchDoctor> List;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayDoctorName
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("DoctorName"));
        DoctorEmail
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("DoctorEmail"));

        HospitalName.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("HospitalName"));
        HospitalID.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("HospitalID"));
        Specialization.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("Specialization"));

        Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List = mySQLConnect.getDataHospitals();
                TableView.setItems(List);
            }

        });
        Back.setOnAction(
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

    class dataClassSearchDoctor {
        String DoctorName;
        String DoctorEmail;
        String HospitalName;
        String HospitalID;
        String Specialization;

        dataClassSearchDoctor(String hospitalName, String hospitalID, String doctorName, String specialization,
                String doctorEmail) {
            DoctorName = doctorName;
            DoctorEmail = doctorEmail;
            HospitalName = hospitalName;
            HospitalID = hospitalID;
            Specialization = specialization;
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

        public static ObservableList<dataClassSearchDoctor> getDataHospitals() {
            Connection conn = connectDB();
            ObservableList<dataClassSearchDoctor> List = FXCollections.observableArrayList();
            try {
                PreparedStatement ps = conn
                        .prepareStatement(
                                "select name, email,hospital_name, hospital_id, specialization from doctor natural join hospital where name = ?");
                ps.setString(1, DoctorName.getText());
                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i = 1;
                    dataClassSearchDoctor data = new dataClassSearchDoctor(rs.getString(3), rs.getString(4),
                            rs.getString(1), rs.getString(5), rs.getString(2));
                    List.add(data);
                }
                if (i == 0) {
                    System.out.println("No doctor with given name.");
                }

            } catch (Exception exception) {
                System.err.println(exception);
            }
            return List;
        }
    }

}
