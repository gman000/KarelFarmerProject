package karel.farmProject;

import kareltherobot.Robot;
import kareltherobot.World;


/**
 * 
 * pre: Farmer is a Robot which is located at (4, 1), facing North without Beepers and turned on
 * 
 * post: Farmer is back in shed (4,1) facing North without Beepers and turned off after ordering its team
 * to plant, harvest and coop the crops. All harvested beepers are in harvest bin (19,17)
 * 
 * @author gnir
 *
 *
 * 
 *
 */

public class Farmer extends Robot{

	private Planter Planter;
	private Harvester Harvester;
	private Coop Coop;
	
	/**
	 * class constructor 
	 */
	
	public Farmer() {
		super(4, 1, North, 0);
		
		World.readWorld("Farm.kwld");
		World.setVisible();
		World.setDelay(3);
		
		Planter = new Planter();
		Harvester = new Harvester();
		Coop = new Coop();
	}
	/**
	 * pre: coop is an uberBot at 19,19 facing South without Beepers  in beeper bag
	 * post: coop delivered nSeeds to 1,1 and returns to 19,19 facing South without Beepers in beeper bag
	 * @param nSeeds the amount of seeds specified 
	 */
	public void orderSeed(int nSeeds) {
		Coop.deliverSeed(nSeeds);
	}
	
	/**
	 * pre: Planter is an uberBot in planter shed (2,1) with no beepers in beeper bag
	 * 
	 * post: Planter is back in planter shed with seeds planted in accordance to specifications
	 * If randSeedsON is true, planter would take excess seeds to drop off location and then return to shed
	 * 
	 * 
	 * @param length length of beepers in farm 
	 * @param width  width of beepers in farm
	 * @param randSeedsON a boolean based on whether the planter should plant random number of seeds
	 */
	
	public void plantField(int length, int width, boolean randSeedsON) {
		Planter.plantField(length, width, randSeedsON);
	}
	
	public void plantField(int length, int width) {
		plantField(length, width, false);
	}
	
	public void plantField() {
		plantField(5, 5);
	}
	
	/**
	 * pre: Harvester is in harvester shed (3,1) with no beepers in beeper bag
	 * 
	 * post: Harvester picks up all the seeds (beepers) on the specified width and length farm. 
	 * Those seeds are put down in drop off location (1,1).
	 * Harvester then returns back to shed without any beepers in beeper bag
	 * 
	 * @param length length of beepers in farm
	 * @param width width of beepers in farm
	 */
	
	public void harvestField(int length, int width) {
		Harvester.harvestField(length, width);
	}
	
	public void harvestField() {
		harvestField(5, 5);
	}
	
	/**
	 * pre: coop in coop shed (19,19) without any beepers in beeper bag
	 * 
	 * post: coop picks up all harvested seeds (beepers) and drops them off in harvest bin (19, 17)
	 * Coop then returns to shed without any beepers in beeper bag.
	 */
	
	public void pickUpHarvest() {
		Coop.pickUpHarvest();
	}
	
	private void pickUpSeed() {
		Coop.pickUpSeed();
	}
	
	/**
	 * farmer returns home facing North 
	 * @param length
	 * @param width
	 */
	
	public void goHome(int length,int width) {
		turnAround();
		move(width/2);
		turnLeft();
		move(length/2);
		turnAround();
		move(3);
		turnLeft();
		moveToWall();
		turnLeft();
		move(2);
		turnAround();
	}
	
	private void move(int nSteps) {
		for(int i=0;i<nSteps;i++) { // specified amount of Steps
			super.move();
		}
	}
	
	private void moveToWall() {
		if(frontIsClear()) {
			move();
			moveToWall();
		}
	}
	
	private void turnAround() {
		turnLeft();
		turnLeft();
	}
	
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	/**
	 * pre: farmer,coop,planter and harvester parked
	 * 
	 * post: harvest is collected and placed in harvest bin after farm of seeds was already planted and 
	 * harvested. All workers return to sheds and farmer back to its house
	 * @param length
	 * @param width
	 * @param randSeedsON
	 */
	
	public void goFarm(int length, int width, boolean randSeedsON){
		int nSeeds = length * width;
		
		if(randSeedsON) {
			orderSeed(nSeeds*=5);
		}
		else {
			orderSeed(nSeeds);
		}
		
		moveToCenter(length, width);
		plantField(length, width, randSeedsON);
		pickUpSeed();
		
		harvestField(length, width);
		
		goHome(length,width);
		
		pickUpHarvest();
	}

	/**
	 * Coop collects the unused seed that planter didn't plant
	 * @param length
	 * @param width
	 */

	public void goFarm(int length, int width) {
		goFarm(length, width, false);
	}
	
	public void goFarm() {
		goFarm(5, 5, false);
	}
	
	/**
	 * moves to Center Field
	 * @param length
	 * @param width
	 */
	
	private void moveToCenter(int length, int width) {
		move(2);
		turnRight();
		move(2);
		turnRight();
		moveToWall();
		
		turnAround();
		move(length/2);
		turnRight();
		move(width/2);
		
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Farmer jim = new Farmer();
		
		// C Level 5x5 farm with a beeper at each location
		jim.goFarm();
		
		// B level LxW farm with a beeper at each location
//		jim.goFarm(3, 3);
		
		// A level LxW farm with random number of beepers between 0-5 at each location
//		jim.goFarm(7,3, true);
		
		jim.turnOff();
	}

}
