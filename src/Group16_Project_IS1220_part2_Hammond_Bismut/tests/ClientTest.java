package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Chef;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.Contact;
import Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard.*;

public class ClientTest {

	// Test HashCode()
	@Test
	public void testHashCode_differentObjects() {
		Client client = new Client("Bob","Red","bobred","123456");
		Chef chef = new Chef("Bob","Red","bobred","123456");
		assertNotEquals(client.hashCode(), chef.hashCode());
	}
	@Test
	public void testHashCode_caseSensitiveness() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		Client client2 = new Client("bob","red","BobRed","123456");
		assertNotEquals(client1.hashCode(), client2.hashCode());
	}
	@Test
	public void testHashCode_differentClients() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		Client client2 = new Client("Martin","Dupond","martin59","000000");
		assertNotEquals(client1.hashCode(), client2.hashCode());
	}
	@Test
	public void testHashCode_sameClient() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.associateAgreement(true);
		Client client2 = new Client("Bob","Red","bobred","123456");
		client2.associateAgreement(false);
		assertEquals(client1.hashCode(), client2.hashCode());
	}	

	// Test Equals()
	@Test
	public void testEquals_nullParameter() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertFalse(client1.equals(null));
	}
	@Test
	public void testEquals_nonClientParameter() {
		Client client = new Client("Bob","Red","bobred","123456");
		Chef chef = new Chef("Bob","Red","bobred","123456");
		assertFalse(client.equals(chef));
	}
	@Test
	public void testEquals_differentClients() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		Client client2 = new Client("Martin","Dupond","martin59","000000");
		assertFalse(client1.equals(client2));
	}
	@Test
	public void testEquals_caseSensitiveness() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		Client client2 = new Client("bob","red","BobRed","123456");
		assertFalse(client1.equals(client2));
	}
	@Test
	public void testEquals_sameClient() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.associateAgreement(true);
		Client client2 = new Client("Bob","Red","bobred","123456");
		client2.associateAgreement(false);
		assertTrue(client1.equals(client2));
	}

	// Test Client()
	@Test 
	public void testClientRightParameters() {
	    try {
	      new Client("1","2","3","4");
	      new Client("string","string","string","string");
	    } catch (Exception e) {
	      fail(e.getMessage());
	    }
	}
	@Test (expected=NullPointerException.class)
	public void testClientNullParameter() {
		new Client(null,null,null,null);       
	}
	@Test (expected=IllegalArgumentException.class)
	public void testClientEmptyStringParameter(){
		new Client("Bob","Red","","123456");     
	}

	// Test GetBirthDay()
	@Test
	public void testGetBirthday_nullIfNotSpecified() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertNull(client1.getBirthday());
	}
	@Test
	public void testGetBirthday_returnType() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setBirthday(4, 7);
		assertTrue(client1.getBirthday() instanceof Calendar);
	}
	@Test
	public void testGetBirthday_matchDay() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setBirthday(4, 7);
		assertEquals(4,client1.getBirthday().get(Calendar.DAY_OF_MONTH));
	}
	@Test
	public void testGetBirthday_matchMonth() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setBirthday(4, 7);
		// Dans la classe Calendar définie en Java, les mois sont indexés à partir de 0 donc juillet correspond à 6
		assertEquals(6,client1.getBirthday().get(Calendar.MONTH));
	}

	// Test setBirthday()
	@Test
	public void testSetBirthday_invalidInput() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setBirthday(34, 17);
		// La date d'anniversaire n'a pas été affectée
		assertNull(client1.getBirthday());
	}
	@Test
	public void testSetBirthday_correctInput() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setBirthday(4, 7);
		assertNotNull(client1.getBirthday());
	}
	
	// Test GetAddress()
	@Test
	public void testGetAddress_emptyIfNotSpecified() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertEquals("",client1.getAddress());
	}
	@Test
	public void testGetAddress_returnType() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setAddress("5 avenue Sully-Prudhomme, 92290 Châtenay-Malabry");
		assertTrue(client1.getAddress() instanceof String);
	}
	@Test
	public void testGetAddress_matchParameter() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setAddress("5 avenue Sully-Prudhomme, 92290 Châtenay-Malabry");
		assertEquals("5 avenue Sully-Prudhomme, 92290 Châtenay-Malabry",client1.getAddress());
	}

	// Test setAddress()
	public void testSetAddress_correctInput() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.setAddress("5 avenue Sully-Prudhomme, 92290 Châtenay-Malabry");
		assertNotEquals("",client1.getAddress());
	}

	// Test getContactToUse()
	@Test
	public void testGetContactToUse_nullIfNoContactInfoSpecified() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		// Si on essaye de fixer le ContactToUse sans avoir renseigné les informations de contact, il reste null
		client1.setContactToUse(Contact.email);
		assertNull(client1.getContactToUse());
	}
	@Test
	public void testGetContactToUse_matchForPhone() {
		Client client1 = new Client("Lucie","Hammond","lulu","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client1.setContactToUse(Contact.phone);
		assertEquals(Contact.phone, client1.getContactToUse());
	}
	@Test
	public void testGetContactToUse_matchForEmail() {
		Client client1 = new Client("Lucie","Hammond","lulu","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client1.setContactToUse(Contact.email);
		assertEquals(Contact.email, client1.getContactToUse());
	}
	@Test
	public void testGetContactToUse_emailDefault() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		// Quand on ne renseigne rien, c'est l'email par défaut
		assertEquals(Contact.email, client1.getContactToUse());
	}
	
	// Test setContectToUse()
	@Test
	public void testSetContactToUse_nullParameter() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		client1.setContactToUse(null);
		/* Si des informations de contact ont été données, cette variable ne peut pas être nulle 
		(elle est affectée automatiquement). On ne peut pas la mettre à null à la main*/
		assertNotNull(client1.getContactToUse());
	}
	@Test
	public void testSetContactToUse_noSetIfNoRightInfoGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		// Seul le téléphone est donné et on veut associer le mail
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		// A ce stade le téléphone est mis par défaut
		client1.setContactToUse(Contact.email);
		assertEquals(Contact.phone,client1.getContactToUse());
	}
	@Test
	public void testSetContactToUse_setIfInfoGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		// Seul le téléphone est donné et on veut associer le mail
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.setContactToUse(Contact.phone);
		assertNotNull(client1.getContactToUse());
	}

	// Test getAgreement
	public void testGetAgreement_defaultValue() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertFalse(client1.getAgreement());
	}
	public void testGetAgreement_falseIfNoContactInfoGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		// Si on ne donne pas d'infos de contact, l'agreement reste false
		client1.associateAgreement(true);
		assertFalse(client1.getAgreement());
	}
	public void testGetAgreement_matchValue() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.associateAgreement(true);
		assertTrue(client1.getAgreement());
	}

	// Test associateAgreement
	@Test
	public void testAssociateAgreement_rightSetIfContactInfosGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.associateAgreement(true);
		assertTrue(client1.getAgreement());
	}
	@Test
	public void testAssociateAgreement_noSetIfNoContactInfosGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.associateAgreement(true);
		// A ce stade l'agreement vaut true
		client1.removeContactInfos(Contact.phone);
		// Comme on a retiré toutes les infos de contact, l'agreement doit valoir false, même si on veut le mettre à true
		client1.associateAgreement(true);
		assertFalse(client1.getAgreement());
	}

	// Test getFavouriteMeals()
	@Test
	public void testGetFavouriteMeals_emptyList() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertEquals(new ArrayList<Meal>(), client1.getFavouriteMeals());
	}
	@Test
	public void testGetFavouriteMeals_returnType() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertTrue(client1.getFavouriteMeals() instanceof ArrayList<?>);
	}

	// Test getContactInfos()
	@Test
	public void testGetContactInfos_emptyMap() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertEquals(new HashMap<Contact,String>(), client1.getContactInfos());
	}
	@Test
	public void testGetContactInfos_matchInfos() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		assertEquals("06 73 77 99 58",client1.getContactInfos().get(Contact.phone));
	}

	@Test
	public void testGetCard_defaultCard() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		assertTrue(client1.getCard() instanceof BasicCard);
	}

	// Test associateCard()
	@Test
	public void testAssociateCard_MatchAssociation() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.associateCard(CardType.lottery);
		assertTrue(client1.getCard() instanceof LotteryCard);
	}
	@Test
	public void testAssociateCard_nullParameter() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.associateCard(null);
		// Le type de carte n'est pas changé, c'est la carte basique par défault
		assertTrue(client1.getCard() instanceof BasicCard);
	}

	// Test addContactInfos()
	@Test
	public void testAddContactInfos_nullType() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(null, "06 73 77 99 58");
		// L'information n'a pas été ajoutée car le type est null
		assertEquals(0, client1.getContactInfos().size());
	}
	@Test
	public void testAddContactInfos_rightParameters() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		/*Par souci de simplicité, on ne vérifie pas si l'information de contact donnée correspond vraiment à
		un numéro de téléphone ou à une adresse mail*/
		assertEquals(1, client1.getContactInfos().size());
	}

	// Test removeContactInfos()
	@Test
	public void testRemoveContactInfos_nullType() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.removeContactInfos(null);
		// Aucune information n'a été retirée car le type est null
		assertEquals(1, client1.getContactInfos().size());
	}
	@Test
	public void testRemoveContactInfos_infoNotGiven() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.removeContactInfos(Contact.email);
		// L'email n'avait jamais été donné
		assertEquals(1, client1.getContactInfos().size());
	}
	@Test
	public void testRemoveContactInfos_rightParameters() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.removeContactInfos(Contact.phone);
		assertEquals(0, client1.getContactInfos().size());
	}

	// Test adjustContactToUse()
	@Test
	public void testAdjustContactToUse_onlyPhoneSet() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		assertEquals(Contact.phone,client1.getContactToUse());
	}
	@Test
	public void testAdjustContactToUse_onlyMailSet() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		assertEquals(Contact.email,client1.getContactToUse());
	}
	@Test
	public void testAdjustContactToUse_defaultWhenBothSet() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.addContactInfos(Contact.email, "lucie.hammond@student.ecp.fr");
		assertEquals(Contact.email,client1.getContactToUse());
	}
	@Test
	public void testAdjustContactToUse_nullWhenNoContactInfos() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.addContactInfos(Contact.phone, "06 73 77 99 58");
		client1.setContactToUse(Contact.phone);
		client1.removeContactInfos(Contact.phone);
		assertNull(client1.getContactToUse());
	}

	// Test printInfos()
	@Test
	public void testPrintInfos() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		client1.printInfos();
		// On vérifie juste que la méthode ne renvoie pas d'erreur si on ne renseigne aucune infos supplémentaire au client
	}
}
