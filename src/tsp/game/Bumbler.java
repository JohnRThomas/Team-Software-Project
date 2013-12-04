package tsp.game;

import imageBase.BaseImage;

import java.awt.Point;

public class Bumbler extends Enemy{

	private int horizontalVelocity = 1;
	private int verticalVelocity = 1;
	private int velocityCap = 5;
	private int accel = 1;
	private int accelFrequency = 30;
	private int accelCountdown = accelFrequency;
	
	public Bumbler(int xStart, int yStart, int d, int h, int g,
			String[] imageName, boolean collide) {
		
		super(xStart, yStart, d, h, g, imageName, collide);
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
		if (accelCountdown > 0){
			accelCountdown -= 1;
			
		}
		else{
			accelCountdown = accelFrequency;
			if (playerCenter.x >= getX() + getWidth()/2){
				if (horizontalVelocity < 0) horizontalVelocity += accel;
				horizontalVelocity += accel;
				if (horizontalVelocity > velocityCap) horizontalVelocity = velocityCap;
			}
			else{
				if (horizontalVelocity > 0) horizontalVelocity -= accel;
				horizontalVelocity -= accel;
				if (horizontalVelocity < -velocityCap) horizontalVelocity = -velocityCap;
			}
			if (playerCenter.y >= getY() + getHeight()/2){
				if (verticalVelocity < 0) verticalVelocity += accel;
				verticalVelocity += accel;
				if (verticalVelocity > velocityCap) verticalVelocity = velocityCap;
			}
			else{
				if (verticalVelocity > 0) verticalVelocity -= accel;
				verticalVelocity -= accel;
				if (verticalVelocity < -velocityCap) verticalVelocity = -velocityCap;
			}
		}
		if (playerCenter.x >= getX() + getWidth()/2){
			setX(getX() + horizontalVelocity) ;
		}
		else{
			setX(getX() + horizontalVelocity) ;
		}
		if (playerCenter.y >= getY() + getHeight()/2){
			setY(getY() + verticalVelocity) ;
		}
		else{
			setY(getY() + verticalVelocity) ;
		}
		
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

}
