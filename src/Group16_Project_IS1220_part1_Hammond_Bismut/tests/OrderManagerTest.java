package Group16_Project_IS1220_part1_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part1_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part1_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;

public class OrderManagerTest {

	// Test OrderManager()
	@Test
	public void testOrderManager() {
		/* On vérifie qu'une commande est bien initialisée directement avec ce constructeur 
		(appel de la méthode beginActivity())*/
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		assertNotNull(orderManager.getCurrentOrder());
	}
	
	// Test cancelModifications()
	@Test
	public void testCancelModifications() {
		// Ces trois premières lignes permettent d'instancier l'orderManager
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		
		// Le fait d'annuler la commande enlève les effets de l'appel automatique de beginActivity()
		orderManager.cancelModifications();
		assertNull(orderManager.getCurrentOrder());	
	}
	
	// Test beginNewOrder()
	@Test
	public void testBeginNewOrder() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		
		orderManager.cancelModifications();
		orderManager.beginNewOrder();
		assertNotNull(orderManager.getCurrentOrder());
	}

	// Test beginActivity() 
	@Test
	public void testBeginActivity() {
		// Même effet que beginNewOrder avec une ligne affichée en moins
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		
		orderManager.cancelModifications();
		orderManager.beginActivity();
		assertNotNull(orderManager.getCurrentOrder());
	}
	
	// Test saveModifications()
	@Test
	public void testSaveModifications() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		orderManager.beginActivity();
		// Ici une commande est créée qui est vide mais on peut quand même l'enregistrer
		orderManager.saveModifications();
		// On vérifie qu'une commande a bien été enregistrée
		assertEquals(1,restaurant.getStoredOrders().size());
	}
	
	// Test selectMeal()
	@Test
	public void testSelectMeal_mealNotInMenu() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		
		// Il n'y a aucun plat dans le menu
		orderManager.selectMeal("Tartiflette",2);
		assertEquals(0,orderManager.getCurrentOrder().getMealsToBuy().size());
	}
	@Test
	public void testSelectMeal_rightMealName() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		Meal meal = new Meal("Tartiflette",18.5);
		restaurant.getMenu().getMeals().add(meal);
		orderManager.selectMeal("Tartiflette",2);
		assertTrue(orderManager.getCurrentOrder().getMealsToBuy().containsKey(meal));
	}
	@Test
	public void testSelectMeal_rightQuantity() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		OrderManager orderManager = new OrderManager(client, restaurant);
		Meal meal = new Meal("Tartiflette",18.5);
		restaurant.getMenu().getMeals().add(meal);
		orderManager.selectMeal("Tartiflette",2);
		assertEquals(2,(int) orderManager.getCurrentOrder().getMealsToBuy().get(meal));
	}

	// Test addToFavouriteMeals()
	@Test
	public void testAddToFavouriteMeals() {
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		restaurant.getMenu().getMeals().add(new Meal("Tartiflette",18.5));
		OrderManager orderManager = new OrderManager(client, restaurant);
		orderManager.addToFavouriteMeals("Tartiflette");
		assertEquals(1,client.getFavouriteMeals().size());
	}

	// Test removeToFavouriteMeals()
	@Test
	public void testRemoveToFavouriteMeals() {
		// On reprend le même cas que pour addToFavouriteMeal et on enlève le plat ajouté
		Client client = new Client ("Bob", "Red", "bobred", "123456");
		Restaurant restaurant = new Restaurant("Crêperie");
		restaurant.getMenu().getMeals().add(new Meal("Tartiflette",18.5));
		OrderManager orderManager = new OrderManager(client, restaurant);
		orderManager.addToFavouriteMeals("Tartiflette");
		orderManager.removeToFavouriteMeals("Tartiflette");
		assertEquals(0,client.getFavouriteMeals().size());
	}
}
