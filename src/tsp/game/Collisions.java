package tsp.game;

import imageBase.BaseImage;

import java.util.ArrayList;

public class Collisions {
	
	public static ArrayList<BaseImage> entities = new ArrayList<BaseImage>();
	
	public static void runCollisions(BaseImage entity){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getBounds().intersects(entity.getBounds()) && entities.get(i) != entity)entities.get(i).collideWith(entity);
		}
	}
}
