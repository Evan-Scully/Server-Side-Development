
public class House {
	private String name;
	private String address;
	private int bedrooms;
	
	public House(String n, String a, int b) {
		this.name=n;
		this.bedrooms=b;
		this.address=a;
		
	}
	
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public int getBedrooms() {
		return this.bedrooms;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	public void setAddress(String a) {
		this.address = a;
	}
	public void setBedrooms(int b) {
		this.bedrooms = b;
	}
}
