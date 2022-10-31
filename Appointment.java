class Appointment{

protected double fees;
protected String time;
protected String date;


public Appointment(double fees,String time, String date ){
 
    this.fees=fees;
    this.time=time;
    this.date=date;
    
}
}
public void setfees(double fees){
    this.fees=fees;
}
public String getfees(){
    return this.fees;
}

public void settime(String time){
    this.time=time;
}
public String gettime(){
    return this.time;
}

public void setdate(String date){
    this.date=date;
}
public String getdate(){
    return this.date;
}
