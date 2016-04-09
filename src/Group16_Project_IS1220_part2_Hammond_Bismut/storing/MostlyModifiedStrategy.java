package Group16_Project_IS1220_part2_Hammond_Bismut.storing;

import java.util.ArrayList;
import java.util.HashMap;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.Order;

/**
 * Stratégie de tri de l'historique des commandes stockées qui affiche seulement les plats commandés lorsqu'ils 
 * ont été personnalisés
 * @author Lucie
 *
 */
public class MostlyModifiedStrategy implements OrderingStrategy {
	/**
	 * liste de toutes les commandes passées
	 */
	private ArrayList<Order> storedOrders;
	/**
	 * Dictionnaire qui recense les plats déjà commandés en étant personnalisés et qui leur associe le nombre de fois 
	 * où ils ont été commandés de cette façon
	 */
	private HashMap<Meal,Integer> modifiedMeals;

	/**
	 * Constructeur. On initialise un dictionnaire vide pour stocker les résultats de la recherche
	 * @param storedOrders l'historique de toutes les commandes passées
	 */
	public MostlyModifiedStrategy(ArrayList<Order> storedOrders) {
		super();
		this.storedOrders = storedOrders;
		modifiedMeals = new HashMap<Meal,Integer>();
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
	 * @return le dictionnaire qui recense les plats déjà commandés en étant personnalisés et qui leur associe
	 *  le nombre de fois où ils ont été commandés de cette façon
	 */
	public HashMap<Meal, Integer> getModifiedMeals() {
		return modifiedMeals;
	}

	/**
	 * Setter
	 * @param modifiedMeals le dictionnaire qui recense les plats déjà commandés en étant personnalisés et 
	 * qui leur associe le nombre de fois où ils ont été commandés de cette façon
	 */
	public void setModifiedMeals(HashMap<Meal, Integer> modifiedMeals) {
		this.modifiedMeals = modifiedMeals;
	}


	/**
	 * Affiche les résultats stockés dans le dictionnaire modifiedMeals par ordre décroissant de la quantité (du plus souvent
	 * commandé au moins souvent commandé)
	 */
	@Override
	public void showStoredMeals() {
		System.out.println("Voici les plats les plus souvent personnalisés :");
		this.findStoredMeals();
		while(modifiedMeals.size()>0){
			int quantity = 0;
			Meal nextMeal = null;
			for(Meal meal : modifiedMeals.keySet()){
				if(modifiedMeals.get(meal)>quantity){
					quantity = modifiedMeals.get(meal);
					nextMeal = meal;
				}
			}
			nextMeal.quickOverview(true);
			System.out.println("Commandé " + modifiedMeals.get(nextMeal) + " fois\n");
			modifiedMeals.remove(nextMeal);
		}
	}
	
	/**
	 * Trie les commandes de l'historique avec le critère voulu (en ne gardant que le plats qui ont été personnalisés
	 * quand ils ont été commandés) et stocke le résultat dans le dictionnaire modifiedMeal
	 */
	private void findStoredMeals(){
		for(Order order : storedOrders){
			for(Meal meal : order.getMealsToBuy().keySet()){
				if(meal.getIngredientsAdded().size()>0){
					this.addModifiedMeal(meal,order.getMealsToBuy().get(meal));
				}
			}
		}
	}

	/**
	 * Ajoute un repas dans le dictionnaire avec une certaine quantité. Si le repas n'est pas déjà présent, on crée
	 * une nouvelle clef pour le représenter, sinon on met juste à jour la quantité totale. On stocke avec la même
	 * clef les repas de même nom, même si les personnalisations étaient différentes
	 * @param meal repas à ajouter
	 * @param quantity nombre de repas commandés à ajouter
	 */
	private void addModifiedMeal(Meal meal, int quantity) {
		if(modifiedMeals.containsKey(meal))
			modifiedMeals.put(meal,modifiedMeals.get(meal) + quantity);
		else
			modifiedMeals.put(meal, quantity);
	}
}
