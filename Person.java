//Person Class
abstract class Person {
	// Fields
	protected String Name;
	protected String DateOfBirth;
	protected String Person_ID;
	
	protected String Gender;
	
	protected String Address;
	protected int Pincode;
	
	// << Constructor >>
	public Person (String name, String dob, String gender, String address, int pincode) {
		this.Name = name;
		this.DateOfBirth = dob;
		
		this.Gender = gender;
		
		this.Address = address;
		this.Pincode = pincode;
	}
	
	// Respective Accessors and Mutators
	public void setName (String name) {
		this.Name = name;
	}
	
	public String getName () {
		return Name;
	}
	public void setDob (String dob) {
		this.DateOfBirth = dob;
	}
	
	public String getDob () {
		return DateOfBirth;
	}
	
	public void setGender (String gender) {
		this.Gender = gender;
	}
	
	public String getGender () {
		return Gender;
	}
	
	public void setAddressLine(String address) {
		this.Address = address;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setPincode (int pincode) {
		this.Pincode = pincode;
	}
	
	public int getPincode () {
		return Pincode;
	}
	
	public abstract int Create_ID();
}
