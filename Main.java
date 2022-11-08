package swastha;

// TARUN

import java.sql.*;
import java.util.*;

public class Main {

    public static void register(int x, Statement stmt) {
        // x==1: doctor
        // x==0: patient
        Scanner sc = new Scanner(System.in);

        // general attributes for person class
        String name;
        String email;
        String pass;
        int pincode;

        if (x == 1) {
            /*
             * Doctor attributes:
             * Email
             * Specialization
             * Hospital ID.
             */

            System.out.println("Registering as a doctor...");
            String specialization;
            String hospitalID;

            try {
                while (true) {

                    System.out.print(" Enter Your Email: ");
                    email = sc.nextLine();
                    ResultSet rs = stmt.executeQuery("select * from person where email ='" + email + "'");
                    if (rs.next()) {
                        System.out.println(
                                "This email is already registered! Please login or choose another mail to register with.");
                    } else {
                        System.out.println("Valid Email!");
                        System.out.print("Enter your name: ");
                        name = sc.nextLine();
                        System.out.print("\nEnter your pincode: ");
                        pincode = sc.nextInt();
                        sc.nextLine(); // this is to make sure the next scanner reads the input properly
                        System.out.println("\nEnter your password: ");
                        pass = sc.nextLine();
                        while (true) {
                            if (pass.length() >= 8) {
                                break;
                            } else {
                                System.out.println("Your password must be atleast 8 letters long.");
                                System.out.println("\nEnter your password: ");
                                pass = sc.nextLine();
                            }
                        }
                        System.out.print("\nEnter your specialization(enter 0 for help): ");
                        specialization = sc.nextLine();
                        while (specialization.equals("0")) {
                            System.out.println("Swastha=> Allowed specializations list:");
                            System.out.println("1=> IMMUNOLOGY");
                            System.out.println("2=> ANESTHESIOLOGY");
                            System.out.println("3=> DERMATOLOGY");
                            System.out.println("4=> NEUROLOGY");
                            System.out.println("5=> PEDIATRICS");
                            System.out.println("6=> PATHOLOGY");
                            System.out.println("7=> ONCOLOGY");
                            System.out.println("8=> DENTISTRY");
                            System.out.println("9=> OPTHALMOLOGY");
                            System.out.println("10=> EMERGENCY");
                            System.out.println("11=> GYNECOLOGY");
                            System.out.println("12=> ENT");
                            System.out.print("\nEnter your specialization(enter 0 for help): ");
                            specialization = sc.nextLine();
                        }
                        System.out.println("\nEnter hospital ID: ");
                        hospitalID = sc.nextLine();

                        // Doctor doctor = new Doctor(name, pass, specialization, hospitalID, pincode);

                        stmt.executeUpdate(
                                "insert into person values('" + name + "','" + email + "','" + pass + "'," + pincode
                                        + ")");
                        stmt.executeUpdate(
                                "insert into doctor values('" + email + "','" + hospitalID + "','" + specialization
                                        + "')");
                        System.out.println("=> Congrats! You're now registered with Swastha- the ultimate health app.");
                        break;
                    }

                }

            } catch (Exception e) {

                System.err.println(e);

            }
        } else {
            System.out.println("Registering as a default user...");
            try {
                while (true) {
                    System.out.print(" Enter Your Email: ");
                    email = sc.nextLine();
                    ResultSet rs = stmt.executeQuery("select * from person where email ='" + email + "'");
                    if (rs.next()) {
                        System.out.println(
                                "This email is already registered! Please login or choose another mail to register with.");
                    } else {
                        System.out.println("Valid Email!");
                        System.out.print("Enter your name: ");
                        name = sc.nextLine();
                        System.out.print("\nEnter your pincode: ");
                        pincode = sc.nextInt();
                        sc.nextLine();
                        System.out.println("\nEnter your password: ");
                        pass = sc.nextLine();
                        while (true) {
                            if (pass.length() >= 8) {
                                break;
                            } else {
                                System.out.println("Your password must be atleast 8 letters long.");
                                System.out.println("\nEnter your password: ");
                                pass = sc.nextLine();
                            }
                        }
                        stmt.executeUpdate(
                                "insert into person values('" + name + "','" + email + "','" + pass + "'," + pincode
                                        + ")");
                        System.out.println("Swastha=> Registered as a default user!");
                        System.out.println("WELCOME TO SWASTHA! :) ");
                        break;
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        sc.close();
    }

    public static void login(String email, Statement stmt) {
        Scanner sc = new Scanner(System.in);
        String password;
        System.out.println("Registered email!");
        System.out.print("Enter password: ");
        password = sc.nextLine();

        try {
            ResultSet rs1 = stmt.executeQuery(
                    "select name,specialization from doctor natural join person where email = '" + email + "' and "
                            + "password = '" + password + "'"); // checks if you are a doctor
            if (rs1.next()) {
                System.out.println("Welcome, Dr. " + rs1.getString(1));
                System.out.println("Your Specialization: " + rs1.getString(2));
            } else {
                ResultSet rs2 = stmt.executeQuery(
                        "select name from person where email = '" + email + "' and "
                                + "password = '" + password + "'"); // if you are not a doctor
                if (rs2.next()) {
                    System.out.println("Welcome, " + rs2.getString(1));
                    System.out.println("Hope You're having a great day so far! This is swastha. Health app.");
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        sc.close();
    }

    public static void main(String[] args) {
        try {

            // JDBC boilerplate

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                    "PVCTARUN");
            Statement stmt = con.createStatement();

            stmt.executeUpdate(
                    "create table if not exists person(name varchar(50),email varchar(50),password varchar(50),pincode int)");

            stmt.executeUpdate(
                    "create table if not exists doctor(email varchar(50), hospitalid varchar(50),specialization enum('IMMUNOLOGY','ANESTHESIOLOGY','DERMATOLOGY','ENT','NEUROLOGY','PEDIATRICS','PATHOLOGY','ONCOLOGY','DENTISTRY','OPTHALMOLOGY','EMERGENCY','GYNECOLOGY'))");
            // n = stmt.executeUpdate("create table if not exists patient");
            // n = stmt.executeUpdate("create table if not exists hospital");
            // n = stmt.executeUpdate("create table if not exists appointment");

            // input variables

            int doc_or_patient;
            String inputreader;

            // Authentication / Registration

            Scanner sc = new Scanner(System.in);
            System.out.println("Swastha: Enter Your Email Id!");
            inputreader = sc.nextLine();

            // IT IS IMPORTANT TO ALREADY HAVE THE PERSON TABLE IN YOUR DATABASE.
            ResultSet rs = stmt.executeQuery("select * from person where email ='" + inputreader + "'");

            if (rs.next()) { // checking if an account exists with the given email
                login(inputreader, stmt);
            } else {
                System.out
                        .println(
                                "Swastha=> Oops! Looks like you're not registered with our app.\n Would you like to register?");
                System.out.println("Swastha=> Do you want to register as a Doctor? Press 1 if you do, and 0 if not.");
                doc_or_patient = sc.nextInt();

                register(doc_or_patient, stmt);
            }

            sc.close();
            con.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
