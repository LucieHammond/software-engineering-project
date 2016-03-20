package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

import java.util.HashSet;

/**
 * Cette classe représente la carte d'un restaurant
 * @author Lucie
 *
 */
public class Menu {
	/**
	 * liste des plats disponibles sur la carte
	 */
	private HashSet<Meal> meals;
	
	/**
	 * Constructeur. Initialise une liste de plats vide
	 */
	public Menu() {
		super();
		meals = new HashSet<Meal>();
	}

	/**
	 * Getter
	 * @return liste des plats disponibles
	 */
	public HashSet<Meal> getMeals() {
		return meals;
	}	

	/**
	 * Setter liste des plats disponibles
	 * @param meals
	 */
	public void setMeals(HashSet<Meal> meals) {
		this.meals = meals;
	}

	/**
	 * Affiche dans la console les plats du menu qui bénéficient d'offres spéciales
	 */
	public void showSpecialOffers(){
		System.out.println("Voici les offres spéciales actuelles");
		for(Meal meal : meals){
			if(meal.getSpecialOffer()!=-1)
				System.out.println(meal.getName() + " : " + meal.getSpecialOffer() + " €");
		}
	}
	
	/**
	 * Affiche un résumé du menu entier dans la console
	 */
	public void showMenu(){
		for(Meal meal :meals){
			meal.quickOverview(true);
		}
		System.out.println("");
	}
}
