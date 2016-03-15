package Group16_Project_IS1220_part1_Hammond_Bismut.users;

public class Chef extends User {

	public Chef(String firstname, String lastname, String username,
			String password) {
		super(firstname, lastname, username, password);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Chef){
			Chef c2 = (Chef) obj;
			return (c2.getFirstname().equals(this.firstname) 
					&& c2.getLastname().equals(this.lastname)
					&& c2.getUsername().equals(this.username)
					&& c2.getPassword().equals(this.password));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 + (19 * firstname.hashCode() * lastname.hashCode() * username.hashCode() * password.hashCode());
	}

}
