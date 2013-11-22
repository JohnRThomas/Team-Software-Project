package tsp.game;

import java.awt.Point;

import imageBase.BaseImage;

public class Floater extends Enemy{

	public Floater(int xStart, int yStart, int d, int h, int g,
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
		if (playerCenter.x >= getX() + getWidth()/2){
			setX(getX() + 1) ;
		}
		else{
			setX(getX() - 1) ;
		}
		if (playerCenter.y >= getY() + getHeight()/2){
			setY(getY() + 1) ;
		}
		else{
			setY(getY() - 1) ;
		}
	}

	@Override
	public void attack(BaseImage entity) {
		((Player)entity).takeDamage(damage);
	}

	@Override
	public void die() {
		// TODO KILL THIS UNIT
	}

	@Override
	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0) die();
//		TODO add in the take damage animation if the enemy doesn't die
	}
	
}
