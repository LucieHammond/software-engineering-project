package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.MenuManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.Order;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Chef;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Registration;

public class RestaurantTest {

	@Test
	public void testHashCode_differentObjects() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		String autreRestaurant = "Le Parapente";
		assertNotEquals(restaurant.hashCode(), autreRestaurant.hashCode());
	}
	@Test
	public void testHashCode_caseSensitiveness() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("le parapente");
		assertNotEquals(restaurant.hashCode(), restaurant2.hashCode());
	}
	@Test
	public void testHashCode_differentRestaurants() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("L'Altiplano");
		assertNotEquals(restaurant.hashCode(), restaurant2.hashCode());
	}
	@Test
	public void testHashCode_samerRestaurant() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("Le Parapente");
		assertEquals(restaurant.hashCode(), restaurant2.hashCode());
	}	

	@Test
	public void testEquals_nonClientParameter() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		assertFalse(restaurant.equals("Le Parapente"));
	}
	@Test
	public void testEquals_differentRestaurants() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("L'Altiplano");
		assertFalse(restaurant.equals(restaurant2));
	}
	@Test
	public void testEquals_caseSensitiveness() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("le parapente");
		assertFalse(restaurant.equals(restaurant2));
	}
	@Test
	public void testEquals_sameRestaurant() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Restaurant restaurant2 = new Restaurant("Le Parapente");
		assertTrue(restaurant.equals(restaurant2));
	}


	@Test
	public void testRegisterClient() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.registerClient("Bob","Red","bobred","123456");
		assertTrue(restaurant.getCurrentActivity() instanceof Registration);
	}

	@Test
	public void testInsertChef() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.insertChef("Auguste", "Gusteau", "gugus","313131");
		assertTrue(restaurant.getChefs().contains(new Chef("Auguste", "Gusteau", "gugus","313131")));
	}

	@Test
	public void testLogin_chef() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.insertChef("Auguste", "Gusteau", "gugus","313131");
		restaurant.login("gugus","313131");
		assertTrue(restaurant.getCurrentActivity() instanceof MenuManager);
	}
	@Test
	public void testLogin_client() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.registerClient("Bob","Red","bobred","123456");
		Registration registration = (Registration) restaurant.getCurrentActivity();
		registration.saveModifications();
		restaurant.logout();
		restaurant.login("bobred","123456");
		assertTrue(restaurant.getCurrentActivity() instanceof OrderManager);
	}

	@Test
	public void testLogout() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.insertChef("Auguste", "Gusteau", "gugus","313131");
		restaurant.login("gugus","313131");
		restaurant.logout();
		assertNull(restaurant.getCurrentUser());
	}

	@Test
	public void testStoreOrder() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		Client client = new Client("Bob","Red","bobred","123456");
		Order order = new Order(client, restaurant);
		restaurant.storeOrder(order);
		assertTrue(restaurant.getStoredOrders().contains(order));
	}

	@Test
	public void testModifyClientInfo() {
		Restaurant restaurant = new Restaurant("Le Parapente");
		restaurant.registerClient("Bob","Red","bobred","123456");
		Registration registration = (Registration) restaurant.getCurrentActivity();
		registration.saveModifications();
		restaurant.logout();
		restaurant.login("bobred","123456");
		restaurant.modifyClientInfo();
		assertTrue(restaurant.getCurrentActivity() instanceof Registration);
	}

}
