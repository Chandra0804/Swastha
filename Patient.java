//Patient Class

public class Patient extends Person{

	public Patient(String name, String dob, String gender, String address, int pincode) {
		super(name, dob, gender, address, pincode);
	}
	
	protected String Blood_Group;
	protected String Patient_ID;
	
	public void set_Blood_Group(String Blooad_Group)
	{
		this.Blood_Group = Blood_Group;
	}
	
	public String get_Blood_Group()
	{
		return Blood_Group;
	}
	
	@Override
	public int Create_ID()
	{
		
	}

}
