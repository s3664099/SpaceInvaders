package Model;

public class Tank {
	
	//Normally all of these elements would be private and getters and setters implemented
	//however this is a game of space invaders, and all of these elements need to be accessed
	//and adjusted. As such I have left them as public.
	
	//This is the tank object at the bottom of the screen.
	//sets the size of the tank
	public int width=50;
	public int height=25;
	public int topWidth=30;
	public int topHeight=10;
	public int gunHeight=20;
			
	//starting location
	public int x=575;
	public int y=500;
	public int topX=585;
	public int topY=490;
	public int gunX=595;
	public int gunY=470;
	
	//This sets up whether the tank is moving or not
	public boolean horizontal = false;
	
	//This method causes the tank to move
	public void move(int step)
	{
		x+=step;
		topX+=step;
		gunX+=step;
	}

}
