package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

import java.util.HashSet;

public class Menu {
	private HashSet<Meal> meals;
	private Meal mealUnderConstruction;
	
	public Menu() {
		super();
		meals = new HashSet<Meal>();
	}

	public HashSet<Meal> getMeals() {
		return meals;
	}

	public void setMeals(HashSet<Meal> meals) {
		this.meals = meals;
	}
	
	public Meal getMealUnderConstruction() {
		return mealUnderConstruction;
	}

	public void setMealUnderConstruction(Meal mealUnderConstruction) {
		this.mealUnderConstruction = mealUnderConstruction;
	}

	public void showSpecialOffers(){
		for(Meal meal : meals){
			if(meal.getSpecialOffer()!=-1)
				System.out.println(meal.getName() + " : " + meal.getSpecialOffer() + " €");
		}
	}
	
	public void showMenu(){
		for(Meal meal :meals){
			if(meal.getSpecialOffer()==-1){
				System.out.println(meal.getName() + " : " + meal.getPrice() + " €");
			}else{
				System.out.println(meal.getName() + " : " + meal.getSpecialOffer() + " € -- Offre spéciale");
			}
		}
	}
}
