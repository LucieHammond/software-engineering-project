package Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard;

import Group16_Project_IS1220_part2_Hammond_Bismut.orders.Order;

/**
 * L'interface des cartes de fidélité: leur but est de déterminer le prix de la commande,
 * car tous les clients ont une carte de fidélité
 * @author rafbis
 *
 */
public interface FidelityCard {
	
	public static final double ingredientPrice = 2;
	
	/**
	 * Méthode qui calcule le prix de la commande après l'effet de la carte de fidélité
	 * @param commande
	 * @return prix calculé suivant la carte de fidélité que possède le client
	 */
	public abstract double getPrice(Order order);
	
}
