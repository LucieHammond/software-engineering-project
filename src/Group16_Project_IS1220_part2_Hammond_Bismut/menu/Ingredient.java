package Group16_Project_IS1220_part2_Hammond_Bismut.menu;

/**
 * Classe qui représente les ingrédients des plats en tant que constituants de ces derniers
 * Remarque : comme l'ingradient est caractérisé par sa quantité, il n'est défini que par
 * rapport au plat qui le contient et n'a donc pas de sens en dehors de sa relation avec lui.
 * @author Lucie
 *
 */
public class Ingredient {
	/**
	 * le nom de l'ingrédient
	 */
	private String name;
	/**
	 * la quantité
	 */
	private int quantity;

	/**
	 * Constructeur
	 * @param name nom de l'ingrédient
	 * @param quantity quantité de l'ingrédient
	 */
	public Ingredient(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	/**
	 * Getter
	 * @return nom de l'ingrédient
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter nom de l'ingrédient
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter
	 * @return quantité de l'ingrédient
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Setter
	 * @param quantité quantité de l'ingrédient
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Affiche dans la console une liste qui décrit l'ingrédient.
	 */
	public void print(){
		System.out.println(this.name + " : " + this.quantity);
	}
	
	/**
	 * On redéfinit la méthode equals de Objet afin d'empêcher l'utilisateur d'ajouter deux fois le même ingrédient
	 * dans un plat (c'est à dire deux fois un ingredient avec le même nom)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ingredient){
			Ingredient i2 = (Ingredient) obj;
			return (i2.getName().equals(this.name));
		}
		return false;
	}

	/**
	 * On redéfinit également la méthode hashCode pour la même raison que equals
	 */
	@Override
	public int hashCode() {
		return 18 + name.hashCode();
	}
}
