package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;

public class PointCardTest {

	@Test
	public void testGetPrice() {
		Client client = new Client ("John", "Cagnol", "JohnCagnol", "999999");
		client.associateCard(CardType.point);
		OrderManager orderManager = new OrderManager(client, new Restaurant("Restaurant Universitaire"));
		/* avec cette commande, le client en a pour 200€ donc il gagne 20 points
		 * Je n'ai pas utilisé selectMeal ici car cette fonction aurait vérifié si le plat se trouve dans le menu, 
		 * ce qui n'est pas le cas
		 */
		orderManager.getCurrentOrder().getMealsToBuy().put(new Meal("Tartiflette",20),10);
		orderManager.saveModifications();
		orderManager.beginNewOrder();
		orderManager.getCurrentOrder().getMealsToBuy().put(new Meal("Tartiflette",20),3);
		orderManager.getCurrentOrder().getMealsToBuy().put(new Meal("Fondue",17.5),4);
		// Comme le client a plus de 20 points, il a une réduction
		assertEquals((20*3+17.5*4)*0.9,client.getCard().getPrice(orderManager.getCurrentOrder()),0);
	}
}
