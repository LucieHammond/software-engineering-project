package Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore;

import java.util.ArrayList;
import java.util.HashSet;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.storing.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.*;

/**
 * Cette classe définit un restaurant dans lequel le système est implémenté
 * @author Lucie
 *
 */
public class Restaurant {
	/**
	 * le nom du restaurant
	 */
	private String name;
	/**
	 * la carte du restaurant qui indique l'ensemble des plats proposés
	 */
	private Menu menu;
	/**
	 * la liste des chefs du restaurant, c'est-à-dire la liste des administrateurs du système pour ce restaurant
	 * les serveurs, cuisiniers et autres employés peuvent notamment s'inscrire en tant que chef.
	 */
	private HashSet<Chef> chefs;
	/**
	 * utilisateur actuel du système dans le restaurant (celui qui s'est connecté)
	 */
	private User currentUser;
	/**
	 * activité réalisée actuellement par le système dans le restaurant (inscription d'un nouveau client,
	 * commande d'un repas, modification de la carte). Elle est spécifiée automatiquement quand un utilisateur
	 * se connecte suivant son rôle (client, chef) ou sur sa demande (modification du profil)
	 */
	private Activity currentActivity;
	/**
	 * liste des commandes qui ont été passées dans ce restaurant
	 */
	private ArrayList<Order> storedOrders;
	
	/**
	 * Constructeur. Initialise une liste vide de chefs et de commandes passées ainsi qu'un menu vide
	 * @param name nom du restaurant
	 */
	public Restaurant(String name) {
		super();
		this.name = name;
		menu = new Menu();
		chefs = new HashSet<Chef>();
		storedOrders = new ArrayList<Order>();
	}
	
	/**
	 * Getter
	 * @return nom du restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * @param name nom du restaurant
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * @return la carte du restaurant
	 */
	public Menu getMenu() {
		return menu;
	}
	
	/**
	 * Setter
	 * @param menu la carte du restaurant
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	/**
	 * Getter
	 * @return la liste des chefs du restaurant
	 */
	public HashSet<Chef> getChefs() {
		return chefs;
	}
	
	/**
	 * 
	 * @param chefs la liste des chefs du restaurant
	 */
	public void setChefs(HashSet<Chef> chefs) {
		this.chefs = chefs;
	}

	/**
	 * Getter
	 * @return l'utilisateur actuel 
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Setter
	 * @param currentUser l'utilisateur actuel
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * Getter
	 * @return l'activité actuelle
	 */
	public Activity getCurrentActivity() {
		return currentActivity;
	}

	/**
	 * Setter
	 * @param currentActivity l'activité actuelle
	 */
	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	/**
	 * Getter
	 * @return la liste des commandes effectuées dans le restaurant
	 */
	public ArrayList<Order> getStoredOrders() {
		return storedOrders;
	}
	
	/**
	 * Setter
	 * @param storedOrders la liste des commandes effectuées dans le restaurant
	 */
	public void setStoredOrders(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}

	/**
	 * Première étape de l'enregistrement d'un client, celle qui permet de créer un nouveau client à partir
	 * des 4 informations indispensables à l'ouverture de son compte. Pour le moment, l'utilisateur n'est pas 
	 * encore sauvegardé dans le système mais l'activité actuelle est initialisée à Registration (activité qui
	 * permet de finaliser l'inscription).
	 * @param firstname prenom
	 * @param lastname nom
	 * @param username identifiant
	 * @param password mot de passe
	 */
	public void registerClient(String firstname, String lastname, String username, String password){
		// Si le précédent utilisateur ne s'est pas déconnecté, on le déconnecte d'office
		if(currentUser!=null)
			logout();
		try{ 
			Client client = new Client(firstname, lastname, username, password);
			currentActivity = new Registration(client,this,false);
		} catch(IllegalArgumentException e){ 
			System.out.println("Impossible d'enregistrer le client : ses identifiants doivent être des chaines de caractère "
					+ "non vides");}
	}

	/**
	 * Méthode qui permet d'inscrire un nouveau chef au restaurant. L'inscription d'un chef est plus rapide que celle 
	 * d'un client puisqu'il n'y a pas besoin d'apporter d'informations supplémentaires
	 * @param firstname prenom
	 * @param lastname nom
	 * @param username identifiant
	 * @param password mot de passe
	 */
	public void insertChef(String firstname, String lastname, String username, String password){
		try{
			Chef chef = new Chef(firstname, lastname, username, password);
			if(chefs.add(chef))
				System.out.println("Le chef " + firstname + " " + lastname + " a bien été ajouté");
			else
				System.out.println("Ce chef est déjà enregistré dans le système");
			login(username,password);
		}catch(IllegalArgumentException e){}
	}
	
	/**
	 * Méthode qui sert à réaliser la connection, aussi bien pour un chef que pour un client.
	 * L'utilisateur actuel est changé automatiquement et l'activité actuelle est initialisée à OrderManager 
	 * ou MenuManager suivant le statut de l'utilisateur. Ces activités permettront de réaliser des actions plus 
	 * spécifiques à leurs rôles.
	 * @param username identifiant
	 * @param password mot de passe
	 * @return un booléen qui indique si l'opération est un succès
	 */
	public boolean login(String username, String password){
		// Si le précédent utilisateur ne s'est pas déconnecté, on le déconnecte d'office
		if(currentUser!=null)
			logout();
		// On cherche si les identifiants donnés correspondent à ceux d'un client enregistré
		for(Client client : CoreSystem.getSharedSystem().getClients()){
			if(client.getUsername().equals(username) && client.getPassword().equals(password)){
				// Affichage d'un message de bienvenue personnalisé
				System.out.println("Bonjour " + client.getFirstname() + " " + client.getLastname() + 
						" !\nBienvenue sur l'interface client de notre système ");
				System.out.println("Ici vous pouvez :\n- passer une commande \n- consulter la carte"
						+ "\n- modifier votre profil \n- rechercher les plats les plus commandés");
				currentUser = client;
				currentActivity = new OrderManager(client, this);
				return true;
			}
		}
		// On cherche si les identifiants donnés correspondent à ceux d'un chef enregistré
		for(Chef chef : chefs){
			if(chef.getUsername().equals(username) && chef.getPassword().equals(password)){
				// Affichage d'un message de bienvenue personnalisé
				System.out.println("Bonjour chef " + chef.getFirstname() + " " + chef.getLastname() + 
						".\nBienvenue sur l'interface administrateur de notre système ");
				currentUser = chef;
				currentActivity = new MenuManager(this.menu);
				return true;
			}
		}
		System.out.println("Erreur d'authentification : vérifiez que vos identifiants sont corrects");
		return false;
	}
	
	/**
	 * Méthode qui représente la déconnection de l'utilisateur
	 */
	public void logout(){
		currentUser = null;
		currentActivity = null;
		System.out.println("Merci de votre visite ! A une prochaine fois !\n\n");
	}
	
	/**
	 * Permet de stocker une commande dans la liste qui retrace l'historique
	 * Elle est appelée quand l'utilisateur sauvegarde sa commande
	 * @param order la commande à sauvagarder
	 */
	public void storeOrder(Order order){
		storedOrders.add(order);
	}
	
	/**
	 * Affiche les plats commandés dans le restaurant en les triant suivant un critère passé en paramêtre
	 * @param criteria le critère de tri (il n'en existe que trois possible)
	 */
	public void showMeals(OrderingStrategy.Criteria criteria){
		switch(criteria){
		// Plats commandés tels quels
		case unchanged:
			new UnchangedStrategy(storedOrders).showStoredMeals();
			break;
		// Plats modifiés (ajout d'ingrédients)
		case mostlyModified:
			new MostlyModifiedStrategy(storedOrders).showStoredMeals();
			break;
		// Plats commandés quand ils bénéficiaient d'une réduction
		case justOnSale:
			new JustOnSaleStrategy(storedOrders).showStoredMeals();
			break;
		}
	}
	
	/**
	 * Méthode qui permet à un client de modifier les informations de son profil (adresse, carte de fidélité,
	 * informations de contact... mais pas l'anniversaire pour éviter la fraude).
	 */
	public void modifyClientInfo(){
		if(currentUser instanceof Client)
			currentActivity = new Registration((Client) currentUser,this,true);
		else
			System.out.println("Aucun utilisateur actuellement connecté : connectez vous ou inscrivez vous "
					+ "avant de modifier votre profil");
	}
	
	/**
	 * On redéfinit la méthode equals de Object pour éviter d'ajouter deux fois le même restaurant dans la 
	 * base de donnée du système (qui stocke la liste des restaurants différents). Deux restaurants sont
	 * considérés comme égaux si ils ont le même nom.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Restaurant){
			Restaurant r2 = (Restaurant) obj;
			return (r2.getName().equals(this.name));
		}
		return false;
	}
	
	/**
	 * On redéfinit également la méthode hashCode pour la même raison que equals. Ainsi le HashSet des restaurants
	 * ne peux pas contenir deux restaurants de même nom.
	 */
	@Override
	public int hashCode() {
		return 20 + name.hashCode();
	}
}
