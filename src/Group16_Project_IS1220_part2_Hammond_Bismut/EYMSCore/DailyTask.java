package Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore;

import java.util.TimerTask;

import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.BirthdayNotification;

/**
 * Cette classe définit l'ensemble des actions à réaliser tous les matins à 7h30
 * Elle est appelée par le timer initialisé avec le système
 * @author Lucie
 *
 */
public class DailyTask extends TimerTask {

	/**
	 * Pour le moment, la seule action à réaliser chaque jour est d'envoyer une notification à tous les clients qui fêtent
	 * leur anniversaire ce jour là.
	 */
	@Override
	public void run() {
		new BirthdayNotification().notifyClients();
	}

}
