package Model;

import java.lang.Math;
import java.util.Random;

public class AlienTopTwo extends Alien {
	
	//these set out the outline of the alien
	private int[] xPos = {20,20,10,10,20,20,50,50,60,60,50,50};
	private int[] yPos = {20,30,30,80,80,50,50,80,80,30,30,20};
	private int noPoints = 12;
	
	//sets the direction the fleet moves (left or right)
	private static int step = 1;

	public AlienTopTwo () {

		super.createAlien(xPos, yPos, noPoints);

	}
	
	public void changeHorizontalPosition(int move) {
	
		super.changeHorizontalPosition(move);
		
	}
	
	public void changeVerticalPosition(int move) {
	
		super.changeVerticalPosition(move);		
		
	}
		
	//this moves the alien across the screen
	public void moveAlien(int step, boolean movingDown) {
		
		super.moveAlien(step, movingDown);
		
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
		return super.getRightSide();
	}
	
	public int getLeftSide()
	{
		return super.getLeftSide();
	}
	
	public int getTopSide()
	{
		return super.getTopSide();
	}
	
	public int getBottomSide()
	{
		return super.getBottomSide();
	}
	
	public int getRightEdge()
	{
		return super.getRightEdge();
	}
	
	public int getLeftEdge()
	{
		return super.getLeftEdge();
	}
	
	public int getTopEdge()
	{
		return super.getTopEdge();
	}
	
	public int getBottomEdge()
	{
		return super.getBottomEdge();
	}
	
	public void setLeftSide(int side)
	{
		super.setLeftSide(side);
	}
	
	public void setRightSide(int side)
	{
		super.setRightSide(side);
	}
	
	//This returns the value of the score if the alien is taken out
	public int getScore()
	{
		return super.getScore();
	}
	
	//getters for the eye coordinates
	//I have used values so that I only need to write one getter as opposed to
	//a getter for each of the coordinates of the eye
	public int getEyeCoords(int coord)
	{
		return super.getEyeCoords(coord);
				
	}
	
	//getter for the ALIENSIZE and ALIENDEPTH value
	//I have used a boolean value so that it can get either/or
	public int getDepth(boolean depth)
	{
		return super.getDepth(depth);
	}
	
	//getter and the setter for the step
	public int getStep()
	{
		return super.getStep();
	}
	
	public void setStep(int step)
	{
		super.setStep(step);
	}
			
}
