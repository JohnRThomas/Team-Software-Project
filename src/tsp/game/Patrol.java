package tsp.game;

import java.awt.Point;

import imageBase.BaseImage;

public class Patrol extends Enemy{

	int patrolStart = 0;
	int patrolEnd = 200;
	int speed = 2;
	int direction = 1;
	int YBound = 420;
	
	public Patrol(int xStart, int yStart, int d, int h, int g,
			String[] imageName, boolean collide) {
		super(xStart, yStart, d, h, g, imageName, collide);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collideWith(BaseImage entity) {
		if(entity instanceof Player){
			attack(entity);
		}else if(entity instanceof Enemy){
			
		}
	}

	@Override
	public void move(Point playerCenter) {
		gravity();
		setX(getX() + speed*direction);
		if (getX() >= patrolEnd) direction = -1;
		else if (getX() <= patrolStart) direction = 1;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(BaseImage entity) {
		((Player)entity).takeDamage(damage);
	}

	@Override
	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0) die();
//		TODO add in the take damage animation if the enemy doesn't die
	}
	
	private void gravity(){
		setY(getY()-gravity); // fall according to gravity

		if (getY() < 10) { // if at top of screen
			setY(10); // reset position
			gravity = -1; // reset gravity
		}

		// TODO Can't have just one static YBound, since there could be many platforms
		if(getY() + getHeight() >= YBound) { // if at bottom of screen
			setY(YBound - getHeight()); // reset position
			gravity = 0; // reset gravity
		}

		if (gravity > -10) { // if not at terminal velocity
			gravity = gravity - 1; // increase fall rate
		}
	}

}
