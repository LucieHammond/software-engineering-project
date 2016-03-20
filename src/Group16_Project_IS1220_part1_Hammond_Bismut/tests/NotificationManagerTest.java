package Group16_Project_IS1220_part1_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import Group16_Project_IS1220_part1_Hammond_Bismut.notifications.ContactObserver;
import Group16_Project_IS1220_part1_Hammond_Bismut.notifications.NotificationManager;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client.Contact;

public class NotificationManagerTest {
	
	@Test
	public void testAddClientWithAgreement_agreementFalse() {
		/* Cette méthode n'est normalement appelée que pour des clients qui ont accepté les notifications
		 * Néanmoins, on revérifie que c'est le cas dans la méthode
		 */
		// On réinitialise la classe NotificationManager
		NotificationManager.setClientsWithAgreement(new HashSet<ContactObserver>());
		
		Client client = new Client("Bob","Red","bobred","123456");
		client.associateAgreement(false);
		NotificationManager.addClientWithAgreement(client);
		assertEquals(0,NotificationManager.getClientsWithAgreement().size());
	}
	@Test
	public void testAddClientWithAgreement_agreementTrue() {
		// On réinitialise la classe NotificationManager
		NotificationManager.setClientsWithAgreement(new HashSet<ContactObserver>());
		
		Client client = new Client("Lucie","Hammond","lulu","123456");
		client.addContactInfos(Contact.phone, "06 73 77 99 58");
		client.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client.associateAgreement(true);
		NotificationManager.addClientWithAgreement(client);
		assertEquals(1,NotificationManager.getClientsWithAgreement().size());
	}
	@Test
	public void testAddClientWithAgreement_clientAddOnlyOnce() {
		// On réinitialise la classe NotificationManager
		NotificationManager.setClientsWithAgreement(new HashSet<ContactObserver>());
		
		Client client = new Client("Lucie","Hammond","lulu","123456");
		client.addContactInfos(Contact.phone, "06 73 77 99 58");
		client.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client.associateAgreement(true);
		NotificationManager.addClientWithAgreement(client);
		NotificationManager.addClientWithAgreement(client);
		assertEquals(1,NotificationManager.getClientsWithAgreement().size());
	}

	@Test
	public void testRemoveClientWithAgreement() {
		// On réinitialise la classe NotificationManager
		NotificationManager.setClientsWithAgreement(new HashSet<ContactObserver>());
		
		// On reprend la même situation que pour addClientWithAgreement(client)
		Client client = new Client("Lucie","Hammond","lulu","123456");
		client.addContactInfos(Contact.phone, "06 73 77 99 58");
		client.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client.associateAgreement(true);
		NotificationManager.addClientWithAgreement(client);
		NotificationManager.removeClientWithAgreement(client);
		assertEquals(0,NotificationManager.getClientsWithAgreement().size());
	}

}
