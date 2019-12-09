package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.control.ButtonBar;

import Model.Player;

import view.GameOverPopup;
import view.SpaceFrame;

public class NewGameEvent implements ActionListener {

	SpaceFrame frame;
	GameOverPopup bar;
	
	public NewGameEvent(SpaceFrame frame, GameOverPopup bar) {
		this.frame = frame;
		this.bar = bar;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		//clears the current screen if the frame to generate a new game
		frame.clearScreen();
		
		//kills this dialogue box
		bar.dispose();
		
	}

}
