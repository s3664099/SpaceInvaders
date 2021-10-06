package Model;

public class Explosion {
	
	//the characteristics of the outer circle
	public int outX = 0;
	public int outY = 0;
	public int outer = 20;
	
	//the characteristics of the inner circle
	//this has not been implemented at this stage
	public int inX = 0;
	public int inY = 0;
	public int inner = 5;

	//The timer for when the explosion is on screen
	public int timer = 0;
	
	//Constructor that sets the location of the explosion
	public Explosion(int outX, int outY, int inX, int inY) {
		this.outX = outX;
		this.outY = outY;
		this.inX = inX;
		this.inY = inY;
	}
	
	//method to cause the explosion to expand
	public void expand(int step) {
		outY-=step;
		outX-=step;
		inY-=step;
		inX-=step;
		inner+=(step+1);
		outer+=(step*2);

		//increases the timer by one
		timer++;
	
	}

}
