package Group16_Project_IS1220_part2_Hammond_Bismut.menu;

import java.util.HashSet;

/**
 * Classe qui représente un plat
 * @author Lucie
 *
 */
public class Meal{
	/**
	 * nom du plat
	 */
	private String name;
	/**
	 * liste des ingrédients de base contenus dans le plat. Modifiable uniquement par les chefs
	 */
	private HashSet<Ingredient> ingredients;
	/**
	 * liste des ingrédients ajoutés dans le plat lors d'une commande. Modifiable uniquement par les clients
	 */
	private HashSet<Ingredient> ingredientsAdded;
	/**
	 * prix du plat tel qu'il a été indiqué par le chef
	 */
	private double price;
	/**
	 * Ce nombre indique le nouveau prix du plat si celui-ci fait l'objet d'une offre spéciale.
	 * Il vaut -1 sinon.
	 */
	private double specialOffer = -1;
	
	/**
	 * Constructeur
	 * @param name nom du plat
	 * @param price prix indiqué par le chef
	 */
	public Meal(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		// On initialise des listes d'ingrédients vides au moment de la création du plat
		ingredients = new HashSet<Ingredient>();
		ingredientsAdded = new HashSet<Ingredient>();
	}
	
	/**
	 * Le rôle de ce constructeur est de dupliquer un repas existant.
	 * Il permet à un client de modifier un plat du menu en ajoutant des ingrédients sans affecter le plat de base
	 * De même, les plats des commandes stochées dans l'historique ne seront pas affectés par une modification ou une
	 * suppression ultérieure de ce plat par le chef 
	 * @param meal plat à cloner
	 */
	@SuppressWarnings("unchecked")
	public Meal(Meal meal){
		this.name = meal.name;
		this.price = meal.price;
		this.ingredients = (HashSet<Ingredient>) meal.ingredients.clone();
		this.ingredientsAdded = (HashSet<Ingredient>) meal.ingredientsAdded.clone();
		this.specialOffer = meal.specialOffer;
	}
	
	/**
	 * Getter
	 * @return nom du plat
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * Setter
	 * @param name nom du plat
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter
	 * @return liste des ingrédients du plat
	 */
	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * Setter
	 * @param ingredients liste des ingrédients du plat
	 */
	public void setIngredients(HashSet<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	/**
	 * Getter
	 * @return prix de base du plat
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Setter
	 * @param price prix de base du plat
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Getter
	 * @return prix du plat en offre spéciale. Vaut -1 si pas d'offre spéciale
	 */
	public double getSpecialOffer() {
		return specialOffer;
	}

	/**
	 * Setter 
	 * @param specialOffer prix du plat en offre spéciale. Vaut -1 si pas d'offre spéciale
	 */
	public void setSpecialOffer(double specialOffer) {
		this.specialOffer = specialOffer;
	}

	/**
	 * Getter
	 * @return liste des ingrédient ajoutés par le client en train de faire sa commande
	 */
	public HashSet<Ingredient> getIngredientsAdded() {
		return ingredientsAdded;
	}
	
	/**
	 * Setter
	 * @param ingredientsAdded liste des ingrédient ajoutés par le client en train de faire sa commande
	 */
	public void setIngredientsAdded(HashSet<Ingredient> ingredientsAdded) {
		this.ingredientsAdded = ingredientsAdded;
	}

	/**
	 * Ajoute un ingrédient au plat de base proposé sur la carte. Seul un chef peut effectuer cette tâche. 
	 * Cette méthode est donc appelée par le MenuManager auquel seul le chef peut accéder.
	 * @param ingredientName nom de l'ingrédient
	 * @param quantity quantité de l'ingrédient
	 */
	public void addIngredient(String ingredientName, int quantity){
		Ingredient ingredient = new Ingredient(ingredientName, quantity);
		if(ingredients.add(ingredient)){
			System.out.println("Ingrédient ajouté : " + ingredientName + " (" + quantity + ")");
		}else{
			System.out.println("Cet ingrédient est déjà dans le plat");
		}
	}
	
	/**
	 * Enlève un ingrédient du plat de base proposé sur la carte.. Seul un chef peut effectuer cette tâche. Cette méthode est donc appelée par
	 * le MenuManager auquel seul le chef peut accéder.
	 * @param ingredientName nom de l'ingrédient
	 */
	public void removeIngredient(String ingredientName){
		for(Ingredient ingredient : ingredients){
			if(ingredient.getName().equals(ingredientName)){
				ingredients.remove(ingredient);
				System.out.println("L'ingredient " + ingredientName + " a été retiré");
			}else
				System.out.println("Cet ingrédient n'est pas dans le plat");
		}
	}
	
	/**
	 * Ajoute un ingrédient supplémentaire au plat pour une commande. Seul un client peut effectuer cette tâche. 
	 * Cette méthode est donc appelée par le OrderManager auquel seul le client peut accéder.
	 * @param ingredientName nom de l'ingrédient
	 * @param quantity quantité de l'ingrédient
	 */
	public void addNewIngredient(String ingredientName, int quantity){
		Ingredient ingredient = new Ingredient(ingredientName, quantity);
		if(ingredientsAdded.add(ingredient)){
			System.out.println("Vous avez ajouté " + quantity + " " + ingredientName
					+ " au plat " + this.name);
		}else{
			System.out.println("Cet ingrédient a déjà été ajouté au plat " + this.name);
		}
	}
	
	/**
	 * Enlève un ingrédient du plat pour une commande. Seul un client peut effectuer cette tâche. Cette méthode est 
	 * donc appelée par le OrderManager auquel seul le client peut accéder.
	 * @param ingredientName nom de l'ingrédient
	 */
	public void removeNewIngredient(String ingredientName){
		for(Ingredient ingredient : ingredientsAdded){
			if(ingredient.getName().equals(ingredientName)){
				ingredientsAdded.remove(ingredient);
				System.out.println("L'ingredient " + ingredientName + " a été retiré du plat " + this.name);
			}else
				System.out.println("Vous n'avez pas ajouté cet ingrédient au plat " + this.name);
		}
	}
	
	/**
	 * Affiche les détails du plat dans la console. Méthode utilisée par le chef pour voir le plat qu'il a créé
	 */
	public void showMeal(){
		System.out.println("\n--- " + this.name + " ---");
		for (Ingredient ingredient :ingredients){
			ingredient.print();
		}
		System.out.println("--------------------------");
		System.out.println("Prix : " + this.price + " €");
		if(specialOffer!=-1){
			System.out.println("Offre spéciale : " + this.specialOffer + " €");
		}
	}
	
	/**
	 * Affiche dans la console les informations du plats sans rentrer dans les détails des ingrédients de base
	 * qui le composent (nom, prix, offre spéciale, ingrédients ajoutés). Cette méthode est utilisée lorsqu'on 
	 * veut afficher une vue d'ensemble sur tout le menu ou sur une commande.
	 * @param withPersonalization un boolen qui indique s'il on affiche les modifications personnelles ou pas
	 */
	public void quickOverview(boolean withPersonalization){
		System.out.println(this.name + " : " + this.price + " €");
		if(specialOffer!=-1){
			System.out.println("    -- Offre spéciale : " + specialOffer + " €");
		}
		if(ingredientsAdded.size()>0 && withPersonalization){
			System.out.print("Ingrédients ajoutés : ");
			// L'entier iteration permet juste de ne pas ajouter de virgule après le dernier ingrédient
			// On isole ainsi le dernier ingrédient à être ajouté et on l'affiche sans virgule
			int iteration = 0;
			for (Ingredient ingredient : ingredientsAdded){
				iteration++;
				System.out.print(ingredient.getName());
				if(iteration != ingredientsAdded.size())
					System.out.print(", ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * On redéfinit la méthode equals pour permettre au systeme de reconnaitre si un même plat (avec le même nom)
	 * est ajouté plusieurs fois au menu. Deux plats ne peuvent pas avoir le même nom sur la carte
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Meal){
			Meal m2 = (Meal) obj;
			return(m2.getName().equals(this.name) && m2.getIngredientsAdded().equals(ingredientsAdded));
		}
		return false;
	}

	/**
	 * On redéfinit également la méthode hashCode pour la même raison que equals
	 */
	@Override
	public int hashCode() {
		int hashCode = name.hashCode();
		for(Ingredient ingredient : ingredientsAdded){
			hashCode+=ingredient.hashCode();
		}
		return hashCode;
	}
}
