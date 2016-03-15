package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

public class OrderManager {
	private Order currentOrder;
	private Menu menu;
	
	public OrderManager(Menu menu) {
		super();
		this.menu = menu;
	}

	public void cancelOrder(){
		currentOrder = null;
	}
	
	public void selectMeal(String mealName, int quantity){
		for(Meal meal:menu.getMeals()){
			
		}
	}
}
