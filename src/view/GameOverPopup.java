package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Player;

public class GameOverPopup extends JFrame {
	
	private JLabel centreField;
	private ButtonBar bar;
	
	public GameOverPopup (SpaceFrame frame) {
		
		//places a message in the title bar
		super("Game Over Man! Game Over");

		setSize(400,150);
		
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//places the question in the centre of the screen
		centreField = new JLabel();
		centreField.setText("Do you want to play again?");
		
		add(centreField, BorderLayout.CENTER);
		
		//creates a bar at the bottom to hold the option buttons
		bar = new ButtonBar(frame, this);
		add(bar, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}

}
