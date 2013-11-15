package imageBase;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tsp.game.Collisions;

public abstract class BaseImage {
	private int xPoint,yPoint = 0;
	private int xSize, ySize;
	private Image usedImage;
	private boolean canCollide;	
	
	/**
	 * 
	 * @param int xStart: 		x Start Position
	 * @param int yStart: 		y Start Position
	 * @param String imageName:	name of image to import, "(filename).(filetype)"
	 * @param boolean collide:	say if player can collide with the image
	 */
	public BaseImage(int xStart,int yStart, String imageName, boolean collide){
		xPoint = xStart;
		yPoint = yStart;
		usedImage = Toolkit.getDefaultToolkit().getImage(imageName);
		setCanCollide(collide);
		BufferedImage bimg = null;
		try {
			System.out.println(imageName);
			bimg = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		xSize = bimg.getWidth();
		ySize = bimg.getHeight();
		
	}
	
	/**
	 * 
	 * @return Image used
	 */
	public Image getImage(){
		return usedImage;
	}
	public Image setImage(String newImage){
		usedImage = Toolkit.getDefaultToolkit().getImage(newImage);
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(newImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		xSize = bimg.getWidth();
		ySize = bimg.getHeight();
		return usedImage;
	}
	
	/**
	 * @return y position
	 */
	public int getX(){
		return xPoint;
	}
	/**
	 * @return y position
	 */
	public int getY(){
		return yPoint;
	}
	/**
	 * @param int y: new y position
	 * @return y position
	 */
	public int setY(int y){
		yPoint = y;
		return yPoint;
	}
	/**y+50
	 * @param int x: new x position
	 * @return x position
	 */
	public int setX(int x){
		xPoint = x;
		return xPoint;
	}
	
	/**
	 * @return Width of image
	 */
	public int getWidth(){
		return xSize;
	}
	/**
	 * @return Height of image
	 */
	public int getHeight(){
		return ySize;
	}

	public boolean canCollide() {
		return canCollide;
	}

	public void setCanCollide(boolean canCollide) {
		this.canCollide = canCollide;
		if(canCollide){
			Collisions.entities.add(this);
		}else{
			Collisions.entities.remove(this);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xPoint, yPoint, xSize, ySize);
	}
	
	public abstract void collideWith(BaseImage entity);
	
}
