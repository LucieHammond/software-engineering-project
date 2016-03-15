package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

import java.util.HashSet;

public class Meal {
	private String name;
	private HashSet<Ingredient> ingredients;
	private double price;
	
	public Meal(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		ingredients = new HashSet<Ingredient>();
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
	
	public void showMeal(){
		System.out.println("--- " + this.name + " ---");
		for (Ingredient ingredient :ingredients){
			ingredient.print();
		}
		System.out.println("--------------------------");
		System.out.println("Prix : " + this.price + " €");
	}
}
