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

	Graphics background;
	baseImage backgroundImage = new baseImage(0,0,"baseBackground.jpg",false);
	Image offScreenImage;
	int imageX,imageY =0;

	private int enemyX = 400, enemyY = 400;

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
			}
			else{
				if(player.x <= getBounds().width)player.x += 10;
				else player.x = getBounds().width -20;
			}
			break;
		case 's':
			if(player.y <= this.getBounds().height)player.y += 10;
			else player.y = getBounds().height - 20;
			break;
		case 'w':
			if(player.y > 10)player.y -= 10;
			else player.y = 10;
			break;	
		case 'k':
			end(false);
			break;
		}
		moveEnemy();
		repaint();
		if (player.x > enemyX-20 && player.x < enemyX+120){
			if (player.y > enemyY-20 && player.y < enemyY+120){
				end(true);
			}
		}
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
		background.drawImage(backgroundImage.getImage(), backgroundImage.getX(), backgroundImage.getY(), this);
		background.drawImage(Toolkit.getDefaultToolkit().getImage("basePlatform.jpg"), 0, 400, this);
		g.drawImage(offScreenImage, backgroundImage.getX(), backgroundImage.getY(),this); 
				
		
		g.setColor(Color.GREEN);
		g.fillOval(player.x, player.y, 20, 20);

		g.setColor(Color.RED);
		g.fillRect(enemyX, enemyY, 100, 100);

	}

	public void end(boolean death){
		mainWindow.endGame(death);
		// TODO: Remove player oval from screen, then present EndScreen
		// TODO: Get fancy by adding a death sound/animation
	}

	private void moveEnemy(){

		if (player.x+10 > enemyX+50){
			enemyX += (Math.random()*5);
		}
		else{
			enemyX -= (Math.random()*5);
		}
		if (player.y+10 > enemyY+50){
			enemyY += (Math.random()*5);
		}
		else{
			enemyY -= (Math.random()*5);
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
