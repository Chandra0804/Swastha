package application;                                                                                                               
                                                                                                                                   
import java.util.ArrayList;                                                                                                        
                                                                                                                                   
class Doctor extends Person{                                                                                                       
	                                                                                                                               
	public Doctor(String name, String email, String password, String dob, String gender, String address, int pincode) {                                           
		super(name, email, password, dob, gender, address, pincode);                                                                                
	}                                                                                                                              
                                                                                                                                   
	protected String Specialization;                                                                                               
	protected String Hospital_ID;                                                                                                  
	protected double fees;                                                                                                         
	ArrayList<Appointments> Patient_list = new ArrayList<Appointments>();                                                          
	protected int Patient_index = 1;                                                                                               
	protected Patient p;                                                                                                           
	                                                                                                                               
	public void setHospital_ID(String Hospital_ID)                                                                                 
	{                                                                                                                              
		this.Hospital_ID= Hospital_ID;                                                                                             
	}                                                                                                                              
	                                                                                                                               
	public String getHospital_ID()                                                                                                 
	{                                                                                                                              
		return Hospital_ID;                                                                                                        
	}                                                                                                                              
	                                                                                                                               
	public void setSpecialization(String Specialization)                                                                           
	{                                                                                                                              
		this.Specialization = Specialization;                                                                                      
	}                                                                                                                              
	                                                                                                                               
	public String getSpecailization()                                                                                              
	{                                                                                                                              
		return Specialization;                                                                                                     
	}                                                                                                                              
	                                                                                                                               
	public void setfees(double fees){                                                                                              
		this.fees = fees;                                                                                                          
	}                                                                                                                              
	                                                                                                                               
	public double getfees(){                                                                                                       
		return fees;                                                                                                               
	}                                                                                                                              
	                                                                                                                               
	public int add_Patient(Patient p , String time , String date)                                                                  
	{                                                                                                                              
		Patient_list.add(new Appointments(p,time,date));                                                                           
		return Patient_index++;                                                                                                    
	}                                                                                                                              
	                                                                                                                               
	public void remove_Patient(int index)                                                                                          
	{                                                                                                                              
		Patient_list.remove(index);                                                                                                
	}                                                                                                                              
	                                                                                                                               
	@Override                                                                                                                      
	public int login(String email , String pass)                                                                                   
	{                                                                                                                              
		if(super.Email.equals(email))                                                                                              
		{                                                                                                                          
			if(super.Password.equals(pass))                                                                                        
			{                                                                                                                      
				return 1;                                                                                                          
			}                                                                                                                      
			else return 0;                                                                                                         
		}                                                                                                                          
		else return 0;                                                                                                             
	}                                                                                                                              
	                                                                                                                               
}                                                                                                                                  
