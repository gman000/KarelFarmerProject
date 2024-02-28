package karel.farmProject;

import karel.UberBot;

public class Planter extends UberBot{

	/**
	 * pre: planter is an uberBot located at planter shed (2,1) facing east with no beepers in beeper bag
	 * 
	 * post: planter plants seeds in specified length and width farm and drops excess seeds in drop off bin
	 * planter then returns to planter shed with no beepers in beeper bag
	 */
	
	public Planter() {
		super(2, 1, East, 0);
	}
	
	/**
	 * Planter moves to pick up (1,1) and picks all seeds
	 */
	
	public void pickupSeed() {
		moveToPickUp();
		pickAllBeepers();
		turnEast();
	}
	
	/**
	 * 
	 * pre: Planter is in planter shed with zero beepers in beeper bag
	 * 
	 * post: Planter picks up seed, and plants them in specified length and width farm
	 * then, planter drops off excess seed in drop off (1,1) and returns to planter shed
	 * 
	 * @param length length of field
	 * @param width width of field
	 * @param randSeedsON a boolean which determines whether planter should plant random num seeds between 
	 * 1-5 or simply 1 seed
	 */
	
	public void plantField(int length, int width, boolean randSeedsON) {
		pickupSeed();
		
		moveToPlant();
		
		for(int i=0; i<length; i++) {
			plantRow(width, randSeedsON);
			transition();
		
		}
		park();

	}
	
	public void plantField() {
		plantField(5, 5, false);
	}

	/**
	 * plants a single row of beepers depending on width and the amount of beepers depends on randSeedsON.
	 * @param width width of field
	 * @param randSeedsON a boolean which determines whether planter should plant random num seeds between 
	 * 1-5 or simply 1 seed
	 */
	
	private void plantRow(int width, boolean randSeedsON) {
		for(int i=0; i<width; i++) {
			plant(randSeedsON);
			move();
		}
		turnAround();
		move();
		turnAround();
	}
	
	/**
	 * plants either a single seed or a random number between 1-5 depending on whether randSeedsON = true
	 * 
	 * @param randSeedsON a boolean which determines whether planter should plant random num seeds between 
	 * 1-5 or simply 1 seed
	 */
	private void plant(boolean randSeedsON) {
		if(randSeedsON) {
			// random int is between 0-5
			int myRandInt = (int)(Math.random() * 6); //selects random number of seeds to plant at given location
			putBeeper(myRandInt); //plants the random number of seeds
		}
		else {
			putBeeper(); 
		}
	}


	/**
	 * Planter moves to origin  /  pick up (1,1)
	 */
	private void moveToPickUp() {
		move();
		goToOrigin();
	}
	/**
	 * Planter moves to place where it should start planting (fieldcorner) (3,3)
	 */
	private void moveToPlant() {
		move();
		turnNorth();
		move(3);
		turnEast();
		move();
		turnSouth();
		move();
		turnEast();
	}
	/**
	 * transitions planter from one row to next by moving up one
	 */
	private void transition() {
		if(facingEast()) {
			moveLt();
			turnAround();
		}
		else {
			moveRt();
			turnAround();
			
		}
	}
	/**
	 * planter moves back to planter shed facing east
	 */
	private void park() {
		turnEast();
		move();
		
		goToOrigin();
		putAllBeepers();
		turnEast();
		
		move();
		turnNorth();
		move();
		turnWest();
		move();
		turnEast();
	}
	
}

