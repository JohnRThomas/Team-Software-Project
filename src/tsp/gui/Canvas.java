package tsp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tsp.game.Enemy;
import tsp.game.Player;
import tsp.imageMaker.BuildImages;
import tsp.imageMaker.MakeImages;

public class Canvas extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	Player player = new Player(0, 400, 20, 20, -1, 100, 5, 60, Color.GREEN);
	//Enemy evilRedBox = new Enemy(-40, -40, 100, 100, 5);
	Enemy evilRedBox = new Enemy(-40, -40, 3, "res/images/evilRedBox.png", true);
	private boolean /*up = false, down = false,*/ left = false, right = false; // should be taken care of in Player
	boolean playerMovePosX =false, playerMoveNegX = false;
	boolean gameOver = false;
	private int jumpMax = 2;
	//private int playerSpeed = 5;
	//protected int playerWidth = 20, playerHeight = 20;
	//private int enemyWidth = 100, enemyHeight = 100;
	//private int counter = 0;


	private MainWindow mainWindow;

	Graphics background;


	BuildImages stageMaker;
	MakeImages imageList;

	Image offScreenImage;
	int imageX,imageY =0;

	//int enemyX = -40, enemyY = -40;
	int gravCounter = 0;
	int jumpCount = 0;
	public Canvas(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;

		BuildImages stageMaker = new BuildImages();
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

		//draw enemy
		g.setColor(Color.RED);
		g.fillRect(evilRedBox.getX(), evilRedBox.getY(), evilRedBox.getWidth(), evilRedBox.getHeight());
		
		//draw player
		g.setColor(player.color);
		g.fillOval(player.x, player.y, player.width, player.height);
		
		g.setColor(Color.black);
		g.fillRect(1500+imageList.getBaseBackground().getX(), 300, 100, 100);

		// draw health value
		// TODO make more visible
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString(player.health.toString(), this.getWidth()-40, 20);

	}

	public void end(boolean death){
		gameOver = true;
		mainWindow.endGame(death);
		// TODO: Remove player oval from screen, then present EndScreen
		// TODO: Get fancy by adding a death sound/animation
	}

	public void movePlayer(){ // TODO Commit this when convenient

		if (left){

			if ((player.x + player.width/2 < getBounds().width/2)
					&& (imageList.getBaseBackground().getX() < 0) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() +player.speed);
				playerMoveNegX = true;
			}
			else{
				if(player.x >= player.speed)player.x -= player.speed;
				else player.x = 0;
			}
		}
		if (right){
			//System.out.println(imageX +" " + player.x + " " +getBounds().width/2);


			if ((player.x+player.width/2 > getBounds().width/2 )
					&& (imageList.getBaseBackground().getX() > -800) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() -player.speed);
				playerMovePosX = true;
			}
			else{ 
				if(player.x + player.width + player.speed <= getBounds().width)player.x += player.speed; // player width is 20
				else player.x = getBounds().width - player.width;
			}
		}
	}

	public void moveEnemy(){
		//		counter += 1;
		if (gameOver) return;
		if (player.x+10 >= evilRedBox.getX() + 50){
			evilRedBox.setX(evilRedBox.getX() + 1) ;
		}
		else{
			evilRedBox.setX(evilRedBox.getX() - 1) ;
		}
		if (player.y+10 >= evilRedBox.getY() + 50){
			evilRedBox.setY(evilRedBox.getY() + 1) ;
		}
		else{
			evilRedBox.setY(evilRedBox.getY() - 1) ;
		}
		if (playerMovePosX){
			evilRedBox.setX(evilRedBox.getX() - player.speed) ;
			playerMovePosX =false;
		}
		if (playerMoveNegX){
			evilRedBox.setX(evilRedBox.getX() + player.speed) ;
			playerMoveNegX =false;
		}
		// TODO Hey, look, I can make the enemy grow
		//		if (counter > 120){
		//			counter = 0;
		//			evilRedBox.width += 10;
		//			evilRedBox.height += 10;
		//		}

		//		if (evilRedBox.x > 700){
		//			evilRedBox.x = 700;
		//		}
		//		if (evilRedBox.x < 0){
		//			evilRedBox.x = 0;
		//		}
		//		if (evilRedBox.y > 500){
		//			evilRedBox.y = 500;
		//		}
		//		if (evilRedBox.y < 0){
		//			evilRedBox.y = 0;
		//		}


	}
}




