package tsp.game;

import imageBase.BaseImage;

import java.awt.Point;

import tsp.gui.Canvas;

public class Jumper extends Enemy{

	private int jumpTimer = 120;
	private int countdown = jumpTimer;
	private int horizontalVelocity = 0;
	private int gravWait = 3;
	private int gravCounter = gravWait;
//	TODO find a way to deliver the locations of platforms to the Jumper
//	private Canvas canvas;
	
	public Jumper(int xStart, int yStart, int d, int h, int g,
			String[] imageName, boolean collide) {
		super(xStart, yStart, d, h, -1, imageName, collide);
		// TODO Auto-generated constructor stub
	}

//	public void setCanvas(Canvas canvas){
//		this.canvas = canvas;
//	}
	
	@Override
	public void collideWith(BaseImage entity) {
		if(entity instanceof Player){
			attack(entity);
		}
		else if(entity instanceof Background){
			collide(this, entity);
		}
		else if(entity instanceof Enemy){
			attack(entity);
		}
	}

	@Override
	public void move(Point playerCenter) {
		if (countdown > 0){
			countdown -= 1;
			setX(getX() + horizontalVelocity);
		}
		else {
			countdown = jumpTimer;
			gravity = 10;
			horizontalVelocity = (playerCenter.x-(getX()+getWidth()/2))/70;
		}
		gravity();
		
	}

	@Override
	public void attack(BaseImage entity) {
		if (entity instanceof Player){
			((Player)entity).takeDamage(damage);
		}
		else if (entity instanceof Background){
			collide(this, entity);
		}
		else if (entity instanceof Enemy){
			((Enemy)entity).takeDamage(damage);
		}
		
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
			horizontalVelocity = 0;
		}

		if (bottom < top && bottom < left && bottom < right) {
			//bottom is closest side
			thisEnemy.setY(entity.getY() + entity.getHeight());
			((Enemy) thisEnemy).setGravity(-1);
		}

		if (left < top && left < right && left < bottom) {
			//left is closest side
			thisEnemy.setX(entity.getX() - thisEnemy.getWidth());
		}

		if (right < top && right < left && right < bottom) {
			//right is closest side
			thisEnemy.setX(entity.getX() + entity.getWidth());
		}
	}

	@Override
	public void die() {
		System.out.print("THE ENEMY HAS DIED");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int damage) {
		health -= damage/2;
		if (health < 0) die();
//		TODO add in the take damage animation if the enemy doesn't die
	}
	
	private void gravity(){
		setY(getY()-gravity); // fall according to gravity

		if (getY() < 10) { // if at top of screen
			setY(10); // reset position
			gravity = -1; // reset gravity
		}

		if (gravity > -10) { // if not at terminal velocity
			if (gravCounter > 0){
				gravCounter -= 1;
			}
			else {
				gravCounter = gravWait;
				gravity = gravity - 1; // increase fall rate
			}
		}
	}

}
