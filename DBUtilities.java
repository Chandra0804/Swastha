import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtilities {

    public static void changeScene(ActionEvent event, String fxmlfile) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(DBUtilities.class.getResource(fxmlfile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void SignUpDoctor(ActionEvent event, Doctor doc) {
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckUserExists = con.prepareStatement("select * from doctor where email = ?");
            psCheckUserExists.setString(1, doc.getEmail());
            rs = psCheckUserExists.executeQuery();

            if (rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            } else {
                psInsert = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?)");
                psInsert.setString(1, doc.getName());
                psInsert.setString(2, doc.getDob());
                psInsert.setString(3, doc.getGender());
                psInsert.setString(4, doc.getAddress());
                psInsert.setInt(5, doc.getPincode());
                psInsert.setString(6, doc.getSpecialization());
                psInsert.setString(7, doc.getHospital_ID());
                psInsert.setDouble(8, doc.getFees());
                changeScene(event, "DoctorLogin.fxml");

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
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
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
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect email or password");
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

    public void SignUpPatient(ActionEvent event , Patient Patient_Object) {

        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = connection.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, Patient_Object.getEmail());

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            }
            else {

                String Name, DateOfBirth, Email, Password, Gender, Address,Blood_Group;
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

    public void LoginPatient(ActionEvent event , String Email , String Password) {

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
                }
                else{
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

    public static void CreateAppointment(ActionEvent event,Appointment app)
    {
        Connection con = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psInsert = con.prepareStatement("insert into appointment values(?,?,?,?,?)");
            psInsert.setString(1, app.getId());
            psInsert.setString(2,app.getTime());
            psInsert.setString(3, app.getDate());
            psInsert.setString(4, app.getDoctor().getEmail());
            psInsert.setString(5, app.getPatient().getEmail());
            changeScene(event,"AppointmentsofPatient.fxml");

        } catch (Exception e) {
            System.err.println(e);
        }finally{
            if(rs != null)
            {
                try{
                    rs.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(psInsert!=null)
            {
                try{
                    psInsert.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(con!=null)
            {
                try{
                    con.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Cancel_Appointment(ActionEvent event,Appointment app)
    {
        Connection con = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            psCheckAppointmentExists = con.prepareStatement("select * from appointment where appointmentID = ?");
            psCheckAppointmentExists.setString(1, app.getId());
            rs = psCheckAppointmentExists.executeQuery();

            if(rs.isBeforeFirst())
            {
                psDelete = con.prepareStatement("delete from appointment where appointmentID = ?");
                psDelete.setString(1,app.getId());
                psDelete.executeQuery();
                changeScene(event,"AppointmentsofPatient.fxml");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointment found to delete with given Appointment ID ");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        }finally{
            if(rs != null)
            {
                try{
                    rs.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(psCheckAppointmentExists != null)
            {
                try{
                    psCheckAppointmentExists.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(psDelete != null)
            {
                try{
                    psDelete.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
           
            if(con!=null)
            {
                try{
                    con.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Search_Appointment(ActionEvent event,Appointment app)
    {
        Connection con = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            psCheckAppointmentExists = con.prepareStatement("select * from appointment where appointmentID = ?");
            psCheckAppointmentExists.setString(1, app.getId());
            rs = psCheckAppointmentExists.executeQuery();

            if(rs.isBeforeFirst()){
                changeScene(event,"AppointmentsofPatient.fxml");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointments found");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        }finally{
            if(rs != null)
            {
                try{
                    rs.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(psCheckAppointmentExists != null)
            {
                try{
                    psCheckAppointmentExists.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            
            if(con!=null)
            {
                try{
                    con.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
        }

    }

    public static void searchHospital(ActionEvent event,Hospital hospital) {

        Connection connection = null;
        PreparedStatement psCheckHospitalStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckHospitalStatus = connection.prepareStatement("select * from hospital where hospital_name = ?");
            resultset = psCheckHospitalStatus.executeQuery();

            if(resultset.isBeforeFirst()) {
                changeScene(event, "HospitalList.fxml");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Hospitals found");
                alert.show();
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if(resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(psCheckHospitalStatus != null) {
                try {
                    psCheckHospitalStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void searchDoctor(ActionEvent event,Doctor doctor) {

        Connection connection = null;
        PreparedStatement psCheckDoctorStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckDoctorStatus = connection.prepareStatement("select * from doctor where name = ?");
            resultset = psCheckDoctorStatus.executeQuery();

            if(resultset.isBeforeFirst()) {
                changeScene(event, "DoctorList.fxml");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Doctors found");
                alert.show();
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if(resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if(psCheckDoctorStatus != null) {
                try {
                    psCheckDoctorStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                    }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editDoctor(ActionEvent event,Doctor doc, String Attribute, String change_to) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, doc.getEmail());
            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {

                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, doc.getEmail());
                rs2 = stmt.executeQuery();

                if (rs2.isBeforeFirst()) {

                    // changing the parameterized object's attributes
                    if ((Attribute.toLowerCase()).equals("hospitalid")) {
                        doc.setHospital_ID(change_to);
                    } else if ((Attribute.toLowerCase()).equals("specialization")) {
                        doc.setSpecialization(change_to);
                    } else if ((Attribute.toLowerCase()).equals("address")) {
                        doc.setAddress(change_to);
                    } else if ((Attribute.toLowerCase()).equals("pincode")) {
                        doc.setPincode(Integer.parseInt(change_to));
                    }

                    // changing the sql relation
                    psupdate = con.prepareStatement("update doctor set ? = ? where email = ? and password = ?");
                    psupdate.setString(1, Attribute);
                    psupdate.setString(2, change_to);
                    psupdate.setString(3, doc.getEmail());
                    psupdate.setString(4, doc.getPassword());

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
            if(rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editPatient(ActionEvent event,Patient patient, String Attribute, String change_to) {
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
            if(rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if(con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }
}
