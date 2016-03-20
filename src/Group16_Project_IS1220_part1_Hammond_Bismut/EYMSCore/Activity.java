package Group16_Project_IS1220_part1_Hammond_Bismut.EYMSCore;

/**
 * Interface qui définit les principales actions proposées aux utilisauteurs pour interagir avec les acteurs du système
 * aux cours des différentes activiités qu'ils peuvent être amenés à effectuer quand ils sont connectés 
 * (inscription, commande d'un repas, modification de la carte). Les utilisateurs n'auront pas accès aux mêmes activités
 * suivant leur statut (chef, client)
 * @author Lucie
 *
 */
public interface Activity {
	
	/**
	 * Méthode qui initialise l'activité. Souvent elle permet d'afficher un message d'introduction dans la console
	 * qui explique ce qu'il est possible de faire
	 */
	public abstract void beginActivity();
	
	/**
	 * Annule les modifications réalisées
	 */
	public abstract void cancelModifications();
	
	/**
	 * Sauvegarde les modifications réalisées
	 */
	public abstract void saveModifications();
	
	/**
	 * Affiche sur la console l'état des modifications pour informer l'utilisateur
	 */
	public abstract void showModifications();
}
