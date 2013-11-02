package tsp.game;


/**
 * Basic Player Class
 *		Needs more variables and stuff
 */

import java.awt.Color;

public class Player {
	public int x;
	public int y;
	public int gravity;
	public Integer health;
	public Color color;
	public int hitTimer;
	public int width;
	public int height;
	public int speed;
	
	public Player(int x, int y, int width, int height, int gravity, int health, int speed, int hitTimer, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.health = health;
		this.speed = speed;	
		this.hitTimer = hitTimer;
		this.color = color;
	}

}


