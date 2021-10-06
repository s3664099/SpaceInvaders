package Model;

public class Tank {
	
	//Normally all of these elements would be private and getters and setters implemented
	//however this is a game of space invaders, and all of these elements need to be accessed
	//and adjusted. As such I have left them as public.
	
	//This is the tank object at the bottom of the screen.
	//sets the size of the tank
	private int width=50;
	private int height=25;
	private int topWidth=30;
	private int topHeight=10;
	private int gunHeight=20;
			
	//starting location
	private int xStart=575;
	private int yStart=500;
	private int topX=585;
	private int topY=490;
	private int gunX=595;
	private int gunY=470;
	
	//This sets up whether the tank is moving or not
	private boolean horizontal = false;
	
	//This method causes the tank to move
	public void move(int step)
	{
		xStart+=step;
		topX+=step;
		gunX+=step;
	}

	//Getters and setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTopWidth() {
		return topWidth;
	}

	public void setTopWidth(int topWidth) {
		this.topWidth = topWidth;
	}

	public int getTopHeight() {
		return topHeight;
	}

	public void setTopHeight(int topHeight) {
		this.topHeight = topHeight;
	}

	public int getGunHeight() {
		return gunHeight;
	}

	public void setGunHeight(int gunHeight) {
		this.gunHeight = gunHeight;
	}

	public int getXStart() {
		return xStart;
	}

	public void setXStart(int x) {
		this.xStart = x;
	}

	public int getYStart() {
		return yStart;
	}

	public void setYStart(int y) {
		this.yStart = y;
	}

	public int getTopX() {
		return topX;
	}

	public void setTopX(int topX) {
		this.topX = topX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	public int getGunX() {
		return gunX;
	}

	public void setGunX(int gunX) {
		this.gunX = gunX;
	}

	public int getGunY() {
		return gunY;
	}

	public void setGunY(int gunY) {
		this.gunY = gunY;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

}
