package Group16_Project_IS1220_part1_Hammond_Bismut.users;

public abstract class User {
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	
	public User(String firstname, String lastname, String username,
			String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	abstract public boolean equals(Object obj);
	
	@Override
	abstract public int hashCode();
}
