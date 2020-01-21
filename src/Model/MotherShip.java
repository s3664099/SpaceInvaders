package Model;

public class MotherShip extends Alien {
	
	private int[] xPos = {20, 50, 50, 60, 60, 70, 70, 60, 60, 50, 50, 40, 40, 30, 30, 20,
			20, 10, 10, 0, 0, 10, 10, 20};
	private int[] yPos = {0, 0, 10, 10, 20, 20, 30, 30, 40, 40, 30, 30, 40, 40, 30, 30,
			40, 40, 30, 30, 20, 20, 10, 10};
	private int noPoints = 24;
	
	private int score = 100;
	private int leftEdge = 0;
	private int rightEdge = 70;
	private int bottom = 40;
	
	public MotherShip() {
		
		super.createAlien(xPos, yPos, noPoints);
	}
	
	public void changeVerticalPosition(int move) {
		
		leftEdge += move;
		rightEdge += move;
		
		for (int i=0;i<xPos.length;++i)
		{
			xPos[i]+=move;
		}
		
	}
		
	//this moves the alien across the screen
	public void moveAlien(int step, boolean moveDown) {

		changeVerticalPosition(step);
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
		return rightEdge;
	}
	
	public int getLeftEdge()
	{
		return leftEdge;
	}
	
	public int getTopEdge()
	{
		return super.getTopEdge();
	}
	
	public int getBottomEdge()
	{
		return bottom;
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
		return score;
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
