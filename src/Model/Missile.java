package Model;

public class Missile {
	
	public int width = 10;
	public int height = 20;
	public int startX;
	public int startY;
	
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

}
