package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

import java.util.HashSet;

public class Meal {
	private String name;
	private HashSet<Ingredient> ingredients;
	private HashSet<Ingredient> ingredientsAdded;
	private double price;
	private double specialOffer = -1;
	
	public Meal(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		ingredients = new HashSet<Ingredient>();
		ingredientsAdded = new HashSet<Ingredient>();
	}
	
	/**
	 * Le rôle de ce constructeur est de dupliquer un repas existant.
	 * Il permet à un client de modifier un plat du menu en ajoutant des ingrédients 
	 * sans affecter le plat de base
	 * @param meal
	 */
	public Meal(Meal meal){
		this.name = meal.name;
		this.price = meal.price;
		this.ingredients = meal.ingredients;
		this.ingredientsAdded = meal.ingredientsAdded;
		this.specialOffer = meal.specialOffer;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(HashSet<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(double specialOffer) {
		this.specialOffer = specialOffer;
	}

	public void addIngredient(String ingredientName, int quantity){
		Ingredient ingredient = new Ingredient(ingredientName, quantity);
		if(ingredients.add(ingredient)){
			System.out.println("Ingrédient ajouté : " + ingredientName + " (" + quantity + ")");
		}else{
			System.out.println("Cet ingrédient est déjà dans le plat");
		}
	}
	
	public void removeIngredient(String ingredientName){
		for(Ingredient ingredient : ingredients){
			if(ingredient.getName().equals(ingredientName)){
				ingredients.remove(ingredient);
				System.out.println("L'ingredient " + ingredientName + " a été retiré");
			}else
				System.out.println("Cet ingrédient n'est pas dans le plat");
		}
	}
	
	public void addNewIngredient(String ingredientName, int quantity){
		Ingredient ingredient = new Ingredient(ingredientName, quantity);
		if(ingredientsAdded.add(ingredient)){
			System.out.println("Vous avez ajouté " + quantity + " " + ingredientName
					+ " au plat " + this.name);
		}else{
			System.out.println("Cet ingrédient a déjà été ajouté au plat " + this.name);
		}
	}
	
	public void removeNewIngredient(String ingredientName){
		for(Ingredient ingredient : ingredientsAdded){
			if(ingredient.getName().equals(ingredientName)){
				ingredientsAdded.remove(ingredient);
				System.out.println("L'ingredient " + ingredientName + " a été retiré du plat " + this.name);
			}else
				System.out.println("Vous n'avez pas ajouté cet ingrédient au plat " + this.name);
		}
	}
	
	public void showMeal(){
		System.out.println("--- " + this.name + " ---");
		for (Ingredient ingredient :ingredients){
			ingredient.print();
		}
		System.out.println("--------------------------");
		System.out.println("Prix : " + this.price + " €");
		if(specialOffer!=-1){
			System.out.println("Offre spéciale : " + this.specialOffer + " €");
		}
	}
}
