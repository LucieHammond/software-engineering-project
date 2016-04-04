package Group16_Project_IS1220_part2_Hammond_Bismut.notifications;

import java.util.HashSet;

import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;

/**
 * Cette classe abstraite représente une observable chargée d'informer les observers qui assurent chacun le contact 
 * à un client lorsqu'il est nécessaire de leur envoyer des notifications. Les implémentations concrêtes de cette
 * classe sont dédiées à un type de notification particulier pour lesquelles elles sont appelées (anniversaire, nouvelle
 * offre)
 * @author Lucie
 *
 */
public abstract class NotificationManager {

	/**
	 * liste des observers de contact associés aux clients qui acceptent les notifications
	 * cette liste est commune à toutes les sous classes donc définie dans une variable statique
	 */
	private static HashSet<ContactObserver> clientsWithAgreement = new HashSet<ContactObserver>();
	/**
	 * liste des observers à notifer pour une notification en particulier
	 */
	protected HashSet<ContactObserver> clientsToNotify = new HashSet<ContactObserver>();

	/**
	 * Getter statique
	 * @return la liste es observers de contact associés aux clients qui acceptent les notifications
	 */
	public static HashSet<ContactObserver> getClientsWithAgreement() {
		return clientsWithAgreement;
	}
	
	/**
	 * Setter
	 * @params la liste des observers de contact à notifier
	 */
	public static void setClientsWithAgreement(
			HashSet<ContactObserver> clientsWithAgreement) {
		NotificationManager.clientsWithAgreement = clientsWithAgreement;
	}
	
	/**
	 * Setter statique
	 * @return la liste es observers de contact associés aux clients qui acceptent les notifications
	 */
	public void setClientsToNotify(HashSet<ContactObserver> clientsToNotify) {
		this.clientsToNotify = clientsToNotify;
	}
	
	/**
	 * Getter 
	 * @return la liste des observers de contact à notifier
	 */
	public HashSet<ContactObserver> getClientsToNotify() {
		return clientsToNotify;
	}

	/**
	 * Cette méthode ajoute un nouvel observer de contact à la liste statique globale des observers pour représenter
	 * le client passé en paramêtre. Cet observer sera de type MailObserver si le client veut être notifié par mail et
	 * de type PhoneObserver s'il veut être notifié par téléphone.
	 * Cette méthode est celle appelée quand un client sauvegarde ses données à la fin de son inscription ou après 
	 * modification de son profil (s'il a accepté les notifications). Dans ce dernier cas, elle met à jour l'observer 
	 * associé au client et le supprimant et le rajoutant.
	 * @param client le client qui a accepté de recevoir des notifications 
	 * @return
	 */
	public static boolean addClientWithAgreement(Client client) {
		if(!client.getAgreement())
			return false;
		// On retire d'abord de la liste des observers le client qui s'y trouve peut être déjà
		NotificationManager.removeClientWithAgreement(client);
		
		ContactObserver contact;
		if (client.getContactToUse()==Client.Contact.email){
			contact = new MailObserver(client);
		}
		else if (client.getContactToUse()==Client.Contact.phone){
			contact = new PhoneObserver(client);
		}
		else
			return false;
		NotificationManager.clientsWithAgreement.add(contact);
		return true;
	}
	
	/**
	 * Cette méthode retire l'observer de contactassocié à un client si celui-ci a decidé de ne plus accepter les
	 * notifications. Elle est appelée après la sauvegarde des informations du profil.
	 * @param client le client qui n'accepte plus de recevoir des notifications 
	 * @return
	 */
	public static boolean removeClientWithAgreement(Client client) {
		for(ContactObserver observer:clientsWithAgreement){
			if(observer.getClient()==client){
				return clientsWithAgreement.remove(observer);
			}
		}
		return false;
	}
	
	/**
	 * On informe les observers de contact de la liste clientsToNotify qu'il faut qu'ils contactent le client 
	 * auquel ils sont associés pour la notification que représente la sous classe en question
	 */
	public abstract void notifyClients();
	
}
