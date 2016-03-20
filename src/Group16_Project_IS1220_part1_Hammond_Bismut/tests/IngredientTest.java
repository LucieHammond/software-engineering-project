package Group16_Project_IS1220_part1_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Ingredient;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.Client;

public class IngredientTest {

	@Test
	public void testHashCode_differentObjects() {
		Ingredient ingredient = new Ingredient("Fromage", 2);
		String ingredientName = "Fromage";
		assertNotEquals(ingredient.hashCode(), ingredientName.hashCode());
	}
	@Test
	public void testHashCode_caseSensitiveness() {
		Ingredient ingredient = new Ingredient("Fromage", 2);
		Ingredient ingredient2 = new Ingredient("fromage", 2);
		assertNotEquals(ingredient.hashCode(), ingredient2.hashCode());
	}
	@Test
	public void testHashCode_differentIngredients() {
		Ingredient ingredient = new Ingredient("Fromage", 2);
		Ingredient ingredient2 = new Ingredient("Reblochon", 2);
		assertNotEquals(ingredient.hashCode(), ingredient2.hashCode());
	}
	@Test
	public void testHashCode_sameIngredient() {
		Ingredient ingredient1 = new Ingredient("Oeuf", 6);
		Ingredient ingredient2 = new Ingredient("Oeuf", 4);
		assertEquals(ingredient1.hashCode(), ingredient2.hashCode());
	}	

	// Test Equals()
	@Test
	public void testEquals_nonClientParameter() {
		Ingredient ingredient = new Ingredient("Fromage", 2);
		String ingredientName = "Fromage";
		assertFalse(ingredient.equals(ingredientName));
	}
	@Test
	public void testEquals_differentIngredients() {
		Ingredient ingredient = new Ingredient("Fromage", 2);
		Ingredient ingredient2 = new Ingredient("Reblochon", 2);
		assertFalse(ingredient.equals(ingredient2));
	}
	@Test
	public void testEquals_caseSensitiveness() {
		Client client1 = new Client("Bob","Red","bobred","123456");
		Client client2 = new Client("bob","red","BobRed","123456");
		assertFalse(client1.equals(client2));
	}
	@Test
	public void testEquals_sameIngredients() {
		Ingredient ingredient1 = new Ingredient("Oeuf", 6);
		Ingredient ingredient2 = new Ingredient("Oeuf", 4);
		assertTrue(ingredient1.equals(ingredient2));
	}
}
