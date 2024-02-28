package karel.farmProject;

import karel.UberBot;

public class Coop extends UberBot{
	
	/**
	 * pre: Coop is an uberBot at coop shed (19,19) facing South with no beepers in beeper bag
	 * 
	 * post: After delivering all the specified amount of seed from the seed bin and collecting all the
	 * harvest from the delivery location, Coop is back at its Coop shed with no beepers in beeper bag
	 * All harvest is in harvest bin.
	 * 
	 * 
	 */
	
	public Coop() {
		super(19, 19, South, 0);
	}
	
	/**
	 * coop picks beeper in accordance to specified nSeeds
	 * @param nSeeds the amount of seeds ordered to pick up from Seed bin
	 */
	public void pickBeeper(int nSeeds) {
		for(int i=0; i<nSeeds; i++) {
			pickBeeper(); // picks specified amount of seed
		}
	}
	
	/**
	 * pre: In coop shed facing south with no beepers in beeper bag
	 * 
	 * post: Delivers the nSeeds to drop off (1,1), and then returns to Coop shed 
	 * 
	 * @param nSeeds specified amount of seeds
	 */
	public void deliverSeed(int nSeeds) {
		moveToSeedBin();
		
		pickBeeper(nSeeds);
		
		goToOrigin(); // drop off location (1,1)
		putAllBeepers();
		park();
	}
	
	/**
	 * pre: In coop shed with no beepers in beeper bag
	 * 
	 * post: Picks up harvest from  drop off location and drops all of the harvest in harvest bin
	 * Then returns to coop shed without any beepers in beeper bag.
	 */
	
	public void pickUpHarvest() {
		goToOrigin();
		pickAllBeepers();
		park();
		
		moveToHarvestBin();
		putAllBeepers();
		
		moveToCoopShed();
	}
	
	/**
	 * pre: In coop shed with no beepers in beeper bag
	 * 
	 * post: Picks up unused seed from drop off location and drops all of the unused seed back in seed bin
	 * Then returns to coop shed without any beepers in beeper bag.
	 */
	
	public void pickUpSeed() {
		goToOrigin();
		
		pickAllBeepers();
		
		park();
		
		moveToSeedBin();
		putAllBeepers();
		
		turnSouth();
		move();
		turnEast();
		move();
		turnNorth();
		move();
		turnAround(); // back in coop shed facing south
	}
	/**
	 * Coop goes to origin (1,1) or also known as delivery location
	 */
	public void goToOrigin() {
		turnSouth();
		moveToWall();
		turnWest();
		moveToWall();
	}
	/**
	 * Coop goes to seed bin (19,18)
	 */
	private void moveToSeedBin() {
		turnSouth();
		move();
		turnWest();
		move();
		turnNorth();
		move();
	}
	/**
	 * Coop goes to coop shed (19,19)
	 */
	private void moveToCoopShed() {
		turnAround();
		move();
		turnLeft();
		move(2);
		turnNorth();
		move();
		turnAround();
	}
	/**
	 * Coop goes to harvest bin (19,17)
	 */
	private void moveToHarvestBin() {
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
	}
	/**
	 * Coop returns to shed (19,19)
	 */
	private void park() {
		turnAround();
		move(18);
		turnLeft();
		moveToWall();
		turnAround();	
	}
}


