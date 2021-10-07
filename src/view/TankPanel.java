package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Explosion;
import Model.Missile;
import Model.Player;
import Model.Tank;

//TODO: change next level so that it is on this screen. Increase the step, player level, and make all aliens visible.
//Load six background images, and the one displayed is based on the level.

@SuppressWarnings("serial")
public class TankPanel extends JPanel {
	
	//sets up the parameters of the panel
	private int delay = 1000/60;
	private int step; //the amount of movement
	private int TANKSTEP = 2;
	private int BOMBSTEP = 6;
	private int panelWidth = 770;
	private Timer timer;
	
	//creates a tank object
	private Tank tank;
	
	//creates a missile object
	private Missile missile;
	private int bombScore = 500;
	
	//defines the size of the fleet
	int FLEETROW = 8;
	int FLEETDEPTH = 3;
	
	//creates an alien fleet
	AlienImage[][] alien = new AlienImage[FLEETROW][FLEETDEPTH];
	AlienImage[][] alienAltImage = new AlienImage[FLEETROW][FLEETDEPTH];
	AlienImage motherShip;
	int motherShipStep = 2;
	boolean motherShipAppear = false;
	
	//creates a list to store any bombs that aliens might drop
	List<Missile> bombs = new LinkedList<Missile>();
	
	//reads the key press
	//KeyCode right arrow 39
	//KeyCode left arrow 37
	int keyPress = 0;
	
	//this indicates whether a missile is on the screen
	boolean missileVisible = false;
	
	//this creates an object for a potential explosion
	Explosion explode;
	boolean explosion = false;
	boolean pause = false;
	
	//flag for when the tank is hit
	boolean tankHit = false;
	
	//stores the player details and the JFrame
	SpaceFrame frame;
	Player player;
	
	//Variables to determine the type of alien being drawn
	private boolean alienType = false;
	private int alienDrawCounter = 0;
	private BufferedImage[] backgroundImage;
	
	public TankPanel(SpaceFrame frame, Player player, BufferedImage[] backgroundImage)
	{
				
		this.frame = frame;
		this.player = player;
		this.backgroundImage = backgroundImage;
				
		//creates a tank object
		tank = new Tank();
		
		//creates the alien fleet and puts it in position
		this.refreshAliens();
						
		//creates a timer for when the graphics are moving and sets up an action listener
		timer = new Timer(delay, new ActionListener() 
		{

			//whenever an action occurs the image is repainted
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
			
		});
		
		timer.start();
	}
	
	//generates the aliens
	private void refreshAliens() {
		
		//Sets the speed of the alien
		step = player.getLevel()/5;
		step ++;
		
		boolean alienImage = false;
		
		for (int i=0;i<FLEETROW;i++) {
			for (int j=0;j<FLEETDEPTH; j++) {
				
				alien[i][j] = new AlienImage(i,j, 0, alienImage);
				alienAltImage[i][j] = new AlienImage(i,j, 1, alienImage);
				alienImage = !alienImage;
			}
		}
		
		alien[0][0].setEdges(alien[FLEETROW-1][0].getRightEdge());
		alienAltImage[0][0].setEdges(alienAltImage[FLEETROW-1][0].getRightEdge());
		
	}
	
	//this method paints the tank
	public void paintComponent(Graphics e)
	{
				
		//calls the superclass
		super.paintComponent(e);
		
		System.out.println(step);
		
		int imageNo = player.getLevel()%6;
		
		//Draws the background image
		e.drawImage(backgroundImage[imageNo],0,0,800,600,this);
		
		//checks to see if the timer has been paused
		if(pause) {
			tankHit = false;
			pause = false;
			timer.setDelay(delay);
		}
				
		//moves the object along the vertical axis
		if(tank.isHorizontal() && !tankHit)
		{
			checkMove();			
		}
		
		//this checks the fleet size to make sure that the fleet will cross the entire screen
		checkSize();
		
		//checks the direction and sets the boundaries of the fleet
		alien[0][0].checkDirection(step, panelWidth);
		
		//starts a count of the number of aliens still visibile.
		int alienCount = 0;
		
		//generates the aliens
		for (int i = 0;i<FLEETROW;i++) {
			for (int j=0;j<FLEETDEPTH;j++) {

				//checks to see if the alien is visible
				if(alien[i][j].getVisibility()) {
					
					alienCount +=1;
					
					checkAlienVisibility(i,j, e);
					
					//checks to see if the alien drops a bomb
					if(alien[i][j].checkDrop(player.getLevel()))
					{
						//if the random number is 1, a bomb is created
						dropBomb(alien[i][j].getLeftEdge()+20, alien[i][j].getTopEdge()+20);
					}
					
					//Checks to see if the alien has collided with the player
					//or the alien has reached the bottom of the screen (ie, has landed)
					//If it has, then it is game over man, game over.
					if(alien[i][j].getBottomEdge()>getHeight() ||
							(alien[i][j].getBottomEdge()>tank.getGunY() 
							&& alien[i][j].getLeftEdge()>tank.getXStart()
							&& alien[i][j].getRightEdge()<(tank.getXStart()+tank.getWidth()))) {

						drawExplosion(e);
						tankHit=true;
						timer.stop();
						frame.gameOver();
					}
				}
			}
			
		}
		
		//If there are no aliens left, and new level is generated.
		//A new fleet is generated and the frame is updated
		if (alienCount == 0) {
			
			newLevel();
			
		}
		
		alienDrawCounter ++;
		
		if(alienDrawCounter==10) {
			alienType = !alienType;	
			alienDrawCounter=0;
		}
									
		//sets the bomb colour to yellow
		e.setColor(Color.YELLOW);
		
		//checks to see if any bombs have been drops
		if(bombs.size()>0) {
			
			bombs(e);
			
		}
									
		//sets the missile colour to red
		e.setColor(Color.RED);
		
		//If a missile has been fired
		if(missileVisible) {

			//calls the draw missile function
			drawMissile(e);

		}
		
		//if there is an explosion
		if(explosion) {
			
			//calls the method to draw it
			drawExplosion(e);
			
		}
		
		//checks to see if the tank if functioning
		if(!tankHit) {
			//sets the colour of the object
			e.setColor(Color.BLUE);
		
			//sets the shape of the object
			e.fillRect(tank.getXStart(),tank.getYStart(),tank.getWidth(),tank.getHeight());
			e.fillRect(tank.getTopX(),tank.getTopY(),tank.getTopWidth(),tank.getTopHeight());
			e.fillRect(tank.getGunX(),tank.getGunY(),tank.getTopHeight(),tank.getGunHeight());
		}
		
		if (motherShipAppear) {
			motherShip.checkDirection(8, panelWidth);
			motherShip.drawAlien(e, motherShipStep);
						
			if (motherShip.getLeftEdge()>1500) {
				motherShipAppear = false;
			}
			
		} else {
			checkMotherShip();
		}
		
	}
	
	//method that handles the player moving
	private void checkMove() {
		
		//if the right arrow is pressed
		if(keyPress == 39)
		{
			//and the tank is not at the right edge of the screen
			if(tank.getXStart()<panelWidth-tank.getWidth())
			{
				//moves the tank to the right
				tank.move(TANKSTEP);
			}
			
		//if the left arrow is pressed	
		} else if (keyPress == 37) {
			
			//and the tank is not at the left edge of the screen
			if(tank.getXStart()>0)
			{
				//moves the tank to the left
				tank.move(-TANKSTEP);
			}
			
		}
		
	}
	
	//Method handling the alien visibility and size of the fleet.
	private void checkAlienVisibility(int i, int j, Graphics e) {
		
		if (alien[i][j].getAlienImageVersion()) {
			//determines which alien to draw and draws it
			if (alienType) {
			
				alien[i][j].drawAlien(e, step);
			
			} else {
			
				alienAltImage[i][j].drawAlien(e, step);
			}
			
		} else {
			
			//determines which alien to draw and draws it
			if (alienType) {
			
				alienAltImage[i][j].drawAlien(e, step);
			
			} else {
			
				alien[i][j].drawAlien(e, step);
			}
			
		}
	}
	
	//Sets up a new level
	private void newLevel() {
		
		player.increaseLevel();
		this.refreshAliens();
		pause = true;
		frame.updateStatus();
		
	}
	
	//Determines whether a mothership appears on the screen
	private void checkMotherShip() {
		
		Random random = new Random();

		if (random.nextInt(1000)<1) {
			motherShip = new AlienImage(0,0, 2, false);
			motherShip.setLeftSide(0);
			motherShip.setRightSide(panelWidth);
			motherShipAppear = true;
		}
		
	}
		
	//this method deals with the bombs
	public void bombs(Graphics e){
		//creates a list of bombs to remove
		List<Missile> bombsToRemove = new LinkedList<Missile>();
		
		//iterates across the list of bombs
		for(Missile bomb:bombs) {
			
			//checks to see if the bomb has hit the player
			if(bomb.getStartY()>tank.getGunY() && bomb.getStartX()>tank.getXStart() 
					&& bomb.getStartX()<(tank.getXStart()+tank.getWidth()) 
					&& bomb.getStartY()<(tank.getGunY()+tank.getHeight()+tank.getTopHeight()+tank.getGunHeight())) {
				
				//if it does and explosion is generated
				explode = new Explosion(tank.getGunX(), tank.getYStart(), tank.getGunX(), tank.getYStart());
				explosion = true;
				tankHit = true;
				
				//removes a life from the player
				player.takeLife();
				
				//if the player is down to 0 lives
				if(player.getLives()==0)
				{
					//the game stops
					timer.stop();
					
					frame.gameOver();
				
				} else {
					
					//otherwise the status bar is updated
					frame.updateStatus();
					
				}
				
				
				bombsToRemove.add(bomb);
			}
			
			//checks to see if the bomb and the player's missile have collided
			//checks to see if the bomb has hit the player
			if (missileVisible) {
				if(bomb.getStartY()>missile.getStartY() && bomb.getStartX()>missile.getStartX() 
					&& bomb.getStartX()<(missile.getStartX()+missile.getWidth()) 
					&& bomb.getStartY()<(missile.getStartY()+missile.getHeight())) {
				
					//if it does and explosion is generated
					explode = new Explosion(tank.getGunX(), tank.getYStart(), tank.getGunX(), tank.getYStart());
					explosion = true;
					player.setScore(bombScore);
					missileVisible = false;
								
					bombsToRemove.add(bomb);
					frame.updateStatus();
				}
			}
				
			
			//checks to see if the bomb has reached the bottom of the screen
			if(bomb.getStartY()+20<getHeight()) {
				
				//if it hasn't the bomb is drawn
				drawBomb(e,bomb);
				
			} else {
				
				//otherwise it is removed from the list
				bombsToRemove.add(bomb);
			}
		}
		
		//removes all of the bombs from the list that need to be removed
		for (Missile bomb:bombsToRemove) {
			bombs.remove(bomb);
		}
	}
	
	//Method to check the size of the fleet to determine how far across the screen
	//it will move
	public void checkSize()
	{
		//flag to say that the left side has not been found
		boolean foundLeft=false;
		
		//loops through all the aliens
		for (int i = 0; i<FLEETROW;i++) {
			for (int j=0;j<FLEETDEPTH;j++) {
				
				//if the alien is visible, and the left has not been found
				if(alien[i][j].getVisibility() && !foundLeft)
				{
					//sets the left side of the alien for the fleet
					alien[i][j].setLeftSide(alien[i][j].getLeftEdge());

					//flags the left side as having been found
					foundLeft=true;
				}

				//if the alien is visible
				if(alien[i][j].getVisibility())
				{
					//then sets the right side of the fleet with the right side of this alien
					//As such the last visible alien on the right will be the right side of the fleet
					alien[i][j].setRightSide(alien[i][j].getRightEdge());
				}
				
			}
		}
		
	}
	
	//method for drawing the missile fired by the player
	public void drawMissile(Graphics e) {
		
		//sets up a flag to see if the missile has hit an alien
		boolean hit=false;
		
		//checks to see if the missile has hit the alien	
		for (int i=0;i<FLEETROW;++i){
			for (int j=0;j<FLEETDEPTH;++j) {
				if (missile.getStartX()>alien[i][j].getLeftEdge() && missile.getStartX()<alien[i][j].getRightEdge() 
						&& missile.getStartY()<alien[i][j].getBottomEdge() && missile.getStartY()>alien[i][j].getTopEdge() &&
						alien[i][j].getVisibility()) {
					
					//if it has, then the missile, and the alien, vanish
					missileVisible = false;
					alien[i][j].setVisibility(false);
					hit = true;
					
					explode = new Explosion(alien[i][j].getLeftEdge()+5, alien[i][j].getTopEdge()+5,
							alien[i][j].getLeftEdge()+10, alien[i][j].getTopEdge()+10);
					explosion = true;
					
					//the player's score is increased
					player.setScore(alien[i][j].getScore());
					
					//and the panel is refreshed
					frame.updateStatus();
				}			
			}
		}
		
		//checks to see if missile has hit the mothership
		if (motherShipAppear) {

			if (missile.getStartX()>motherShip.getLeftEdge() && missile.getStartX()<motherShip.getRightEdge() 
				&& missile.getStartY()<motherShip.getBottomEdge()) {
			
				//if it has, then the missile, and the alien, vanish
				missileVisible = false;
				motherShipAppear = false;
				hit = true;
			
				explode = new Explosion(motherShip.getLeftEdge()+5, motherShip.getTopEdge()+5,
					motherShip.getLeftEdge()+10, motherShip.getTopEdge()+10);
				explosion = true;
			
				//the player's score is increased
				player.setScore(motherShip.getScore());
			
				//and the panel is refreshed
				frame.updateStatus();
			}
		}
		
		//if the missile hasn't hit an alien
		if(!hit) {

			if(missile.getStartY()>0) {
				
				//if it hasn't, moves it upwards
				missile.move(BOMBSTEP);
				e.fillRect(missile.getStartX(), missile.getStartY(), missile.getWidth(), missile.getHeight());	
				
			} else {
			
				//otherwise makes it disappear
				missileVisible=false;
			}
		}
	}
	
	//method to draw a bomb that is being dropped
	public void drawBomb(Graphics e, Missile bomb)
	{
		bomb.drop(BOMBSTEP);
		e.fillRect(bomb.getStartX(), bomb.getStartY(), bomb.getWidth(), bomb.getHeight());
	}
	
	//method to draw an explosion
	public void drawExplosion(Graphics e) {
		
		//draws the outer circle
		e.setColor(Color.YELLOW);
		e.fillOval(explode.outX, explode.outY, explode.outer, explode.outer);

		//draws the inner circle (disabled)
		//e.setColor(Color.YELLOW);
		//e.fillOval(explode.inX, explode.inY, explode.inner, explode.inner);
		
		//expands the size of the explosion
		explode.expand(step);
		
		//checks to see if the timer has expired
		if(explode.timer>10)
		{
			//turns the explosion off if this is the case
			explosion = false;
			
			//checks to see if the tank has been hit
			if(tankHit) {
				
				//sets the pause flag
				pause = true;
				
				//changes the timer to pause the game slightly
				timer.setDelay(100000/60);
				
			}
		}

	}

	//stops the tank moving
	public void stopMove()
	{
		tank.setHorizontal(false);
	}

	//the horizontal move method
	public void moveHorizontal(int keyPress)
	{
				
		this.keyPress = keyPress;
		tank.setHorizontal(true);
	}
	
	public void fireMissile()
	{
		//checks to see if a missile is already on the screen
		if(!missileVisible) {
			
			//if it isn't, creates a new missile
			missile = new Missile(tank.getGunX(), tank.getGunY());
			missileVisible=true;
		}
	}
	
	//creates a new bomb object
	public void dropBomb(int x, int y)
	{
		
		Missile bomb = new Missile(x,y);

		//adds the bomb to the list of bombs
		bombs.add(bomb);
	}

}
