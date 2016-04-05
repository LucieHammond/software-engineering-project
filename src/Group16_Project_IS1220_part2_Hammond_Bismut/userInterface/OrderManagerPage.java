package Group16_Project_IS1220_part2_Hammond_Bismut.userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Group16_Project_IS1220_part2_Hammond_Bismut.orders.OrderManager;

public class OrderManagerPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GraphicalUserInterface gui = GraphicalUserInterface.getSharedInstance();
	private OrderManager manager;
	private JButton[] button = new JButton[6];
	private JPanel[] row = new JPanel[6];
	private JTextField[] field = new JTextField[4];
	
	public OrderManagerPage(OrderManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
