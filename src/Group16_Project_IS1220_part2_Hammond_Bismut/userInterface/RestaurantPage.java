package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.Restaurant;
import Group16_Project_IS1220_part2_Hammond_Bismut.storing.OrderingStrategy.Criteria;

public class RestaurantPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	private Restaurant restaurant;
	private JButton[] button = new JButton[6];
	private JPanel[] row = new JPanel[4];
	private JTextField[] field = new JTextField[4];
	private int fonction;

	public RestaurantPage(Restaurant restaurant){
		super(new GridLayout(0,1));
		this.restaurant = restaurant;
		
		// On met en place ces composants uniques et constants
		button[0] = new JButton("Inscription Client");
		button[1] = new JButton("Inscription Chef");
		button[2] = new JButton("Me connecter à mon compte");
		button[3] = new JButton("Voir les commandes passées");
		button[4] = new JButton("Connexion");
		button[5] = new JButton("Annuler");
		for(int i=0;i<6;i++){button[i].addActionListener(this);}
		for(int i=0;i<2;i++){button[i].setPreferredSize(new Dimension(200,50));}
		for(int i=2;i<4;i++){button[i].setPreferredSize(new Dimension(275,50));}
		for(int i=0;i<4;i++){field[i] = new JTextField(20);}
		
		this.setDefaultPanel();
	}
	
	public void setDefaultPanel(){
		this.removeAll();
		for(int i=0;i<4;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(145, 215, 245));
			row[i].setLayout(new FlowLayout(FlowLayout.CENTER,80,10));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,40));
		
		row[0].add(new JLabel("Vous vous trouvez sur la page d'accueil du restaurant " + restaurant.getName()));
		row[1].add(button[0]);
		row[1].add(button[1]);
		row[2].add(button[2]);
		row[3].add(button[3]);
		for(int i=0;i<4;i++){this.add(row[i]);}	
	}
	
	public void setLoginPanel(){
		fonction=3;
		this.removeAll();
		for(int i=0;i<4;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(145, 215, 245));
			row[i].setLayout(new FlowLayout(FlowLayout.CENTER,40,30));
		}
		row[0].add(new JLabel("Connexion d'un utilisateur"));
		
		field[2].setText("");
		field[3].setText("");
		row[1].add(new JLabel("Nom d'utilisateur :"));
		row[1].add(field[2]);
		row[2].add(new JLabel("Mot de passe :"));
		row[2].add(field[3]);
		
		row[3].add(button[4]);
		row[3].add(button[5]);
		for(int i=0;i<4;i++){
			this.add(row[i]);
		}
	}
	
	public void setRegistrationPane(boolean isChef){
		fonction = isChef?2:1;
		this.removeAll();
		for(int i=0;i<6;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(145, 215, 245));
			row[i].setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
		}
		if(isChef){row[0].add(new JLabel("Inscription d'un nouveau chef"));}
		else{row[0].add(new JLabel("Inscription d'un nouveau client"));}
		
		for(int i=0;i<4;i++){field[i].setText("");}
		
		row[1].add(new JLabel("Prénom :"));
		row[1].add(field[0]);
		row[2].add(new JLabel("Nom de famille :"));
		row[2].add(field[1]);
		row[3].add(new JLabel("Nom d'utilisateur :"));
		row[3].add(field[2]);
		row[4].add(new JLabel("Mot de passe :"));
		row[4].add(field[3]);
		
		row[5].add(button[4]);
		row[5].add(button[5]);
		for(int i=0;i<6;i++){
			this.add(row[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]){
			setRegistrationPane(false);
		}
		if(e.getSource()==button[1]){
			setRegistrationPane(true);
		}
		if(e.getSource()==button[2]){
			setLoginPanel();
		}
		if(e.getSource()==button[3]){
			String input = (String) JOptionPane.showInputDialog(gui,
					"Choisissez un critère de tri pour les plats :",
					"Historique des commandes", JOptionPane.PLAIN_MESSAGE, null ,
					new String[]{"commandés sans changement","le plus souvent modifiés",
							"commandés en promotion"}, null);
			if(input.equals("commandés sans changement")){restaurant.showMeals(Criteria.unchanged);}
			if(input.equals("le plus souvent modifiés")){restaurant.showMeals(Criteria.mostlyModified);}
			if(input.equals("commandés en promotion")){restaurant.showMeals(Criteria.justOnSale);}
		}
		if(e.getSource()==button[4]){
			if(fonction == 3){
				String username = field[2].getText();
				String password = field[3].getText();
				if(!username.matches("^[a-zA-Z0-9&#_.-]+$")){
					System.out.println("le nom d'utilisateur contient des caractères non autorisés");}
				else{
					restaurant.login(username, password);
				}
			}else{
				String firstname = field[0].getText();
				String lastname = field[1].getText();
				String username = field[2].getText();
				String password = field[3].getText();
				if(!firstname.matches("^[a-zA-Z-]+$")){
					System.out.println("le prénom indiqué contient des caractères non autorisés");
				}
				else if(!lastname.matches("^[a-zA-Z- ]+$")){
					System.out.println("le nom de famille indiqué contient des caractères non autorisés");
				}
				else if(!username.matches("^[a-zA-Z0-9&#_.-]+$")){
					System.out.println("le nom d'utilisateur choisi contient des caractères non autorisés");
				}
				else{
					if(fonction==1){restaurant.registerClient(firstname, lastname, username, password);}
					if(fonction==2){restaurant.insertChef(firstname, lastname, username, password);}
				}
			}
		}
		if(e.getSource()==button[5]){
			this.setDefaultPanel();
		}
	}

}
