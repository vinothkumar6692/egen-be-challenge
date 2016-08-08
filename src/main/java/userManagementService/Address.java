package userManagementService;

public class Address {
	private String street;
	private String city;
	private String country;
	private String state;
	private int zip;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public Address(String street, String city, String country, String state, int zip) {
		this.street = street;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zip = zip;
	}
	

}
