class Appointment{

 protected double Fees;
 protected String Time;
 protected String Date;
 
 public Appointment(double fees, String time, String date ){
 
    this.Fees = fees;
    this.Time = time;
    this.Date = date;
    
 }
 
 public void setFees(double fees){
    this.Fees = fees;
 }
 
 public String getFees(){
    return Fees;
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
}
