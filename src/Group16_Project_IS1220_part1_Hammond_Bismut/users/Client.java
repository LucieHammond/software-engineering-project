package Group16_Project_IS1220_part1_Hammond_Bismut.users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part1_Hammond_Bismut.fidelitycard.*;

/**
 * La classe qui définit les clients du restaurant
 * @author rafbis
 *
 */
public class Client extends User {
	
	/**
	 * La date d'anniversaire des clients
	 */
	private Calendar birthday;
	/**
	 * L'adresse e-mail des clients
	 */
	private String address = "";
	/**
	 * L'accord ou non des clients pour recevoir des notifications
	 */
	private boolean agreement;
	/**
	 * La liste des moyens pour notifier les clients
	 * @author rafbis
	 *
	 */
	public static enum Contact {email,phone}
	/**
	 * Les informations pour contacter les clients. 
	 * Se présente sous forme d'un dictionnaire qui associe à un type de contact 
	 * les informations concernant ce type de contact (numéro de tel, adresse email)
	 */
	private HashMap<Contact,String> contactInfos;
	/**
	 * Le type de contact avec lequel le client veut être notifié
	 */
	private Contact contactToUse;
	/**
	 * La liste des plats préférés d'un client
	 */
	private ArrayList<Meal> favouriteMeals;
	/**
	 * La carte de fidélité des clients
	 */
	private FidelityCard card;
	/**
	 * Les différents types de carte de fidélité
	 * @author rafbis
	 *
	 */
	public static enum CardType {basic,point,lottery}

	/**
	 * Constructeur : par défaut l'agreement est false et la carte de fidélité est une carte basique
	 * @param firstname Son prénom
	 * @param lastname Son nom
	 * @param username Son nom d'utilisateur
	 * @param password Son mot de passe
	 */
	public Client(String firstname, String lastname, String username,
			String password) {
		super(firstname, lastname, username, password);
		contactInfos = new HashMap<Contact,String>();
		favouriteMeals = new ArrayList<Meal>();
		card = new BasicCard();
		agreement = false;
	}

	/**
	 * Getter
	 * @return La date d'anniversaire
	 */
	public Calendar getBirthday() {
		return birthday;
	}
	
	/**
	 * Setter (à partir d'un objet Calendar)
	 * @param birthday La date d'anniversaire
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * Setter (à partir de deux entiers)
	 * @param birthday La date d'anniversaire
	 */
	public void setBirthday(int day, int month) {
		if(birthday!=null)
			System.out.println("Vous ne pouvez pas changer la date de votre anniversaire");
		else{
			if(day<=0 || day>31 || month <=0 || month >12)
				System.out.println("Cette date n'existe pas. Recommencez avec une des données correctes");
			else{
				birthday = Calendar.getInstance();
				birthday.set(Calendar.MONTH,month-1);
				birthday.set(Calendar.DAY_OF_MONTH, day);
				System.out.println("Vous avez indiqué que votre anniversaire est le " + day + "/" 
						+ month);
			}
		}
	}

	/**
	 * Getter
	 * @return l'adresse du client
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter
	 * @param address l'adresse du client
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter
	 * @return le contact à utiliser pour notifier le client des offres
	 */
	public Contact getContactToUse() {
		return contactToUse;
	}

	/**
	 * Setter
	 * @param le contact à utiliser pour notifier le client des offres
	 */
	public void setContactToUse(Contact contactToUse) {
		if(contactInfos.containsKey(contactToUse)){
			this.contactToUse = contactToUse;
			System.out.println("Nous avons bien noté le contact à utiliser pour vous envoyer des offres");
		}
		else
			System.out.println("Impossible d'utiliser ce contact.\n "
					+ "Vous n'avez pas renseigné les informations nécessaires pour vous joindre");
	}

	/**
	 * Getter
	 * @return L'accord pour recevoir des notifications
	 */
	public boolean getAgreement() {
		return agreement;
	}
	
	/**
	 * Setter
	 * @param agreement L'accord pour recevoir des notifications
	 */
	public void setAgreement(boolean agreement) {
		adjustContactToUse();
		this.agreement = agreement;
	}

	/**
	 * Demande l'accord pour recevoir des notifications.
	 * Cette fonction vérifie que le client a renseigné des informations de contact
	 * avant de mettre l'agreement à true. Sinon elle le laisse à false
	 * @param agreement Un booléen qui représente l'accord pour recevoir des notifications
	 */
	public void associateAgreement(boolean agreement) {
		if(agreement && contactToUse!=null){
			this.agreement = agreement;
			System.out.println("Vous avez accepté de recevoir des notifications");
		}
		else if(!agreement){
			this.agreement = agreement;
			System.out.println("Vous avez refusé de recevoir des notifications");
		}else
			System.out.println("Nous ne pouvons pas vous envoyer de notifications si"
					+ "vous ne renseignez pas vos informations de contact.\n"
					+ "Remplissez d'abord ces informations puis réessayez");
	}
	
	/**
	 * Getter
	 * @return la liste des plats préférés du client
	 */
	public ArrayList<Meal> getFavouriteMeals() {
		return favouriteMeals;
	}
	
	/**
	 * Setter
	 * @param favouriteMeals la liste des plats préférés du client
	 */
	public void setFavouriteMeals(ArrayList<Meal> favouriteMeals) {
		this.favouriteMeals = favouriteMeals;
	}
	
	/**
	 * Getter
	 * @return Les informations pour contacter les clients
	 */
	public HashMap<Contact, String> getContactInfos() {
		return contactInfos;
	}
	
	/**
	 * Setter
	 * @param contactInfos Les informations pour contacter les clients
	 */
	public void setContactInfos(HashMap<Contact, String> contactInfos) {
		this.contactInfos = contactInfos;
	}

	/**
	 * Getter
	 * @return La carte de fiélité
	 */
	public FidelityCard getCard() {
		return card;
	}

	/**
	 * Associe au client une carte de fidélité du type donné en paramêtre
	 * Remplace le setter pour cet attribut
	 * @param card La carte de fidélité
	 */
	public void associateCard(CardType type) {
		if(type != null){
			switch(type)
			{
				case basic:
					card = new BasicCard();
					System.out.println("Vous avez choisi une carte de fidélité basique");
					break;
				case point:
					card = new PointCard();
					System.out.println("Vous avez choisi une carte de fidélité à points");
					break;
				case lottery:
					card = new LotteryCard();
					System.out.println("Vous avez choisi une carte de fidélité lotterie");
					break;
			}
		}	
	}

	/**
	 * Permet de rentrer les informations pour notifier le client
	 * @param type Permet de déterminer si on le contacte par e-mail ou téléphone
	 * @param contactInfo L'adresse e-mail ou le numéro du client
	 */
	public boolean addContactInfos(Contact type, String contactInfo){
		if(type==null){
			System.out.println("Erreur : type de contact invalide");
			return false;
		}
		else if(!contactInfos.containsKey(type)){
			 contactInfos.put(type, contactInfo);
			 System.out.println("L'information suivante a bien été enregistrée :\n"
					 + type + " : " + contactInfo);
			 this.adjustContactToUse();
			 return true;
		}else{
			 System.out.println("Ce type de contact est déjà enregistré");
			 return false;
		}
	}
	
	/**
	 * Retire une information de contact du profil
	 * @param type : le type d'information de contact que le client veut retirer (email, telephone)
	 * @return un booléen qui indique si l'opération a réussi
	 */
	public boolean removeContactInfos(Contact type){
		if(type==null){
			System.out.println("Erreur : type de contact invalide");
			return false;
		}
		if(contactInfos.containsKey(type)){
			 contactInfos.remove(type);
			 System.out.println("L'information de contact a bien été retirée");
			 this.adjustContactToUse();
			 return true;
		}else{
			 System.out.println("Aucune information de ce type n'a été renseignée");
			 return false;
		}
	}
	
	/**
	 * Cette fonction est appelée chaque fois que les informations de contact sont modifiées
	 * Elle permet de mettre à jour automatiquement le contact par défaut en fonction des informations données
	 */
	private void adjustContactToUse(){
		// Si il n'y a pas d'infos de contact, le contact à utiliser ne peut pas avoir une autre valeur que null
		if(contactInfos.size()==0){
			contactToUse=null;
			agreement = false; // On n'oublie pas de retirer l'agreement par la même occasion
		}
		// Si un seul contact est renseigné, c'est celui là qui est choisi par défaut
		else if(contactInfos.size()==1){
			for(Contact contact:contactInfos.keySet())
				contactToUse= contact;
		} // Sinon, c'est le mail qui est choisi par défaut
		else{
			contactToUse=Contact.email;
		}
	}
	
	/**
	 * On redéfinit la méthode equals pour permettre au systeme de reconnaitre si un même utilisateur s'inscrit deux fois
	 * et donc pour ne pas l'ajouter deux fois dans la liste
	 */
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

	/**
	 * On redéfinit également la méthode hashCode pour la même raison que equals
	 */
	@Override
	public int hashCode() {
		return 21 + firstname.hashCode() * lastname.hashCode() * username.hashCode() * password.hashCode();
	}
	
	/**
	 * Affiche dans la console les informations du client
	 */
	public void printInfos(){
		System.out.println("\n----- Nouveau client -----");
		System.out.println("Nom : " + lastname);
		System.out.println("Prenom : " + firstname);
		System.out.println("Adresse : " + address);
		System.out.println("Pour vous contacter : ");
		if(contactInfos.size()==0)
			System.out.println("Non renseignées");
		else{
			for(Contact type : contactInfos.keySet()){
				System.out.println("-  " + type + " : " + contactInfos.get(type));
			}
		}
		if(agreement){
			System.out.println("Accepte de recevoir des notifications : oui");
			System.out.print("Les notifications seront envoyées par ");
			if(contactToUse==Contact.email)
				System.out.println("email");
			else
				System.out.println("téléphone");
		}
		else
			System.out.println("Accepte de recevoir des notifications : non");
		if(card instanceof BasicCard)
			System.out.println("Carte de fidélité : basique");
		else if(card instanceof PointCard)
			System.out.println("Carte de fidélité : carte à points");
		else
			System.out.println("Carte de fidélité : carte lotterie");
		if(birthday!=null){
			System.out.println("Date d'anniversaire : " + birthday.get(Calendar.DAY_OF_MONTH) + "/"
					+ (birthday.get(Calendar.MONTH)+1));
		}
		System.out.print("Plats préférés : ");
		int iteration = 0;
		for(Meal meal : favouriteMeals){
			iteration++;
			System.out.print(meal.getName());
			if(iteration != favouriteMeals.size())
				System.out.print(", ");
		}
		System.out.println("\n");
	}
}
