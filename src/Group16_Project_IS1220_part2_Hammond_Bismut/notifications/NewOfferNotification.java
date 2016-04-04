package Group16_Project_IS1220_part2_Hammond_Bismut.notifications;

/**
 * Cette classe correspond en quelque sorte à une observable qui est initialisée et appelée lorsqu'il faut 
 * notifier les clients qu'une nouvelle offre est disponible (juste après la création de l'offre par le chef)
 * @author Lucie
 *
 */
public class NewOfferNotification extends NotificationManager {

	/**
	 * nom du plat concerné par la nouvelle offre
	 */
	private String mealName;
	/**
	 * prix du plat sans l'offre
	 */
	private double oldPrice;
	/**
	 * nouveau prix du plat (avec l'offre)
	 */
	private double newPrice;
	
	/**
	 * Constructeur. Cette classe stocke dans ces attributs les informations sur la nouvelle offre qui 
	 * lui sont donnés en paramêtre. Elle enverra ces paramêtres aux observers de contact pour qu'ils les 
	 * incluent dans le message envoyé aux clients
	 * @param mealName
	 * @param oldPrice
	 * @param newPrice
	 */
	public NewOfferNotification(String mealName, double oldPrice, double newPrice){
		super();
		this.mealName = mealName;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		clientsToNotify = NotificationManager.getClientsWithAgreement();
	}
	
	/**
	 * Getter
	 * @return le nom du plat concerné par la nouvelle offre
	 */
	public String getMealName() {
		return mealName;
	}

	/**
	 * Setter
	 * @param mealName le nom du plat concerné par la nouvelle offre
	 */
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	/**
	 * Getter
	 * @return le prix du plat sans l'offre
	 */
	public double getOldPrice() {
		return oldPrice;
	}

	/**
	 * Setter
	 * @param oldPrice le prix du plat sans l'offre
	 */
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	/**
	 * Getter
	 * @return le nouveau prix du plat (avec l'offre)
	 */
	public double getNewPrice() {
		return newPrice;
	}

	/**
	 * Setter
	 * @param newPrice le nouveau prix du plat (avec l'offre)
	 */
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	/**
	 * Cette méthode notifie tous les clients qui ont accepté d'être notifiés. Ces clients sont représentés dans 
	 * ce cas par des observers spécialisés pour le contact qui implémentent l'interface ContactObservervariable et 
	 * qui sont recensés dans une liste statique de la classe mère NotificationManager. Ces éléments seront chargés
	 * de rédiger et d'envoyer à leur façon le message de notification.
	 */
	@Override
	public void notifyClients() {
		for(ContactObserver observer : clientsToNotify){
			observer.sendNewOfferNotification(this.mealName, this.oldPrice, this.newPrice);
		}	
	}
}
