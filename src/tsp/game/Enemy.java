package tsp.game;

import imageBase.BaseImage;


/**
 * Basic Enemy class that can be extended and stuff
 *
 */
public class Enemy extends BaseImage {
	private int health ;
	
	public Enemy(int xStart, int yStart, int h, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);
		health = h ;
	}
	
	/**
	 * 
	 * @return health
	 */
	public int getHealth() {
		return health ;
	}
	
	/**
	 * 
	 * @param h
	 * @return the new health h
	 */
	public int setHealth(int h) {
		return health = h;
	}
}
