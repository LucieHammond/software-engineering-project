package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Group16_Project_IS1220_part2_Hammond_Bismut.EYMSCore.CoreSystem;

public class GraphicalUserInterface extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static GraphicalUserInterface sharedInstance = new GraphicalUserInterface();

	private GraphicalUserInterface(){
		super("Enjoy Your Meal System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 400);
		this.setResizable(false);
		this.setInitialPanel();
		
		CoreSystem.initializeSituation();
		System.out.println("Le système est éteint. Alllumez-le pour commencer");
	}

	public static GraphicalUserInterface getSharedInstance() {
		return sharedInstance;
	}

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
	
	public static void main(String[] args) {
		GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CoreSystem.systemTurnOn();
		StartingPage start = new StartingPage();
		setContentPane(start);
		setVisible(true);
	}
}
