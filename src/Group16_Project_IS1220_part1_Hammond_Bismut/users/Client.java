package Group16_Project_IS1220_part1_Hammond_Bismut.users;

import java.util.HashMap;

public class Client extends User {
	
	private String address;
	private boolean agreement;
	private enum Contact {email,phone}
	private HashMap<Contact,String> contactInfos;
	//private ArrayList<Meal> favouriteMeals;
	//private FidelityCard card;

	public Client(String firstname, String lastname, String username,
			String password) {
		super(firstname, lastname, username, password);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getAgreement() {
		return agreement;
	}

	public void associateAgreement(boolean agreement) {
		this.agreement = agreement;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Client){
			Client c2 = (Client) obj;
			return (c2.getFirstname().equals(this.firstname) 
					&& c2.getLastname().equals(this.lastname)
					&& c2.getUsername().equals(this.username)
					&& c2.getPassword().equals(this.password));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 21 + firstname.hashCode() * lastname.hashCode() * username.hashCode() * password.hashCode();
	}
	
	public void addContactInfos(Contact type, String contactInfo){
		 if(!contactInfos.containsKey(type)){
			 contactInfos.put(type, contactInfo);
			 System.out.println("L'information suivante a bien été enregistrée :\n"
					 + type + " : " + contactInfo);
		 }else{
			 System.out.println("Ce type de contact est déjà enregistré");
		 }
	}
}
