package swastha;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Constructor;

import com.mysql.cj.conf.IntegerProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DoctorAppointmentsController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<dataClass, String> DateColumn;

    @FXML
    private TableColumn<dataClass, String> PatientNameColumn;

    @FXML
    private TableView<dataClass> TableView;

    @FXML
    private TableColumn<dataClass, String> TimeColumn;

    @FXML
    private TextField EmailField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        DateColumn.setCellValueFactory(new PropertyValueFactory<dataClass, String>("Date"));
        PatientNameColumn.setCellValueFactory(new PropertyValueFactory<dataClass, String>("Name"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<dataClass, String>("Time"));
        TableView.getItems().setAll(result()); // here im setting the items in the table to what's in the resultant list from result().

      // below is the functionality for back button
        BackButton.setOnAction(
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

    // data class to make objects and insert them into the table
    public class dataClass {
        private String PatientName;
        private String Date;
        private String Time;

        dataClass(String patientname, String date, String time) {
            this.PatientName = patientname;
            this.Date = date;
            this.Time = time;
        }

        public void setPatientName(String patientName) {
            PatientName = patientName;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getDate() {
            return Date;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getTime() {
            return Time;
        }

    }

    // function that gets data from the database and returns the resultant data
    private List<dataClass> result() {
        List ll = new LinkedList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "PVCTARUN");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select patientEmail,doctorEmail,date,time from appointment where doctorEmail = '"
                            + EmailField.getText() + "'");
            if (rs.next()) {
                String Date = rs.getString(3);
                String Time = rs.getString(4);
                String patientEmail = rs.getString(1);
                String patientName;

                rs = stmt.executeQuery("select name from patient where email = '" + patientEmail + "'");
                patientName = rs.getString(1);

                dataClass data = new dataClass(patientName, Date, Time);
                ll.add(data);

            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return ll;
    }

}
