package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SpaceFrame;

public class EndGameEvent implements ActionListener {

	//function that basically brings the game to an end
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		System.exit(0);
		
	}
	
	

}
