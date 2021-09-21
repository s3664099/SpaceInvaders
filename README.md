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

![Screenshot](https://raw.githubusercontent.com/s3664099/SpaceInvaders/img/ScreenShot.png)

## Upgrades

*Version 1*
- Document Code
- Next Levels Difficulty
- Change Icons

*Version 2*
- Power Ups
- Different Alien Weapons
- Dive Bombing Aliens

## Notes

## Code Walkthrough

### Classes

**Start**

*1.Start*

**controller**
