package Model;

import java.util.Random;

public class Alien {
	
	//these set out the outline of the alien
	private int[] xPos = {20,20,10,10,30,30,20,20,50,50,40,40,60,60,50,50};
	private int[] yPos = {20,30,30,70,70,60,60,50,50,60,60,70,70,30,30,20};
	private int noPoints = 16;
	
	//the define the edges of the alien
	private int leftEdge = 10;
	private int rightEdge = 50;
	private int bottom = 70;
	private int top = 20;
	
	//these define the eyes
	private int eye1x = 25;
	private int eye1y = 25;
	private int eye2x = 40;
	private int eye2y = 25;
	private int eyeWidth = 5;
	
	//sets the direction the fleet moves (left or right)
	private static int step = 1;
	
	//value of the alien
	private int score = 10;
		
	//sets up the size of the fleet
	private static int leftSide = 0;
	private static int rightSide = 0;
	private static int topSide = 0;
	private static int bottomSide = 0;
	
	private static int ALIENSIZE = 90;
	private static int ALIENDEPTH = 70;
	
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
		
	//this moves the alien across the screen
	public void moveAlien(int step) {
						
		changeHorizontalPosition(step*this.step);
		
	}
	
	//this method returns a random number
	public int checkMissileDrop() {
		
		Random random = new Random();
		
		return random.nextInt(1000);
	}
	
	//getters for drawing the alien graphic
	public int[] getXPos()
	{
		return xPos;
	}
	
	public int[] getYPos()
	{
		return yPos;
	}
	
	public int getNoPoints()
	{
		return noPoints;
	}
	
	public int getRightSide()
	{
		return rightSide;
	}
	
	public int getLeftSide()
	{
		return leftSide;
	}
	
	public int getTopSide()
	{
		return topSide;
	}
	
	public int getBottomSide()
	{
		return bottomSide;
	}
	
	public int getRightEdge()
	{
		return rightEdge;
	}
	
	public int getLeftEdge()
	{
		return leftEdge;
	}
	
	public int getTopEdge()
	{
		return top;
	}
	
	public int getBottomEdge()
	{
		return bottom;
	}
	
	public void setLeftSide(int side)
	{
		leftSide = side;
	}
	
	public void setRightSide(int side)
	{
		rightSide = side;
	}
	
	//This returns the value of the score if the alien is taken out
	public int getScore()
	{
		return score;
	}
	
	//getters for the eye coordinates
	//I have used values so that I only need to write one getter as opposed to
	//a getter for each of the coordinates of the eye
	public int getEyeCoords(int coord)
	{
		int eye = 0;
		
		if (coord == 1)
			eye = eye1x;
		else if (coord == 2)
			eye = eye1y;
		else if (coord == 3)
			eye = eye2x;
		else if (coord == 4)
			eye = eye2y;
		else if (coord == 5)
			eye = eyeWidth;
		
		return eye;
		
	}
	
	//getter for the ALIENSIZE and ALIENDEPTH value
	//I have used a boolean value so that it can get either/or
	public int getDepth(boolean depth)
	{
		int value = 0;
		
		if (depth)
			value = ALIENSIZE;
		else
			value = ALIENDEPTH;
		
		return value;
	}
	
	//getter and the setter for the step
	public int getStep()
	{
		return step;
	}
	
	public void setStep(int step)
	{
		this.step = step;
	}

}
