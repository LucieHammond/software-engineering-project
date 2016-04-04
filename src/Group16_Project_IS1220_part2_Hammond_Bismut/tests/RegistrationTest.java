package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;
import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard.*;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Registration;

public class RegistrationTest {

	// Test Registration()
	@Test
	public void testRegistration() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		
		/* On vérifie que la variable qui stocke le client en train de s'inscrire a bien été initialisée*/
		assertNotNull(registration.getClientUnderRegistration());
	}

	// Test cancelModifications()
	@Test
	public void testCancelModifications() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.cancelModifications();
		
		/* On vérifie que le variable qui stocke le client en train de s'inscrire a bien été remise à null*/
		assertNull(registration.getClientUnderRegistration());
	}

	// Test saveModifications()
	@Test
	public void testSaveModifications() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.saveModifications();
		// On vérifie qu'un client a été ajouté au CoreSystem
		assertEquals(1,CoreSystem.getSharedSystem().getClients().size());
	}

	// Test associateAddress()
	@Test
	public void testAssociateAddress() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.associateAddress("5 avenue Sully-Prudhomme, 92290 Châyenay-Malabry");
		assertEquals("5 avenue Sully-Prudhomme, 92290 Châyenay-Malabry",client.getAddress());
	}

	// Test associateAgreement()
	@Test
	public void testAssociateAgreement() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		// Il est nécéssaire d'ajouter des infos de contact pour pouvoir mettre l'agreement à true
		registration.addContactInfos(Contact.phone, "06 73 77 99 58");
		registration.associateAgreement(true);
		assertTrue(client.getAgreement());
	}

	// Test associateCard()
	@Test
	public void testAssociateCard() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.associateCard(CardType.lottery);
		assertTrue(client.getCard() instanceof LotteryCard);
	}

	// Test addContactInfos
	@Test
	public void testAddContactInfos_numberOfContactAdded() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.addContactInfos(Contact.phone, "06 73 77 99 58");
		assertEquals(1,client.getContactInfos().size());
	}
	@Test
	public void testAddContactInfos_rightContactAdded() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.addContactInfos(Contact.phone, "06 73 77 99 58");
		assertEquals("06 73 77 99 58",client.getContactInfos().get(Contact.phone));
	}

	// Test associateBirthday()
	@Test
	public void testAssociateBirthday_wrongParameters() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.associateBirthday(53,16);
		// La variable birthday reste null car on a spécifié une date qui n'existe pas
		assertNull(client.getBirthday());
	}
	@Test
	public void testAssociateBirthday_matchDay() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.associateBirthday(4,7);
		// La variable birthday reste null car on a spécifié une date qui n'existe pas
		assertEquals(4, client.getBirthday().get(Calendar.DAY_OF_MONTH));
	}
	@Test
	public void testAssociateBirthday_matchMonth() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		registration.associateBirthday(4,7);
		// La variable birthday reste null car on a spécifié une date qui n'existe pas
		// Dans la classe Calendar, les mois sont numérotés à partir de 0
		assertEquals(6, client.getBirthday().get(Calendar.MONTH));
	}

	// Test chooseContactToUse()
	@Test
	public void testChooseContactToUse() {
		Client client = new Client("Bob","Red","bobred","123456");
		Registration registration = new Registration(client, new Restaurant("Pizzeria"), false);
		// On a besoin d'ajouter les informations de contact pour choisir le type de contact à utiliser
		registration.addContactInfos(Contact.phone, "06 73 77 99 58");
		registration.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		registration.chooseContactToUse(Contact.phone);
		assertEquals(Contact.phone, client.getContactToUse());
	}
}
