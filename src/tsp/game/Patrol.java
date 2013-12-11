package tsp.game;

import java.awt.Point;

import imageBase.BaseImage;

public class Patrol extends Enemy{

	int patrolStart = 0;
	int patrolEnd = 1000000;
	int speed = 2;
	int direction = 1;
	
	public Patrol(int xStart, int yStart, int d, int h, int g,
			String[] imageName, boolean collide) {
		super(xStart, yStart, d, h, g, imageName, collide);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collideWith(BaseImage entity) {
		if(entity instanceof Player){
			attack(entity);
		}
		else if(entity instanceof Background){
			collide(this, entity);
		}
		else if(entity instanceof Enemy){
			
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
	
	private void collide(Enemy thisEnemy, BaseImage entity) {
		int top = 9999;
		int left = 9999;
		int right = 9999;
		int bottom = 9999;

		// top collision
		top = Math.abs(thisEnemy.getY() + thisEnemy.getHeight() - entity.getY());

		// bottom collision
		bottom = Math.abs(thisEnemy.getY() - entity.getY() - entity.getHeight());	

		// left collision
		left = Math.abs(thisEnemy.getX() + thisEnemy.getWidth() - entity.getX());

		// right collision
		right = Math.abs(thisEnemy.getX() - entity.getX() - entity.getWidth());



		if (top < left && top < right && top < bottom) {
			//top is closest side
			thisEnemy.setY(entity.getY() - thisEnemy.getHeight());
			((Enemy) thisEnemy).setGravity(-1);
		}

		if (bottom < top && bottom < left && bottom < right) {
			//bottom is closest side
			thisEnemy.setY(entity.getY() + entity.getHeight());
			((Enemy) thisEnemy).setGravity(-1);
		}

		if (left < top && left < right && left < bottom) {
			//left is closest side
			thisEnemy.setX(entity.getX() - thisEnemy.getWidth());
			direction *= -1;
		}

		if (right < top && right < left && right < bottom) {
			//right is closest side
			thisEnemy.setX(entity.getX() + entity.getWidth());
			direction *= -1;
		}
	}
	
	private void gravity(){
		setY(getY()-gravity); // fall according to gravity

		if (getY() < 10) { // if at top of screen
			setY(10); // reset position
			gravity = -1; // reset gravity
		}

		if (gravity > -10) { // if not at terminal velocity
			gravity = gravity - 1; // increase fall rate
		}
	}

}
