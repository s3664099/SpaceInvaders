package Model;

public class Player {
	
	//sets the lives and the score for the players and the leve
	private int lives = 3;
	private int score = 0;
	private int level = 1;
	
	//increases the player score by a set amount
	public void setScore(int add) {

		score+=add;
	}
	
	//reduces the number of lives by one
	public void takeLife() {
		lives --;
	}
	
	//gets the player's total score
	public int getScore() {
		return score;
	}
	
	//gets the level that the player is on
	public int getLevel() {
		return level;
	}

	//gets the number of lives
	public int getLives() {
		return lives;
	}

}
