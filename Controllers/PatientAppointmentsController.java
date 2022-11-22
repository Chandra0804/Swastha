package swastha;

import java.beans.EventHandler;
import java.net.URL;
import java.util.*;

import com.mysql.cj.protocol.Resultset;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class PatientAppointmentsController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<dataClassPatient, String> DateColumn;

    @FXML
    private TableColumn<dataClassPatient, String> DoctorColumn;

    @FXML
    private TableColumn<dataClassPatient, Integer> FeesColumn;

    @FXML
    private TableColumn<dataClassPatient, String> HospitalColumn;

    @FXML
    private TableColumn<dataClassPatient, String> SpecializationColumn;

    @FXML
    private TableView<dataClassPatient> TableView;

    @FXML
    private TableColumn<dataClassPatient, String> TimeColumn;

    @FXML
    private TextField EmailField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateColumn.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("Date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("Time"));
        DoctorColumn.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorName"));
        FeesColumn.setCellValueFactory(new PropertyValueFactory<dataClassPatient, Integer>("DoctorFees"));
        HospitalColumn.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorHospital"));
        SpecializationColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorSpecialization"));
        TableView.getItems().setAll(resultPatient());

        BackButton.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            DBUtilities.changeScene(event, "PatientHome.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

    class dataClassPatient {
        String Date;
        String Time;
        String DoctorName;
        int DoctorFees;
        String DoctorHospital;
        String DoctorSpecialization;

        dataClassPatient(String doctorName, String doctorSpecialization, String date, String time,
                String doctorHospital, int fees) {

            this.Date = date;
            this.Time = time;
            this.DoctorName = doctorName;
            this.DoctorHospital = doctorHospital;
            this.DoctorSpecialization = doctorSpecialization;
            this.DoctorFees = fees;

        }
    }

    private List<dataClassPatient> resultPatient() {
        ResultSet rs;
        ResultSet rs1;
        List ll = new LinkedList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "PVCTARUN");
            Statement stmt = con.createStatement();
            rs = stmt
                    .executeQuery("select patientEmail,doctorEmail,date,time from appointment where patientEmail = '"
                            + EmailField.getText() + "'");
            while (rs.next()) {

                // getting Appointment Info
                String Date = rs.getString(3);
                String Time = rs.getString(4);
                String DoctorEmail = rs.getString(2);

                // getting Doctor info
                rs1 = stmt.executeQuery("select name, hospital_id, fees,specialization from doctor where email = '"
                        + DoctorEmail + "'");
                String DoctorName = rs.getString(1);
                String Hospital_ID = rs.getString(2);
                int Fees = rs.getInt(3);
                String Specialization = rs.getString(4);

                // getting Hospital Info
                ResultSet rs2 = stmt
                        .executeQuery("select name from hospital where hospital_id = '" + Hospital_ID + "'");
                String HospitalName = rs2.getString(1);
                dataClassPatient data = new dataClassPatient(DoctorName, Specialization, Date, Time, HospitalName,
                        Fees);
                ll.add(data);

            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return ll;
    }

}
