package tsp.imageMaker;
import java.awt.Image;

import tsp.game.Bumbler;
import tsp.game.Enemy;
import tsp.game.Floater;
import tsp.game.Jumper;
import tsp.game.Patrol;


public class MakeEnemies {


	Enemy listOfEnemies [];


	/**
	 * 
	 * @param numImages number of images to be used
	 */
	public void setSize(int numImages){
		listOfEnemies = new Enemy[numImages];
	}
	/**
	 * 
	 * @param layerPosition: int saying where which layer Image is
	 * 						 cannot exceed total number of Images for list
	 * @param x:			X Position of Image
	 * @param y:			Y Position of Image
	 * @param name:			String with Images name (do not need to include res/images/)
	 * @param canColide: 	boolean of saying if image can collide with other objects
	 * @return
	 */
	public Enemy setEnemy(int layerPosition,int x, int y, int damage, int health, int gravity, String name[], boolean canColide){
		String namePath[] = new String[name.length];
		for (int i =0; i< name.length; i++){
			namePath[i] = "res/images/" + name[i];
		}
		listOfEnemies[layerPosition] = new Jumper(x, y, damage, health, gravity, namePath, canColide);
		return listOfEnemies[layerPosition];
	}


	/**
	 * 
	 * @param layerPosition layer position of Enemy
	 * @return: get the Enemy(not the actual image)
	 */
	public Enemy getEnemy(int layerPosition){
		return listOfEnemies[layerPosition];
	}
	/**
	 * 
	 * @param layerPosition layer position of Enemy
	 * @return: get the Image of an object
	 */
	public Image getEnemyImage(int layerPosition){
		return listOfEnemies[layerPosition].getImage();
	}

//	/**
//	 * 
//	 * @param index
//	 * 				index of enemy to be removed
//	 * @return
//	 * 				the enemy removed
//	 */
//	public Enemy remove(int index){
//		Enemy old = listOfEnemies[index];
//		listOfEnemies[index] = null;
//		int tmp = index;
//		while(tmp + 1 < getSize()){
//			listOfEnemies[tmp] = listOfEnemies[tmp+1];
//			listOfEnemies[tmp+1] = null;
//			tmp++;
//		}
//		setSize(getSize() - 1) ;
//		return old;
//	}

	/**
	 * 
	 * @return number of images
	 */
	 public int getSize(){
		return listOfEnemies.length;
	}


}


