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
	private String enemyImages[];
	private int usedImage = 0;
	public Enemy(int xStart, int yStart, int d, int h, int g, String imageName[], boolean collide) {
		super(xStart, yStart, imageName[0], collide);
		enemyImages = imageName;
		damage = d ;
		health = h ;
		gravity = g ;
	}

	public void incrementImage(){

		usedImage+=1;
		if(usedImage >= enemyImages.length){
			usedImage =0;
		}
		super.setImage(enemyImages[usedImage]);
	}
	
	@Override
	public void collideWith(BaseImage entity) {
		if(entity instanceof Player){
			((Player)entity).takeDamage(damage);
		}else if(entity instanceof Enemy){
			
		}
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
