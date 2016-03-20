package Group16_Project_IS1220_part1_Hammond_Bismut.orders;

import Group16_Project_IS1220_part1_Hammond_Bismut.EYMSCore.*;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.*;

/**
 * Classe qui représente l'activité de réalisation d'une commande par les clients du système
 * Elle offre à ces derniers un ensemble de méthodes pour réaliser ce qu'ils souhaitent
 * @author Lucie
 *
 */
public class OrderManager implements Activity{
	
	/**
	 * la commande en cours de modification. Cette variable permet de stocker les modifications de manière temporaire 
	 * avant de sauvegarder la commande finie dans le système global
	 */
	private Order currentOrder;
	/**
	 * le restaurant dans lequel la commande est effectuée
	 */
	private Restaurant restaurant;
	/**
	 * le client qui passe la commande
	 */
	private Client client;
	
	/**
	 * Constructeur. L'initialisation de ce type d'activité lance automatiquement la méthode beginActivity()
	 * qui affiche des informations pour commencer
	 * @param client client qui passe la commande
	 * @param restaurant restaurant dans lequel la commande est effectuée
	 */
	public OrderManager(Client client, Restaurant restaurant) {
		super();
		this.client = client;
		this.restaurant = restaurant;
		this.beginActivity();
	}

	/**
	 * Getter
	 * @return le restaurant dans lequel est passée la commande
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Setter
	 * @param restaurant le restaurant dans lequel est passée la commande
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Getter
	 * @return le client qui passe la commande
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Setter
	 * @param client le cient qui passe la commande
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * Getter
	 * @return la commande en cours
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}

	/**
	 * Setter
	 * @param currentOrder la commande en cours
	 */
	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	/**
	 * Cette méthode est utilisée si un même client veut effectuer plusieurs commandes à la suite sans se déconnecter
	 * et se reconnecter
	 */
	public void beginNewOrder(){
		System.out.println("Nouvelle commande");
		beginActivity();
	}

	/**
	 * Annule la commande en cours
	 */
	@Override
	public void cancelModifications(){
		currentOrder = null;
	}
	
	/**
	 * Affiche la liste des plats présents sur la carte et initie une nouvelle commande
	 */
	@Override
	public void beginActivity(){
		System.out.println("\nVoici les plats disponibles :");
		restaurant.getMenu().showMenu();
		currentOrder = new Order(this.client, this.restaurant);
	}

	/**
	 * Correspond à la commande saveOrder de l'interface d'administration. Enregistre la commande créé par le client et
	 * la stocke dans l'historique des commandes du restaurant
	 */
	@Override
	public void saveModifications() {
		if(currentOrder == null)
			System.out.println("Il n'y a aucune commande à sauvegarder");
		else{
			System.out.println("Votre commande a bien été prise en compte");
			currentOrder.calculatePrice(true);
			restaurant.storeOrder(new Order(currentOrder));
			currentOrder=null;
		}
	}

	/**
	 * Affiche la commande en cours
	 */
	@Override
	public void showModifications() {
		if(currentOrder==null)
			System.out.println("Erreur : pas de commande en cours");
		else
			currentOrder.showOrder();
	}
	
	/**
	 * Sélectionne un plat pour l'ajouter dans la commande avec la quantité voulue (nombre de personnes)
	 * On ajoute une copie du repas dans la commande pour que :
	 * - les modifications de l'utilisateur n'affectent pas le plat initial
	 * - un changement dans le plat initial n'affecte pas la commande déjà effectuée
	 * @param mealName nom du plat
	 * @param quantity nombre de fois où le plat apparaît dans la commande 
	 * @return
	 */
	public boolean selectMeal(String mealName, int quantity){
		if(currentOrder==null){
			System.out.println("Erreur : pas de commande en cours");
			return false;
		}
		for(Meal meal : restaurant.getMenu().getMeals()){
			if(meal.getName().equals(mealName)){
				// C'est ici que l'on duplique le plat
				currentOrder.addMeal(new Meal(meal),quantity);
				if(quantity == 1)
					System.out.println("Vous avez sélectionné le plat " + meal.getName() + " pour "
						+ "1 personne");
				else
					System.out.println("Vous avez sélectionné le plat " + meal.getName() + " pour "
							+ quantity + " personnes");
				return true;
			}
		}
		System.out.println("Aucun plat de ce nom n'est disponible");
		return false;
	}
	
	/**
	 * Permet de personnaliser un plat déjà dans la commande, c'est à dire d'y ajouter des ingrédients
	 * @param mealName plat dans lequel le client veut ajouter un ingrédient
	 * @param ingredientName nom de l'ingrédient à ajouter
	 * @param quantity quantité de l'ingrédient à ajouter
	 * @return un booléen qui indique si l'opération a réussi ou non
	 */
	public boolean personalizeMeal(String mealName, String ingredientName, int quantity){
		if(currentOrder==null){
			System.out.println("Erreur : pas de commande en cours");
			return false;
		}
		for(Meal meal : currentOrder.getMealsToBuy().keySet()){
			if(meal.getName().equals(mealName)){
				meal.addNewIngredient(ingredientName, quantity);
				return true;
			}
		}
		System.out.println("Ce plat n'est pas inclus dans votre commande");
		return false;
	}
	
	/**
	 * Enlève un ingrédient ajouté précédemment par le cient dans un plat
	 * @param mealName le plat dans lequel on veut retirer l'ingrédient
	 * @param ingredientName nom de l'ingrédient 
	 * @return un booléen qui indique si l'opération a réussi ou non
	 */
	public boolean removePersonalization(String mealName, String ingredientName){
		if(currentOrder==null){
			System.out.println("Erreur : pas de commande en cours");
			return false;
		}
		for(Meal meal : currentOrder.getMealsToBuy().keySet()){
			if(meal.getName().equals(mealName)){
				meal.removeNewIngredient(ingredientName);
				return true;
			}
		}
		System.out.println("Ce plat n'est pas inclus dans votre commande");
		return false;
	}
	
	/**
	 * Ajoute un plat à la liste des plats préférés du client (visible sur son profil)
	 * @param mealName non du plat à ajouter
	 * @return un booléen qui indique si l'opération a réussi ou non
	 */
	public boolean addToFavouriteMeals(String mealName){
		for(Meal meal : restaurant.getMenu().getMeals()){
			if(meal.getName().equals(mealName)){
				client.getFavouriteMeals().add(new Meal(meal));
				System.out.println("Vous avez ajouté le plat " + mealName + " à vos plats préférés");
				return true;
			}
		}
		System.out.println("Aucun plat de ce nom n'est disponible");
		return false;
	}
	
	/**
	 * Enlève un plat précédemment indiqué dans les favoris du client
	 * @param mealName nom du plat à enlever
	 * @return un booléen qui indique si l'opération a réussi ou non
	 */
	public boolean removeToFavouriteMeals(String mealName){
		for(Meal meal : client.getFavouriteMeals()){
			if(meal.getName().equals(mealName)){
				client.getFavouriteMeals().remove(meal);
				System.out.println("Vous avez enlevé le plat " + mealName + " de vos plats préférés");
				return true;
			}
		}
		System.out.println("Ce plat n'est pas dans vos favoris");
		return false;
	}
}
