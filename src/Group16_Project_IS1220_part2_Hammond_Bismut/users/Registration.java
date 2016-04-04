package Group16_Project_IS1220_part2_Hammond_Bismut.users;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.NotificationManager;

/**
 * Classe qui représente l'activité d'inscription d'un nouveau client ou de mise à jour du profil d'un client existant
 * Elle offre à ces derniers un ensemble de méthodes pour renseigner ou modifier leurs données personnelles
 * @author Lucie
 *
 */
public class Registration implements Activity {

	/**
	 * client qui est en train d'enregistrer ou de modifier ses données. Cette variable permet de stocker les modifications
	 * de manière temporaire avant de les sauvegarder dans le système global
	 */
	private Client clientUnderRegistration;
	/**
	 * restaurant depuis lequel le client renseigne ses infos. La donnée du restaurant n'influence pas l'activité
	 * d'inscription elle même puisque les clients sont enregistrés de la même façon sur le CoreSystem mais il sera
	 * utile pour revenir à une autre activité une fois les modifications terminées
	 */
	private Restaurant restaurant;
	/**
	 * booléen qui vaut true si le client a déjà un compte et souhaite seulement modifier les infos de son profil
	 * et false si c'est la première fois qu'il vient. La manière de sauvegarder va diffférer suivant les cas
	 */
	private boolean accountAlreadyExists;
	
	/**
	 * Constructeur. L'initialisation de ce type d'activité lance automatiquement la méthode beginActivity()
	 * qui affiche des informations pour commencer
	 * @param client client qui veut renseigner ou modifier ses données
	 * @param restaurant restaurant depuis lequel il se connecte
	 * @param accountAlreadyExists  vaut true si le client a déjà un compte et souhaite seulement modifier ses infos 
	 * et false si c'est la première fois qu'il vient.
	 */
	public Registration(Client client, Restaurant restaurant, boolean accountAlreadyExists) {
		super();
		this.clientUnderRegistration = client;
		this.restaurant = restaurant;
		this.accountAlreadyExists=accountAlreadyExists;
		this.beginActivity();
	}

	/**
	 * Getter
	 * @return le client qui est en train de s'inscrire ou de modifier son profil
	 */
	public Client getClientUnderRegistration() {
		return clientUnderRegistration;
	}

	/**
	 * Setter 
	 * @param clientUnderRegistration le client qui est en train de s'inscrire ou de modifier son profil
	 */
	public void setClientUnderRegistration(Client clientUnderRegistration) {
		this.clientUnderRegistration = clientUnderRegistration;
	}

	/**
	 * Getter
	 * @return le restaurant depuis lequel l'inscription est réalisée
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Setter
	 * @param restaurant le restaurant depuis lequel l'inscription est réalisée
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Getter
	 * @return le booléen qui indique si le client a déjà un compte et souhaite seulement modifier les infos 
	 * de son profil ou si c'est la première fois qu'il vient
	 */
	public boolean isAccountAlreadyExists() {
		return accountAlreadyExists;
	}

	/**
	 * Setter
	 * @param accountAlreadyExists le booléen qui indique si le client a déjà un compte et souhaite seulement modifier les infos 
	 * de son profil ou si c'est la première fois qu'il vient
	 */
	public void setAccountAlreadyExists(boolean accountAlreadyExists) {
		this.accountAlreadyExists = accountAlreadyExists;
	}


	/**
	 * Affiche des informations sur ce que l'utilisateur peut faire avec cette interface d'inscription
	 */
	@Override
	public void beginActivity() {
		if(!accountAlreadyExists){
			System.out.println("Bonjour " + clientUnderRegistration.getFirstname() + " " 
					+ clientUnderRegistration.getLastname());
			System.out.println("Pour finaliser votre inscription, vous pouvez renseigner : ");
		}
		else
			System.out.println("Pour modifier votre profil, vous pouvez renseigner : ");
		System.out.println("- votre adresse \n- vos informations de contact (email, téléphone)"
				+ "\n- votre date d'anniversaire \n- accepter de recevoir des notifications"
				+ "\n- changer de carte de fidélité");
		System.out.println("Quand vous avez fini, sauvegardez votre compte\n");
	}
	
	/**
	 * Annule les modifications du profil effectuées et non encore sauvegardées
	 */
	@Override
	public void cancelModifications() {
		if(accountAlreadyExists)
			restaurant.login(clientUnderRegistration.username, clientUnderRegistration.password);
		clientUnderRegistration = null;
		System.out.println("Modifications annulées");
	}

	/**
	 * Correspond à la commande saveAccount de l'interface d'utilisation. Sauvegarde les modifications faites sur le
	 * profil et demande à mettre à jour la liste des client qui acceptent à recevoir des notifications sur le 
	 * NotificationManager. Ramène l'utilisateur sur l'interface pour passer des commandes
	 */
	@Override
	public void saveModifications() {
		if(clientUnderRegistration ==null)
			System.out.println("Pas de compte à sauvegarder");
		else{
			if(CoreSystem.getSharedSystem().getClients().add(clientUnderRegistration)){
				if(clientUnderRegistration.getAgreement())
					NotificationManager.addClientWithAgreement(clientUnderRegistration);
				System.out.println("Le client " + clientUnderRegistration.getFirstname() + " " 
					+ clientUnderRegistration.getLastname() + " a bien été ajouté");
			}		
			else if(accountAlreadyExists){
				if(clientUnderRegistration.getAgreement())
					NotificationManager.addClientWithAgreement(clientUnderRegistration);
				else
					NotificationManager.removeClientWithAgreement(clientUnderRegistration);
				System.out.println("Vos modifications ont été sauvegardées");
			}else
				System.out.println("Ce client est déjà enregistré dans le système");
			restaurant.login(clientUnderRegistration.username, clientUnderRegistration.password);
			clientUnderRegistration = null;
		}
	}

	/**
	 * Affiche les informations du profil avec les modifications apportées.
	 */
	@Override
	public void showModifications() {
		if(clientUnderRegistration==null){
			System.out.println("Pas d'inscription en cours");
		}else{
			clientUnderRegistration.printInfos();
		}
	}
	
	/**
	 * Associe une adresse au client
	 * @param address l'adresse du client
	 */
	public void associateAddress(String address){
		if(clientUnderRegistration!=null){
			clientUnderRegistration.setAddress(address);
			System.out.println("Vous avez indiqué que votre adresse est : " + clientUnderRegistration.getAddress());
		}
		else
			System.out.println("Pas d'inscription en cours");
	}
	
	/**
	 * Indique si le client accepte de recevoir des notifications ou non
	 * @param agreement le booléen qui stocke cette information
	 */
	public void associateAgreement(boolean agreement){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.associateAgreement(agreement);
		else
			System.out.println("Pas d'inscription en cours");
	}
	
	/**
	 * Associe un type de carte de fidélité au client. Il y a trois types possible (carte basique, carte à points et 
	 * carte de lotterie)
	 * @param type
	 */
	public void associateCard(Client.CardType type){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.associateCard(type);
		else
			System.out.println("Pas d'inscription en cours");
	}

	/**
	 * Ajoute les informations nécessaires pour pouvoir notifier le client. Appelle la méthode du même nom chez le client
	 * @param type Permet de déterminer si on le contacte par e-mail ou téléphone
	 * @param contactInfo L'adresse e-mail ou le numéro du client
	 */
	public void addContactInfos(Client.Contact type, String contact){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.addContactInfos(type,contact);
		else
			System.out.println("Pas d'inscription en cours");
	}
	
	public void removeContactInfo(Client.Contact type){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.removeContactInfos(type);
		else
			System.out.println("Pas d'inscription en cours");
	}
	
	/**
	 * Renseigne la date d'anniversaire du client. Cette date ne peut plus être modifiée ensuite
	 * @param day jour d'anniversaire
	 * @param month mois d'anniversaire
	 */
	public void associateBirthday(int day, int month){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.setBirthday(day,month);
		else
			System.out.println("Pas d'inscription en cours");
	}
	
	/**
	 * Indique le type de contact à utiliser pour contacter le client
	 * @param contact 2 types possibles : email et téléphone
	 */
	public void chooseContactToUse(Client.Contact contact){
		if(clientUnderRegistration!=null)
			clientUnderRegistration.setContactToUse(contact);
		else
			System.out.println("Pas d'inscription en cours");
	}
}
