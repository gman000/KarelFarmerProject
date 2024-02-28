package karel.farmProject;

import karel.UberBot;

public class Harvester extends UberBot{

	/**
	 * pre: Harvester is an UberBot located at (3,1) facing East with no beepers in beeper bag
	 * 
	 * post: Harvester harvests all seeds on farm and carries it to the drop off (1,1). It then returns to 
	 * its shed (3,1) with no beepers in beeper bag
	 */
	
	public Harvester() {
		super(3, 1, East, 0);
	}
	
	/**
	 * pre: field has a specific length and width filled with beepers (ranging from 1 - 5 on each spot) 
	 * 
	 * post: all beepers on field are brought to drop off location
	 * @param length
	 * @param width
	 */
	
	public void harvestField(int length, int width) {
		moveToHarvest();
		
		for(int i = 0; i<length; i++) {
			harvestRow(width);
			transition();
		}
		takeToDelivery();
		park();
	}
	
	public void harvestField() {
		harvestField(5, 5);
	}
	/**
	 * harvests row according to length
	 * @param length specified length of farm
	 */
	private void harvestRow(int length) { 
		for(int i=0; i<length; i++) {
			pickAllBeepers();
			move();
		}
		turnAround();
		move();
		turnAround();
	}
	/**
	 * moves harvester to position where it should start harvesting (fieldcorner)
	 */
	private void moveToHarvest() { 
		turnEast();
		moveToWall();
		turnNorth();
		move();
		turnRight();
		move();
		turnSouth();
		move();
		turnEast();
	}

	/**
	 * harvester drops all beepers in delivery location
	 */
	private void takeToDelivery() {
		turnEast();
		move();
		turnSouth();
		moveToWall();
		turnWest();
		moveToWall();
		
		putAllBeepers();
	}
	
	/**
	 * transitions harvester to be ready for next row plant
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
	 * Harvester returns from delivery location to the Harvester Shed facing East
	 */
	
	public void park() { 
		turnEast();
		move();
		turnNorth();
		move(2);
		turnWest();
		move();
		turnEast();
	}
}
	/**
	 * @param args
	 */


