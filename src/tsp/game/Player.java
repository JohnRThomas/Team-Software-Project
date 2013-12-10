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
	public int speed;
	public int regenSpeed = 30;
	private int regenCounter = 0;
	public int jumpMax;
	public int jumpCount;
	public int victoryCount;
	public int deathCount;
	public String name;
	public boolean gameEnd =false;
	public int oldX=0;
	public int oldY=0;
	public String nameAnimations[];
	public int usedAnimations =0;
	public int totalAnimations =0;
	
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
	

	public Player(int x, int y, int gravity, int health, int max, int speed, int hitTimer, int jumpCount,
			int jumpMax, int victoryCount, int deathCount, String name,String names[]) {

		super(x, y, names[0], true);
		this.nameAnimations =names;
		this.gravity = gravity;
		this.currentHealth = health;
		this.maxHealth = max;
		this.speed = speed;	
		this.hitTimer = hitTimer;
		this.jumpCount = jumpCount;
		this.jumpMax = jumpMax;
		this.victoryCount = victoryCount;
		this.deathCount = deathCount;
		totalAnimations = names.length;
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

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}


	/**
	 * @return old X position
	 */
	public int getOldX(){
		return oldX;
	}
	/**
	 * @return old y position
	 */
	public int getOldY(){
		return oldY;
	}
	/**
	 * @param int y: new y position
	 * @return y position
	 */
	public int setY(int y){
		oldY = super.getY();
		return super.setY(y);
	}
	/**y+50
	 * @param int x: new x position
	 * @return x position
	 */
	public int setX(int x){
		oldX = super.getX();
		return super.setX(x);
		
	}
	public void incrementImage(){

		usedAnimations+=1;
		if(usedAnimations >= totalAnimations){
			usedAnimations =0;
		}
		super.setImage(nameAnimations[usedAnimations]);
	}
	
}


