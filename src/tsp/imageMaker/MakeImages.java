package tsp.imageMaker;

import tsp.game.Background;

import java.awt.Image;
/**
 * 
 * @author Matthew Johnson
 *
 */
public class MakeImages {
	int baseX =0;
	int baseY =0;
	Background listOfImages [];
	
//	/**
//	 * 
//	 * @param numImages number of images to be used
//	 */
//	public makeImages(int numImages){
//		listOfImages = new Background[numImages];
//	}
	
	public void setSize(int numImages){
		listOfImages = new Background[numImages];
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
	public Background setImage(int layerPosition,int x, int y, String name, boolean canColide){
		String namePath = "res/images/" + name;
		listOfImages[layerPosition] = new Background(x,y,namePath,canColide);
		return listOfImages[layerPosition];
	}
	/**
	 * 
	 * @param name:			String with Images name (do not need to include res/images/)
	 * @param canColide: 	boolean of saying if image can collide with other objects
	 * @return
	 */
	public Background setBaseBackgroundImage(String name, boolean canColide){
		String namePath = "res/images/" + name;
		listOfImages[0] = new Background(0,0,namePath,canColide);
		return listOfImages[0];
	}
	
	/**
	 * 
	 * @param layerPosition: int saying where which layer Image is
	 * 						 cannot exceed total number of Images for list
	 * @param xOffset:		X Position of Image relative to baseBackground
	 * @param yOffset:		Y Position of Image relative to baseBackground
	 * @param name:			String with Images name (do not need to include res/images/)
	 * @param canColide: 	boolean of saying if image can collide with other objects
	 * @return
	 */
	public Background setBackgroundImage(int layerPosition,int xOffset, int yOffset, String name, boolean canColide){
		String namePath = "res/images/" + name;
		listOfImages[layerPosition] = new Background(listOfImages[0].getX()+xOffset,listOfImages[0].getX()+xOffset,namePath,canColide);
		return listOfImages[layerPosition];
	}
	
	
	
	
	
	
	/**
	 * @return: get the Background(not the actual image)
	 */
	public Background getBaseBackground(){
		return listOfImages[0];
	}
	/**
	 * 
	 * @param layerPosition layer position of Background
	 * @return: get the Background(not the actual image)
	 */
	public Background getImageBase(int layerPosition){
		return listOfImages[layerPosition];
	}
	
	
	
	
	/**
	 * @return: get the Image of the base background
	 */
	public Image getBaseBackgroundImage(){
		return listOfImages[0].getImage();
	}
	/**
	 * 
	 * @param layerPosition layer position of Background
	 * @return: get the Image of an object
	 */
	public Image getImage(int layerPosition){
		return listOfImages[layerPosition].getImage();
	}
	
	/**
	 * 
	 * @return number of images
	 */
	public int getSize(){
		return listOfImages.length;
	}
	
	public int setBaseX(int i){
		baseX = i;
		return baseX;
	}
	public int getBaseX(){
		return baseX;
	}
	
	public int setBaseY(int i){
		baseY = i;
		return baseY;
	}
	public int getBaseY(){
		return baseY;
	}
	
}
