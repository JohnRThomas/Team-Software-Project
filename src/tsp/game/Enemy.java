package tsp.game;

import imageBase.BaseImage;


/**
 * Basic Enemy class that can be extended and stuff
 *
 */
public class Enemy extends BaseImage {
	private int damage ;
	private int health ;
	private int gravity ;

	public Enemy(int xStart, int yStart, int d, int h, int g, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);
		damage = d ;
		health = h ;
		gravity = g ;
	}

	/**
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage ;
	}

	/**
	 * 
	 * @param d
	 * @return the new damage d
	 */
	public int setDamage(int d) {
		return damage = d;
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

	@Override
	public void collideWith(BaseImage entity) {
		/*if(entity instanceof Player){
			//TODO collide with player
		}else */if(entity instanceof Enemy){
			
		}
	}

	/**
	 * 
	 * @return gravity
	 */
	public int getGravity() {
		return gravity ;
	}

	/**
	 * 
	 * @param g
	 * @return the new gravity g
	 */
	public int setGravity(int g) {
		return gravity = g;
	}
}
