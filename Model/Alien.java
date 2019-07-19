package Model;

import java.util.Random;

public class Alien {
	
	//these set out the outline of the alien
	public int[] xPos = {20,20,10,10,30,30,20,20,50,50,40,40,60,60,50,50};
	public int[] yPos = {20,30,30,70,70,60,60,50,50,60,60,70,70,30,30,20};
	public int noPoints = 16;
	
	//the define the edges of the alien
	public int leftEdge = 10;
	public int rightEdge = 50;
	public int bottom = 70;
	public int top = 20;
	
	//these define the eyes
	public int eye1x = 25;
	public int eye1y = 25;
	public int eye2x = 40;
	public int eye2y = 25;
	public int eyeWidth = 5;
	
	//sets the alien's visibility
	public boolean visible = true;
	
	//value of the alien
	public int score = 10;
	
	//sets the direction the fleet moves (left or right)
	public static int step = 1;
	
	//sets up the size of the fleet
	public static int leftSide = 0;
	public static int rightSide = 0;
	public static int topside = 0;
	public static int bottomside = 0;
	
	public static int ALIENSIZE = 90;
	public static int ALIENDEPTH = 70;
	
	//the position of the alien is changed
	public void changeHorizontalPosition(int move) {
		
		//goes through each of the x-coordinates and changes them by the amount
		//of move
		leftEdge += move;
		rightEdge += move;
		eye1x += move;
		eye2x += move;
		
		for (int i=0;i<xPos.length;++i)
		{
			xPos[i]+=move;
		}
		
	}
	
	//the position of the alien is changed
	public void changeVerticalPosition(int move) {
		
		//goes through each of the x-coordinates and changes them by the amount
		//of move
		bottom += move;
		top += move;
		eye1y += move;
		eye2y += move;
		
		for (int i=0;i<yPos.length;++i)
		{
			yPos[i]+=move;
		}
		
	}
	
	//checks to see which direction the fleet is heading
	public void checkDirection(int step, int width) {
		
		//checks to see if it has reached the left side of the screen
		if (leftSide<5) {
			//sends it in the opposite direction if it has
			this.step = 1;

			//checks to see if it has reached the right side of the screen	
		} else if (rightSide>width-5) {
			this.step = -1;
		}
		
		//moves the fleet boundaries
		leftSide+=(step*this.step);
		rightSide+=(step*this.step);
		
	}
	
	//this moves the alien across the screen
	public void moveAlien(int step) {
						
		changeHorizontalPosition(step*this.step);
		
	}
	
	//this method returns a random number
	public int checkMissileDrop() {
		
		Random random = new Random();
		
		return random.nextInt(1000);
	}
	
}
