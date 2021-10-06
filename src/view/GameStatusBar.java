package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Player;

public class GameStatusBar extends JPanel {
	
	private JLabel label = new JLabel();
	
	//sets up the status bar
	public GameStatusBar(Player player) {
				
		setLayout(new GridLayout(1,3,0,10));
		
		//The bar has an entry for lives, score, and level
		label = new JLabel(String.format("Lives: %d", player.getLives()));
		add(label);
		
		label = new JLabel(String.format("Score: %d", player.getScore()));
		add(label);
		
		label = new JLabel(String.format("Level: %d", player.getLevel()));
		add(label);

	}

}
