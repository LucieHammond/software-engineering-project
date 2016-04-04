package Group16_Project_IS1220_part2_Hammond_Bismut.menu;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Activity;
import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.*;

/**
 * Classe qui représente l'activité de modification de la carte par les administrateurs du système
 * Elle offre à ces derniers un ensemble de méthodes pour réaliser ce qu'ils souhaitent
 * @author Lucie
 *
 */
public class MenuManager implements Activity {
	/**
	 * plat en train d'être créé par le chef. Cette variable permet de stocker les modifications de manière temporaire
	 * avant de les sauvegarder dans le système global
	 */
	private Meal mealUnderConstruction;
	/**
	 * la carte d'un restaurant en train d'être modifiée par le chef
	 */
	private Menu menu;
	
	/**
	 * Constructeur. L'initialisation de ce type d'activité lance automatiquement la méthode beginActivity()
	 * qui affiche des informations pour commencer
	 * @param menu menu à modifier
	 */
	public MenuManager(Menu menu) {
		super();
		this.menu = menu;
		this.beginActivity();
	}

	/**
	 * Getter
	 * @return le plat que le chef est en train de créer
	 */
	public Meal getMealUnderConstruction() {
		return mealUnderConstruction;
	}
	
	/**
	 * Setter
	 * @param mealUnderConstruction le plat que le chef est en train de créer
	 */
	public void setMealUnderConstruction(Meal mealUnderConstruction) {
		this.mealUnderConstruction = mealUnderConstruction;
	}

	/**
	 * Getter
	 * @return la carte du restaurant que le chef est en train de modifier
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Setter
	 * @param menu la carte du restaurant que le chef est en train de modifier
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Affiche la liste des plats présents sur la carte
	 */
	@Override
	public void beginActivity() {
		System.out.println("\nVoici les plats disponibles :");
		menu.showMenu();
	}

	/**
	 * Annule toutes les modifications non enregistrées faites à la carte, c'est à dire le nouveau plat en construction
	 */
	@Override
	public void cancelModifications() {
		mealUnderConstruction = null;
		System.out.println("le plat en cours de création a été annulé");
	}

	/**
	 * Correspond à la commande saveMeal de l'interface d'administration. Enregistre le nouveau plat créé sur la 
	 * carte du restaurant
	 */
	@Override
	public void saveModifications() {
		if(mealUnderConstruction!=null){
			if(menu.getMeals().add(mealUnderConstruction)){
				mealUnderConstruction = null;
				System.out.println("Nouveau plat sauvegardé");
			}
			else
				System.out.println("Un plat de ce nom existe déjà");
		}
		else
			System.out.println("Il n'y a pas de plat en construction");
	}

	/**
	 * Correspond à la commande currentMeal de l'interface d'administration. Affiche les modifications réalisées 
	 * sur le nouveau plat en construction
	 */
	@Override
	public void showModifications() {
		if(mealUnderConstruction!=null)
			mealUnderConstruction.showMeal();
		else
			System.out.println("Il n'y a pas de plat en construction");
	}
	
	/**
	 * Montre les ingrédients (et les quantités) d'un plat du menu
	 * @param mealName le nom du plat dont on veut voir les ingrédients
	 */
	public void listIngredients(String mealName){
		for(Meal meal : menu.getMeals()){
			if(meal.getName().equals(mealName)){
				meal.showMeal();
				return;
			}
		}
		System.out.println("Aucun plat de ce nom n'est disponible");
	}
	
	/**
	 * Initie la création d'un nouveau plat que l'on stocke dans la variable currentMeal en attendant de le sauvegarder
	 * pour de bon.
	 * @param mealName nom du plat
	 * @param price prix de ce plat
	 */
	public void createMeal(String mealName, double price){
		Meal meal = new Meal(mealName,price);
		System.out.println("Vous avez créé le plat " + mealName);
		mealUnderConstruction = meal;
	}
	
	/**
	 * Crée une offre spéciale pour un plat en indiquant le prix de l'offre.
	 * Une fois l'offre ajoutée, cette méthode fait appel à la classe NewOfferNotification pour notifier les clients qui
	 * le souhaitent de cette nouvelle offre.
	 * @param mealName nom du plat auquel on associe une offre spéciale
	 * @param price prix correspondant à l'offre
	 * @return
	 */
	public boolean putInSpecialOffer(String mealName, double price){
		for (Meal meal : menu.getMeals()){
			if(meal.getName().equals(mealName)){
				meal.setSpecialOffer(price); 
				System.out.println("Le plat " + mealName + " a été ajouté à une offre spéciale");
				menu.showSpecialOffers();
				
				// On envoie une notification aux clients qui le souhaitent
				new NewOfferNotification(mealName, meal.getPrice(), price).notifyClients();
				return true;
			}
		}
		System.out.println("Ce plat n'est pas sur la carte");
		return false;
	}
	
	/**
	 * Retire l'offre spéciale affectée à un plat
	 * @param mealName nom du plat concerné
	 * @return
	 */
	public boolean removeFromSpecialOffer(String mealName){
		for (Meal meal : menu.getMeals()){
			if(meal.getName().equals(mealName)){
				if(meal.getSpecialOffer()==-1)
					System.out.println("Aucune offre spéciale ne s'applique à ce menu");
				else{
					meal.setSpecialOffer(-1);
					System.out.println("Le plat " + mealName + " a été retiré de l'offre spéciale");
					
				}return true;	
			}
		}
		System.out.println("Ce plat n'est pas disponible dans ce restaurant");
		return false;
	}
	
	/**
	 * Ajoute un ingrédient au plat en construction. Cette méthode est celle de l'interface à laquelle les chefs
	 * ont accès et appelle la méthode du même nom de la classe menu après avoir vérifié que qu'un plat est bien 
	 * en construction
	 * @param ingredientName nom de l'ingrédient à ajouter
	 * @param quantity quantité de l'ingrédient à ajouter
	 */
	public void addIngredient(String ingredientName, int quantity){
		if(mealUnderConstruction==null)
			System.out.println("Il n'y a pas de plat en construction");
		else{
			mealUnderConstruction.addIngredient(ingredientName, quantity);
		}
	}
	
	public void removeIngredient(String ingredientName){
		if(mealUnderConstruction==null)
			System.out.println("Il n'y a pas de plat en construction");
		else{
			mealUnderConstruction.removeIngredient(ingredientName);
		}
	}
}
