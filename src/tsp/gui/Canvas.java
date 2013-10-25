package tsp.gui;

import imageBase.baseImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tsp.game.Player;

public class Canvas extends JPanel implements KeyListener{
	Player player = new Player();

	private MainWindow mainWindow;
	
	boolean playerMovePosX =false;

	boolean playerMoveNegX = false;
	
	Graphics background;
	baseImage backgroundImage = new baseImage(0,0,"res/images/baseBackground.jpg",false);
	baseImage basePlatform = new baseImage(400,0,"res/images/basePlatform.jpg",false);
	Image offScreenImage;
	int imageX,imageY =0;

	int enemyX = 0, enemyY = 0;
	int gravCounter = 0;
	int jumpCount = 0;
	public Canvas(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;
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
		switch(e.getKeyChar()){
		case 'a':
			if ((player.x == 390 )
					&& (backgroundImage.getX() < 0) ){
				backgroundImage.setX(backgroundImage.getX() +10);
				playerMoveNegX = true;
			}
			else{
				if(player.x > 10)player.x -= 10;
				else player.x = 10;
			}
			break;
		case 'd':
			System.out.println(imageX +" " + player.x + " " +getBounds().width/2);

			if ((player.x == 390 )
					&& (backgroundImage.getX() > -800) ){
				backgroundImage.setX(backgroundImage.getX() -10);
				playerMovePosX = true;
			}
			else{
				if(player.x <= getBounds().width)player.x += 10;
				else player.x = getBounds().width -20;
			}
			break;
		case 's':
			if(player.y <= this.getBounds().height-400)player.y += 10;
			else player.y = getBounds().height - 420;
			break;
		case 'w':
			if (jumpCount < 2) {
				player.gravity = 20;
				player.y = player.y - 10;
				jumpCount++;
			}
			break;	
		case 'k':
			end(false);
			break;
		}
		//moveEnemy();
		repaint();
		/*
		if (player.x > enemyX && player.x+20 < enemyX+100){
			if (player.y > enemyY && player.y+20 < enemyY+100){
				end(true);
			}
		}
		*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
		background.drawImage(backgroundImage.getImage(), 0, 0, this);
		background.drawImage(basePlatform.getImage(), 0, 400, this);
		g.drawImage(offScreenImage, backgroundImage.getX(), backgroundImage.getY(),this); 
				
		
		g.setColor(Color.GREEN);
		g.fillOval(player.x, player.y, 20, 20);

		g.setColor(Color.RED);
		g.fillRect(enemyX, enemyY, 100, 100);
		
		g.setColor(Color.black);
		g.fillRect(1500+backgroundImage.getX(), 300, 100, 100);
	}

	public void end(boolean death){
		mainWindow.endGame(death);
		// TODO: Remove player oval from screen, then present EndScreen
		// TODO: Get fancy by adding a death sound/animation
	}

	public void moveEnemy(){
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
			enemyX -= 10;
			playerMovePosX =false;
			}
		if (playerMoveNegX){
			enemyX += 10;
			playerMoveNegX =false;
			}
		if (enemyX > 700){
			enemyX = 700;
		}
		if (enemyX < 0){
			enemyX = 0;
		}
		if (enemyY > 500){
			enemyY = 500;
		}
		if (enemyY < 0){
			enemyY = 0;
		}
		
		
	}
}


