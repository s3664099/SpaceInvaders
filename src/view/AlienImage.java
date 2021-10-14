package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import Model.Alien;
import Model.AlienTopOne;
import Model.AlienTopTwo;
import Model.MotherShip;

//a class for the alien graphic
public class AlienImage {
	
	private Alien alien;
		
	//sets the alien's visibility
	private boolean visible = true;
	private int edgeSpace = 5;
	private static boolean movingDown = false;
	private static int downMove = 0;
	
	private int type = 0;
	private boolean alienImage;
	private ImageIcon alienIcon;
	
	public AlienImage(int horizontal, int vertical, int type, boolean alienImage, TankPanel panel)
	{
		this.type = type;
		this.alienImage = alienImage;
		
		if (type == 0) {
			alien = new AlienTopOne();
			
			//Checks which level the alien is on, and displays the icon for that level.
			if (vertical == 0) {
				alienIcon = panel.createIcon("Alien-1a.png", 
						alien.getDepth(true), alien.getDepth(false));
			} else if (vertical == 1) {
				alienIcon = panel.createIcon("Alien-2a.png", 
						alien.getDepth(true), alien.getDepth(false));
			} else {
				alienIcon = panel.createIcon("Alien-3a.png", 
						alien.getDepth(true), alien.getDepth(false));
			}
		} else if (type == 1) {
			alien = new AlienTopTwo();
			
			if (vertical == 0) {
				alienIcon = panel.createIcon("Alien-1b.png", alien.getDepth(true), 
						alien.getDepth(false));
			} else if (vertical == 1) {
				alienIcon = panel.createIcon("Alien-2b.png", alien.getDepth(true), 
						alien.getDepth(false));
			} else {
				alienIcon = panel.createIcon("Alien-3b.png", alien.getDepth(true), 
						alien.getDepth(false));
			}
			
		} else if (type == 2 ) {
			alien = new MotherShip();
			alienIcon = panel.createIcon("mothership.png", alien.getDepth(true), alien.getDepth(false));
		}
		
		if (type != 2) {
			//sets the position of the alien
			alien.changeHorizontalPosition(alien.getDepth(true)*horizontal);
			alien.changeVerticalPosition(alien.getDepth(false)*vertical);
		} else {
			alien.changeHorizontalPosition(alien.getDepth(true)*horizontal);
		}
		
	}
	
	//this sets the edges of the aliens
	public void setEdges(int rightEdge)
	{
		alien.setLeftSide(alien.getLeftEdge());
		alien.setRightSide(alien.getRightEdge());
	}
	
	//sets the left side of the fleet
	public void setLeftSide(int side)
	{
		alien.setLeftSide(side);
	}
	
	//sets the right side of the fleet
	public void setRightSide(int side)
	{
		alien.setRightSide(side);
	}
	
	//this returns the right edge of the aliens
	public int getRightEdge()
	{
		return alien.getRightEdge();
	}
	
	//returns the left edge of the alien
	public int getLeftEdge()
	{
		return alien.getLeftEdge();
	}
	
	//returns the top edge of the alien
	public int getTopEdge()
	{
		return alien.getTopEdge();
	}
	
	//returns the bottom edge of the alien
	public int getBottomEdge()
	{
		return alien.getBottomEdge();
	}
	
	//returns the score value of the alien
	public int getScore()
	{
		return alien.getScore();
	}
	
	public boolean getMovingDown() {
		return movingDown;
	}
	
	public boolean getVisibility() {
		return visible;
	}
	
	public void setVisibility(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getAlienImageVersion() {
		return alienImage;
	}
	
	private void alienDirection (int step, int width) {
		
		//checks to see if it has reached the left side of the screen
		if (alien.getLeftSide()<edgeSpace && movingDown == false) {
			
			//sends it in the opposite direction if it has
			//And moves it down one
			alien.setStep(1);
			movingDown = true;

			//checks to see if it has reached the right side of the screen	
		} else if (alien.getRightSide()>width-edgeSpace && movingDown == false) {
			alien.setStep(-1);
			movingDown = true;
			
		//checks to see if the alien is moving down, and whether the counter
		//has reached a point where the alien must start moving across the screen
		//again.
		} else if (movingDown == true) {
			
			if (downMove == 10) {
				downMove = 0;
				movingDown = false;
			} else {
				downMove++;
			}
		}
				
		//moves the fleet boundaries
		int side = alien.getLeftSide();
		alien.setLeftSide(side+=(step*alien.getStep()));

		side = alien.getRightSide();
		alien.setRightSide(side+=(step*alien.getStep()));
	}
	
	//checks to see which direction the fleet is heading
	public void checkDirection(int step, int width) {
		
		if (type != 2) {
			alienDirection(step, width);
		}
		
	}
	
	public void drawAlien(TankPanel panel, Graphics e, int STEP) {
		
		//creates its shape
		alienIcon.paintIcon(panel, e, alien.getLeftEdge(), alien.getBottomEdge());
				
		//this is to get away from the static variable
		boolean downMove = movingDown;
		
		if (type == 2) {
			downMove = false;
		} 
		
		alien.moveAlien(STEP, downMove);
	}
	
	//checks to see if the alien has dropped a bomb
	public boolean checkDrop(int levelModifier)
	{
		//sets the flag to false
		boolean drop = false;
		
		//checks to see if the alien drops a bomb
		if(alien.checkMissileDrop(levelModifier)==1)
		{
			//if it is the case, the flag is set to true
			drop = true;
		}
		
		return drop;
	}

}
