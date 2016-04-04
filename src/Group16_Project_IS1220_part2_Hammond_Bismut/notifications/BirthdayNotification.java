package Group16_Project_IS1220_part2_Hammond_Bismut.notifications;

import java.util.Calendar;

/**
 * Cette classe correspond en quelque sorte à une observable qui est initialisée et appelée lorsqu'il faut 
 * notifier les clients qui le veulent d'une offre d'anniversaire (soit au démarrage du système, soit à 7h30
 * tous les jours)
 * @author Lucie
 *
 */
public class BirthdayNotification extends NotificationManager {

	@Override
	/**
	 * Cette méthode cherche dans la liste des clients qui ont accepté d'être notifiés (variable statique de sa classe
	 * mère NotificationManager) ceux qui fêtent leur anniversaire aujourd'hui (où aujourd'hui désigne le jour où la 
	 * méthode a été appelée) et les stocke dans le tableau clientsToNotify. Puis elle demande à tous les éléments de
	 * ce tableau (qui sont en fait des observers spécialisés implémentant l'interface ContactObserver) de contacter 
	 * à leur façon les clients
	 */
	public void notifyClients() {
		
		Calendar today = Calendar.getInstance();
		for (ContactObserver observer : NotificationManager.getClientsWithAgreement()){
			Calendar birthday = observer.getClient().getBirthday();
			if(birthday!=null){
				if(birthday.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
					&& birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)){
					clientsToNotify.add(observer);
				}
			}
		}
		for(ContactObserver observer : clientsToNotify){
			observer.sendBirthdayNotification();
		}		
	}
}
