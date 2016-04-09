package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;
import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;

public class StartingPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	/**
	 * L'instance unique et partagée de la fenêtre graphique
	 */
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	
	/**
	 * La liste des boutons affichés sur la page
	 */
	private JPanel[] row = new JPanel[3];
	
	/**
	 * La liste des conteneurs horizontaux qui structurent la page
	 */
	private JButton[] button = new JButton[3];

	/**
	 * Constructeur de la page de démarrage du système qui met en place tous les éléments
	 * graphiques
	 */
	public StartingPage(){
		super(new GridLayout(3,1));
		
		for(int i=0;i<3;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(245, 235, 170));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER,80,10));
		row[2].setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		
		button[0] = new JButton("Sélectionner un restaurant");
		button[1] = new JButton("Ajouter un nouveau restaurant");
		button[2] = new JButton("Eteindre le système");
		Dimension dimension = new Dimension(225,50);
		for(int i=0;i<3;i++){button[i].setPreferredSize(dimension);}
		
		row[0].add(new JLabel("Bienvenue sur le Enjoy Your Meal System !"));
		row[1].add(button[0]);
		row[1].add(button[1]);
		row[2].add(button[2]);
		for(int i=0;i<3;i++){
			button[i].addActionListener(this);
			this.add(row[i]);
		}
	}

	/**
	 * Méthode qui est appelée lorque l'utilisateur clique sur un bouton. Une disjonction 
	 * des cas permet de traiter séparément l'action à réaliser pour chaque bouton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]){
			HashSet<Restaurant> restaurants = CoreSystem.getSharedSystem().getRestaurants();
			String[] choices = new String[restaurants.size()];
			int i=0;
			for(Restaurant restaurant : restaurants){
				choices[i] = restaurant.getName();
				i++;}
			String input = (String) JOptionPane.showInputDialog(gui,"Indiquez le restaurant où vous vous trouvez :",
					"Choix du restaurant", JOptionPane.PLAIN_MESSAGE, null ,
					choices,null);
			if(input!=null){
				CoreSystem.getSharedSystem().openInRestaurant(input);
				Restaurant restaurant = CoreSystem.getSharedSystem().getActualRestaurant();
				JTabbedPane tabbedPane = new JTabbedPane ();
				tabbedPane.addTab("Restaurant " + restaurant.getName(), 
						new RestaurantPage(restaurant));
				tabbedPane.addTab("Accueil système", this);
				gui.setContentPane(tabbedPane);
				gui.setVisible(true);
			}
		}
		else if(e.getSource()==button[1]){
			String input = JOptionPane.showInputDialog(gui,
					"Entrez le nom du restaurant à ajouter :","Ajout d'un restaurant", 
					JOptionPane.PLAIN_MESSAGE);
			if(input !=null){
				if(!input.matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
					System.out.println("le nom du restaurant contient des caractères non autorisés");}
				else{CoreSystem.getSharedSystem().addRestaurant(input);}
			}		
		}
		else if(e.getSource()==button[2]){
			int choice = JOptionPane.showOptionDialog(gui, 
					"Voulez vous vraiment éteindre le système ?", "Extinction du système",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null , null , null );
			if(choice==JOptionPane.YES_OPTION){CoreSystem.systemTurnOff();
			gui.setInitialPanel();}
		}
		
	}
}
