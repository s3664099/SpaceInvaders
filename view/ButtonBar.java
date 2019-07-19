package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.EndGameEvent;
import controller.NewGameEvent;

public class ButtonBar extends JPanel {
	
	private JButton yes;
	private JButton no;
	
	public ButtonBar(SpaceFrame frame, GameOverPopup popup) {
		
		setLayout(new FlowLayout());
		
		//creates a button to play again and adds a listener
		yes = new JButton("Yes");
		yes.addActionListener(new NewGameEvent(frame, popup));
		
		//creates a button to bring the game to an end
		no = new JButton("No");
		no.addActionListener(new EndGameEvent());
		
		add(yes);
		add(no);
		
		
	}

}
