package Group16_Project_IS1220_part1_Hammond_Bismut.fidelitycard;

import Group16_Project_IS1220_part1_Hammond_Bismut.orders.Order;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.*;

/**
 * La carte de fidélité par défaut, qui donne accès aux offres spéciales
 * @author rafbis
 *
 */
public class BasicCard implements FidelityCard{
	
	/**
	 * Le constructeur de la carte par défaut (pas nécéssaire de le réécrire)
	 */
	public BasicCard() {
		super();
	}
	
	/**
	 * Calcule le prix en tenant compte des offres spéciales
	 */
	@Override
	public double getPrice(Order order) {
		double price = 0;
		for(Meal meal : order.getMealsToBuy().keySet()){
			if(meal.getSpecialOffer()==-1){
				price+=meal.getPrice() * order.getMealsToBuy().get(meal);
			}else{
				price+=meal.getSpecialOffer() * order.getMealsToBuy().get(meal);
			}
			// On ajoute le prix des ingrédients ajoutés (2€ par unité d'ingrédient ajouté)
			for(Ingredient ingredient : meal.getIngredientsAdded()){
				// quantité de l'ingredient * prix unitaire d'un ingredient * nombre de fois où l'ingrédient a été ajouté
				price+= ingredient.getQuantity() * FidelityCard.ingredientPrice * order.getMealsToBuy().get(meal); 
			}
		}
		return price;
	}
}
