package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.MenuManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;

public class BasicCardTest {

	@Test
	public void testGetPrice() {
		Client client = new Client ("John", "Cagnol", "JohnCagnol", "999999");
		client.associateCard(CardType.basic);
		Restaurant restaurant = new Restaurant("Restaurant Universitaire");
		OrderManager orderManager = new OrderManager(client, restaurant);
		MenuManager menuManager = new MenuManager(restaurant.getMenu());
		menuManager.createMeal("Tartiflette",20);
		menuManager.saveModifications();
		menuManager.createMeal("Raclette",18.5);
		menuManager.saveModifications();
		menuManager.putInSpecialOffer("Tartiflette", 16.75);
		
		// le client prend 2 raclettes et 3 tartiflettes. La tartiflette bénéficie d'une offre spéciale
		orderManager.selectMeal("Tartiflette", 3);
		orderManager.selectMeal("Raclette", 2);
		
		assertEquals(16.75*3+18.5*2,client.getCard().getPrice(orderManager.getCurrentOrder()),0);
	}

}
