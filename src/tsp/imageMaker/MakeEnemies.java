package tsp.imageMaker;
import java.awt.Image;

import tsp.game.Enemy;


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
		public Enemy setEnemy(int layerPosition,int x, int y, int damage, int health, int gravity, String name, boolean canColide){
			String namePath = "res/images/" + name;
			listOfEnemies[layerPosition] = new Enemy(x, y, damage, health, gravity, namePath, canColide);
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
		
		/**
		 * 
		 * @return number of images
		 */
		public int getSize(){
			return listOfEnemies.length;
		}
		
		
	}


