package view;

//TODO: Mother Ship across the top of the screen
//TODO: Extra Points for hitting a missile
//TODO: Fleet drops down a level when hits edge of screen
//TODO: Game Over when fleet reaches the ground
//TODO: More Difficulty as levels go up
//TODO: Random Powerups

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Alien;
import Model.Explosion;
import Model.Missile;
import Model.Player;
import Model.Tank;

@SuppressWarnings("serial")
public class TankPanel extends JPanel {
	
	//sets up the parameters of the panel
	int delay = 1000/60;
	int STEP = 1; //the amount of movement
	int TANKSTEP = 2;
	int BOMBSTEP = 6;
	int x_move = STEP;
	int panelWidth = 770;
	Timer timer;
	
	//creates a tank object
	Tank tank;
	
	//creates a missile object
	Missile missile;
	
	//defines the size of the fleet
	int FLEETROW = 8;
	int FLEETDEPTH = 3;
	
	//creates an alien fleet
	AlienImage[][] alien = new AlienImage[FLEETROW][FLEETDEPTH];
	
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
	
	public TankPanel(SpaceFrame frame, Player player)
	{
		//sets the background of the panel
		setBackground(Color.BLACK);
		
		this.frame = frame;
		this.player = player;
		
		//creates a tank object
		tank = new Tank();
		
		//creates an alien image and puts it in position
		for (int i=0;i<FLEETROW;i++) {
			for (int j=0;j<FLEETDEPTH; j++) {

				alien[i][j] = new AlienImage(i, j);
			}
		}
		
		alien[0][0].setEdges(alien[FLEETROW-1][0].getRightEdge());
				
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
	
	//this method paints the tank
	public void paintComponent(Graphics e)
	{
		//calls the superclass
		super.paintComponent(e);
		
		//checks to see if the timer has been paused
		if(pause) {
			tankHit = false;
			pause = false;
			timer.setDelay(delay);
		}
				
		//moves the object along the vertical axis
		if(tank.horizontal && !tankHit)
		{
			
			//if the right arrow is pressed
			if(keyPress == 39)
			{
				//and the tank is not at the right edge of the screen
				if(tank.x<panelWidth-tank.width)
				{
					//moves the tank to the right
					tank.move(TANKSTEP);
				}
				
			//if the left arrow is pressed	
			} else if (keyPress == 37) {
				
				//and the tank is not at the left edge of the screen
				if(tank.x>0)
				{
					//moves the tank to the left
					tank.move(-TANKSTEP);
				}
				
			}
			
		}
		
		//this checks the fleet size to make sure that the fleet will cross the entire screen
		checkSize();
		
		//checks the direction and sets the boundaries of the fleet
		alien[0][0].checkDirection(STEP, panelWidth);
				
		//generates the aliens
		for (int i = 0;i<FLEETROW;i++) {
			for (int j=0;j<FLEETDEPTH;j++) {

				//checks to see if the alien is visible
				if(alien[i][j].getVisibility()) {

					//draws the alien
					alien[i][j].drawAlien(e, STEP);
					
					//checks to see if the alien drops a bomb
					if(alien[i][j].checkDrop())
					{
						//if the random number is 1, a bomb is created
						dropBomb(alien[i][j].getLeftEdge()+20, alien[i][j].getTopEdge()+20);
					}
					
					//Checks to see if the alien has collided with the player
					//or the alien has reached the bottom of the screen (ie, has landed)
					//If it has, then it is game over man, game over.
					if(alien[i][j].getBottomEdge()>getHeight() ||
							(alien[i][j].getBottomEdge()>tank.gunY && alien[i][j].getLeftEdge()>tank.x
							&& alien[i][j].getRightEdge()<(tank.x+tank.width))) {

						drawExplosion(e);
						tankHit=true;
						timer.stop();
						frame.gameOver();
					}
				}
			}
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
			e.fillRect(tank.x,tank.y,tank.width,tank.height);
			e.fillRect(tank.topX,tank.topY,tank.topWidth,tank.topHeight);
			e.fillRect(tank.gunX,tank.gunY,tank.topHeight,tank.gunHeight);
		}
		
	}
		
	//this method deals with the bombs
	public void bombs(Graphics e){
		//creates a list of bombs to remove
		List<Missile> bombsToRemove = new LinkedList<Missile>();
		
		//iterates across the list of bombs
		for(Missile bomb:bombs) {
			
			//checks to see if the bomb has hit the player
			if(bomb.startY>tank.gunY && bomb.startX>tank.x && bomb.startX<(tank.x+tank.width) 
					&& bomb.startY<(tank.gunY+tank.height+tank.topHeight+tank.gunHeight)) {
				
				//if it does and explosion is generated
				explode = new Explosion(tank.gunX, tank.y, tank.gunX, tank.y);
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
				
			
			//checks to see if the bomb has reached the bottom of the screen
			if(bomb.startY+20<getHeight()) {
				
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
				if (missile.startX>alien[i][j].getLeftEdge() && missile.startX<alien[i][j].getRightEdge() 
						&& missile.startY<alien[i][j].getBottomEdge() && missile.startY>alien[i][j].getTopEdge() &&
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

		//if the missile hasn't hit an alien
		if(!hit) {

			if(missile.startY>0) {
				
				//if it hasn't, moves it upwards
				missile.move(BOMBSTEP);
				e.fillRect(missile.startX, missile.startY, missile.width, missile.height);	
				
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
		e.fillRect(bomb.startX, bomb.startY, bomb.width, bomb.height);
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
		explode.expand(STEP);
		
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
		tank.horizontal = false;
	}

	//the horizontal move method
	public void moveHorizontal(int keyPress)
	{
		this.keyPress = keyPress;
		tank.horizontal = true;
	}
	
	public void fireMissile()
	{
		//checks to see if a missile is already on the screen
		if(!missileVisible) {
			
			//if it isn't, creates a new missile
			missile = new Missile(tank.gunX, tank.gunY);
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
