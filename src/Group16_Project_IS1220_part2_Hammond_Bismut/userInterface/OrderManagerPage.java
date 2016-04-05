package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.Meal;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Registration;

public class OrderManagerPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	private OrderManager manager;
	private JButton[] button = new JButton[11];
	private JPanel[] row = new JPanel[7];
	
	public OrderManagerPage(OrderManager manager) {
		super(new GridLayout(0,1));
		this.manager = manager;
		
		button[0] = new JButton("Sélectionner un plat");
		button[1] = new JButton("Voir les ingrédients");
		button[2] = new JButton("Personnaliser un plat");
		button[3] = new JButton("Enlever une personnalisation");
		button[4] = new JButton("Ajouter à mes plats préférés");
		button[5] = new JButton("Enlever de mes plats préférés");
		button[6] = new JButton("Sauvegarder la commande");
		button[7] = new JButton("Voir la commande");
		button[8] = new JButton("Annuler la commande");
		button[9] = new JButton("Passer une nouvelle commande");
		button[10] = new JButton("Modifier mon profil");
		for(int i=0;i<11;i++){button[i].addActionListener(this);}
		for(int i=0;i<2;i++){button[i].setPreferredSize(new Dimension(170,40));}
		button[2].setPreferredSize(new Dimension(220,40));
		button[3].setPreferredSize(new Dimension(220,40));
		button[4].setPreferredSize(new Dimension(220,40));
		button[5].setPreferredSize(new Dimension(220,40));
		button[9].setPreferredSize(new Dimension(225,50));
		button[10].setPreferredSize(new Dimension(175,50));
		setStartingPanel();
		
	}
	
	public void setNewOrderPanel(){
		this.removeAll();
		for(int i=0;i<7;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(225, 175, 245));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[3].setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		row[5].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
		row[2].setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
		row[4].setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
		row[6].setLayout(new FlowLayout(FlowLayout.CENTER,15,0));
		
		row[0].add(new JLabel("Gestion de votre commande :"));
		row[1].add(button[0]);
		row[1].add(button[1]);
		row[2].add(button[2]);
		row[2].add(button[3]);
		row[3].add(new JLabel("Indiquez quels sont vos plats préférés :"));
		row[4].add(button[4]);
		row[4].add(button[5]);
		row[5].add(new JLabel("--------------------------------------------"));
		row[6].add(button[6]);
		row[6].add(button[7]);
		row[6].add(button[8]);
		
		for(int i=0;i<7;i++){add(row[i]);}
	}
	
	public void setStartingPanel(){
		this.removeAll();
		for(int i=0;i<2;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(225, 175, 245));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,70));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER,60,0));
		
		row[0].add(new JLabel("Bienvenue sur l'interface client du système !"));
		row[1].add(button[9]);
		row[1].add(button[10]);
		
		for(int i=0;i<2;i++){add(row[i]);}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Set<Meal> mealsInMenu;
		if(e.getSource()==button[2] || e.getSource()==button[3]){
			mealsInMenu = manager.getCurrentOrder().getMealsToBuy().keySet();
		}else{mealsInMenu = manager.getRestaurant().getMenu().getMeals();}
		String[] meals = new String[mealsInMenu.size()];
		int i=0;
		for(Meal meal:mealsInMenu){
			meals[i] = meal.getName();
			i++;}
		if(e.getSource()==button[0]){
			JComboBox<String> selectedMeal = new JComboBox<String>(meals);
			JComboBox<Integer> quantity = new JComboBox<Integer>(
					new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20});
			Object[] message = {"Sélectionnez un plat :\n",selectedMeal,"Quantité :",quantity};
			int option = JOptionPane.showOptionDialog(null, message, "Ajouter un plat à la commande", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if(option==JOptionPane.OK_OPTION){
				manager.selectMeal((String) selectedMeal.getSelectedItem(), 
						(Integer)quantity.getSelectedItem());
			}
		}
		if(e.getSource()==button[1]){
			String selectedMeal = (String) JOptionPane.showInputDialog(gui,
					"Choisissez le plat dont vous \nvoulez voir les ingrédients :",
					"Liste de ingrédients", JOptionPane.PLAIN_MESSAGE, null ,meals,null);
			if(selectedMeal!=null){manager.listIngredients(selectedMeal);}
		}
		if(e.getSource()==button[2]){
			JComboBox<String> selectedMeal = new JComboBox<String>(meals);
			JTextField ingredient = new JTextField();
			JComboBox<Integer> quantity = new JComboBox<Integer>(
					new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20});
			Object[] message = {"Sélectionnez un plat :",selectedMeal,
					"Nom de l'ingrédidient à ajouter :",ingredient,"Quantité :",quantity};
			int option = JOptionPane.showOptionDialog(null, message, "Personnaliser un plat", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Personnaliser", "Annuler"},"Personnaliser");
			if(option==JOptionPane.OK_OPTION){
				if(!ingredient.getText().matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$"))
					System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				else
					manager.personalizeMeal((String) selectedMeal.getSelectedItem(), 
							ingredient.getText(), (Integer) quantity.getSelectedItem()); 
			}
		}
		if(e.getSource()==button[3]){
			JComboBox<String> selectedMeal = new JComboBox<String>(meals);
			JTextField ingredient = new JTextField();
			Object[] message = {"Sélectionnez un plat :",selectedMeal,
					"Nom de l'ingrédidient à enlever :",ingredient};
			int option = JOptionPane.showOptionDialog(null, message, "Enlever une personnalisation", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Retirer", "Annuler"},"Retirer");
			if(option==JOptionPane.OK_OPTION){
				if(!ingredient.getText().matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$"))
					System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				else
					manager.removePersonalization((String) selectedMeal.getSelectedItem(), 
							ingredient.getText()); 
			}
		}
		if(e.getSource()==button[4]){
			String selectedMeal = (String) JOptionPane.showInputDialog(gui,
					"Choisissez le plat que vous voulez \najouter à vos favoris :",
					"Ajouter aux plats préférés", JOptionPane.PLAIN_MESSAGE, null ,meals,null);
			if(selectedMeal!=null){manager.addToFavouriteMeals(selectedMeal);}
		}
		if(e.getSource()==button[5]){
			String selectedMeal = (String) JOptionPane.showInputDialog(gui,
					"Choisissez le plat que vous voulez \nenlever de vos favoris :",
					"Retrait d'un plat préféré", JOptionPane.PLAIN_MESSAGE, null ,meals,null);
			if(selectedMeal!=null){manager.removeFromFavouriteMeals(selectedMeal);}
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
		if(e.getSource()==button[9]){
			manager.beginNewOrder();
			setNewOrderPanel();
		}
		if(e.getSource()==button[10]){
			manager.getRestaurant().modifyClientInfo();
			Registration manager = (Registration) CoreSystem.getSharedSystem().getActualRestaurant().getCurrentActivity();
			JTabbedPane tabbedPane = (JTabbedPane) gui.getContentPane();
			tabbedPane.removeTabAt(0);
			tabbedPane.insertTab("Gestion du profil", null, new RegistrationPage(manager), null, 0);
			tabbedPane.setSelectedIndex(0);
			gui.setContentPane(tabbedPane);
			gui.setVisible(true);	
		}

	}

}
