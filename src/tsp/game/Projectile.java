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

	public Projectile(int xStart, int yStart, int xSpeed, int ySpeed, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);
		
		this.xSpeed = xSpeed ;
		this.ySpeed = ySpeed ;
	}

	@Override
	public void collideWith(BaseImage entity) {
		// TODO Auto-generated method stub
		
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
	 * @param d
	 * @return the new y speed
	 */
	public int setYSpeed(int y) {
		return ySpeed = y;
	}

}
