package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.*;

public class MealTest {

	@Test
	public void testHashCode_differentObjects() {
		Meal meal = new Meal("Tartiflette",18.5);
		Menu menu = new Menu();
		assertNotEquals(meal.hashCode(), menu.hashCode());
	}
	@Test
	public void testHashCode_caseSensitiveness() {
		Meal meal = new Meal("Tartiflette",18.5);
		Meal meal2 = new Meal("tartiflette",18.5);
		assertNotEquals(meal.hashCode(), meal2.hashCode());
	}
	@Test
	public void testHashCode_differentMeals() {
		Meal meal = new Meal("Tartiflette",18.5);
		Meal meal2 = new Meal("Raclette",21.5);
		assertNotEquals(meal.hashCode(), meal2.hashCode());
	}
	@Test
	public void testHashCode_sameMeal() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addIngredient("Rebolchon",1);
		Meal meal2 = new Meal("Tartiflette",18.5);
		meal.addIngredient("Lardons",4);
		assertEquals(meal.hashCode(), meal2.hashCode());
	}	
	
	@Test
	public void testEquals_nonMealParameter() {
		Meal meal = new Meal("Tartiflette",18.5);
		Menu menu = new Menu();
		assertFalse(meal.equals(menu));
	}
	@Test
	public void testEquals_differentMeals() {
		Meal meal = new Meal("Tartiflette",18.5);
		Meal meal2 = new Meal("Raclette",21.5);
		assertFalse(meal.equals(meal2));
	}
	@Test
	public void testEquals_caseSensitiveness() {
		Meal meal = new Meal("Tartiflette",18.5);
		Meal meal2 = new Meal("tartiflette",18.5);
		assertFalse(meal.equals(meal2));
	}
	@Test
	public void testEquals_sameClient() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addIngredient("Rebolchon",1);
		Meal meal2 = new Meal("Tartiflette",18.5);
		meal.addIngredient("Lardons",4);
		assertTrue(meal.equals(meal2));
	}

	@Test
	public void testAddIngredient() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addIngredient("Reblochon",1);
		assertTrue(meal.getIngredients().contains(new Ingredient("Reblochon",1)));
	}

	@Test
	public void testRemoveIngredient() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addIngredient("Reblochon",1);
		meal.removeIngredient("Reblochon");
		assertFalse(meal.getIngredients().contains(new Ingredient("Reblochon",1)));
	}

	@Test
	public void testAddNewIngredient() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addNewIngredient("Reblochon",1);
		assertTrue(meal.getIngredientsAdded().contains(new Ingredient("Reblochon",1)));
	}

	@Test
	public void testRemoveNewIngredient() {
		Meal meal = new Meal("Tartiflette",18.5);
		meal.addNewIngredient("Reblochon",1);
		meal.removeNewIngredient("Reblochon");
		assertFalse(meal.getIngredientsAdded().contains(new Ingredient("Reblochon",1)));
	}

}
