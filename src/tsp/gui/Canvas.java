package tsp.gui;

import imageBase.BaseImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tsp.game.Player;
import tsp.imageMaker.buildImages;
import tsp.imageMaker.makeImages;

public class Canvas extends JPanel implements KeyListener{
	Player player = new Player();
	private boolean /*up = false, down = false,*/ left = false, right = false; // should be taken care of in Player
	boolean playerMovePosX =false, playerMoveNegX = false;
	boolean gameOver = false;
	private int jumpMax = 2;
	private int playerSpeed = 5;
	protected int playerWidth = 20, playerHeight = 20;
	private int enemyWidth = 100, enemyHeight = 100;
	private int counter = 0;


	private MainWindow mainWindow;
	
	
	Graphics background;
	

	buildImages stageMaker;
	makeImages imageList;
	
	Image offScreenImage;
	int imageX,imageY =0;

	int enemyX = -40, enemyY = -40;
	int gravCounter = 0;
	int jumpCount = 0;
	public Canvas(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;
		
		buildImages stageMaker = new buildImages();
		imageList = stageMaker.getFile("stage1",imageList);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Handles when key is pressed
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W){
			if (jumpCount < jumpMax) {
				player.gravity = 20;
				player.y = player.y - 10;
				jumpCount++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A){
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S){
//			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D){
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W){
//			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A){
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S){
//			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D){
			right = false;
		}
	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//adds background image
		if (offScreenImage == null) {
			offScreenImage    = createImage(1600, getHeight());
			background = offScreenImage.getGraphics();
		}
		background.clearRect(0, 0,1600, getHeight() + 1);
		
		//draws images
		for(int i =0; i< imageList.getSize(); i++){
			background.drawImage(imageList.getImageBase(i).getImage(), imageList.getImageBase(i).getX(), imageList.getImageBase(i).getY(), this);
		
		}
		
		g.drawImage(offScreenImage, imageList.getBaseBackground().getX(), imageList.getBaseBackground().getY(),this); 
		g.setColor(Color.GREEN);
		g.fillOval(player.x, player.y, playerWidth, playerHeight);

		g.setColor(Color.RED);
		g.fillRect(enemyX, enemyY, enemyWidth, enemyHeight);
		
		g.setColor(Color.black);
		g.fillRect(1500+imageList.getBaseBackground().getX(), 300, 100, 100);
	}

	public void end(boolean death){
		gameOver = true;
		mainWindow.endGame(death);
		// TODO: Remove player oval from screen, then present EndScreen
		// TODO: Get fancy by adding a death sound/animation
	}

	public void movePlayer(){ // TODO Commit this when convenient
		
		if (left){
			if ((player.x + playerWidth/2 < getBounds().width/2)
					&& (imageList.getBaseBackground().getX() < 0) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() +playerSpeed);
				playerMoveNegX = true;
			}
			else{
				if(player.x >= playerSpeed)player.x -= playerSpeed;
				else player.x = 0;
			}
		}
		if (right){
			System.out.println(imageX +" " + player.x + " " +getBounds().width/2);

			if ((player.x+playerWidth/2 > getBounds().width/2 )
					&& (imageList.getBaseBackground().getX() > -800) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() -playerSpeed);
				playerMovePosX = true;
			}
			else{ 
				if(player.x + playerWidth + playerSpeed <= getBounds().width)player.x += playerSpeed; // player width is 20
				else player.x = getBounds().width - playerWidth;
			}
		}
	}
	
	public void moveEnemy(){
//		counter += 1;
		if (gameOver) return;
		if (player.x+10 >= enemyX+50){
			enemyX += 1;
			
		}
		else{
			enemyX -= 1;
		}
		if (player.y+10 >= enemyY+50){
			enemyY += 1;
		}
		else{
			enemyY -= 1;
		}
		if (playerMovePosX){
			enemyX -= playerSpeed;
			playerMovePosX =false;
			}
		if (playerMoveNegX){
			enemyX += playerSpeed;
			playerMoveNegX =false;
			}
		// TODO Hey, look, I can make the enemy grow
//		if (counter > 120){
//			counter = 0;
//			enemyWidth += 10;
//			enemyHeight += 10;
//		}
		
//		if (enemyX > 700){
//			enemyX = 700;
//		}
//		if (enemyX < 0){
//			enemyX = 0;
//		}
//		if (enemyY > 500){
//			enemyY = 500;
//		}
//		if (enemyY < 0){
//			enemyY = 0;
//		}
		
		
	}
}




