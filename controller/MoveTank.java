package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.TankPanel;

//Listener class that listens for particular key presses
public class MoveTank implements KeyListener {

	private TankPanel panel;
	
	public MoveTank(TankPanel panel) {
		this.panel = panel;
	}
	
	//Activated when the key is pressed, and held down
	@Override
	public void keyPressed(KeyEvent e) {
		
		//called when a key is pressed
		panel.moveHorizontal(e.getKeyCode());
				
	}

	//called when a key is released
	@Override
	public void keyReleased(KeyEvent e) {
		
		panel.stopMove();	
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

		//if the space bar is pressed, fires a missile
		if (e.getKeyCode()==0)
			panel.fireMissile();
		
	}

}
