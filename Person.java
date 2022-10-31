//Person Class
class Person {
	// Fields
	protected String Name;
	protected String DateOfBirth;
	protected String AadharNumber;
	
	protected String Gender;
	protected String BloodGroup;
	
	protected String AddressLine;
	protected String Landmark;
	protected String City;
	protected int Pincode;
	
	protected String District;
	protected String State;
	
	// << Constructor >>
	public Person (String name, String dob, String aadhar, String gender, String bloodGroup,
			String dno_street, String landmark, String city, String district, String state, int pincode) {
		this.Name = name;
		this.DateOfBirth = dob;
		this.AadharNumber = aadhar;
		
		this.Gender = gender;
		this.BloodGroup = bloodGroup;
		
		this.AddressLine = dno_street;
		this.Landmark = landmark;
		this.City = city;
		this.Pincode = pincode;
		
		this.District = district;
		this.State = state;
	}
	
	// Respective Accessors and Mutators
	public void setName (String name) {
		this.Name = name;
	}
	
	public String getName () {
		return this.Name;
	}
	public void setDob (String dob) {
		this.DateOfBirth = dob;
	}
	
	public String getDob () {
		return this.DateOfBirth;
	}
	
	public void setAadhar (String aadhar) {
		this.AadharNumber = aadhar;
	}
	
	public String getAadhar () {
		return this.AadharNumber;
	}
	
	public void setGender (String gender) {
		this.Gender = gender;
	}
	
	public String getGender () {
		return this.Gender;
	}
	
	public void setBloodGroup (String bloodGroup) {
		this.BloodGroup = bloodGroup;
	}
	
	public String getBloodGroup () {
		return this.BloodGroup;
	}
	
	public void setAddressLine(String addressLine) {
		this.AddressLine = addressLine;
	}
	
	public String getAddressLine() {
		return this.AddressLine;
	}
	
	public void setLandmark (String landmark) {
		this.Landmark = landmark;
	}
	
	public String getLandmark () {
		return this.Landmark;
	}
	
	public void setCity (String city) {
		this.City = city;
	}
	
	public String getCity () {
		return this.City;
	}
	
	public void setPincode (int pincode) {
		this.Pincode = pincode;
	}
	
	public int getPincode () {
		return this.Pincode;
	}
	
	public void setDistrict (String district) {
		this.District = district;
	}
	
	public String getDistrict () {
		return this.District;
	}
	
	public void setState (String state) {
		this.State = state;
	}
	
	public String getState() {
		return this.State;
	}
}
