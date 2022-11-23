import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtilities {

    public static void createTables() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                    "MOHAN RAO");
            stmt = con.createStatement();

            stmt.executeUpdate(
                    "create table if not exists patient(name varchar(50), Blood_Group varchar(20), DateOfBirth varchar(20), email varchar(50), password varchar(50), Gender varchar(20), Address varchar(300), Pincode int, Patient_Weight int, Patient_Height decimal(10,2))");

            stmt.executeUpdate(
                    "create table if not exists doctor(Hospital_ID varchar(30), Name varchar(50), Gender varchar(20), DateOfBirth varchar(20), Specialization varchar(50), Email varchar(50), Password varchar(50), Address varchar(200), Pincode int,Fees double)");
            stmt.executeUpdate(
                    "create table if not exists hospital(hospital_name varchar(50), hospital_ID varchar(50), ph_no varchar(15))");
            stmt.executeUpdate(
                    "create table if not exists appointment(appointmentID varchar(50),time varchar(50),date varchar(50),doctorEmail varchar(50) , patientEmail varchar(50))");
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static void changeScene(ActionEvent event, String fxmlfile) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(DBUtilities.class.getResource(fxmlfile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    
public static void SignUpDoctor(ActionEvent event, Doctor doc)
{

Connection con = null;
PreparedStatement psInsert = null;
PreparedStatement psCheckUserExists = null;
ResultSet rs = null;

try
{
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
psCheckUserExists = con.prepareStatement("select * from doctor where email = ?");
psCheckUserExists.setString(1, doc.getEmail());
rs = psCheckUserExists.executeQuery();

if (rs.isBeforeFirst())
{
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("There is already an account registered with this email");
alert.show();
} 



else
{
psInsert = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?)");
psInsert.setString(1, doc.getHospital_ID());
psInsert.setString(2, doc.getName());
psInsert.setString(3, doc.getGender());
psInsert.setString(4, doc.getDob());
psInsert.setString(5, doc.getSpecialization());
psInsert.setString(6, doc.getEmail());
psInsert.setString(7, doc.getPassword());
psInsert.setString(8, doc.getAddress());
psInsert.setInt(9, doc.getPincode());
psInsert.setDouble(10, doc.getFees());



String csvFile = "E:\\IIIT SRI CITY\\Swastha_Project\\EXCEL_CSV_DATA\\CSV_Data\\Doctor_CSV.csv";

CSVWriter cw = new CSVWriter(new FileWriter(csvFile, true));

String pincode = Integer.toString(doc.getPincode());
String fees = Double.toString(doc.getFees());


String Add_Data_To_CSV[] = {doc.getHospital_ID(), doc.getName(), doc.getGender(), doc.getDob(), doc.getSpecialization(), doc.getEmail(), doc.getPassword(), doc.getAddress(), pincode, fees};

cw.writeNext(Add_Data_To_CSV);

cw.close();
	
System.out.print("\nInsertion of Doctor Record into Database Server is Successfully Implemented\n");

changeScene(event, "DoctorLogin.fxml");

}



catch (Exception e)
{
System.err.println(e);
}

finally
{
if (rs != null)
	{ try { rs.close(); }
          catch (Exception e) { System.err.println(e); }
        }


if (psCheckUserExists != null)
	{ try { psCheckUserExists.close(); }
          catch (Exception e) { System.err.println(e); }
        }


if (psInsert != null)
	{ try { psInsert.close(); }
	  catch (Exception e) { System.err.println(e); }
        }


if (con != null)
	{ try { con.close(); }
	  catch (Exception e) { System.err.println(e); }
        }

}



} // End Sign_Up_Doctor
        
        
        
        
        
        

    public static void LoginDoctor(ActionEvent event, String Email, String Password) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, Email);
            rs1 = ps.executeQuery();
            if (rs1.isBeforeFirst()) {
                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, Email);
                stmt.setString(2, Password);
                rs2 = stmt.executeQuery();
                if (rs2.isBeforeFirst()) {
                    changeScene(event, "DoctorHome.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect email or password");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please recheck your entered email or password");
                alert.show();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void SignUpPatient(ActionEvent event, Patient Patient_Object) {

        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = connection.prepareStatement("select * from patient where email = ?");
            ps.setString(1, Patient_Object.getEmail());

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            } else {

                String Name, DateOfBirth, Email, Password, Gender, Address, Blood_Group;
                int Pincode, Patient_Weight;
                double Patient_Height;
                Name = Patient_Object.getName();
                Blood_Group = Patient_Object.getBlood_Group();
                DateOfBirth = Patient_Object.getDob();
                Email = Patient_Object.getEmail();
                Password = Patient_Object.getPassword();
                Gender = Patient_Object.getGender();
                Address = Patient_Object.getAddress();
                Pincode = Patient_Object.getPincode();
                Patient_Weight = Patient_Object.getWeight();
                Patient_Height = Patient_Object.getHeight();

                stmt = connection.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, Name);
                stmt.setString(2, Blood_Group);
                stmt.setString(3, DateOfBirth);
                stmt.setString(4, Email);
                stmt.setString(5, Password);
                stmt.setString(6, Gender);
                stmt.setString(7, Address);
                stmt.setInt(8, Pincode);
                stmt.setInt(9, Patient_Weight);
                stmt.setDouble(10, Patient_Height);
                stmt.executeQuery();

                changeScene(event, "PatientLogin.fxml");

            }

        } // End try block

        catch (Exception e) {
            System.err.print(e);
        }

        finally {

            if (rs != null) {
                try {
                    rs.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

        } // End finally

    } // End Sign_Up_Patient Method

    public static void LoginPatient(ActionEvent event, String Email, String Password) {

        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = connection.prepareStatement("select * from patient where email = ?");
            ps.setString(1, Email);

            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {
                stmt = connection.prepareStatement("select * from patient where email = ? and password = ?");
                stmt.setString(1, Email);
                stmt.setString(2, Password);
                rs2 = stmt.executeQuery();
                if (rs2.isBeforeFirst()) {
                    changeScene(event, "PatientHome.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect email or password");
                    alert.show();
                }

            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }

        } // End try block

        catch (Exception e) {
            System.err.print(e);
        }

        finally {

            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

        } // End finally

    } // End Login_Patient Class

    public static void CreateAppointment(ActionEvent event, Appointment app) {
        Connection con = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psInsert = con.prepareStatement("insert into appointment values(?,?,?,?,?)");
            psInsert.setString(1, app.getId());
            psInsert.setString(2, app.getTime());
            psInsert.setString(3, app.getDate());
            psInsert.setString(4, app.getDoctor().getEmail());
            psInsert.setString(5, app.getPatient().getEmail());
            changeScene(event, "AppointmentsofPatient.fxml");

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Cancel_Appointment(ActionEvent event, Appointment app) {
        Connection con = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckAppointmentExists = con.prepareStatement("select * from appointment where appointmentID = ?");
            psCheckAppointmentExists.setString(1, app.getId());
            rs = psCheckAppointmentExists.executeQuery();

            if (rs.isBeforeFirst()) {
                psDelete = con.prepareStatement("delete from appointment where appointmentID = ?");
                psDelete.setString(1, app.getId());
                psDelete.executeQuery();
                changeScene(event, "AppointmentsofPatient.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointment found to delete with given Appointment ID ");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckAppointmentExists != null) {
                try {
                    psCheckAppointmentExists.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Search_Appointment(ActionEvent event, Appointment app) {
        Connection con = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckAppointmentExists = con.prepareStatement("select * from appointment where appointmentID = ?");
            psCheckAppointmentExists.setString(1, app.getId());
            rs = psCheckAppointmentExists.executeQuery();

            if (rs.isBeforeFirst()) {
                changeScene(event, "AppointmentsofPatient.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointments found");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckAppointmentExists != null) {
                try {
                    psCheckAppointmentExists.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void searchHospital(ActionEvent event, Hospital hospital) {

        Connection connection = null;
        PreparedStatement psCheckHospitalStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckHospitalStatus = connection.prepareStatement("select * from hospital where hospital_name = ?");
            resultset = psCheckHospitalStatus.executeQuery();

            java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> hospitalResultList = new ArrayList<>(columnCount);
            while (resultset.next()) {
                int i = 1;
                while (i <= columnCount) {
                    hospitalResultList.add(resultset.getString(i++));
                }

                if (resultset.isBeforeFirst()) {
                    changeScene(event, "HospitalList.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No Hospitals found");
                    alert.show();
                }
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckHospitalStatus != null) {
                try {
                    psCheckHospitalStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void searchDoctor(ActionEvent event, Doctor doctor) {

        Connection connection = null;
        PreparedStatement psCheckDoctorStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckDoctorStatus = connection.prepareStatement("select * from doctor where name = ?");
            resultset = psCheckDoctorStatus.executeQuery();
            java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> doctorResultList = new ArrayList<>(columnCount);
            while (resultset.next()) {
                int i = 1;
                while (i <= columnCount) {
                    doctorResultList.add(resultset.getString(i++));
                }

                if (resultset.isBeforeFirst()) {
                    changeScene(event, "DoctorList.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No Doctors found");
                    alert.show();
                }
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (psCheckDoctorStatus != null) {
                try {
                    psCheckDoctorStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editDoctor(ActionEvent event, String email, String password, String Attribute,
            String change_to) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, email);
            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {

                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, email);
                rs2 = stmt.executeQuery();

                if (rs2.isBeforeFirst()) {

                    // changing the sql relation
                    psupdate = con.prepareStatement("update doctor set ? = ? where email = ? and password = ?");
                    psupdate.setString(1, Attribute);
                    psupdate.setString(2, change_to);
                    psupdate.setString(3, email);
                    psupdate.setString(4, password);

                    psupdate.executeUpdate();
                    changeScene(event, "DoctorHome.fxml");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Password!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editPatient(ActionEvent event, Patient patient, String Attribute, String change_to) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from patient where email = ?");
            ps.setString(1, patient.getEmail());
            rs1 = ps.executeQuery();
            if (rs1.isBeforeFirst()) {

                stmt = con.prepareStatement("select * from patient where email = ? and password = ?");
                stmt.setString(1, patient.getEmail());
                rs2 = stmt.executeQuery();

                if (rs2.isBeforeFirst()) {

                    if ((Attribute.toLowerCase()).equals("address")) {
                        patient.setAddress(change_to);
                    } else if ((Attribute.toLowerCase()).equals("height")) {
                        patient.setHeight(Double.parseDouble(change_to));
                    } else if ((Attribute.toLowerCase()).equals("weight")) {
                        patient.setWeight(Integer.parseInt(change_to));
                    } else if ((Attribute.toLowerCase()).equals("pincode")) {
                        patient.setPincode(Integer.parseInt(change_to));
                    }

                    psupdate = con.prepareStatement("update patient set ? = ? where email = ? and password = ?");
                    psupdate.setString(1, Attribute);
                    psupdate.setString(2, change_to);
                    psupdate.setString(3, patient.getEmail());
                    psupdate.setString(4, patient.getPassword());

                    psupdate.executeUpdate();
                    changeScene(event, "PatientHome.fxml");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Password!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public void Load_Patient_CSV() {

        try {

            String csvFilePath = "Patient_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists patient");

            stmt.execute(
                    "create table if not exists patient(name varchar(50), Blood_Group varchar(20), DateOfBirth varchar(20), Email varchar(50), Password varchar(50), Gender varchar(20), Address varchar(300), Pincode int, Patient_Weight int, Patient_Height decimal(10,2))");
            stmt.execute("truncate table patient");

            PreparedStatement statement = connection
                    .prepareStatement("insert into Patient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 20;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String ID = data[0];
                String name = data[1];
                String blood_group = data[2];
                String dob = data[3];
                String email = data[4];
                String password = data[5];
                String gender = data[6];

                String address = "";
                int index = 7;

                while (true) {
                    boolean isNumeric = data[index].chars().allMatch(Character::isDigit);
                    if (isNumeric) {
                        int length = address.length();
                        address = address.substring(2, length - 1);
                        break;
                    } else {
                        address = address + "," + data[index];
                        index = index + 1;
                    }
                }

                int pincode = Integer.parseInt(data[index]);
                int weight = Integer.parseInt(data[index + 1]);
                double height = Double.parseDouble(data[index + 2]);

                statement.setString(1, ID);
                statement.setString(2, name);
                statement.setString(3, blood_group);
                statement.setString(4, dob);
                statement.setString(5, email);
                statement.setString(6, password);
                statement.setString(7, gender);
                statement.setString(8, address);
                statement.setInt(9, pincode);
                statement.setInt(10, weight);
                statement.setDouble(11, height);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Patient Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    } // End Load_Patient_CSV Class

    public void Load_Doctor_CSV() {

        try {

            String csvFilePath = "Doctor_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists doctor");
            stmt.execute(
                    "create table if not exists doctor(Hospital_ID varchar(30), Name varchar(50), Gender varchar(20), DateOfBirth varchar(20), Specialization varchar(50), Email varchar(50), Password varchar(50), Address varchar(200), Pincode int)");
            stmt.execute("truncate table doctor");

            PreparedStatement statement = connection
                    .prepareStatement("insert into doctor values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 20;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String ID = data[0];
                String name = data[1];
                String gender = data[2];
                String dob = data[3];
                String specialization = data[4];
                String email = data[5];
                String password = data[6];

                String address = "";
                int index = 7;

                while (true) {
                    boolean isNumeric = data[index].chars().allMatch(Character::isDigit);
                    if (isNumeric) {
                        int length = address.length();
                        address = address.substring(2, length - 1);
                        break;
                    } else {
                        address = address + "," + data[index];
                        index = index + 1;
                    }
                }

                int pincode = Integer.parseInt(data[index]);

                statement.setString(1, ID);
                statement.setString(2, name);
                statement.setString(3, gender);
                statement.setString(4, dob);
                statement.setString(5, specialization);
                statement.setString(6, email);
                statement.setString(7, password);
                statement.setString(8, address);
                statement.setInt(9, pincode);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Doctor Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    } // End Load_Doctor_CSV Class

}
