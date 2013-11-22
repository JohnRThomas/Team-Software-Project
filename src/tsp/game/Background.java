package tsp.game;

import imageBase.BaseImage;

public class Background extends BaseImage{
	String name;
	public Background(int xStart, int yStart, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);
		name = imageName;
		System.out.println(name);
	}

	@Override
	public void collideWith(BaseImage entity) {
		if (entity instanceof Player) {
			int top = 9999;
			int left = 9999;
			int right = 9999;
			int bottom = 9999;

			// top collision
			top = Math.abs(entity.getY() + entity.getHeight() - this.getY());

			// bottom collision
			bottom = Math.abs(entity.getY() - this.getY() - this.getHeight());	

			// left collision
			left = Math.abs(entity.getX() + entity.getWidth() - this.getX());

			// right collision
			right = Math.abs(entity.getX() - this.getX() - this.getWidth());



			if (top < left && top < right && top < bottom) {
				//top is closest side
				entity.setY(this.getY() - entity.getHeight());
				((Player) entity).setGravity(-1);
				((Player) entity).jumpCount = 0;
			}

			if (bottom < top && bottom < left && bottom < right) {
				//bottom is closest side
				entity.setY(this.getY() + this.getHeight());
				((Player) entity).setGravity(-1);
			}

			if (left < top && left < right && left < bottom) {
				//left is closest side
				entity.setX(this.getX() - entity.getWidth());
				//((Player) entity).setGravity(-1); enable for wall-slide mechanic
			}

			if (right < top && right < left && right < bottom) {
				//right is closest side
				entity.setX(this.getX() + this.getWidth());
				//((Player) entity).setGravity(-1); enable for wall-slide mechanic
			}
			
			if(name.compareTo("res/images/killBlock.png")==0){
				((Player)entity).currentHealth =0;
			}
			if(name.compareTo("res/images/endZone.png")==0){
				((Player)entity).gameEnd =true;
			}
		}
	}
}
