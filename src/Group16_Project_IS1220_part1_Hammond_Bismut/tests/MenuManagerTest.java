package Group16_Project_IS1220_part1_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Ingredient;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Menu;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.MenuManager;

public class MenuManagerTest {

	@Test
	public void testCancelModifications() {
		MenuManager menuManager = new MenuManager(new Menu());
		menuManager.createMeal("Tartiflette", 18);
		menuManager.cancelModifications();
		assertNull(menuManager.getMealUnderConstruction());
	}

	@Test
	public void testSaveModifications() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		menuManager.saveModifications();
		assertTrue(menuManager.getMenu().getMeals().contains(new Meal("Tartiflette", 18)));
	}

	@Test
	public void testCreateMeal() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		assertEquals(new Meal("Tartiflette", 18),menuManager.getMealUnderConstruction());
	}

	@Test
	public void testPutInSpecialOffer_beforeSpecialOffer() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		// La variable spcialOffer vaut -1 s'il n'y a pas d'offre spéciale
		assertEquals(-1,menuManager.getMealUnderConstruction().getSpecialOffer(),0);
	}
	@Test
	public void testPutInSpecialOffer_afterSpecialOffer() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		menuManager.saveModifications();
		menuManager.putInSpecialOffer("Tartiflette", 15.25);
		Meal tartiflette = null;
		for(Meal meal : menu.getMeals()){
			if(meal.getName()=="Tartiflette")
				tartiflette = meal;
		}
		assertEquals(15.25,tartiflette.getSpecialOffer(),0);
	}

	@Test
	public void testRemoveFromSpecialOffer() {
		// On reprend le scénario précédent
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		menuManager.saveModifications();
		menuManager.putInSpecialOffer("Tartiflette", 15.25);
		menuManager.removeFromSpecialOffer("Tartiflette");
		Meal tartiflette = null;
		for(Meal meal : menu.getMeals()){
			if(meal.getName()=="Tartiflette")
				tartiflette = meal;
		}
		assertEquals(-1,tartiflette.getSpecialOffer(),0);
	}

	@Test
	public void testAddIngredient() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		menuManager.addIngredient("Saumon fumé", 2);
		assertTrue(menuManager.getMealUnderConstruction().getIngredients().contains(new Ingredient("Saumon fumé", 2)));
	}
	
	@Test
	public void testRemoveIngredient() {
		Menu menu = new Menu();
		MenuManager menuManager = new MenuManager(menu);
		menuManager.createMeal("Tartiflette", 18);
		menuManager.addIngredient("Saumon fumé", 2);
		menuManager.removeIngredient("Saumon fumé");
		assertFalse(menuManager.getMealUnderConstruction().getIngredients().contains(new Ingredient("Saumon fumé", 2)));
	}

}
