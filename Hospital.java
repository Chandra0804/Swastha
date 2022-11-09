package swastha;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;

public class Hospital {

    protected String Hospital_Name;
    protected String ph_no;
    protected ArrayList<Doctor> doctors = new ArrayList<>();
    protected String Address;
    protected int pincode;

    public String getHospitalName() {
        return Hospital_Name;
    }

    public void setHospitalName(String hospital_Name) {
        Hospital_Name = hospital_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor doctor) {
        doctors.add(doctor);
    }

    public String getPhno() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public static void main(String[] args) {
    }
}
