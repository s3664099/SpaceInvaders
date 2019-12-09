package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Model.Player;

import controller.MoveTank;

@SuppressWarnings("serial")
public class SpaceFrame extends JFrame {
	
	private TankPanel panel;
	private GameStatusBar statusBar;
	private Player player;
	
	public SpaceFrame(Player player) 
	{
		//places a title at the top of the frame
		super("Space Invaders");
		
		this.player = player;
		
		//Generates the window
		setLayout(new BorderLayout());
		
		newGame();
		
		//kills the window when the 'x' is clicked at the top
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//sets the boundaries of the frame.
		setBounds(100,100, 800,600);
		
		setVisible(true);
	}
	
	//Clears the frame in preparation for a new game
	public void clearScreen() {
		
		//removes the panel and the status bar
		remove(panel);
		remove(statusBar);
		
		//creates a new player
		player = new Player();
		
		newGame();
		
		//refreshes the screen
		this.revalidate();
		this.repaint();
		
	}
	
	//new game method
	public void newGame() {
				
		//calls the functions to generate a new game
		createGame();
		createStatusBar();
		
		add(panel, BorderLayout.CENTER);
				
	}
	
	//method that generates the main parts of the game
	public void createGame() {
		
		//creates a panel to place in the window
		panel = new TankPanel(this, player);
		
		//Adds a key listener to the panel
		panel.addKeyListener(new MoveTank(panel));
		
		//These two lines are required if we are to add a keylistener to the panel
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}
	
	//creates a status bar and adds it to the panel
	public void createStatusBar() {
		
		statusBar = new GameStatusBar(player);
		add(statusBar, BorderLayout.NORTH);
	}
	
	//removes the status bar and then adds it back to the panel, refreshed
	public void updateStatus() {
		
		remove(statusBar);
		createStatusBar();
		this.revalidate();
		this.repaint();
	}
	
	//method to generate the game over popup
	public void gameOver() {
		
		GameOverPopup gameOver = new GameOverPopup(this);
		
	}
	
}
