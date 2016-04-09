package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;

public class GraphicalUserInterface extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/**
	 * L'instance unique et partagée de l'interface graphique qui hérite de JFrame.
	 */
	private static GraphicalUserInterface sharedInstance = new GraphicalUserInterface();

	/**
	 * Constructeur de l'interface graphique. Il crée la fenêtre principale en lui donnant
	 * un nom et des dimensions, et établit également une situation initiale
	 */
	private GraphicalUserInterface(){
		super("Enjoy Your Meal System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 400);
		this.setResizable(false);
		this.setInitialPanel();
		
		CoreSystem.initializeSituation();
		System.out.println("Le système est éteint. Alllumez-le pour commencer");
	}

	/**
	 * Il est plus pratique de n'avoir qu'une seule instance de l'interface graphique
	 * (pattern singleton) pour pouvoir partager plus facilement cette instance entre les 
	 * différentes pages de l'interface (qui héritent de JPanel)
	 * @return L'instance unique et partagée de l'interface graphique qui hérite de JFrame.
	 */
	public static GraphicalUserInterface getSharedInstance() {
		return sharedInstance;
	}

	/**
	 * Cette méthode met en place la page initiale qui apparait lorsque le système est éteint
	 * et qui comporte un unique bouton pour l'allumer
	 */
	public void setInitialPanel(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,150));
		panel.setBackground(Color.lightGray);
		JButton button = new JButton("Démarrer le systeme");
		button.setPreferredSize(new Dimension(150,50));
		button.addActionListener(this);
		panel.add(button);
		this.setContentPane(panel);
		setVisible(true);
	}
	
	/**
	 * Cette méthode main permet de créer et de faire fonctionner l'interface graphique du
	 * système.
	 */
	public static void main(String[] args) {
		GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "démarrer le système"
	 * C'est la classe GraphicalUserInterface elle-même qui est le listener du bouton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CoreSystem.systemTurnOn();
		StartingPage start = new StartingPage();
		setContentPane(start);
		setVisible(true);
	}
}
