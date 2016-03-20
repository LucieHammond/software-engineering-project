package Group16_Project_IS1220_part1_Hammond_Bismut.notifications;

import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;

/**
* C'est l'observer appelé pour envoyer des informations par téléphone. On associe au client un observer de ce type
* si il a accepté de recevoir des notifications et qu'il a indiqué le téléphone comme le type de contact à utiliser
* @author Lucie
*
*/
public class PhoneObserver implements ContactObserver{

	/**
	 * le client auquel l'observer est associé
	 */
	private Client client;
	
	/**
	 * Constructeur
	 * @param client le client auquel l'observer est associé
	 */
	public PhoneObserver(Client client) {
		super();
		this.client = client;
	}
	
	/**
	 * Getter
	 * @return le client auquel l'observer est associé
	 */
	@Override
	public Client getClient() {
		return client;
	}
	
	/**
	 * Setter
	 * @param client le client auquel l'observer est associé
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Rédige et envoie un message par SMS au client pour le notifier d'une offre d'anniversaire. En pratique ici le
	 * message est juste affiché dans la console en spécifiant le numéro de téléphone auquel il devrait être envoyé
	 */
	@Override
	public void sendBirthdayNotification() {
		System.out.println("\nEnvoi d'un SMS automatique à " + client.getContactInfos().get(Client.Contact.phone));
		System.out.println("\"Joyeux anniversaire " + client.getFirstname() + " " + client.getLastname() + " !");
		System.out.println("A l'occasion de ce jour spécial, vous bénéficiez de 20% de réductions sur toutes vos"
				+ " commandes d'aujourd'hui dans n'importe lequel de nos restaurants.");
		System.out.println("Nous esperons vous voir bientôt !\"\n");		
	}

	/**
	 * Rédige et envoie un message par SMS au client pour le notifier d'une nouvelle offre. En pratique ici le
	 * message est juste affiché dans la console en spécifiant le numéro de téléphone auquel il devrait être envoyé
	 */
	@Override
	public void sendNewOfferNotification(String mealname, double oldPrice, double newPrice) {
		System.out.println("\nEnvoi d'un SMS automatique à " + client.getContactInfos().get(Client.Contact.phone));
		System.out.println("\"Bonjour " + client.getFirstname() + " " + client.getLastname() + " !");
		System.out.println("Actuellement, le menu " + mealname + " bénéfinie d'une offre spéciale.");
		System.out.println("Au lieu de " + oldPrice + " €, il n'est plus qu'à " + newPrice + " €");
		System.out.println("Viens vite en profiter !\"\n");	
	}
}
