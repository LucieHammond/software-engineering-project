package Group16_Project_IS1220_part1_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part1_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part1_Hammond_Bismut.fidelitycard.BasicCard;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part1_Hammond_Bismut.orders.Order;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;

public class OrderTest {

	// Test Order(order)
	// La duplication est une étpae clef qui doit être réussie
	@Test
	public void testOrderOrder_sameBuyer() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order1 = new Order(client, new Restaurant("MacDonald"));
		Order order2 = new Order(order1);
		assertEquals(order1.getBuyer(),order2.getBuyer());
	}
	@Test
	public void testOrderOrder_sameRestaurant() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order1 = new Order(client, new Restaurant("MacDonald"));
		Order order2 = new Order(order1);
		assertEquals(order1.getRestaurant(),order2.getRestaurant());
	}
	@Test
	public void testOrderOrder_sameMealsToBuy() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order1 = new Order(client, new Restaurant("MacDonald"));
		Order order2 = new Order(order1);
		assertEquals(order1.getMealsToBuy(),order2.getMealsToBuy());
	}
	@Test
	public void testOrderOrder_sameTotalPrice() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order1 = new Order(client, new Restaurant("MacDonald"));
		Order order2 = new Order(order1);
		assertEquals(order1.getTotalPrice(),order2.getTotalPrice(),0);
	}
	@Test
	public void testOrderOrder_diffentObjects() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order1 = new Order(client, new Restaurant("MacDonald"));
		Order order2 = new Order(order1);
		assertNotEquals(order1,order2);
	}
	
	// Test calculatePrice()
	@Test
	public void testCalculatePrice() {
		Client client = new Client("Raphaël", "Bismut", "raph", "336622");
		Order order = new Order(client, new Restaurant("MacDonald"));
		// On ne peut pas utiliser addMeal ici parce que la méthode n'est pas public
		order.getMealsToBuy().put(new Meal("Raclette",15),2);
		BasicCard card = new BasicCard();
		order.calculatePrice(true);
	
		assertEquals(card.getPrice(order),order.getTotalPrice(),0.01);// Le prix est arrondi au centime près
	}
}
