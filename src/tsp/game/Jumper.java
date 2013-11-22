package tsp.game;

import imageBase.BaseImage;

import java.awt.Point;

import tsp.gui.Canvas;

public class Jumper extends Enemy{

	private int jumpTimer = 120;
	private int countdown = jumpTimer;
	private int YBound = 420;
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
		}else if(entity instanceof Enemy){
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
			horizontalVelocity = 4;
			if (playerCenter.x < getX()) horizontalVelocity = -2;
			if (playerCenter.x == getX()) horizontalVelocity = 0;
		}
		gravity();
		
	}

	@Override
	public void attack(BaseImage entity) {
		if (entity instanceof Player){
			((Player)entity).takeDamage(damage);
		}
		else if (entity instanceof Enemy){
			((Enemy)entity).takeDamage(damage);
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

		// TODO Can't have just one static YBound, since there could be many platforms
		if(getY() + getHeight() >= YBound) { // if at bottom of screen
			setY(YBound - getHeight()); // reset position
			horizontalVelocity = 0;
			gravity = 0; // reset gravity
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
