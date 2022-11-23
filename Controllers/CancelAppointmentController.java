package swastha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class CancelAppointmentController implements Initializable {

    @FXML
    private TableColumn<dataClassApptCancel, String> AppointmentIDColumn;

    @FXML
    private TextField AppointmentIDField;

    @FXML
    private Button BackButton;

    @FXML
    private Button CancelButton;

    @FXML
    private DatePicker DateField;

    @FXML
    private TableColumn<dataClassApptCancel, String> DoctorNameColumn;

    @FXML
    private TableColumn<dataClassApptCancel, String> DoctorSpecializationColumn;

    @FXML
    private TextField EmailField;

    @FXML
    private TableColumn<dataClassApptCancel, String> HospitalNameColumn;

    @FXML
    private Button SearchButton;

    @FXML
    private TableView<dataClassApptCancel> TableView;

    @FXML
    private TableColumn<dataClassApptCancel, String> TimeColumn;

    @FXML
    private TextField PatientEmailField;

    ObservableList<dataClassApptCancel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassApptCancel, String>("DoctorName"));
        HospitalNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassApptCancel, String>("HospitalName"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<dataClassApptCancel, String>("AppointmentTime"));
        DoctorSpecializationColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassApptCancel, String>("DoctorSpecialization"));

        // when cancel button is pressed, the appointment with the given appointment id
        // is deleted.
        CancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                            "PVCTARUN");
                    if (AppointmentIDField != null) {
                        PreparedStatement stmt = con.prepareStatement(
                                "delete from appointment where appointmentID = ?");
                        stmt.setString(1, AppointmentIDField.getText());
                        stmt.executeUpdate();
                    } else {
                        System.out.println("NO SUCH APPT. ID TO DELETE");
                    }

                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        });

        SearchButton.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (DateField != null) {
                            result();
                            TableView.getItems().setAll(list);
                        } else {
                            System.out.println("ENTER DATE!"); // just a log
                        }
                    }

                });

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

    class dataClassApptCancel {
        String DoctorName;
        String DoctorSpecialization;
        String HospitalName;
        String AppointmentTime;
        String AppointmentID;

        dataClassApptCancel(String docname, String docspec, String dochos, String appttime, String apptid) {
            this.DoctorName = docname;
            this.DoctorSpecialization = docspec;
            this.HospitalName = dochos;
            this.AppointmentTime = appttime;
            this.AppointmentID = apptid;
        }
    }

    private void result() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select doctorEmail,time,appointmentID from appointment where date = '"
                            + DateField.getValue().toString() + "' and patientEmail = '" + PatientEmailField.getText()
                            + "'");
            int i = 0;
            while (rs.next()) {
                i = 1;
                String docMail = rs.getString(1);
                String apptTime = rs.getString(3);
                String apptID = rs.getString(4);
                ResultSet rs2 = stmt
                        .executeQuery(
                                "select name,specialization,hospital_ID from doctor where email = '" + docMail + "'");
                String docName = rs2.getString(1);
                String docSpec = rs2.getString(2);
                String docHospID = rs2.getString(3);

                ResultSet rs3 = stmt.executeQuery("select name from hospital where hospital_id = '" + docHospID + "'");
                String HospName = rs3.getString(1);
                dataClassApptCancel data = new dataClassApptCancel(docName, docSpec, HospName, apptTime, apptID);
                list.add(data);

            }
            if (i == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("No Appointments Scheduled For That Date.");
                alert.show();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
