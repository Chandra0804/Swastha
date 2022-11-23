import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BookAppointmentController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Button BookButton;

    @FXML
    private DatePicker DateField;

    @FXML
    private TableColumn<dataClassAppt, String> DoctorEmailColumn;

    @FXML
    private TextField DoctorEmailField;

    @FXML
    private TableColumn<dataClassAppt, String> DoctorNameColumn;

    @FXML
    private TextField DoctorNameField;

    @FXML
    private TableColumn<dataClassAppt, Double> FeesColumn;

    @FXML
    private TableColumn<dataClassAppt, String> HospitalNameColumn;

    @FXML
    private TextField HospitalNameField;

    @FXML
    private Button SearchButton;

    @FXML
    private TableView<dataClassAppt> TableView;

    @FXML
    private TextField TimeField;

    @FXML
    private TextField PatientEmailField;

    ObservableList<dataClassAppt> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        DoctorEmailColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("DoctorEmail"));
        DoctorNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("DoctorName"));
        HospitalNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("HospitalName"));
        FeesColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, Double>("Fees"));

        BookButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                            "MOHAN RAO");
                    PreparedStatement stmt = con.prepareStatement("insert into appointment values(?,?,?,?,?)");
                    String uuid = UUID.randomUUID().toString();
                    stmt.setString(1, uuid);
                    stmt.setString(2, TimeField.getText());
                    stmt.setString(3, DateField.getValue().toString());
                    stmt.setString(4, DoctorEmailField.getText());
                    stmt.setString(5, PatientEmailField.getText());
                    stmt.executeUpdate();

                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        });

        SearchButton.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        result();
                        TableView.getItems().setAll(list);
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

    class dataClassAppt {
        String DoctorName;
        String DoctorEmail;
        String HospitalName;
        Double Fees;

        dataClassAppt(String doctorName, String doctorEmail, String hospitalName, Double fees) {
            this.DoctorEmail = doctorEmail;
            this.DoctorName = doctorName;
            this.HospitalName = hospitalName;
            this.Fees = fees;
        }

    }

    private void result() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select name,email,hospital_id,fees from doctor where name = '%"
                    + DoctorNameField.getText() + "%'");
            while (rs.next()) {
                String docName = rs.getString(1);
                String docMail = rs.getString(2);
                String hospID = rs.getString(3);
                Double fee = rs.getDouble(4);

                ResultSet rs2 = stmt
                        .executeQuery("select hospital_name from hospital where hospital_id = '" + hospID + "'");
                String hospName = rs2.getString(1);
                dataClassAppt data = new dataClassAppt(docName, docMail, hospName, fee);
                list.add(data);

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
