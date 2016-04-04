package Group16_Project_IS1220_part2_Hammond_Bismut.storing;

/**
 * Cette interface représente la stratégie de tri de l'historique des commandes d'un restaurant pour les afficher suivant des
 * critères données avec des algorithmes différents
 * @author Lucie
 *
 */
public interface OrderingStrategy {

	/**
	 * les différents types de critères possible (3 actuellement)
	 * @author Lucie
	 *
	 */
	public static enum Criteria {unchanged,mostlyModified,justOnSale}
	
	/**
	 * Affiche le résultat du tri des commandes dans la console toujours par ordre du plat le plus commandé au plat
	 * le moins commandé
	 */
	public abstract void showStoredMeals();

}
	