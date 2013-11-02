package tsp.imageMaker;

import imageBase.BaseImage;

import java.awt.Image;

import tsp.game.Background;
/**
 * 
 * @author Matthew Johnson
 *
 */
public class MakeImages {
	BaseImage listOfImages [];
	
//	/**
//	 * 
//	 * @param numImages number of images to be used
//	 */
//	public makeImages(int numImages){
//		listOfImages = new BaseImage[numImages];
//	}
	
	public void setSize(int numImages){
		listOfImages = new BaseImage[numImages];
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
	public BaseImage setBaseBackgroundImage(int layerPosition,int x, int y, String name, boolean canColide){
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
	public BaseImage setBaseBackgroundImage(String name, boolean canColide){
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
	public BaseImage setBackgroundImage(int layerPosition,int xOffset, int yOffset, String name, boolean canColide){
		String namePath = "res/images/" + name;
		listOfImages[layerPosition] = new Background(listOfImages[0].getX()+xOffset,listOfImages[0].getX()+xOffset,namePath,canColide);
		return listOfImages[layerPosition];
	}
	
	
	
	
	
	
	/**
	 * @return: get the BaseImage(not the actual image)
	 */
	public BaseImage getBaseBackground(){
		return listOfImages[0];
	}
	/**
	 * 
	 * @param layerPosition layer position of BaseImage
	 * @return: get the BaseImage(not the actual image)
	 */
	public BaseImage getImageBase(int layerPosition){
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
	 * @param layerPosition layer position of BaseImage
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
	
	
}
