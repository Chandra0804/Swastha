import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class DBUtilities {

    public static void signupDoctor(Doctor doc)
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

    public static void logindoctor(Doctor doc) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            ps = con.prepareStatement("select password from person where email = ?");
            
            rs = ps.executeQuery("select password from person where email = '" + doc.getEmail() + "'");
            if (rs.next()) {
                /*
                 * if ((doc.login(doc.getemail(),rs.getString(1)))) {
                 * output.setText("Login Success");
                 * }
                 */
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
