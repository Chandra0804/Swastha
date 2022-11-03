package application;

class Appointments{
	
 protected String Time;
 protected String Date;
 protected Doctor doc;
 protected Patient p;
 
 public Appointments(Doctor doc ,String time, String date ){
 
    this.Time = time;
    this.Date = date;
    this.doc = doc;
    
 }
 
 public Appointments(Patient p ,String time, String date ){
	 
	    this.Time = time;
	    this.Date = date;
	    this.p = p;
 }

 public void setTime(String time){
    this.Time = time;
 }

 public String getTime(){
    return Time;
 }

 public void setDate(String date){
    this.Date = date;
 }

 public String getDate(){
    return Date;
 }
 
 public Doctor getDoctor(){
	 return doc;
 }
 
 public Patient getPatient(){
	 return p;
 }
}
