# SpaceInvaders
**Author**: David Sarkies
**Version**: 0.6

## Introduction
This was a project that I created over the mid-semester break in 2019. It is basically a version of Space Invaders. Close, but pretty scrappy. The main reason that I worked on this was so that I could retain the knowledge that I had built up over the past year and a half in my computer science degree. Also, I felt that Space Invaders was reasonably simply but also reasonably challenging, so that it would challenge me, but it would also be achievable.

The game is written in Java, and I am using the Model/View/Controller pattern that I learnt in SADI, namely because it is one of the major design patterns out there, and that it works very well for graphical presentations. Also, when I worked on it in my SADI course (which is now known as Further Programming) we used it to build a game. As such, it turned out to be a useful tool to use in this project. The game has also been written in Java, namely because that is not only one of the langauges that I knew at the time, but it is also a language that I knew how to use for graphic programming.

At this stage, I am not using icons for the various objects, but rather simply block graphics. However, my plan is to covert the code so that proper icons are used instead of the blocks. That will no doubt be a challenge, but I hopefull will be able to get it to work.

*Current Status*

At this stage I have one level working. There are three rows of aliens, which have been created using blocks, and they all look the same. They do animate between two forms, and each of the aliens are at a different spot to the one next to it. The aliens drop bombs, move across the screen and move to the bottom slowly. Also, a mother ship moves across the top of the screen.

Score is kept for hitting the aliens, which is 10 points for the alien, and 100 points for the mother ship. An explosion appears when the alien, or mothership, is hit. The aliens move across the screen, and down one when they reach the edge, though the game will ignore aliens that have been shot, meaning that when a column is taken out, it will take longer for the aliens to reach the edge of the screen. If the player is hit, one life is lost, and when all lives are lost the game is over, and the player is given the option to play again, or end the game. Also, when the player is hit, the game pauses for a short time.

The score, and the number of lives, are displayed at the top of the screen.

To complete version one, I plan on having the player move to a new level, which is more difficult, when all of the aliens are killed. I also plan on using icons instead of the block graphics I'm using at the moment.

For version 2, I plan on adding power-ups, different alien weapons, and dive bombing aliens.

## Executing the Code
One the code has been down loaded, move to the main directory and type the following:

*javac *\/\*.java*

This will compile the code. Once the code has been compiled, to start the game type the following:

*java start.Start*

It is necessary to indicate the package where the driver program is located. When you do that, you should recieve a screen as follows:

![Screenshot](https://raw.githubusercontent.com/s3664099/SpaceInvaders/master/img/Screenshot.png)

## Upgrades

*Version 1*
- Document Code
- Next Levels Difficulty (Change backgrounds, )
- Change Icons
- ~~Add Background~~
- ~~Set the setters & getters for the models.~~

*Version 2*
- Add Barriers
- Power Ups
	- shields
	- laser that strikes through
	- guided missiles
	- missiles that fly through
	- automatic firing
- Different Alien Weapons
- Dive Bombing Aliens
- Aliens with Hitpoints
- Mothership fires lasers
- Mouse Control (particularly for powerups)

## Code Walkthrough
What I'll do is that I'll walkthrough the code based upon the classes that are being called, which means that I will start with the driver (start) class and move through to the view, since that is called from the start class.

## Updates
6 October 2021 - Added background image instead of a black background. Set up code for new level.

### Classes

**Start**
This is a standalone package that I created to hold the driver class, or rather the start class. Half the reason I have this is because right from the beginning of my studies, I was taught that we should get as far away with the public static void class as possible (since the static can cause havoc with the code). Since the driver does not fit into of the other packages, it was best to simply put it into a package of its own.

*1.Start*
This is a pretty tricky little class, and I can't say that I fully understand how it worked - I just copied the SADI project that I did. Anyway, it runs the game, but in doing so it uses Swing, which is one of the Java graphic libraries. Since there are multiple things running at the same time, threading is involved, and because it is Java, it runs being the scenes.

Anyway, after invoking swing, it then creates an inner class, which allows the threading, and this innerclass summons the base window, which is the SpaceFrame (I have to work out how to add a background that is a galactic background, sort of, I'll work it out). It creates a new player object, and passes this into the frame.

**view**
This package handles all of the classes that are involved in the graphical display. This is pretty much how the model, view, controller model works, with the view holding the all of the classes that deal with display, the controller holding the classes that deal with controls, and the model holding the classes that represent the core running of the program.

*1.SpaceFrame*
This class is an extension of the JFrame class, and is the basic frame for the game. This is where all of the action takes place. The constructor takes a Player object that is passed through from the Start Class. It also holds a TankPanel object, a GameStatusBar onject, and a Player Object.

The constructor builds the basis of the game, including passing the title that appears at the top of the frame to the super class. It also sets the layout as a BorderLayout, and generates a new game by calling the newGame() method. It then sets the default close, the size, and the final thing it does it that the makes the frame visible.

- clearScreen(): This method basically restarts the game. It removes the current things on the screen, and the current player, and refreshes everything. This is called when the player uses up all of their lives, and wishes to play again.

- newGame(): This starts a new game by calling the createGame() method and the createStatusBar() method. It then adds the items to the frame by passing through to the add() method.

- createGame(): This method generates the main aspects of the game. It creates a panel that is place in the pane, call a TankPanel, and adds a listener to the panel that is used by the player to control the tank. the setFocusable and requestFocusInWindow are required for the keylistener to work.

- createStatusBar(): This method creates the status bar and adds it to the panel.

- updateStatus(): This method removes the current status bar, creates a new status bar, and then refreshes the frame.

- gameOver(): This method is called when the game ends. It generates a GameOverPopup which asks the player whether they wish to quit, or play again.

*2.TankPanel*
This class sets up the main playing area, and sets the various constants that control the game. It also sets up the objects that will
appear on the screen, including the tank, the missiles, and the alien ships. The speed of the various entities are including, which
exist as the variables 'step'. The missle and tank steps are constants (though this could change) and the alien ones are variable.

- TankPanel(): this is the constructor for the TankPanel class, and takes a player and frame. It will create a new tank, and then populates the aliens. The aliens are set as alternating images. A time is set up which adds a listener for the keypresses. Whenever something changes on the panel it is redrawn.

- paintComponent(): this is the main method for this class, and redraws the panel whenever it is drawn. It checks to see if a key has been pressed and executes the action if it has. It also moves the alien fleet. The method checks whether the alien exists, and whether the tank/alien has reached the edge of the screen, and if it has then it cannot move any further. The aliens will switch direction once an alien has hit the edge of the screen. When the aliens are drawn, it will determine whether a bomb has been dropped, and if it has, it will create a bomb. The bomb also checks to see whether it has hit the player, and will destroy the player if it has.

- checkMotherShip(): This method checks to see if a mothership appears. It is a random check. If it does, it sets it at one edge of the screen and it moves across to the other edge.

- bombs(): This method manages both the bombs and the missiles. It goes through all the bombs on the screen, and if they hit the player, the player loses a life, and if the player's lives are down to zero, the game ends. Bombs that hit the player, or reach the bottom of the screen, are added to a list of bombs to be removed, and are removed one by one. This needs to be done from a separate list, as the game will crash otherwise.

- checkSize(): This basically locates the left and the right side of the alien fleet. It will determine the visible alien that is most to the left, and most to the right. This is used to determine when the aliens move down and switch direction.

- drawMissile(): This method manages the missle. It will detect whether the missile has collided either either an alien, or a mothership. If it has, it will remove the alien/mothership and increase the player's score. Otherwise it will move the missile up one step. If the missile reaches the top of the screen, the missle is removed.

- drawBomb(): This method moves the bomb one step, and draws it.

- drawExplosion(): The method that handles the explosion. It also determines whether the explosion involves the tank, and pauses the game if that is the case. This is to prevent the tank from being hit by multiple missiles.

- stopMove(): This method sets the boolean that will prevent the tank from moving any further.

- moveHorizontal(): This method sets the value of the keypress, and allows the tank to move.

- fireMissile(): The method that checks to see if the missle is on the screen, and if it is, prevents another missile from appearing, otherwise it will create a missile.

- dropBomb(): creates a new bomb object, and adds it to a list of the bombs.

*3.GameStatusBar*
This method only contains the constructor, and it manages the status bar at the top of the screen. It lists the level, the score, and the number of lives the player has remaining. It takes the player object to obtain the player related information. The details aren't stored here as it is refreshed whenever the frame is refreshed.

*4.GameOverPopup*
This class contains only a constructor, and it generates the game over popup when the player runs out of lives. The popup displays the game over text, and asks the player whether they wish to play again. The class creates a centre label which holds the text that asks the player if they wish to play again, and a bar at the bottom which holds the yes and no buttons.

*5.ButtonBar*
This class forms a part of the GameOverPopup and displays two buttons at the bottom. The buttons are 'Yes' and 'No'. The yes button triggers a new game event, which restarts the game, and the no button triggers an event that brings the game to an end.

*6.AlienImage*
This class handles all of the aliens, including the mothership. The class sets the position of the alien, and it also moves the alien, as well as storing the score that is recieved when they alien is destroyed. The variables include visibility, which says whether the alien is alive (true) or dead (false). It also has a boolean which tells whether the alien is moving down, how far it moves down, and the positioning of the edge.

- AlienImage(): This is the construction and it takes the alien's initial position, both horizontal and vertical, as well as the type of alien. It sets the alien based on the type, and also sets its position, based on whether it is an alien, or a mothership

- setEdges(): This method sets the left and right edge of the alien.

- setLeftSide(): This method sets the left side of the fleet.

- setRightSide(): This method sets the right side of the fleet.

- getRightEdge(): This method returns the right edge of the alien.

- getLeftEdge(): This method returns the left edge of the alien.

- getTopEdge(): The top edge of the alien is returned.

- getBottomEdge(): The bottom of the alien is returned.

- getScore(): The value of the alien in points is returned. This is called when the alien is killed.

- getMovingDown(): This returns the boolean to tell the program whether the alien is moving down or not.

- getVisibility(): The visibility status of the alien is returned.

- setVisibility(): The visibility status of the alien is set. This is basically called when the alien is destroyed.

- getAlienImageVersion(): The image version of the alien is returned.

- alienDirection(): This method handles the movement of the alien. First it detects whether it has reached the edge of the screen, and if it has then it moves down. However, if it is moving down, it will determine if it has moved down the appropriate amount, and the send it moving in the opposite direction. Otherwise, it simply moves the alien in the direction that it is heading, at the speed that it is heading.

- checkDirection(): This method checks the direction that the alien is moving, and moves the alien in that direction.

- drawAlien(): This method draws the alien that the position that has been determined. It sets the colour, draws the outline, and the eyes. Once it has been drawn, it them moves it.

- checkDrop(): This method simply determines whether the alien has dropped a missile, and it calls the method that determines this randomly.

**Model**
Where the view package handles all the graphics, the model handles the aspects of the items that are not used in the view. The move functions are handled here, as this information is a part of the model, as opposed to the view (though there are some view aspects to the movement.)

*1.Player*
This class handles all of the logic for the player. Mind you, it really only has the score, lives, and level at this stage, though this could change in version 2. The lives start at 3, the score at 0, and the level at 1.

- setScore(): This method increases the score by the amount that is passed through to it.

- takeLife(): This method reduces the player's lives by 1.

- getScore(): The current score is returned

- getLevel(): The current level is returned.

- getLives(): The number of lives that the player has is returned.

*2.Tank*
Okay, my teacher will have an absolute fit when they see this class, which handles the tank. Instead of creating a bunch of setters and getters (though apparently you can do that automatically with Eclipse, I'm still too lazy, though I have put this in as a todo). The variables that handle the position of the tank. The variables that have been set is the starting position, as well as the size. It also sets whether the tank is moving or not.

- move(): This method changes the varibles that handle the tank's position, and moves it the value of the step.

30 Sept 2021 - changed method to make variables private and added getters & setters.

*3.Alien*
Like the tank class above, this class defines the size, and the starting location, of the aliens. However, since there are multiple aliens, a number of the scores are static, meaning that they apply to *all* the objects that are generated. These variables apply to the edges of the fleet, as well as the speed that the fleet moves across the screen. There are also variables that apply to the position of the alien within the fleet.

- createAlien(): while this should be a constructor, some issues with Java, and the variables that are passed through to it, cause problems, so a method was used instead. This sets up the position of the alien, as well as advising the number of points that make up the corners of the alien's position.

- changeHorizontalPosition(): this function goes through all of the variables that marks the horizontal position of the alien, and moves them a number of positions as defined by the move variable which is passed through.

- changeVerticalPosition(): this function goes through all of the variables that mark the alien's veritcal position, and moves them a number of positions as defined by the variable that has been passed through to it.

- moveAlien(): This function moves the alien, and takes the step, which is the number of spaces that it moves, and a boolean that tells it whether it is moving down or not.

- checkMissileDrop(): this generates a random number to determine whether the alien drops a bomb or not.

The rest are setters and getters, however some of them I have been a bit lazy with in that since all of them are the same, a variable is passed through to determine which value is passed back.

*4.AlienTopOne/AlienTopTwo*
These are subclasses of the alien and really only define their appearance. These were created so as to have some movement as the aliens make their way across the screen. The only difference are the variables that define the way the alien looks. Each of the methods mimic the methods of the superclass (Alien), and basically call the superclass, or they just do what the superclass does.

- AlienTopOne/AlienTopTwo: this is the constructor, and it calls the createAlien() method in the superclass.

*5.MotherShip*
This is another subclass of Alien, except this one builds the mothership, which travels horizontally across the screen. The class is made up of getters and setters for the positioning and movement of the alien. However, it has one specific class which moves it. The variables that are defined in the class are used to build the frame of the alien.

- MotherShip() - this is the constructor that passes that variables of the mothership through to the superclass that creates the object.

- changeVerticlePosition() - this changes the position of the mothership icon so that it moves from the left side of the screen to the right.

- moveAlien() - this mimics a method in the superclass, and calls the changeVerticlePosition() method. It takes two variables, the step, and a boolean indicating whether it is moving down or not. Since the mothership does not move down, this is not used.

*6. Explosion*
This is the basis for the explosion that is drawn whenever an alien is hit, or when the tank is hit. It sets the boundaries, and the initial size of the image.

- Explosion(): this is the constructor

- Expand(): This method enlarges the image, but it also counts the timer up so that when the timer reaches a certain point, the image is removed from the screen.

*7. Missile*
This class handles the missile/bomb. The missile is fired by the player, the bomb is dropped by the alien. It sets the size of the missile, and has two variales which are used to define the starting position 

- Missile(): This is the constructor where the starting position of the missle is passed through and given to the positional variables.

- move(): this method takes the step, which sets how fast it moves. It will change the position of the missile by the step. This moves the missile up the screen, and is used by the player.

- drop(): this method does the opposite of the move() method.

**Controller**

*1. MoveTank*
This implements a keylistener and listens for key presses, and acts on the tank when a specific key is pressed. It takes the tank panel as a variable.

- MoveTank(): this is the constructor, and stores the tankpanel, which it acts upon.

- keyPressed(): this reads a keypress, and if it responds to the correct key, will move the tank. This acts as long as the key is held down. It calles the moveHorizontal() method on the tank panel.

- keyReleased(): this checks the key that has been released, and if it corresponds, it stops the tank from moving. It calls the stopMove() method on the tank panel.

- keyTyped(): this checks to see if the key that is pressed if the spacebar, and if it is it calls the fireMissile() method on the tabk panel.

*2. NewGameEvent*





