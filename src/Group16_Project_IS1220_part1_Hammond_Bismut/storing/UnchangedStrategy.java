package Group16_Project_IS1220_part1_Hammond_Bismut.storing;

import java.util.ArrayList;
import java.util.HashMap;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.*;
import Group16_Project_IS1220_part1_Hammond_Bismut.orders.Order;

/**
 * Stratégie de tri de l'historique des commandes stockées qui affiche seulement les plats commandés lorsqu'ils n'ont pas
 * été personnalisés
 * @author Lucie
 *
 */
public class UnchangedStrategy implements OrderingStrategy {
	/**
	 * liste de toutes les commandes passées
	 */
	private ArrayList<Order> storedOrders;
	/**
	 * Dictionnaire qui recense les plats déjà commandés tels quels et qui leur associe le nombre de fois où ils ont 
	 * été commandés de cette façon
	 */
	private HashMap<Meal,Integer>unchangedMeals;

	/**
	 * Constructeur. On initialise un dictionnaire vide pour stocker les résultats de la recherche
	 * @param storedOrders l'historique de toutes les commandes passées
	 */
	public UnchangedStrategy(ArrayList<Order> storedOrders) {
		super();
		this.storedOrders = storedOrders;
		unchangedMeals = new HashMap<Meal,Integer>();
	}
	
	/**
	 * Getter
	 * @return la liste de toutes les commandes passées
	 */
	public ArrayList<Order> getStoredOrders() {
		return storedOrders;
	}

	/**
	 * Setter
	 * @param storedOrders la liste de toutes les commandes passées
	 */
	public void setStoredOrders(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}

	/**
	 * Getter
	 * @return le dictionnaire qui recense les plats déjà commandés tels quels et qui leur associe le nombre 
	 * de fois où ils ont été commandés de cette façon
	 */
	public HashMap<Meal, Integer> getUnchangedMeals() {
		return unchangedMeals;
	}

	/**
	 * Setter
	 * @param unchangedMeals le dictionnaire qui recense les plats déjà commandés tels quels et qui leur 
	 * associe le nombre de fois où ils ont été commandés de cette façon
	 */
	public void setUnchangedMeals(HashMap<Meal, Integer> unchangedMeals) {
		this.unchangedMeals = unchangedMeals;
	}
	
	/**
	 * Affiche les résultats stockés dans le dictionnaire unchangedMeals par ordre décroissant de la quantité (du plus
	 * souvent commandé au moins souvent commandé)
	 */
	@Override
	public void showStoredMeals() {
		System.out.println("Voici les plats commandés sans modifications :");
		this.findStoredMeals();
		while(unchangedMeals.size()>0){
			int quantity = 0;
			Meal nextMeal = null;
			for(Meal meal : unchangedMeals.keySet()){
				if(unchangedMeals.get(meal)>quantity){
					quantity = unchangedMeals.get(meal);
					nextMeal = meal;
				}
			}
			nextMeal.quickOverview(false);
			System.out.println("Commandé " + unchangedMeals.get(nextMeal) + " fois\n");
			unchangedMeals.remove(nextMeal);
		}
	}
	
	/**
	 * Trie les commandes de l'historique avec le critère voulu (en ne gardant que le plats qui n'ont pas été personnalisés
	 * quand ils ont été commandés) et stocke le résultat dans le dictionnaire unchangedMeal
	 */
	private void findStoredMeals(){
		for(Order order : storedOrders){
			for(Meal meal : order.getMealsToBuy().keySet()){
				if(meal.getIngredientsAdded().size()==0){
					this.addUnchangedMeal(meal,order.getMealsToBuy().get(meal));
				}
			}
		}
	}

	/**
	 * Ajoute un repas dans le dictionnaire avec une certaine quantité. Si le repas n'est pas déjà présent, on crée
	 * une nouvelle clef pour le représenter, sinon on met juste à jour la quantité totale.
	 * @param meal repas à ajouter
	 * @param quantity nombre de repas identiques commandés à ajouter
	 */
	private void addUnchangedMeal(Meal meal, int quantity) {
		if(unchangedMeals.containsKey(meal))
			unchangedMeals.put(meal,unchangedMeals.get(meal) + quantity);
		else
			unchangedMeals.put(meal, quantity);
	}
}
