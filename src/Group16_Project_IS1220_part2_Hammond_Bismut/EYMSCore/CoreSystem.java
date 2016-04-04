package Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Timer;

import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.Contact;
import Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard.LotteryCard;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.storing.*;

/**
 * Représente le système dans son ensemble, qui stocke dans un espace commun les données sur tous les restaurants
 * dans lequels il est proposé.
 * @author Lucie
 *
 */
public class CoreSystem{

	/**
	 * Liste des restaurants dans lesquels le système est implémenté et proposé
	 */
	private HashSet<Restaurant> restaurants;
	/**
	 * Liste des clients inscrits dans le système. Contrairement aux chefs, les clients ne sont pas associés à un
	 * restaurant particulier. Ils peuvent se connecter sur leur compte depuis des restaurants différents
	 */
	private HashSet<Client> clients;
	/**
	 * Le système étant unique, on le représente par un singleton
	 */
	private static CoreSystem sharedInstance;
	
	/**
	 * Le restaurant sur lequel le système est allumé
	 */
	private Restaurant actualRestaurant;
	
	/**
	 * Constructeur du système. Il est privé car le système est un singleton.
	 * La création d'un système tout neuf est l'occasion de programmer un timer
	 * qui permettra d'envoyer tous les matins à 7h30 une notification aux clients
	 * dont c'est l'anniversaire (et qui ont accepté de les recevoir)
	 */
	private CoreSystem() {
		super();
		restaurants = new HashSet<Restaurant>();
		clients = new HashSet<Client>();
		
		Timer timer = new Timer();
		Calendar date = Calendar.getInstance();
	    date.set(Calendar.HOUR, 7);
	    date.set(Calendar.MINUTE, 30);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    date.set(Calendar.AM_PM, Calendar.AM);
	    // On crée un timer qui est programmé pour se lancer tous les jours à 7h30
	    timer.schedule(new DailyTask(),date.getTime(),1000 * 60 * 60 * 24);
	}
	
	/**
	 * C'est la méthode qui mermet de récupérer le singleton de cette classe. 
	 * Si le système n'a jamais été initialisé, on crée un nouveau système.
	 * Sinon on récupère celui qui existe déjà ainsi que toutes ses données.
	 * @return le sigleton CoreSystem
	 */
	public static CoreSystem getSharedSystem(){
		if(sharedInstance==null)
			sharedInstance =new CoreSystem();
		return sharedInstance;
	}

	/**
	 * Méthode qui permet de mettre à jour le système, notemment pour enregistrer toutes les modifocations
	 * faites lors de la mise en place de la situation initiale
	 * @param sharedInstance l'instance partagée du système
	 */
	public static void setSharedInstance(CoreSystem sharedInstance) {
		CoreSystem.sharedInstance = sharedInstance;
	}

	/**
	 * Cette méthode représente le démarrage du système.
	 * Elle est l'occasion d'envoyer des notification aux clients dont c'est l'anniversaire
	 * @return l'instance unique du système
	 */
	public static CoreSystem systemTurnOn(){
		System.out.println("Démarrage du système");
		new BirthdayNotification().notifyClients();
		return CoreSystem.getSharedSystem();
	}
	
	/**
	 * Cette méthode représente l'arrêt du système
	 */
	public static void systemTurnOff(){
		System.out.println("Extinction du système");
	}
	
	/**
	 * Getter
	 * @return la liste des restaurants dans lesquels le système est implémenté
	 */
	public HashSet<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	/**
	 * Setter
	 * @param restaurants la liste des restaurants dans lesquels le système est implémenté
	 */
	public void setRestaurants(HashSet<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	/**
	 * Getter
	 * @return le restaurant sur lequel le système est allumé
	 */
	public Restaurant getActualRestaurant() {
		return actualRestaurant;
	}

	/**
	 * Setter
	 * @param actualRestaurant le restaurant sur lequel le système est allumé
	 */
	public void setActualRestaurant(Restaurant actualRestaurant) {
		this.actualRestaurant = actualRestaurant;
	}

	/**
	 * Getter
	 * @return la liste des clients qui se sont inscrits
	 */
	public HashSet<Client> getClients() {
		return clients;
	}
		
	/**
	 * Setter
	 * @param clients la liste des clients qui se sont inscrits
	 */
	public void setClients(HashSet<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Ajoute un restaurant dans le système.
	 * @param restaurantName  le nom du restaurant à ajouter
	 */
	public boolean addRestaurant(String restaurantName){
		for(Restaurant restaurant : restaurants){
			if(restaurant.getName().equalsIgnoreCase(restaurantName)){
				System.out.println("Ce restaurant est déjà dans le système");
				return false;
			}
		}
		System.out.println("Le restaurantant " + restaurantName + " a été aujouté au système");
		return true;
	}
	
	/**
	 * Représente la mise en service du système dans un restaurant. Permet de récupérer un objet restaurant
	 * à partir duquel il sera possible d'effectuer les opérations pour lequel le système a été créé
	 * @param restaurantName le nom du restaurant 
	 * @return le restaurant dans lequel on démarre l'interface du système
	 */
	public boolean openInRestaurant (String restaurantName){
		// On vérifie qu'il y a bien un restaurant de ce nom dans le système
		for(Restaurant restaurant : restaurants){
			if(restaurant.getName().equalsIgnoreCase(restaurantName)){
				System.out.println("Bienvenue au restaurant " + restaurant.getName() + " !");
				actualRestaurant = restaurant;
				return true;
			}
		}
		System.out.println("Il n'y a pas de restaurant de ce nom");
		return false;
	}

	/**
	 * Cette méthode permet de simuler les scénarios pour la première partie du projet. Elle sera retirée ensuite
	 */
	private static void executeScenario(int numero){
		CoreSystem system = null;
		Restaurant restaurant = null;
		if(numero>1 && numero<15){
			system = CoreSystem.getSharedSystem();
			if(system.openInRestaurant("Le Sun Cafe"))
				restaurant = system.getActualRestaurant();
		}
		switch(numero){
		case 1 :
			// Test case n°1 : Démarrage du système
			System.out.println("\nTest case n°1 : Démarrage du système");
			System.out.println("-----------------------------------------------------------");
			system = CoreSystem.systemTurnOn();
			// On allume le système sur le restaurant Sun Cafe précédemment initialisé et rempli
			if(system.openInRestaurant("Le Sun Cafe"))
				restaurant= system.getActualRestaurant();
			break;
		case 2:
			// Test case n°2 : Inscription d'un client
			System.out.println("\nTest case n°2 : Inscription d'un nouveau client");
			System.out.println("-----------------------------------------------------------");
			restaurant.registerClient("Lucie", "Hammond", "lulu92", "123456");
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				registration.associateAddress("5 avenue Sully-Prudhomme, 92290 Châtenay-Malabry");
				registration.addContactInfos(Client.Contact.email, "lucie.hammond@student.ecp.fr");
				registration.addContactInfos(Client.Contact.phone, "06 73 77 99 58");
				registration.associateAgreement(true);
				registration.chooseContactToUse(Client.Contact.phone);
				registration.associateCard(Client.CardType.point);
				registration.showModifications();
				registration.saveModifications();
			}
			restaurant.logout();
			break;
		case 3 :
			// Test case n°3 : Commander un repas
			System.out.println("\nTest case n°3: Commander un repas");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("martin58", "111111");
			if(restaurant.getCurrentActivity() instanceof OrderManager){
				OrderManager orderManager = (OrderManager) restaurant.getCurrentActivity();
				orderManager.selectMeal("Tartiflette", 1);
				orderManager.personalizeMeal("Tartiflette", "Salade verte", 1);
				orderManager.personalizeMeal("Tartiflette", "Vin blanc", 1);
				orderManager.showModifications();
				orderManager.saveModifications();
				orderManager.addToFavouriteMeals("Tartiflette");
			}
			restaurant.logout();
			break;
		case 4 :
			// Test case n°4 : Insertion d'un chef
			System.out.println("\nTest case n°4 : Insertion d'un chef");
			System.out.println("-----------------------------------------------------------");
			restaurant.insertChef("Auguste", "Gusteau", "ratatouille", "987654");
			restaurant.logout();
			break;
		case 5 :
			// Test case n°5 : Création d'un nouveau plat
			System.out.println("\nTest case n°5 : Création d'un nouveau plat");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("adrien_T", "666666");
			if(restaurant.getCurrentActivity() instanceof MenuManager){
				MenuManager menuManager = (MenuManager) restaurant.getCurrentActivity();
				menuManager.createMeal("Poêlée savoyarde", 18);
				menuManager.addIngredient("Pomme de terre", 4);
				menuManager.addIngredient("Oignon", 2);
				menuManager.addIngredient("Lardons", 1);
				menuManager.addIngredient("Reblochon", 1);
				menuManager.addIngredient("Crème fraîche", 2);
				menuManager.addIngredient("Gruyere", 1);
				menuManager.addIngredient("Vin blanc", 2);
				menuManager.addIngredient("Herbes de provence", 1);
				menuManager.addIngredient("Huile d'olive", 1);
				menuManager.addIngredient("Sel", 1);
				menuManager.addIngredient("Basilic", 1);
				menuManager.showModifications();
				menuManager.saveModifications();
			}
			restaurant.logout();
			break;
		case 6:
			// Test case n°6 : Ajouter une offre spéciale
			System.out.println("\nTest case n°6 : Ajouter une offre spéciale");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("adeline_G", "777777");
			if(restaurant.getCurrentActivity() instanceof MenuManager){
				MenuManager menuManager = (MenuManager) restaurant.getCurrentActivity();
				menuManager.putInSpecialOffer("Raclette", 18.5);
			}
			restaurant.logout();
			break;
		case 7:
			// Test case n°7 : Retirer une offre spéciale
			System.out.println("\nTest case n°7 : Retirer une offre spéciale");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("adeline_G", "777777");
			if(restaurant.getCurrentActivity() instanceof MenuManager){
				MenuManager menuManager = (MenuManager) restaurant.getCurrentActivity();
				menuManager.removeFromSpecialOffer("Tartiflette");
			}
			restaurant.logout();
			break;
		case 8:
			// Test case n°8 : Modifier ses informations personnelles
			System.out.println("\nTest case n°8 : Modifier ses informations personnelles");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("martin58", "111111");
			restaurant.modifyClientInfo();
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				registration.showModifications();
				registration.addContactInfos(Contact.email, "martin.durand@hotmail.fr");
				registration.chooseContactToUse(Contact.email);
				registration.associateCard(CardType.lottery);
				registration.showModifications();
				registration.saveModifications();
			}
			restaurant.logout();
			break;
		case 9:
			// Test case n°9 : Envoyer une offre d'anniversaire
			System.out.println("\nTest case n°9 : Envoyer une offre d'anniversaire");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("simon7", "555555");
			restaurant.modifyClientInfo();
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				// On met le jour d'aujourd'hui pour le test
				Calendar today = Calendar.getInstance();
				registration.associateBirthday(today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH)+1);
				registration.associateAgreement(true);
				registration.saveModifications();
			}
			restaurant.logout();
			/* On éteind et on rallume le système pour voir les offres d'anniversaires (elles sont lancées
			 * quand on allume le système 
			 */
			CoreSystem.systemTurnOff();
			system = CoreSystem.systemTurnOn();
			break;
		case 10:
			// Test case n°10 : Tester la politique de prix avec une carte de fidélité à points
			System.out.println("\nTest case n°10 : Tester la politique de prix avec une carte de fidélité à points");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("michelD", "222222");
			restaurant.modifyClientInfo();
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				registration.associateCard(CardType.point);
				registration.saveModifications();
			}
			if(restaurant.getCurrentActivity() instanceof OrderManager){
				OrderManager orderManager = (OrderManager) restaurant.getCurrentActivity();
				// Avec la commande suivante, on dépense plus de 200€ et on obtient 20 points sur la carte à points
				orderManager.selectMeal("Raclette", 5);
				orderManager.selectMeal("Fondue savoyarde", 3);
				orderManager.selectMeal("Tartiflette", 2);
				orderManager.selectMeal("Gratin savoyard", 1);
				orderManager.showModifications();
				orderManager.saveModifications();
				
				// On passe une nouvelle commande
				orderManager.beginNewOrder();
				orderManager.selectMeal("Fondue savoyarde", 2);
				orderManager.selectMeal("Croziflette", 1);
				orderManager.selectMeal("Tartiflette", 1);
				orderManager.personalizeMeal("Tartiflette", "Saumon fumé", 1);
				orderManager.showModifications();
				orderManager.saveModifications();
			}
			restaurant.logout();
			break;
		case 11:
			// Test case n°11 :  Tester la politique de prix avec une carte de fidélité lotterie
			System.out.println("\nTest case n°11 : Tester la politique de prix avec une carte de fidélité lotterie");
			System.out.println("-----------------------------------------------------------");

			/* Pour la simulation, on considère un client qui va au restaurant tous les jours pendant 5 ans 
			 * et qui commande à chaque fois une tartiflette. On regarde combien de fois il a gagné son repas
			 * en 5 ans. En théorie il a une chance sur 40 de gagner donc en 5 ans on estime autour de 46
			 * (=365*5/40) le nombre de fois où il gagne son repas. Bien sûr le nombre simulé peut varier bcp
			 * suivant les simuations 
			 */
			
			restaurant.login("bob2005", "333333");
			restaurant.modifyClientInfo();
			int compteur=0;
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				registration.associateCard(CardType.lottery);
				registration.saveModifications();
			}
			System.out.println("Début de la simulation sur 5 ans\n");
			if(restaurant.getCurrentActivity() instanceof OrderManager){
				OrderManager orderManager = (OrderManager) restaurant.getCurrentActivity();
				for(int i=0;i<1825;i++){
					Client client = (Client) restaurant.getCurrentUser();
					orderManager.setCurrentOrder(new Order(client,restaurant));
					orderManager.getCurrentOrder().getMealsToBuy().put(new Meal("Tartiflette",15.5), 1);
					Order order = orderManager.getCurrentOrder();
					LotteryCard card = (LotteryCard) client.getCard();
					card.setDefinitive(true);
					if (card.getPrice(order)==0)
						compteur++;
				}			
			}
			System.out.println("En passant une commande par jour pendant 5 ans, le client a gagné " + compteur
					+ " fois son repas");
			restaurant.logout();
			break;
		case 12:
			// Test case n°12 : Consulter l'historique des commandes réalisées
			System.out.println("\nTest case n°12 : Consulter l'historique des commandes réalisées");
			System.out.println("-----------------------------------------------------------");
			restaurant.showMeals(OrderingStrategy.Criteria.unchanged);
			restaurant.showMeals(OrderingStrategy.Criteria.mostlyModified);
			restaurant.showMeals(OrderingStrategy.Criteria.justOnSale);
			break;
		case 13:
			// Test case n°13 : Indiquer ses plats préférés
			System.out.println("\nTest case n°13 : Indiquer ses plats préférés");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("martin58", "111111");
			if(restaurant.getCurrentActivity() instanceof OrderManager){
				OrderManager orderManager = (OrderManager) restaurant.getCurrentActivity();
				orderManager.addToFavouriteMeals("Fondue savoyarde");
				orderManager.addToFavouriteMeals("Raclette");
			}
			restaurant.modifyClientInfo();
			if(restaurant.getCurrentActivity() instanceof Registration){
				Registration registration = (Registration) restaurant.getCurrentActivity();
				registration.showModifications();
				registration.cancelModifications();
			}
			restaurant.logout();
			break;
		case 14:
			// Test case n°14 : Changer de restaurant
			System.out.println("\nTest case n°14 : Changer de restaurant");
			System.out.println("-----------------------------------------------------------");
			restaurant.login("bob2005", "333333");
			restaurant.logout();
			Restaurant restaurant2 = null;
			if(system.openInRestaurant("Le Parapente"))
				restaurant2 = system.getActualRestaurant();
			restaurant2.login("bob2005", "333333");
			restaurant2.logout();
			break;
		}
				
	}
	/**
	 * Méthode dans laquelle on peut tester les cas d'utilisation qui servent d'exemple et de test du système.
	 */
	public static void main(String[] args) {
		
		initializeSituation();
		
		executeScenario(0);
	}
	
	/**
	 * Cette méthode permet d'initialiser le système afin de mettre en place une situation initiale qui 
	 * servira de base pour les scénarios d'utilisation
	 */
	protected static void initializeSituation(){
		/* Toutes les actions suivantes sont effectuées sans utiliser les commandes de base pour que le 
		 * code reste muet. ca le rend aussi plus compliqué
		 */
		// On ajoute les restaurants
		CoreSystem system = CoreSystem.getSharedSystem();
		system.getRestaurants().add(new Restaurant("Le Parapente"));
		system.getRestaurants().add(new Restaurant("L'Altiplano"));
		system.getRestaurants().add(new Restaurant("Altitude 2050"));
		system.getRestaurants().add(new Restaurant("L'Etoile"));
		Restaurant restaurant = new Restaurant("Le Sun Cafe");
		system.getRestaurants().add(restaurant);
		
		// On ajoute des plats au menu du Sun Cafe
		Meal tartiflette = new Meal("Tartiflette", 15.5);
		tartiflette.getIngredients().add(new Ingredient("Pomme de terre", 4));
		tartiflette.getIngredients().add(new Ingredient("Oignon", 1));
		tartiflette.getIngredients().add(new Ingredient("Lardons", 2));
		tartiflette.getIngredients().add(new Ingredient("Reblochon", 1));
		tartiflette.getIngredients().add(new Ingredient("Huile", 1));
		tartiflette.getIngredients().add(new Ingredient("Sel", 1));
		tartiflette.getIngredients().add(new Ingredient("Poivre", 1));
		tartiflette.setSpecialOffer(14);
		
		Meal fondue = new Meal("Fondue savoyarde", 17);
		fondue.getIngredients().add(new Ingredient("Beaufort", 2));
		fondue.getIngredients().add(new Ingredient("Compté", 2));
		fondue.getIngredients().add(new Ingredient("Tome de savoie", 1));
		fondue.getIngredients().add(new Ingredient("Vin blanc", 2));
		fondue.getIngredients().add(new Ingredient("Kirsch", 1));
		fondue.getIngredients().add(new Ingredient("Maïzena", 1));
		fondue.getIngredients().add(new Ingredient("Ail", 1));
		fondue.getIngredients().add(new Ingredient("Pain", 1));
		fondue.setSpecialOffer(16.25);
		
		Meal raclette = new Meal("Raclette", 21);
		raclette.getIngredients().add(new Ingredient("Fromage à raclette", 3));
		raclette.getIngredients().add(new Ingredient("Pommes de terre", 4));
		raclette.getIngredients().add(new Ingredient("Jambon", 3));
		raclette.getIngredients().add(new Ingredient("Saucisson", 1));
		raclette.getIngredients().add(new Ingredient("Oignon", 1));
		raclette.getIngredients().add(new Ingredient("Poivre", 1));
		
		Meal gratin = new Meal("Gratin savoyard", 13.5);
		gratin.getIngredients().add(new Ingredient("Oeuf", 4));
		gratin.getIngredients().add(new Ingredient("Jambon", 2));
		gratin.getIngredients().add(new Ingredient("Maïzena", 2));
		gratin.getIngredients().add(new Ingredient("Lait", 1));
		gratin.getIngredients().add(new Ingredient("Noix", 1));
		gratin.getIngredients().add(new Ingredient("Sel", 1));
		gratin.getIngredients().add(new Ingredient("Poivre", 1));
		
		Meal croziflette = new Meal("Croziflette", 15);
		croziflette.getIngredients().add(new Ingredient("Crozets", 3));
		croziflette.getIngredients().add(new Ingredient("Jambon", 4));
		croziflette.getIngredients().add(new Ingredient("Oignon", 1));
		croziflette.getIngredients().add(new Ingredient("Reblochon", 2));
		croziflette.getIngredients().add(new Ingredient("Vin blanc", 1));
		croziflette.getIngredients().add(new Ingredient("Crème fraîche", 1));
		croziflette.getIngredients().add(new Ingredient("Huile", 1));
		croziflette.getIngredients().add(new Ingredient("Sel", 1));
		croziflette.getIngredients().add(new Ingredient("Poivre", 1));
	
		Meal rizotto = new Meal("Rizotto", 11.5);
		rizotto.getIngredients().add(new Ingredient("Veau", 5));
		rizotto.getIngredients().add(new Ingredient("Riz", 4));
		rizotto.getIngredients().add(new Ingredient("Echalotes", 2));
		rizotto.getIngredients().add(new Ingredient("Vin blanc", 1));
		rizotto.getIngredients().add(new Ingredient("Sel", 1));
	
		restaurant.getMenu().getMeals().add(tartiflette);
		restaurant.getMenu().getMeals().add(raclette);
		restaurant.getMenu().getMeals().add(fondue);
		restaurant.getMenu().getMeals().add(gratin);
		restaurant.getMenu().getMeals().add(croziflette);
		restaurant.getMenu().getMeals().add(rizotto);
	
		// On ajoute des clients au restaurant
		Client martin = new Client("Martin", "Durand", "martin58", "111111");
		Client michel = new Client("Michel", "Dubois", "michelD", "222222");
		Client robert = new Client("Robert", "Petit", "bob2005", "333333");
		Client thomas = new Client("Thomas", "Moreau", "tom_M", "444444");
		Client simon = new Client("Simon", "Laurent", "simon7", "555555");
		martin.getContactInfos().put(Contact.phone, "06 53 76 84 27");
		martin.setAgreement(true);
		NotificationManager.addClientWithAgreement(martin);
		martin.setAddress("82 rue Jean de la Fontaine, 59140 Dunkerque");
		thomas.getContactInfos().put(Contact.email, "thomas.moreau@hotmail.fr");
		thomas.setAgreement(true);
		NotificationManager.addClientWithAgreement(thomas);
		simon.getContactInfos().put(Contact.phone, "01 74 96 24 98");
		simon.setAgreement(true);
		NotificationManager.addClientWithAgreement(simon);
		thomas.setBirthday(Calendar.getInstance());
		
		system.getClients().add(martin);
		system.getClients().add(michel);
		system.getClients().add(robert);
		system.getClients().add(thomas);
		system.getClients().add(simon);
		
		// On ajoute des chefs
		Chef adrien = new Chef ("Adrien", "Trouilloud", "adrien_T","666666");
		Chef adeline = new Chef("Adeline", "Grattard", "adeline_G", "777777");
		restaurant.getChefs().add(adrien);
		restaurant.getChefs().add(adeline);
		
		// On ajoute des commandes
		Order order1 = new Order(martin, restaurant);
		order1.getMealsToBuy().put(new Meal(tartiflette), 2);
		order1.getMealsToBuy().put(new Meal(gratin), 1);
		order1.getMealsToBuy().put(new Meal(fondue), 1);
		
		Order order2 = new Order(michel, restaurant);
		Meal tartiflette2 = new Meal(tartiflette);
		tartiflette2.getIngredientsAdded().add(new Ingredient("Champignons",2));
		tartiflette2.getIngredientsAdded().add(new Ingredient("Croutons",3));
		order2.getMealsToBuy().put(tartiflette2, 1);
		order2.getMealsToBuy().put(new Meal(croziflette), 1);
		
		Order order3 = new Order(simon,restaurant);
		Meal tartiflette3 = new Meal(tartiflette);
		tartiflette3.getIngredientsAdded().add(new Ingredient("Magré de canard",3));
		order3.getMealsToBuy().put(tartiflette3, 2);
		order3.getMealsToBuy().put(new Meal(raclette), 3);
		order3.getMealsToBuy().put(new Meal(croziflette), 1);
		
		Order order4 = new Order(robert,restaurant);
		Meal rizotto2 = new Meal(rizotto);
		rizotto2.getIngredientsAdded().add(new Ingredient("Abricots secs",1));
		rizotto2.getIngredientsAdded().add(new Ingredient("Noisettes",1));
		order4.getMealsToBuy().put(rizotto2, 1);
		order4.getMealsToBuy().put(new Meal(croziflette), 1);
		
		restaurant.getStoredOrders().add(order1);
		restaurant.getStoredOrders().add(order2);
		restaurant.getStoredOrders().add(order3);
		restaurant.getStoredOrders().add(order4);
		
		System.out.println("Mise en place de la situation initiale terminée");
		
	}
}
