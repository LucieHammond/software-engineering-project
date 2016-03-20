package Group16_Project_IS1220_part1_Hammond_Bismut.fidelitycard;

import java.util.Random;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Ingredient;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part1_Hammond_Bismut.orders.Order;

/**
 * La carte de fidélité qui offre un repas gratuit de temps en temps
 * @author rafbis
 *
 */
public class LotteryCard implements FidelityCard{
	
	/**
	 * L'objet qui permet de faire jouer le hasard
	 */
	Random random = new Random();
	/**
	 * Ce boolean permet de ne pas révéler si le client a gagné son repas tant que celui-ci n'a pas sauvegardé définitivement sa commande;
	 */
	
	private boolean definitive;
	
	/**
	 * Le constructeur de la carte à points (ici il n'est pas nécessaire de le spécifier car c'est le constructeur par défaut)
	 */
	public LotteryCard() {
		super();
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

	@Override
	/** 
	 * On part du principe que le client n'a pas besoin de savoir s'il a gagné son repas tant qu'il n'a pas
	 * commandé donc on fait le tirage au sort au moment de calculer le prix. On considère donc que le client 
	 * a une certaine probabilité de gagner son repas à chaque fois qu'il commmande mais il ne le sais pas à 
	 * l'avance (sinon il commanderait beaucoup plus). Si il commande plusieurs fois à la suite, il y a des 
	 * tirages au sort différents pour chaque commande (pour éviter la fraude)
	 */
	public double getPrice(Order order) {
		double price = 0;
		if(definitive){
			int lottery = random.nextInt(40);
			boolean luckyDay = (lottery==20);// 20 est un nombre pris au hasard entre 0 et 39
			if (luckyDay){
				System.out.println("Quelle chance ! Votre repas est gratuit aujourd'hui !");
				return price;
			}
		}else{
			System.out.println("Peut-être pourrez vous gagner un repas gratuit aujourd'hui !");
		}
		// Si il n'a pas gagné ou si la commande n'est pas encore sauvegardée définitivement
		for(Meal meal : order.getMealsToBuy().keySet()){
			price+=meal.getPrice() * order.getMealsToBuy().get(meal);
			for(Ingredient ingredient : meal.getIngredientsAdded()){
				price+= ingredient.getQuantity() * FidelityCard.ingredientPrice * order.getMealsToBuy().get(meal); 
			}
		}
		return price;
	}
}
