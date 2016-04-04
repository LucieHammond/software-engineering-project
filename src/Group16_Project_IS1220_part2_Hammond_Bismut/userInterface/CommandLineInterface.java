package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;
import Group16_Project_IS1220_part2_Hammond_Bismut.menu.MenuManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.BirthdayNotification;
import Group16_Project_IS1220_part2_Hammond_Bismut.notifications.NewOfferNotification;
import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;
import Group16_Project_IS1220_part2_Hammond_Bismut.storing.OrderingStrategy.Criteria;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Registration;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.CardType;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client.Contact;

public class CommandLineInterface {
	
	private static Scanner scanner;
	private CoreSystem system;
	private ArrayList<String> commands;
	private ArrayList<Integer> parametersCount;
	
	public CommandLineInterface() {
		super();
		commands = new ArrayList<String>(Arrays.asList("login","createMeal","addIngredient",
				"currentMeal","saveMeal","selectMeal","personalizeMeal","putInSpecialOffer",
				"removeFromSpecialOffer","listIngredients","saveOrder","registerClient",
				"addContactInfo","associateCard","associateAgreement","insertChef","showMeals", 
				"notifyAd", "notifyBirthday", "turnOnSystem", "turnOffSystem", "addRestaurant",
				"openInRestaurant","logout","modifyClientInfos","saveAccount","showAccount",
				"cancelAccountModifications","addAddress","addBirthday","chooseContactToUse",
				"removeContactInfo","beginNewOrder","showOrder","cancelOrder",
				"removePersonalization","addToFavouriteMeals","removeFromFavouriteMeals",
				"cancelMeal","removeIngredient"));
		parametersCount = new ArrayList<Integer>(Arrays.asList(2,2,2,0,0,2,3,2,1,1,0,4,2,1,1,
				4,1,3,0,0,0,1,1,0,0,0,0,0,1,2,1,1,0,0,0,2,1,1,0,1));
		
		// On met en place une situation initiale
		CoreSystem.initializeSituation();
		System.out.println("Le système est éteint. Alllumez-le pour commencer");
	}

	/**
	 * Analyse et demande à faire exécuter la ligne de commande tapée par l'utilisateur.
	 * Après avoir fait vérifier la syntaxe générale de la commande en appelant 
	 * checkInputCommand, cette méthode partitionne la ligne pour en dégager les 
	 * différents paramêtres qui seront ensuite stockés dans un tableau. 
	 * Enfin, elle appelle checkParameters pour continuer le travail
	 * @param line
	 */
	public void performCommandLine(String line){
		int number = checkInputCommand(line);
		if(number!=-1){
			// On isole les paramêtres	
			String parameters = line.split("<")[1];
			parameters = parameters.substring(0, parameters.length()-1);
			
			// Traitement particulier dans le cas où un paramètre risque de contenir des virgules
			if(number==28 && parameters.contains("\"")){
				parameters = parameters.replaceFirst("\"", "");
				/* On remplace toutes les virgules du texte pour faire le partage. 
				 * Elles seront rétablies plus tard
				 */
				parameters = parameters.replace(parameters.substring(0, parameters.indexOf("\"")), 
						parameters.substring(0, parameters.indexOf("\"")).replaceAll(",", "#"));
			}
			String[] args = parameters.split(",");
			// Cas où il n'y a pas de paramètres
			if(parameters.equals(""))
				args = new String[0];
			checkParameters(args, number);
		}
	}
	
	/**
	 * Vérifie le nombre et la validité des paramêtres passés dans la commande (entiers,
	 * nombres décimaux, adresse mail, numéro de téléphone). Dans le cas où les paramêtres
	 * sont corrects, la méthode appelle executeAction, sinon elle renvoie un message
	 * d'erreur
	 * @param args la tableau des arguments de la commande
	 * @param number le numéro de la commande dans la liste des commandes possibles
	 * @return
	 */
	public boolean checkParameters(String[] args, int number){
		// On vérifie qu'il y a le bon nombre de paramètres
		if(args.length!=parametersCount.get(number)){
			System.out.println("Nombre de paramêtres incorrect pour cette commande");
			return false;
		}
		// On ajuste éventuellement les espaces et les guillemets
		for(int i=0;i<args.length;i++){
			if(args[i].startsWith(" "))
				args[i] = args[i].substring(1);
			if(args[i].startsWith("\""))
				{args[i] = args[i].substring(1);}
			if(args[i].endsWith("\""))
				{args[i] = args[i].substring(0,args[i].length()-1);}
		}
		
		// On vérifie la syntaxe des paramêtres
		switch (number){
		case 0:
			if(!args[0].matches("^[a-zA-Z0-9&#_.-]+$")){
				System.out.println("le nom d'utilisateur contient des caractères non autorisés");
				return false;
			}
			break;
		case 1: case 7:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("\\d+(\\.\\d+)?")){
				System.out.println("le prix indiqué n'est pas un nombre décimal");
				return false;
			}
			break;
		case 2:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$")){
				System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("\\d+")){
				System.out.println("la quantité indiquée n'est pas un nombre entier");
				return false;
			}
			break;
		case 5:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("\\d+")){
				System.out.println("la quantité indiquée n'est pas un nombre entier");
				return false;
			}
			break;
		case 6:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$")){
				System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				return false;
			}
			if(!args[2].matches("\\d+")){
				System.out.println("la quantité indiquée n'est pas un nombre entier");
				return false;
			}
			break;
		case 8: case 9: case 36: case 37:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			break;
		case 11: case 15:
			if(!args[0].matches("^[a-zA-Z-]+$")){
				System.out.println("le prénom indiqué contient des caractères non autorisés");
				return false;
			}
			if(!args[1].matches("^[a-zA-Z- ]+$")){
				System.out.println("le nom de famille indiqué contient des caractères non autorisés");
				return false;
			}
			if(!args[2].matches("^[a-zA-Z0-9&#_.-]+$")){
				System.out.println("le nom d'utilisateur choisi contient des caractères non autorisés");
				return false;
			}
			break;
		case 12:
			if(args[0].equalsIgnoreCase("phone")){
				if(!(args[1].matches("^[0-9 ]+") && args[1].replace(" ", "").length()==10)
					&& !(args[1].matches("[+][0-9 ]+") && args[1].replace(" ", "").length()==12)){
					System.out.println("le numéro de téléphone indiqué est invalide");
					return false;
				}
			}
			else if(args[0].equalsIgnoreCase("email") || args[0].equalsIgnoreCase("e-mail")){
				if(!args[1].matches("^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
					System.out.println("l'adresse mail indiquée est invalide");
					return false;
				}
			}
			else{
				System.out.println("le type de contact ne peut être que \"phone\" ou \"email\"");
				return false;
			}
			break;
		case 13:
			if(!args[0].equalsIgnoreCase("basic") && !args[0].equalsIgnoreCase("point")
					&& !args[0].equalsIgnoreCase("lottery")){
				System.out.println("il n'existe que 3 types de cartes de fidelité : \"basic\", "
						+ "\"point\" et \"lottery\"");
				return false;
			}
			break;
		case 14:
			if(!args[0].equalsIgnoreCase("true") && !args[0].equalsIgnoreCase("false")
					&& !args[0].equalsIgnoreCase("yes") && !args[0].equalsIgnoreCase("no")){
				System.out.println("les deux seules valeurs possibles pour l'accord sont "
						+ "\"yes\" (true) ou \"no\" (false)");
				return false;
			}
			break;
		case 16 :
			if(!args[0].equalsIgnoreCase("unchanged") && !args[0].equalsIgnoreCase("mostlyModified")
					&& !args[0].equalsIgnoreCase("justOnSale")){
				System.out.println("il n'y a que 3 critères de tri possibles : \"unchanged\", "
						+ "\"mostlyModified\" et \"justOnSale\"");
				return false;
			}
			break;
		case 17:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("\\d+(\\.\\d+)?") || !args[2].matches("\\d+(\\.\\d+)?")){
				System.out.println("le prix indiqué n'est pas un nombre décimal");
				return false;
			}
			break;
		case 21: case 22:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du restaurant contient des caractères non autorisés");
				return false;
			}
			break;
		case 28:
			args[0] = args[0].replaceAll("#", ",");
			break;
		case 29:
			if(!args[0].matches("\\d+") || !args[1].matches("\\d+")){
				System.out.println("l'un des paramêtres indiqués n'est pas un entier");
				return false;
			}
			break;
		case 30: case 31:
			if(!args[0].equalsIgnoreCase("phone") && !args[0].equalsIgnoreCase("email")
					&& !args[0].equalsIgnoreCase("e-mail")){
				System.out.println("le type de contact ne peut être que \"phone\" ou \"email\"");
				return false;
			}
			break;
		case 35:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff0-9-/& ]+$")){
				System.out.println("le nom du plat contient des caratères non autorisés");
				return false;
			}
			if(!args[1].matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$")){
				System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				return false;
			}
			break;	
		case 39:
			if(!args[0].matches("^[a-zA-Z\u00C0-\u00ff-/& ]+$")){
				System.out.println("le nom de l'ingrédient contient des caratères non autorisés");
				return false;
			}
			break;
		}
		// On appelle la méthode adéquate
		executeAction(args, number);	
		return true;
	}
	
	/**
	 * Execute l'action correspondant à la commande
	 * @param args tableau des arguments de la commande qui sont ceux de la méthode appelée
	 * @param number numéro de la commande dans la liste des commandes possibles
	 */
	public void executeAction(String[] args, int number){
		// Si le système est éteint, on ne peut rien faire tant qu'on ne le démarre pas
		if(system==null && number!=19){
			System.out.println("Vous ne pouvez pas effectuer cette action pour le moment car "
					+ "le système est éteint. \nPour démarrer le EnjoyYourMealSystem, utilisez "
					+ "la commande turnOnSystem<>");
			return;
		}
		// Si aucun restaurant n'est sélectionné, le nombre d'actions est aussi limité
		else if((number<19 || number >22) && system.getActualRestaurant()==null){
			System.out.println("Impossible d'effectuer cette action pour le moment car vous "
					+ "n'avez pas sélectionné de restaurant. Précisez d'abord le nom du "
					+ "restaurant de la chaine depuis lequel vous voulez vous connecter !");
			return;
		}
		// On vérifie que l'utilisateur est sur la bonne interface et qu'il a le droit d'y être
			// 1. Interfae Registration (enregistrement ou modification du profil)
		if((number>=12 && number<=14) || (number>=25 && number<=31)){
			if(system.getActualRestaurant().getCurrentActivity()==null){
				System.out.println("Action refusée. \nConnectez vous d'abord au système avec "
						+ "vos identifiants pour pouvoir modifier votre profil");
				return;}
			else if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				System.out.println("Votre rôle de chef ne vous permet pas de réaliser cette action");
				return;}
			else if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				System.out.println("Vous ne pouvez pas changer vos données d'utilisateur depuis "
						+ "l'interface d'achat."
						+ "\nPour pouvoir modifier les informations de votre profil, utilisez la "
						+ "commmande modifyClientInfos<>");
				return;}
		}
			// 2. Interface OrderManager (commande, achat)
		else if(number==5 || number==6 || number==10 || (number>=32 && number<=37)){
			if(system.getActualRestaurant().getCurrentActivity()==null){
				System.out.println("Action refusée. \nConnectez vous d'abord au système avec "
						+ "vos identifiants pour pouvoir passer une commande");
				return;}
			else if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				System.out.println("Votre rôle de chef ne vous permet pas de réaliser cette action");
				return;}
			else if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				System.out.println("Vous ne pouvez pas gérer de commande pour le moment car "
						+ "\nvous vous trouvez actuellement sur l'interface d'inscription client."
						+ "\nSauvegardez ou annulez les modifications de votre profil avant de "
						+ "passer la commande");
				return;}
		}
			// 3. Interface MenuManager (gestion du menu)
		else if((number>=1 && number <=8 && number!=5 && number!=6) || number==38 || number == 39){
			if(system.getActualRestaurant().getCurrentActivity()==null){
				System.out.println("Action refusée. \nConnectez vous d'abord au système avec "
						+ "vos identifiants pour pouvoir modifier le menu");
				return;}
			else if(!(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager)){
				System.out.println("Votre rôle de client ne vous permet pas de réaliser cette action");
				return;}
		}
		switch(number){
		case 0:
			system.getActualRestaurant().login(args[0], args[1]);
			break;
		case 1:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.createMeal(args[0], Double.parseDouble(args[1]));
			}break;
		case 2:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.addIngredient(args[0], Integer.parseInt(args[1]));
			}break;
		case 3:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.showModifications();
			}break;
		case 4:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.saveModifications();
			}break;
		case 5:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.selectMeal(args[0], Integer.parseInt(args[1]));
			}break;
		case 6:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.personalizeMeal(args[0], args[1], Integer.parseInt(args[2]));
			}break;
		case 7:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.putInSpecialOffer(args[0], Double.parseDouble(args[1]));
			}break;
		case 8:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.removeFromSpecialOffer(args[0]);
			}break;
		case 9:
			if(system.getActualRestaurant().getCurrentActivity()==null){
				System.out.println("Action refusée. \nConnectez vous d'abord au système avec "
						+ "vos identifiants pour pouvoir modifier le menu");
			}else if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.listIngredients(args[0]);
			}
			else if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.listIngredients(args[0]);
			}
			else{
				System.out.println("Vous ne pouvez pas voir le menu pour le moment car "
						+ "\nvous vous trouvez actuellement sur l'interface d'inscription client."
						+ "\nSauvegardez ou annulez les modifications de votre profil pour pouvoir "
						+ "passer une nouvelle commmande");
			}break;
		case 10:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.saveModifications();
			}break;	
		case 11:
			system.getActualRestaurant().registerClient(args[0], args[1], args[2], args[3]);
			break;
		case 12:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				Contact contact;
				if(args[0].equalsIgnoreCase("phone")){contact = Contact.phone;}
				else {contact = Contact.email;}
				manager.addContactInfos(contact, args[1]);	
			}break;
		case 13:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				CardType type;
				if(args[0].equalsIgnoreCase("basic")){type = CardType.basic;}
				else if(args[0].equalsIgnoreCase("point")) {type = CardType.point;}
				else{type = CardType.lottery;};
				manager.associateCard(type);
			}break;
		case 14:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				boolean agreement;
				if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("yes")){
					agreement = true;}
				else{agreement = false;}
				manager.associateAgreement(agreement);
			}break;
		case 15:
			system.getActualRestaurant().insertChef(args[0], args[1], args[2], args[3]);
			break;
		case 16:
			Criteria criteria;
			if(args[0].equalsIgnoreCase("unchanged")){criteria = Criteria.unchanged;}
			else if(args[0].equalsIgnoreCase("mostlyModified")) {criteria = Criteria.mostlyModified;}
			else{criteria = Criteria.justOnSale;};
			system.getActualRestaurant().showMeals(criteria);
			break;
		case 17:
			new NewOfferNotification(args[0],Double.parseDouble(args[1]), Double.parseDouble(args[2])).notifyClients();
			break;
		case 18:
			new BirthdayNotification().notifyClients();
			break;
		case 19:
			if(system!=null)
				System.out.println("Le système est déjà allumé");
			else
				system = CoreSystem.systemTurnOn();
			break;
		case 20:
			CoreSystem.systemTurnOff();
			system = null;
			break;
		case 21:
			system.addRestaurant(args[0]);
			break;
		case 22:
			system.openInRestaurant(args[0]);
			break;
		case 23:
			system.getActualRestaurant().logout();
			break;
		case 24:
			system.getActualRestaurant().modifyClientInfo();
			break;
		case 25:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				manager.saveModifications();
			}break;
		case 26:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				manager.showModifications();
			}break;
		case 27:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				manager.cancelModifications();
			}break;
		case 28:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				manager.associateAddress(args[0]);
			}break;
		case 29:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				manager.associateBirthday(Integer.parseInt(args[0]), Integer.parseInt(args[1]));;
			}break;
		case 30:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				Contact contact;
				if(args[0].equalsIgnoreCase("phone")){contact = Contact.phone;}
				else {contact = Contact.email;}
				manager.chooseContactToUse(contact);
			}break;
		case 31:
			if(system.getActualRestaurant().getCurrentActivity() instanceof Registration){
				Registration manager = (Registration) system.getActualRestaurant().getCurrentActivity();
				Contact contact;
				if(args[0].equalsIgnoreCase("phone")){contact = Contact.phone;}
				else {contact = Contact.email;}
				manager.removeContactInfo(contact);
			}break;
		case 32:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.beginNewOrder();
			}break;
		case 33:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.showModifications();
			}break;
		case 34:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.cancelModifications();
			}break;
		case 35:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.removePersonalization(args[0], args[1]);
			}break;
		case 36:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.addToFavouriteMeals(args[0]);
			}break;
		case 37:
			if(system.getActualRestaurant().getCurrentActivity() instanceof OrderManager){
				OrderManager manager = (OrderManager) system.getActualRestaurant().getCurrentActivity();
				manager.removeFromFavouriteMeals(args[0]);
			}break;
		case 38:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.cancelModifications();
			}break;
		case 39:
			if(system.getActualRestaurant().getCurrentActivity() instanceof MenuManager){
				MenuManager manager = (MenuManager) system.getActualRestaurant().getCurrentActivity();
				manager.removeIngredient(args[0]);
			}break;
		}
	}
	
	/**
	 * 
	 * @param line la ligne de commande tapée par l'utilisateur
	 * @return le numéro de la commande tapée dans la liste des commandes possibles, et -1 si la
	 * commande est incorrecte.
	 */
	public int checkInputCommand(String line){
		String[] partition = line.split("<");
		
		// On teste l'intitulé de la commande
		partition[0] = partition[0].replaceFirst(" ","");
		if(commands.contains(partition[0]) && partition.length>1){
			// On teste ensuite la forme générale de la commande
			if(partition[1].endsWith(">") && !partition[1].contains("<")
					&& !partition[1].substring(0,partition[1].length()-1).contains(">")){
				return commands.indexOf(partition[0]);
			}
		}
		System.out.println("Commande incorrecte. Vérifiez la casse et les espaces.");
		return -1;
	}	
	
	/**
	 * Cette méthode main permet de créer et faire fonctionner l'interface de commande.
	 * Celle-ci peut interpreter en direct les lignes de commandes tapées par l'utilisateur
	 * dans un style console
	 */
	public static void main(String[] args) {
		CommandLineInterface clui = new CommandLineInterface();
		System.out.println("Entrez une commande : ");
		scanner = new Scanner(System.in);
		while(true){
			System.out.print("> ");
			String line = scanner.nextLine();
			clui.performCommandLine(line);
		}
	}
}
