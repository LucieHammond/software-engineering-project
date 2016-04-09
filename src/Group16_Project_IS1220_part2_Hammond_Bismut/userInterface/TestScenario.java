package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestScenario {
	
	/**
	 * L'interface en ligne de commande sur laquelle on simule le scenario qui est
	 * en fait une suite de commandes interprêtées par cette interface
	 */
	private CommandLineInterface cli = new CommandLineInterface();

	public void readScenario(String fileName){
		FileReader freader = null ;
		BufferedReader reader = null ;
		try {
			freader = new FileReader(fileName);
			reader = new BufferedReader (freader);
			String line = "";
			// On lit le fichier ligne par ligne (chaque ligne correspond à une commande)
			while ((line = reader.readLine()) != null) { 
				// On interprête la commande pour réaliser l'action correspondante
				if(line.length()!=0)
					cli.performCommandLine(line);
			}
		} catch (Exception e) {
			System.out.println("Le fichier n'a pas été trouvé");
			System.out.println(e.getMessage());
		} finally {
			if (reader != null) {
				try {reader . close ();}
				catch (IOException e) {}
			}
			if (freader != null) {
				try {freader .close();}
				catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		TestScenario test = new TestScenario();
		test.readScenario("eval/test1.txt");
	}
}
