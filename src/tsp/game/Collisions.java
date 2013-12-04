package tsp.game;

import imageBase.BaseImage;

import java.util.ArrayList;

public class Collisions {

	public static ArrayList<BaseImage> entities = new ArrayList<BaseImage>();

	public static void runCollisions(BaseImage entity){
		for(int i = 0; i < entities.size(); i++){
			//			System.out.println(i+" " +entities.get(i).getBounds());
			//			System.out.println(i+" " +entity.getBounds());
			//			System.out.println(entities.get(i).getImage());
			if(entities.get(i).getBounds().intersects(entity.getBounds()) && entities.get(i) != entity)entities.get(i).collideWith(entity);
		}
	}

	public static void clearCollisions(){
		entities = new ArrayList<BaseImage>();
	}
}
