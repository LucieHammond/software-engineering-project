package Group16_Project_IS1220_part2_Hammond_Bismut.storing;

import java.util.ArrayList;
import java.util.HashMap;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.Order;

/**
 * Stratégie de tri de l'historique des commandes stockées qui affiche seulement les plats commandés lorsqu'ils 
 * étaient en promotion
 * @author Lucie
 *
 */
public class JustOnSaleStrategy implements OrderingStrategy {
	
	/**
	 * liste de toutes les commandes passées
	 */
	private ArrayList<Order> storedOrders;
	/**
	 * Dictionnaire qui recense les plats déjà commandés lorsqu'ils étaient en promotion et qui leur associe le nombre 
	 * de fois où ils ont été commandés de cette façon
	 */
	private HashMap<Meal,Integer> saleMeals;

	/**
	 * Constructeur. On initialise un dictionnaire vide pour stocker les résultats de la recherche
	 * @param storedOrders l'historique de toutes les commandes passées
	 */
	public JustOnSaleStrategy(ArrayList<Order> storedOrders) {
		super();
		this.storedOrders = storedOrders;
		saleMeals = new HashMap<Meal,Integer>();
	}

	/**
	 * Getter
	 * @return liste de toutes les commandes passées dans le restaurant
	 */
	public ArrayList<Order> getStoredOrders() {
		return storedOrders;
	}

	/**
	 * Setter
	 * @param storedOrders liste de toutes les commandes passées dans le restaurant
	 */
	public void setStoredOrders(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}

	/**
	 * Getter
	 * @return le dictionnaire qui recense les plats déjà commandés lorsqu'ils étaient en promotion et qui leur
	 * associe le nombre de fois où ils ont été commandés de cette façon
	 */
	public HashMap<Meal, Integer> getSaleMeals() {
		return saleMeals;
	}

	/**
	 * Setter
	 * @param saleMeals le dictionnaire qui recense les plats déjà commandés lorsqu'ils étaient en promotion et
	 * qui leur associe le nombre de fois où ils ont été commandés de cette façon
	 */
	public void setSaleMeals(HashMap<Meal, Integer> saleMeals) {
		this.saleMeals = saleMeals;
	}

	/**
	 * Affiche les résultats stockés dans le dictionnaire saleMeals par ordre décroissant de la quantité 
	 * (du plus souvent commandé au moins souvent commandé)
	 */
	@Override
	public void showStoredMeals() {
		System.out.println("Voici les plats commandés uniquement en promotion :");
		this.findStoredMeals();
		while(saleMeals.size()>0){
			int quantity = 0;
			Meal nextMeal = null;
			for(Meal meal : saleMeals.keySet()){
				if(saleMeals.get(meal)>quantity){
					quantity = saleMeals.get(meal);
					nextMeal = meal;
				}
			}
			nextMeal.quickOverview(false);
			System.out.println("Commandé " + saleMeals.get(nextMeal) + " fois\n");
			saleMeals.remove(nextMeal);
		}
	}
	
	/**
	 * Trie les commandes de l'historique avec le critère voulu (en ne gardant que le plats qui bénéficiaient d'une
	 * promotion quand ils ont été commandés) et stocke le résultat dans le dictionnaire salesMeal
	 */
	private void findStoredMeals(){
		for(Order order : storedOrders){
			for(Meal meal : order.getMealsToBuy().keySet()){
				if(meal.getSpecialOffer()!=-1){
					this.addSaleMeal(meal,order.getMealsToBuy().get(meal));
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
	private void addSaleMeal(Meal meal, int quantity) {
		if(saleMeals.containsKey(meal))
			saleMeals.put(meal,saleMeals.get(meal) + quantity);
		else
			saleMeals.put(meal, quantity);
	}
}
