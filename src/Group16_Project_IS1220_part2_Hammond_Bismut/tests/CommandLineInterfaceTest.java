package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.userInterface.CommandLineInterface;

public class CommandLineInterfaceTest {

	@Test
	public void testCheckInputCommand_unknownCommand() {
		CommandLineInterface cli = new CommandLineInterface();
		// C'est login et pas Login
		assertEquals(-1, cli.checkInputCommand("Login<LucieH,123456>"));
	}
	@Test
	public void testCheckInputCommand_wrongSyntax() {
		CommandLineInterface cli = new CommandLineInterface();
		// On utilise des < > et pas des ( )
		assertEquals(-1, cli.checkInputCommand("login (LucieH,123456)"));
	}
	@Test
	public void testCheckInputCommand_rightWithSpaces() {
		CommandLineInterface cli = new CommandLineInterface();
		// On peut mettre des espaces avant < et après chaque virgule
		// login est la commande 0
		assertEquals(0, cli.checkInputCommand("login <LucieH, 123456>"));
	}
	@Test
	public void testCheckInputCommand_rightWithoutSpaces() {
		CommandLineInterface cli = new CommandLineInterface();
		// ça marche aussi sans espaces
		assertEquals(0, cli.checkInputCommand("login<LucieH,123456>"));
	}

}
