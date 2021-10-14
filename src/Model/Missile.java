package Model;

public class Missile {
	
	private int width = 20;
	private int height = 40;
	private int startX;
	private int startY;
	
	public Missile(int startX, int startY)
	{
		this.startX = startX;
		this.startY = startY;
	}
	
	//this method is for the missile fired by the player
	public void move(int step) {
		startY-=step;
	}
	
	//this method is for the bomb dropped by the alien
	public void drop(int step) {
		startY+=step;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

}
