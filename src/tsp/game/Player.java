package tsp.game;

/**
 * Basic Player Class
 *		Needs more variables and stuff
 */
public class Player {
	public int x ;
	public int y ;
	public int width ;
	public int height ;
	public int gravity ;
	public int speed ;
	public int health ;
	
	public Player(int x, int y, int width, int height, int gravity, int speed) {
		this.x = x ;
		this.y = y ;
		this.width = width ;
		this.height = height ;
		this.gravity = gravity ;
		this.speed = speed ;
	}
}
