package swastha;

//OVERVIEW: 
/*
 * changed Create_Appointment to createAppointment
 * changed Cancel_Appointment to cancelAppointment
 */

import java.util.ArrayList;

public class Patient extends Person {

    public Patient(String name, String email, String password, String dob, String gender, String address, int pincode) {
        super(name, email, password, dob, gender, address, pincode);
    }

    protected String Blood_Group;
    protected String Patient_ID;
    ArrayList<Appointments> Patient_Appointments = new ArrayList<Appointments>();
    protected int Appointment_ID = 1;

    public void setBlood_Group(String Blood_Group) {
        this.Blood_Group = Blood_Group;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public int createAppointment(Doctor d, String time, String date) {
        Patient_Appointments.add(new Appointments(d, time, date));
        return Appointment_ID++;

    }

    public void cancelAppointment(int ID) {
        Patient_Appointments.remove(ID--);
    }

    @Override
    public int login(String email, String pass) {
        if (Email.equals(email)) {
            if (Password.equals(pass)) {
                return 1;
            } else
                return 0;
        } else
            return 0;
    }
}
