package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.fidelitycard.LotteryCard;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;

public class LotteryCardTest {

	@Test
	public void testGetPrice() {
		Client client = new Client ("John", "Cagnol", "JohnCagnol", "999999");
		client.associateCard(CardType.lottery);
		OrderManager orderManager = new OrderManager(client, new Restaurant("Restaurant Universitaire"));
		/* On fait une boucle while pour vérifier que l'évênement "gagner son repas" peut bien arriver
		 * à défaut de déterminer la probablilté réelle (sinon la boucle est infinie)
		 */
		double price;
		do{
			orderManager.getCurrentOrder().getMealsToBuy().put(new Meal("Tartiflette",19),3);
			LotteryCard card = (LotteryCard) client.getCard();
			card.setDefinitive(true);
			price = client.getCard().getPrice(orderManager.getCurrentOrder());
		}while(price!=0);
		// Si on arrive à sortir de la boucle, on a prouvé ce qu'on voulait. L'assertion suivante n'est pas utile
		assertTrue(true);
	}

}
