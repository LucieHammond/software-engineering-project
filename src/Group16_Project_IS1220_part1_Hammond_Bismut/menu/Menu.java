package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

import java.util.HashSet;
import java.util.HashMap;

public class Menu {
	private HashSet<Meal> meals;
	private Meal mealUnderConstruction;
	private HashMap<Meal,Number> specialOffers;
	
	public Menu() {
		super();
		meals = new HashSet<Meal>();
		specialOffers = new HashMap<Meal, Number>();
	}

	public HashSet<Meal> getMeals() {
		return meals;
	}

	public void setMeals(HashSet<Meal> meals) {
		this.meals = meals;
	}

	public void createMeal(String mealName, double price){
		Meal meal = new Meal(mealName,price);
		mealUnderConstruction = meal;
	}
	
	public void currentMeal(){
		if(mealUnderConstruction!=null)
			mealUnderConstruction.showMeal();
		else
			System.out.println("Il n'y a pas de menu en construction");
	}
	
	public void saveMeal(){
		meals.add(mealUnderConstruction);
		mealUnderConstruction = null;
	}
	
	public void cancelMeal(){
		mealUnderConstruction = null;
	}
	
	public boolean putInSpecialOffer(String mealName, double price){
		for (Meal meal : meals){
			if(meal.getName().equals(mealName)){
				specialOffers.put(meal, price);
				System.out.println("Le menu " + mealName + " a été ajouté à une offre spéciale");
				return true;
			}
		}
		System.out.println("Ce menu n'est pas sur la carte");
		return false;
	}
	
	public boolean removeFromSpecialOffer(String mealName){
		for (Meal meal : specialOffers.keySet()){
			if(meal.getName().equals(mealName)){
				specialOffers.remove(meal);
				System.out.println("Le menu " + mealName + " a été retiré de l'offre spéciale");
				return true;
			}
		}
		System.out.println("Ce menu n'est pas disponible en offre spéciale");
		return false;
	}
	
	public void showSpecialOffers(){
		for(Meal meal : specialOffers.keySet()){
			System.out.println(meal.getName() + " : " + specialOffers.get(meal) + " €");
		}
	}
	
	public void showMenu(){
		for(Meal meal :meals){
			if(!specialOffers.containsKey(meal)){
				System.out.println(meal.getName() + " : " + meal.getPrice() + " €");
			}else{
				System.out.println(meal.getName() + " : " + specialOffers.get(meal) + " € -- Offre spéciale");
			}
		}
	}
}
