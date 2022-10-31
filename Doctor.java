package application;

class Doctor extends Person{
	
	public Doctor(String name, String dob, String gender, String address, int pincode) {
		super(name, dob, gender, address, pincode);
	}

	protected String Specialization;
	protected String Hospital_ID;
	
	public void set_Hospital_ID(String Hospital_ID)
	{
		this.Hospital_ID= Hospital_ID;
	}
	
	public String get_Hospital_ID()
	{
		return Hospital_ID;
	}
	
	public void set_Specialization(String Specialization)
	{
		this.Specialization = Specialization;
	}
	
	public String get_Specailization()
	{
		return Specialization;
	}
	
	@Override
	public int login(String email , String pass)
	{
		if(super.Email.equals(email))
		{
			if(Password.equals(pass))
			{
				return 1;
			}
			else return 0;
		}
		else return 0;
	}
	
}
