package tsp.game;

import imageBase.BaseImage;

/**
 * Basic class for projectiles like lasers! and stuff
 * There is an x and y coordinate speeds in case we want to do that
 * So far probably just get x movement working
 *
 */
public class Projectile extends BaseImage {
	private int xSpeed ;
	private int ySpeed ;
	private boolean hit ;

	public Projectile(int xStart, int yStart, int xSpeed, int ySpeed, boolean hit, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);

		this.xSpeed = xSpeed ;
		this.ySpeed = ySpeed ;
		this.hit = hit ;
	}

	@Override
	public void collideWith(BaseImage entity) {
		if(entity instanceof Enemy) {
			((Enemy)entity).setHealth(((Enemy)entity).getHealth() - 1) ;
			this.setHit(true) ;
			//System.out.println(((Enemy)entity).getHealth());
		}
	}

	/**
	 * 
	 * @return x speed
	 */
	public int getXSpeed() {
		return xSpeed;
	}

	/**
	 * 
	 * @param x
	 * @return the new x speed
	 */
	public int setXSpeed(int x) {
		return xSpeed = x;
	}

	/**
	 * 
	 * @return y speed
	 */
	public int getYSpeed() {
		return ySpeed;
	}

	/**
	 * 
	 * @param y
	 * @return the new y speed
	 */
	public int setYSpeed(int y) {
		return ySpeed = y;
	}

	/**
	 * 
	 * @return if the projectile hit an enemy
	 */
	public boolean getHit() {
		return hit;
	}

	/**
	 * 
	 * @param h
	 * @return the new value of hit
	 */
	public boolean setHit(boolean h) {
		return hit = h;
	}

}
