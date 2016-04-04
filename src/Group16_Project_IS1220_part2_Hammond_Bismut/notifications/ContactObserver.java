package Group16_Project_IS1220_part2_Hammond_Bismut.notifications;

import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;

/**
 * Cette interface décrit le comportement des objets spécialisés dans le contact des clients. Chaque instance des classes
 * qui implémentent cette interface est associée à un client et a accès à toutes les informations de contact de ce client
 * @author Lucie
 *
 */
public interface ContactObserver {
	
	/**
	 * Rédige et envoie un message au client pour son anniversaire. En pratique ici le message est juste affiché
	 * dans la console
	 */
	public abstract void sendBirthdayNotification();
	
	/**
	 * Rédige et envoie un message au client pour le notifier d'une nouvelle offre. En pratique ici le message est 
	 * juste affiché dans la console
	 * @param mealName nom du repas concerné
	 * @param oldPrice prix du repas sans l'offre
	 * @param newPrice nouveau prix du repas avec l'offre
	 */
	public abstract void sendNewOfferNotification(String mealName, double oldPrice, double newPrice);
	
	/**
	 * Getter
	 * @return le client associé à l'instance de la classe
	 */
	public abstract Client getClient();
}
