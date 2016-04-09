package Group16_Project_IS1220_part2_Hammond_Bismut.orders;

import java.util.Calendar;
import java.util.HashMap;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard.*;

/**
 * Cette classe représente les commandes des clients
 * @author Lucie
 *
 */
public class Order {
	/**
	 * le client qui passe la commande
	 */
	private Client buyer;
	/**
	 * le restaurant dans lequel la commande est passée
	 */
	private Restaurant restaurant;
	/**
	 * le prix total de la commmande pour le client tel qu'il est calculé avec la politique de prix de sa carte de fidélité
	 */
	private double totalPrice = 0;
	/**
	 * dictionnaire qui recense la liste des plats commandés (éventuellement personnalisés) chacun associé à une quantité
	 * (nombre de fois où le plat apparaît dans la commande c'est à dire nombre de personnes qui vont manger de ce plat)
	 */
	private HashMap<Meal,Integer> mealsToBuy;
	
	/**
	 * Constructeur. Il initialise une liste de plats vide
	 * @param buyer client qui commande
	 * @param restaurant restaurant dans lequel il commande
	 */
	public Order(Client buyer, Restaurant restaurant) {
		super();
		this.buyer = buyer;
		this.restaurant = restaurant;
		mealsToBuy = new HashMap<Meal, Integer>();
	}
	
	/**
	 * Ce constructeur a pour but de dupliquer une commande pour pouvoir la stocker comme instance unique
	 * @param order
	 */
	@SuppressWarnings("unchecked")
	public Order(Order order) {
		super();
		this.buyer = order.buyer;
		this.restaurant = order.restaurant;
		this.mealsToBuy = (HashMap<Meal, Integer>) order.mealsToBuy.clone();
		this.totalPrice = order.totalPrice;
	}
	
	/**
	 * Getter
	 * @return le client qui passe une commande
	 */
	public Client getBuyer() {
		return buyer;
	}

	/**
	 * Setter
	 * @param buyer le client qui passe une commande
	 */
	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}

	/**
	 * Getter
	 * @return le prix total de la commande tel que calculé avec la politique des prix correspondant à la 
	 * carte de fidélité du client
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Setter
	 * @param totalPrice  le prix total de la commande tel que calculé avec la politique des prix correspondant à la 
	 * carte de fidélité du client
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * Getter
	 * @return le restaurant dans lequel la commande est effectuée
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Setter
	 * @param restaurant le restaurant dans lequel la commande est effectuée
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Getter
	 * @return le dictionnaire des commandes (éventuellement personnalisés)
	 */
	public HashMap<Meal, Integer> getMealsToBuy() {
		return mealsToBuy;
	}
	
	/**
	 * Setter
	 * @param mealsToBuy le dictionnaire des commandes (éventuellement personnalisés)
	 */
	public void setMealsToBuy(HashMap<Meal, Integer> mealsToBuy) {
		this.mealsToBuy = mealsToBuy;
	}

	/**
	 * Ajoute un plat à la commande avec la quantité commandée
	 * @param meal plat à ajouter à la commande
	 * @param quantity nombre de fois où le plat apparaît dans la commande 
	 * c'est à dire nombre de personnes qui vont manger de ce plat
	 */
	protected void addMeal(Meal meal, int quantity){	
		mealsToBuy.put(meal, quantity);
	}
	
	/**
	 * Enlève un plat de la commande
	 * @param meal le plat à enlever
	 */
	protected void removeMeal(Meal meal){
		mealsToBuy.remove(meal);
	}
	
	/**
	 * Calcule le prix de la commande avec la politique de prix corespondant à celle de la carte de fidélité du client.
	 * @param definitively vaut true si on veut calculer le prix définitif au moment de sauvegarder la comande
	 * vaut false si on veut calculer le prix pendant la création de la commande pour l'afficher au client à titre
	 * indicatif
	 */
	public void calculatePrice(boolean definitively){
		/*Le traitement effectué par les cartes à point et par les carte de lotterie diffère en fonction de si le 
		 prix est définitif ou pas*/
		if(buyer.getCard() instanceof PointCard){
			PointCard card = (PointCard) buyer.getCard();
			card.setDefinitive(definitively);
		}else if(buyer.getCard() instanceof LotteryCard){
			LotteryCard card = (LotteryCard) buyer.getCard();
			card.setDefinitive(definitively);
		}
		// Cette expresision permet d'avoir un arrondi du prix à deux décimales près
		totalPrice = Math.round(buyer.getCard().getPrice(this)*100)/100.00;
	}
	
	/**
	 * Affiche le résumé de la commande en cours dans la console
	 */
	public void showOrder(){
		System.out.println("\nVoici votre commande :\n");
		System.out.println("Client : " + buyer.getFirstname() + " " + buyer.getLastname());
		System.out.println("Restaurant : " + restaurant.getName() + "\n");
		for (Meal meal : mealsToBuy.keySet()){
			meal.quickOverview(true);
			if(mealsToBuy.get(meal)==1)
				System.out.println("Pour 1 personne (x1)\n");
			else{
				System.out.println("Pour " + mealsToBuy.get(meal) + " personnes (x" 
						+ mealsToBuy.get(meal) + ")\n");
			}			
		}
		System.out.println("---------------------------");
		this.calculatePrice(false);
		System.out.print("Prix : " + totalPrice + " €");
		Calendar today = Calendar.getInstance();
		Calendar birthday = buyer.getBirthday();
		if(birthday!=null){
			if(birthday.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
					&& birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)){
				double newPrice = Math.round(0.8*totalPrice*100)/100.0;
				System.out.print(" -- Offre d'anniversaire : " + newPrice + " €");
			}
		}
		System.out.println("\n");
	}
}

