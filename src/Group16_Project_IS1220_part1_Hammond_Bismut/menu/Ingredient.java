package Group16_Project_IS1220_part1_Hammond_Bismut.menu;

public class Ingredient {
	private String name;
	private int quantity;

	public Ingredient(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void print(){
		System.out.println(this.name + " : " + this.quantity);
	}
}
