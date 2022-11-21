import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;

public class Search {
    
    public static void searchHospital(Hospital hospital) {

        Connection connection = null;
        PreparedStatement psCheckHospitalStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "POSEIDON@p8iiaf");
            psCheckHospitalStatus = connection.prepareStatement("select * from hospital where Hospital_Name = ?");
            resultset = psCheckHospitalStatus.executeQuery();

            if(resultset.isBeforeFirst()) {
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

    public static void searchDoctor(Doctor doctor) {

        Connection connection = null;
        PreparedStatement psCheckDoctorStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "POSEIDON@p8iiaf");
            psCheckDoctorStatus = connection.prepareStatement("select * from doctor where Name = ?");
            resultset = psCheckDoctorStatus.executeQuery();

            if(resultset.isBeforeFirst()) {
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
}
