package tsp.game.objects;

import tsp.game.Player;
import tsp.gui.Canvas;
import imageBase.BaseImage;

public class StageFinisher extends BaseImage {
	Canvas canvas;
	public StageFinisher(int xStart, int yStart, String imageName,
			boolean collide,Canvas canvas) {
		super(xStart, yStart, imageName, collide);
		this.canvas = canvas;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collideWith(BaseImage entity) {
		if (entity instanceof Player){
			canvas.end(false);
		}
	}

}
