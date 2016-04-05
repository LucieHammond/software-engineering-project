package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.*;

import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Ingredient;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.MenuManager;

public class MenuManagerPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	private MenuManager manager;
	private JButton[] button = new JButton[9];
	private JPanel[] row = new JPanel[7];

	public MenuManagerPage(MenuManager manager){
		super(new GridLayout(0,1));
		this.manager = manager;
		
		button[0] = new JButton("Créer un nouveau plat");
		button[1] = new JButton("Voir les ingrédients");
		button[2] = new JButton("Ajouter un ingrédient");
		button[3] = new JButton("Enlever un ingrédient");
		button[4] = new JButton("Ajouter une offre spéciale");
		button[5] = new JButton("Retirer une offre spéciale");
		button[6] = new JButton("Sauvegarder le plat");
		button[7] = new JButton("Voir le plat");
		button[8] = new JButton("Annuler le plat");

		for(int i=0;i<9;i++){button[i].addActionListener(this);}
		for(int i=0;i<4;i++){button[i].setPreferredSize(new Dimension(200,40));}
		for(int i=4;i<6;i++){button[i].setPreferredSize(new Dimension(220,40));}
		
		for(int i=0;i<7;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(245, 190, 165));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[3].setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		row[5].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER,60,0));
		row[2].setLayout(new FlowLayout(FlowLayout.CENTER,60,0));
		row[4].setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		row[6].setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		
		row[1].add(button[0]);
		row[1].add(button[1]);
		row[2].add(button[2]);
		row[2].add(button[3]);
		row[3].add(new JLabel("Offres spéciales :"));
		row[4].add(button[4]);
		row[4].add(button[5]);
		row[5].add(new JLabel("--------------------------------------------"));
		row[6].add(button[6]);
		row[6].add(button[7]);
		row[6].add(button[8]);
		for(int i=0;i<7;i++){add(row[i]);}
		
		setStartingPanel();
	}
	
	public void setStartingPanel(){
		row[0].removeAll();
		row[0].add(new JLabel("Outils de création des plats :"));
		gui.setVisible(true);
	}
	
	public void setMealToolsPanel(){
		row[0].removeAll();
		row[0].add(new JLabel("Création du plat " + 
				manager.getMealUnderConstruction().getName() + " :"));
		gui.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]){
			if(manager.getMealUnderConstruction()!=null){
				JOptionPane.showMessageDialog(gui,
						"Vous ne pouvez pas créer de nouveau plat pour le moment\n"
						+ "car un autre plat est déjà en cours de construction\n"
						+ "Sauvegardez ou annulez d'abord la création du plat précédent", 
						"Création indisponible",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			JTextField mealName = new JTextField(20);
			JTextField price = new JTextField(10);
			Object[] message = {"Nom de votre plat :\n",mealName,"Prix en € :",price};
			int option = JOptionPane.showOptionDialog(null, message, "Création d'un nouveau plat", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Créer", "Annuler"},"Créer");
			if(option==JOptionPane.OK_OPTION){
				if(!mealName.getText().matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
					System.out.println("le nom du plat contient des caratères non autorisés");
				}
				else if(!price.getText().matches("\\d+(\\.\\d+)?")){
					System.out.println("le prix indiqué n'est pas un nombre décimal");
				}
				else{
					manager.createMeal( mealName.getText(), Double.parseDouble(price.getText()));
					setMealToolsPanel();
				}
			}
		}
		if(e.getSource()==button[1]){
			HashSet<Meal> mealsInMenu = manager.getMenu().getMeals();
			String[] meals = new String[mealsInMenu.size()];
			int i=0;
			for(Meal meal:mealsInMenu){
				meals[i] = meal.getName();
				i++;}
			String selectedMeal = (String) JOptionPane.showInputDialog(gui,
					"Choisissez le plat dont vous \nvoulez voir les ingrédients :",
					"Liste de ingrédients", JOptionPane.PLAIN_MESSAGE, null ,meals,null);
			if(selectedMeal!=null){manager.listIngredients(selectedMeal);}
		}
		if(e.getSource()==button[2]){
			if(manager.getMealUnderConstruction()==null){
				JOptionPane.showMessageDialog(gui,
						"Vous ne pouvez pas ajouter d'ingrédients pour le moment\n"
						+ "car aucun plat n'est en cours de construction\n",
						"Ajout indisponible",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			JTextField ingredient = new JTextField();
			JComboBox<Integer> quantity = new JComboBox<Integer>(
					new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20});
			Object[] message = {"Nom de l'ingrédidient à ajouter :",ingredient,
					"Quantité :",quantity};
			int option = JOptionPane.showOptionDialog(null, message, "Ajouter un ingrédient", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if(option==JOptionPane.OK_OPTION){
				if(!ingredient.getText().matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$"))
					System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				else
					manager.addIngredient(ingredient.getText(), (Integer) quantity.getSelectedItem()); 
			}
		}
		if(e.getSource()==button[3]){
			if(manager.getMealUnderConstruction()==null){
				JOptionPane.showMessageDialog(gui,
						"Vous ne pouvez pas enlever d'ingrédients pour le moment\n"
						+ "car aucun plat n'est en cours de construction\n",
						"Retrait indisponible",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			HashSet<Ingredient> ingredientsInMeal = manager.getMealUnderConstruction().getIngredients();
			String[] ingredients = new String[ingredientsInMeal.size()];
			int i=0;
			for(Ingredient ingredient:ingredientsInMeal){
				ingredients[i] = ingredient.getName();
				i++;}
			String input = (String) JOptionPane.showInputDialog(gui,
					"Nom de l'ingrédient à retirer :","Enlever d'un ingrédient", 
					JOptionPane.PLAIN_MESSAGE, null ,ingredients,null);
			if(input!=null){
				manager.removeIngredient(input); 
			}
		}
		if(e.getSource()==button[4]){
			HashSet<Meal> mealsInMenu = manager.getMenu().getMeals();
			String[] meals = new String[mealsInMenu.size()];
			int i=0;
			for(Meal meal:mealsInMenu){
				meals[i] = meal.getName();
				i++;}
			JComboBox<String> selectedMeal = new JComboBox<String>(meals);
			JTextField price = new JTextField(10);		
			Object[] message = {"Nom du plat à mettre en promotion :",selectedMeal,
					"Prix de l'offre spéciale en € :",price};
			int option = JOptionPane.showOptionDialog(null, message, "Ajouter une offre spéciale", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if(option==JOptionPane.OK_OPTION){
				if(!price.getText().matches("\\d+(\\.\\d+)?")){
					System.out.println("le prix indiqué n'est pas un nombre décimal");}
				else
					manager.putInSpecialOffer((String) selectedMeal.getSelectedItem(), Double.parseDouble(price.getText()));
			}
		}
		if(e.getSource()==button[5]){
			HashSet<Meal> mealsInMenu = manager.getMenu().getMeals();
			String[] meals = new String[mealsInMenu.size()];
			int i=0;
			for(Meal meal:mealsInMenu){
				if(meal.getSpecialOffer()!=-1)
					meals[i] = meal.getName();
				i++;}
			String selectedMeal = (String) JOptionPane.showInputDialog(gui,
					"Choisissez le plat concerné :",
					"Retirer une offre spéciale", JOptionPane.PLAIN_MESSAGE, null ,meals,null);
			if(selectedMeal!=null){manager.removeFromSpecialOffer(selectedMeal);}
		}
		if(e.getSource()==button[6]){
			manager.saveModifications();
			setStartingPanel();
		}
		if(e.getSource()==button[7]){
			manager.showModifications();
		}
		if(e.getSource()==button[8]){
			manager.cancelModifications();
			setStartingPanel();
		}

	}

}
