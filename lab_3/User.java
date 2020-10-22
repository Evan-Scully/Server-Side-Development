
public class User {
	private String name;
	private String address;
	private int age;
	private User[] array = new User[3];
	private int count = 0;
	public User(String n, String a, int age) {
		this.name=n;
		this.age=age;
		this.address=a;
		
	}
	public void addToArray(User u) {
		this.array[count] = u;
		this.count +=1;
	}
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public int getAge() {
		return this.age;
	}
	public User[] getArray() {
		return this.array;
	}
}
