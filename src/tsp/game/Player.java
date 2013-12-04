package tsp.game;


/**
 * Basic Player Class
 *		Needs more variables and stuff
 */

import imageBase.BaseImage;

import java.awt.Color;

public class Player extends BaseImage {
	public int gravity;
	public Integer health;
	public int hitTimer;
	public int width;
	public int height;
	public int speed;
	public int jumpMax;
	public int jumpCount;
	

	public Player(int x, int y, int width, int height, int gravity, int health, int speed, int hitTimer, int jumpCount, int jumpMax) {

		super(x, y, "res/images/player0.png", true);
		
		this.gravity = gravity;
		this.health = health;
		this.speed = speed;	
		this.hitTimer = hitTimer;
		this.jumpCount = jumpCount;
		this.jumpMax = jumpMax;
	}

	@Override
	public void collideWith(BaseImage entity) {
		// TODO Auto-generated method stub
		
	}
	
	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}


	
}


