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
	public Color color;
	public int hitTimer;
	public int width;
	public int height;
	public int speed;
	
	public Player(int x, int y, int width, int height, int gravity, int health, int speed, int hitTimer, Color color) {
		super(x, y, "res/images/player.png", true);
		
		this.gravity = gravity;
		this.health = health;
		this.speed = speed;	
		this.hitTimer = hitTimer;
		this.color = color;
	}

	@Override
	public void collideWith(BaseImage entity) {
		// TODO Auto-generated method stub
		
	}
	
}


