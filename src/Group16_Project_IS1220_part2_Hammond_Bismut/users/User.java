package Group16_Project_IS1220_part2_Hammond_Bismut.users;

/**
 * La classe abstraite de tous les utilisateurs du logiciel
 * @author rafbis
 *
 */
public abstract class User {
	
	/**
	 * Le prénom de l'utilisateur
	 */
	protected String firstname;
	/**
	 * Le nom de l'utilisateur
	 */
	protected String lastname;
	/**
	 * Le nom d'utilisateur de l'utilisateur
	 */
	protected String username;
	/**
	 * Le mot de passe de l'utilisateur
	 */
	protected String password;
	
	/**
	 * Le constructeur abstrait de l'utilistaur du logiciel
	 * @param firstname Son prénom
	 * @param lastname Son nom
	 * @param username Son nom d'utilisateur
	 * @param password Son mot de passe
	 */
	public User(String firstname, String lastname, String username,
			String password) throws IllegalArgumentException{
		super();
		// Les 4 attributs suivants ne peuvent pas être des String vides
		if(firstname.equals("") || lastname.equals("") || username.equals("") || password.equals(""))
			throw new IllegalArgumentException();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Getter
	 * @return Le prénom de l'utilisateur
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * Setter
	 * @param firstname le prénom de l'utilisateur
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * Getter
	 * @return Le nom de l'utilisateur
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * Setter
	 * @param lastname le nom de l'utilisateur
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * Getter
	 * @return Le nom d'utilisateur de l'utilisateur
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter
	 * @param username l'identifiant de l'utilisateur
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter
	 * @return Le mot de passe de l'utilisateur
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter
	 * @param password Le mot de passe de l'utilisateur
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * On redéfinit equals pour ne pas ajouter deux fois le même utilisateur dans les listes
	 * Deux utilisateurs sont considérés égaux si ils ont même prenom, nom, identifiant et mot de passe
	 */
	@Override
	abstract public boolean equals(Object obj);
	
	/**
	 * On redéfinit également hashCode pour la même raison que equals
	 */
	@Override
	abstract public int hashCode();
}

