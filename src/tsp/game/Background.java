package tsp.game;

import imageBase.BaseImage;

public class Background extends BaseImage{

	public Background(int xStart, int yStart, String imageName, boolean collide) {
		super(xStart, yStart, imageName, collide);
	}

	@Override
	public void collideWith(BaseImage entity) {
		
	}
}
