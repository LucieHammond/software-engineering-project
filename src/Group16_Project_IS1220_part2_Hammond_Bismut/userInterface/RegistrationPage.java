package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;

import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.Contact;
import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Registration;

public class RegistrationPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	private Registration manager;
	private JButton[] button = new JButton[10];
	private JPanel[] row = new JPanel[7];
	
	public RegistrationPage(Registration registration){
		super(new GridLayout(0,1));
		this.manager = registration;
		
		button[0] = new JButton("Ajouter un contact");
		button[1] = new JButton("Retirer un contact");
		button[2] = new JButton("Choisir le contact à utiliser");
		button[3] = new JButton("Changer mon accord pour recevoir des notifications");
		button[4] = new JButton("Changer de carte de fidélité");
		button[5] = new JButton("Ajouter une adresse");
		button[6] = new JButton("Indiquer mon anniversaire");
		button[7] = new JButton("Sauvegarder le profil");
		button[8] = new JButton("Voir l'état du profil");
		button[9] = new JButton("Annuler les modifications");
		for(int i=0;i<10;i++){button[i].addActionListener(this);}
		for(int i=0;i<2;i++){button[i].setPreferredSize(new Dimension(165,40));}
		button[2].setPreferredSize(new Dimension(220,40));
		button[3].setPreferredSize(new Dimension(400,40));
		button[4].setPreferredSize(new Dimension(205,40));
		button[5].setPreferredSize(new Dimension(165,40));
		button[6].setPreferredSize(new Dimension(195,40));
		
		for(int i=0;i<7;i++){
			row[i] = new JPanel();
			row[i].setBackground(new Color(160, 235, 170));
		}
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[3].setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		row[5].setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		row[1].setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		row[2].setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		row[4].setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		row[6].setLayout(new FlowLayout(FlowLayout.CENTER,15,0));
		
		row[0].add(new JLabel("Informations de contact"));
		row[1].add(button[0]);
		row[1].add(button[1]);
		row[1].add(button[2]);
		row[2].add(button[3]);
		row[3].add(new JLabel("Autres informations personnelles"));
		row[4].add(button[4]);
		row[4].add(button[5]);
		if(manager.getClientUnderRegistration().getBirthday()==null){row[4].add(button[6]);}
		row[5].add(new JLabel("--------------------------------------------"));
		row[6].add(button[7]);
		row[6].add(button[8]);
		row[6].add(button[9]);
		
		for(int i=0;i<7;i++){add(row[i]);}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]){
			JComboBox<Contact> type = new JComboBox<Contact>(new Contact[]{Contact.email, Contact.phone});
			JTextField contact = new JTextField();
			Object[] message = {"Indiquez le contact ici :\n",type,contact};
			int option = JOptionPane.showOptionDialog(null, message, "Ajouter un contact", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if (option == JOptionPane.OK_OPTION) {
			    if (type.getSelectedItem()==Contact.phone) {
			    	String tel = contact.getText();
			    	if(!(tel.matches("^[0-9 ]+") && tel.replace(" ", "").length()==10)
							&& !(tel.matches("[+][0-9 ]+") && tel.replace(" ", "").length()==12)){
							System.out.println("le numéro de téléphone indiqué est invalide");}
			    	else{manager.addContactInfos(Contact.phone, tel);}
			    }
			    else if(type.getSelectedItem()==Contact.email) {
			    	String email = contact.getText();
					if(!email.matches("^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
							System.out.println("l'adresse mail indiquée est invalide");}
			    	else{manager.addContactInfos(Contact.email, email);}
			    }
			}
		}
		if(e.getSource()==button[1]){
			Set<Contact> contactsAdded = manager.getClientUnderRegistration().getContactInfos().keySet();
			Contact[] contacts = new Contact[contactsAdded.size()];
			int i=0;
			for (Contact type : contactsAdded){
				contacts[i] =type;
				i++;
			}
			Contact type = (Contact) JOptionPane.showInputDialog(gui,"Indiquez le contact à supprimer :",
					"Supprimer un contact", JOptionPane.PLAIN_MESSAGE, null ,contacts,null);
			if(type!=null)
				manager.removeContactInfo(type);
		}
		if(e.getSource()==button[2]){
			Set<Contact> contactsAdded = manager.getClientUnderRegistration().getContactInfos().keySet();
			Contact[] contacts = new Contact[contactsAdded.size()];
			int i=0;
			for (Contact type : contactsAdded){
				contacts[i] =type;
				i++;
			}
			Contact type = (Contact) JOptionPane.showInputDialog(gui,"Choisissez le contact à utiliser :",
					"Contact préféré", JOptionPane.PLAIN_MESSAGE, null ,contacts,
					manager.getClientUnderRegistration().getContactToUse());
			if(type!=null)
				manager.chooseContactToUse(type);
		}
		if(e.getSource()==button[3]){
			int choice = JOptionPane.showOptionDialog(gui, "Acceptez-vous de recevoir des notifications ?",
					"Accord notifications", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null , null , null );
			if(choice==JOptionPane.YES_OPTION){manager.associateAgreement(true);}
			else if(choice==JOptionPane.NO_OPTION){manager.associateAgreement(false);}
		}
		if(e.getSource()==button[4]){
			CardType type = (CardType) JOptionPane.showInputDialog(gui,"Choisissez une carte de fidélité :",
					"Carte de fidélité", JOptionPane.PLAIN_MESSAGE, null ,
					new CardType[]{CardType.basic,CardType.point,CardType.lottery},CardType.basic);
			if(type!=null){manager.associateCard(type);}
		}
		if(e.getSource()==button[5]){
			JTextArea address = new JTextArea(1, 50);
			Object[] message = {"Indiquez votre adresse ci-dessous :\n",address};
			int option = JOptionPane.showOptionDialog(null, message, "Adresse postale", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if(option==JOptionPane.OK_OPTION){
				String input = address.getText();
				if(input!=null && !input.equals("")){manager.associateAddress(input);}
			}
		}
		if(e.getSource()==button[6]){
			JComboBox<Integer> day = new JComboBox<Integer>(
					new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
			JComboBox<Integer> month = new JComboBox<Integer>(
					new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
			Object[] message = {"Indiquez votre date d'anniversaire :\n","                       jour :",
					day, "                       mois :",month,
					"\nVérifiez bien cette information, \nvous ne pourrez plus la modifier"};
			int option = JOptionPane.showOptionDialog(null, message, "Date d'anniversaire", 
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					new String[]{"Ajouter", "Annuler"},"Ajouter");
			if(option==JOptionPane.OK_OPTION){
				manager.associateBirthday((Integer) day.getSelectedItem(), (Integer) month.getSelectedItem());
			}
		}
		if(e.getSource()==button[7]){
			manager.saveModifications();
			OrderManager manager = (OrderManager) CoreSystem.getSharedSystem().getActualRestaurant().getCurrentActivity();
			JTabbedPane tabbedPane = (JTabbedPane) gui.getContentPane();
			tabbedPane.removeTabAt(0);
			tabbedPane.insertTab("Gestion des commandes", null, new OrderManagerPage(manager), null, 0);
			tabbedPane.setSelectedIndex(0);
			RestaurantPage page2 = (RestaurantPage) tabbedPane.getComponentAt(1);
			page2.setAfterLoginPanel();
			gui.setContentPane(tabbedPane);
			gui.setVisible(true);	
		}
		if(e.getSource()==button[8]){
			manager.showModifications();
		}
		if(e.getSource()==button[9]){
			manager.cancelModifications();
			if(manager.isAccountAlreadyExists()){
				OrderManager manager = (OrderManager) CoreSystem.getSharedSystem().getActualRestaurant().getCurrentActivity();
				JTabbedPane tabbedPane = (JTabbedPane) gui.getContentPane();
				tabbedPane.removeTabAt(0);
				tabbedPane.insertTab("Gestion des commandes", null, new OrderManagerPage(manager), null, 0);
				tabbedPane.setSelectedIndex(0);
				RestaurantPage page2 = (RestaurantPage) tabbedPane.getComponentAt(1);
				page2.setAfterLoginPanel();
				gui.setContentPane(tabbedPane);
				gui.setVisible(true);
			}else{
				JTabbedPane tabbedPane = (JTabbedPane) gui.getContentPane();
				tabbedPane.removeTabAt(0);
				gui.setContentPane(tabbedPane);
				gui.setVisible(true);
			}
		}
	}

}
