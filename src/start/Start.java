package start;
import javax.swing.SwingUtilities;

import Model.Player;

import view.SpaceFrame;

public class Start {

	public static void main(String[] args) {
		
		//Creating a thread to allow multiple objects
		SwingUtilities.invokeLater(new Runnable( ) {
			
			//Runs the thread as an inner class
			public void run ()
			{
				new SpaceFrame(new Player());
			}
		});

	}

}
