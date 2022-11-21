import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                    "MOHAN RAO");
            Statement stmt = con.createStatement();

            stmt.executeUpdate(
                    "create table if not exists patient(name varchar(50),email varchar(50),password varchar(50),pincode int,blood_group varchar(20) , height decimal(10,2) , weight int)");

            stmt.executeUpdate(
                    "create table if not exists doctor(name varchar(50),password varchar(50),pincode int,email varchar(50), hospitalid varchar(50),specialization varchar(20),fees int)");

            

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}