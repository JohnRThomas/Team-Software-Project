package tsp.game;


/**
 * Basic Player Class
 *		Needs more variables and stuff
 */

import imageBase.BaseImage;

import java.awt.Color;

public class Player extends BaseImage {
	public int gravity;
	public Integer currentHealth;
	public Integer maxHealth;
	public Color color;
	public int hitTimer;
	public int width;
	public int height;
	public int speed;
	public int regenSpeed = 60;
	private int regenCounter = 0;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gravity
	 * @param health
	 * @param max
	 * @param speed
	 * @param hitTimer
	 * @param color
	 */
	public Player(int x, int y, int width, int height, int gravity, int health, int max, int speed, int hitTimer, Color color) {
		super(x, y, "res/images/player.jpg", true);
		
		this.gravity = gravity;
		this.currentHealth = health;
		this.maxHealth = max;
		this.speed = speed;	
		this.hitTimer = hitTimer;
		this.color = color;
	}

	@Override
	public void collideWith(BaseImage entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void takeDamage(Integer damageTaken){
		if (hitTimer == 0){
			currentHealth -= damageTaken;
			hitTimer = 60;
		}
		else return;
	}
	
	public void regenerate(){
		if (currentHealth < maxHealth){
			regenCounter += 1;
		}
		if (regenCounter == regenSpeed){
			currentHealth += 1;
			regenCounter = 0;
		}
	}
	
}


