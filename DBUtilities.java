import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtilities {

    public static void changeScene(ActionEvent event , String fxmlfile) throws IOException{
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(DBUtilities.class.getResource(fxmlfile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void signupDoctor(ActionEvent event , Doctor doc)
    {
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            psCheckUserExists = con.prepareStatement("select * from doctor where email = ?");
            psCheckUserExists.setString(1, doc.getEmail());
            rs = psCheckUserExists.executeQuery();

            if(rs.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            }
            else{
                psInsert = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?)");
                psInsert.setString(1, doc.getName());
                psInsert.setString(2, doc.getDob());
                psInsert.setString(3, doc.getGender());
                psInsert.setString(4, doc.getAddress());
                psInsert.setInt(5, doc.getPincode());
                psInsert.setString(6, doc.getSpecailization());
                psInsert.setString(7, doc.getHospital_ID());
                psInsert.setDouble(8, doc.getFees());
                changeScene(event, "Doctor_login.fxml");
                
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
            if(psCheckUserExists != null)
            {
                try{
                    psCheckUserExists.close();
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

    public static void logindoctor(ActionEvent event ,String Email , String Password) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1,Email);
            rs1 = ps.executeQuery();
            if (rs1.isBeforeFirst()) {
                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, Email);
                stmt.setString(2,Password);
                rs2 = stmt.executeQuery();
                if(rs2.isBeforeFirst())
                {
                    changeScene(event,"DoctorHome.fxml");
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        finally{
            if(rs1 != null)
            {
                try{
                    rs1.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(rs2 != null)
            {
                try{
                    rs2.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(ps != null)
            {
                try{
                    ps.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
            }
            if(stmt!=null)
            {
                try{
                    stmt.close();
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

}
