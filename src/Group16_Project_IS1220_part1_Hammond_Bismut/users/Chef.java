package Group16_Project_IS1220_part1_Hammond_Bismut.users;

/**
 * La classe qui définit les chefs, donc les admin du logiciel
 * @author rafbis
 *
 */
public class Chef extends User {

	/**
	 * Le constructeur du chef
	 * @param firstname Son prénom
	 * @param lastname Son nom
	 * @param username Son nom d'utilisateur
	 * @param password Son mot de passe
	 */
	public Chef(String firstname, String lastname, String username,
			String password) {
		super(firstname, lastname, username, password);
	}

	/**
	 * On redéfinit la méthode equals pour permettre au systeme de reconnaitre si un même chef s'inscrit deux fois
	 * et donc pour ne pas l'ajouter deux fois dans la liste des chefs
	 */
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

	/**
	 * On redéfinit également la méthode hashCode pour la même raison que equals
	 */
	@Override
	public int hashCode() {
		return 31 + (19 * firstname.hashCode() * lastname.hashCode() * username.hashCode() * password.hashCode());
	}

}
