package Group16_Project_IS1220_part1_Hammond_Bismut.EYMSCore;

import java.util.HashSet;

import Group16_Project_IS1220_part1_Hammond_Bismut.menu.Menu;
import Group16_Project_IS1220_part1_Hammond_Bismut.menu.MenuManager;
import Group16_Project_IS1220_part1_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part1_Hammond_Bismut.users.*;

public class Restaurant {
	private Menu menu;
	private HashSet<Client> clients;
	private HashSet<Chef> chefs;
	private User currentUser;
	private Activity currentActivity;
	private Client clientUnderRegistration;
	
	public Restaurant() {
		super();
		menu = new Menu();
		clients = new HashSet<Client>();
		chefs = new HashSet<Chef>();
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public HashSet<Client> getClients() {
		return clients;
	}
	
	public void setClients(HashSet<Client> clients) {
		this.clients = clients;
	}

	public HashSet<Chef> getChefs() {
		return chefs;
	}

	public void setChefs(HashSet<Chef> chefs) {
		this.chefs = chefs;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public Client getClientUnderRegistration() {
		return clientUnderRegistration;
	}
	
	public void setClientUnderRegistration(Client clientUnderRegistration) {
		this.clientUnderRegistration = clientUnderRegistration;
	}

	public void insertChef(String firstname, String lastname, String username, String password){
		Chef chef = new Chef(firstname, lastname, username, password);
		if(chefs.add(chef))
			System.out.println("Le chef " + firstname + " " + lastname + " a bien été ajouté");
		else
			System.out.println("Ce chef est déjà enregistré dans le système");
		currentUser = chef;
		currentActivity = new MenuManager(this.menu);
	}
	
	public void registerClient(String firstname, String lastname, String username, String password){
		Client client = new Client(firstname, lastname, username, password);
		clientUnderRegistration = client;
		currentActivity = new Registration(client);
	}
	
	public void saveAccount(){
		if(clientUnderRegistration ==null)
			System.out.println("Pas de compte à sauvegarder");
		else{
			if(clients.add(clientUnderRegistration)){
				System.out.println("Le client " + clientUnderRegistration.getFirstname() + " " 
					+ clientUnderRegistration.getLastname() + " a bien été ajouté");
			}		
			else
				System.out.println("Ce client est déjà enregistré dans le système");
			currentUser = clientUnderRegistration;
			currentActivity = new OrderManager(clientUnderRegistration, this);
			clientUnderRegistration = null;
		}
	}
	
	public void cancelRegistration(){
		clientUnderRegistration = null;
	}
	
	public boolean login(String username, String password){
		for(Client client : clients){
			if(client.getUsername().equals(username) && client.getPassword().equals(password)){
				System.out.println("Bonjour " + client.getFirstname() + " " + client.getLastname() + 
						"!\nBienvenue sur l'interface client de notre système ");
				currentUser = client;
				currentActivity = new OrderManager(client, this);
				return true;
			}
		}
		for(Chef chef : chefs){
			if(chef.getUsername().equals(username) && chef.getPassword().equals(password)){
				System.out.println("Bonjour chef " + chef.getFirstname() + " " + chef.getLastname() + 
						".\nBienvenue sur l'interface administrateur de notre système ");
				currentUser = chef;
				currentActivity = new MenuManager(this.menu);
				return true;
			}
		}
		System.out.println("Erreur d'authentification : vérifiez que vos identifiants sont corrects");
		return false;
	}
	
	public void logout(){
		currentUser = null;
		currentActivity = null;
	}
}
