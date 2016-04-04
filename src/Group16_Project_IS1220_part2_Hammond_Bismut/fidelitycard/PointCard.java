package Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Ingredient;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.Order;

/**
 * La carte de fidélité à points
 * @author rafbis
 *
 */
public class PointCard implements FidelityCard{

	/**
	 * La variable qui compte les points de la carte de fidélité
	 */
	private int points = 0;
	
	/**
	 *  Ce boolean permet de ne pas mettre à jour les points si le calcul se fait avant la souvegarde finale;
	 */
	private boolean definitive;
	
	/**
	 * Le constructeur de la carte à points (ici il n'est pas nécessaire de le spécifier car c'est le constructeur par défaut)
	 */
	public PointCard() {
		super();
	}
	
	/**
	 * Getter
	 * @return Le nombre de points présents sur la carte
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter
	 * @param Le nouveau nombre de points à mettre
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Getter
	 * @return le booleen qui dit si la carte est utilisée pour calculer un prix définitif ou pas
	 */
	public boolean isDefinitive() {
		return definitive;
	}

	/**
	 * Setter
	 * @param definitively le booleen qui dit si la carte est utilisée pour calculer un prix définitif ou pas
	 */
	public void setDefinitive(boolean definitive) {
		this.definitive = definitive;
	}

	/**
	 * La méthode qui permet d'ajouter des points sur la carte suite à un achat.
	 * On ajoute un point pour chaque tranche de 10€
	 * @param Le prix de la commande en cours
	 */
	private void addPoints(double price){
		points+= (int) price/10;
	}
	
	/**
	 * La méthode qui retire 20 points de la carte lorsqu'un client les utilise pour avoir la rédcution
	 */
	private void resetPoints(){
		this.setPoints(this.getPoints() - 20);
	}

	/**
	 * Méthode qui permet de calculer le prix d'une commande dans le cas où le client à une carte à points.
	 * Le prix peut être calculé avec la carte à n'importe quel moment pendant la création de la commande
	 * pour informer le client à titre indicatif. Mais le nombre de points n'est réallement mis à jour que 
	 * si la commande est définitive
	 * @param la commande
	 * @return le prix de la commande
	 */
	@Override
	public double getPrice(Order order) {
		double price = 0;
		for(Meal meal : order.getMealsToBuy().keySet()){
			price+=meal.getPrice() * order.getMealsToBuy().get(meal);// prix du plat * nb de fois ou le plat a été commandé
			for(Ingredient ingredient : meal.getIngredientsAdded()){
				// On ajoute au prix : 
				// quantité de l'ingredient * prix unitaire d'un ingredient * nombre de fois où l'ingrédient a été ajouté
				price+= ingredient.getQuantity() * FidelityCard.ingredientPrice * order.getMealsToBuy().get(meal); 
			}
		}
		//On obtient une réduction lorsque l'on a 20 points
		if (points<20){
			if(definitive)
				this.addPoints(price);
			return price;
		}
		else{
			if(definitive)
				this.resetPoints();
			else
				System.out.println("Vous avez gagné une réduction de 10% grâce à votre carte à points");
			return price * 0.9;
		}
	}

}
