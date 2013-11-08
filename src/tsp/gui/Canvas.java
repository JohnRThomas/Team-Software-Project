package tsp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tsp.game.Enemy;
import tsp.game.MoveUnit;
import tsp.game.Player;
import tsp.imageMaker.AllObjects;
import tsp.imageMaker.BuildImages;
import tsp.imageMaker.MakeEnemies;
import tsp.imageMaker.MakeImages;

public class Canvas extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;

	Player player = new Player(0, 400, 20, 20, -1, 100, 5, 60, Color.GREEN);
	
	private boolean /*up = false, down = false,*/ left = false, right = false; // should be taken care of in Player
	boolean playerMovePosX =false, playerMoveNegX = false;
	boolean gameOver = false;
	private int jumpMax = 2;
	//private int playerSpeed = 5;
	//protected int playerWidth = 20, playerHeight = 20;
	//private int enemyWidth = 100, enemyHeight = 100;
	//private int counter = 0;
	MoveUnit mover = new MoveUnit();


	private MainWindow mainWindow;

	Graphics background;


	BuildImages stageMaker;
	MakeImages imageList;
	MakeEnemies enemyList;
	AllObjects objectMaker;

	Image offScreenImage;
	int imageX,imageY =0;

	//int enemyX = -40, enemyY = -40;
	int gravCounter = 0;
	int jumpCount = 0;
	public Canvas(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;
		objectMaker = new AllObjects();
		BuildImages stageMaker = new BuildImages();
		objectMaker = stageMaker.getFile("stage1",objectMaker);
		imageList = objectMaker.getImages();
		enemyList = objectMaker.getEnemies();
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
				player.setY(player.getY() - 10);
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
			System.out.println("imageX"+imageList.getImageBase(i).getX());
			background.drawImage(imageList.getImageBase(i).getImage(), imageList.getImageBase(i).getX(), imageList.getImageBase(i).getY(), this);

		}
		for(int i =0; i< enemyList.getSize(); i++){
			background.drawImage(enemyList.getEnemyImage(i), enemyList.getEnemy(i).getX(), enemyList.getEnemy(i).getY(), this);

		}
		System.out.println("player"+player.getX());
		background.drawImage(player.getImage(), player.getX(), player.getY(), this);
		
		g.drawImage(offScreenImage, imageList.getBaseBackground().getX(), imageList.getBaseBackground().getY(),this); 

		//draw player
//		g.setColor(player.color);
//		g.fillOval(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		
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

		int direction = mover.movePlayer(player, imageList, left, right, getBounds().width);
		if (direction ==0 ){
			playerMovePosX = false;
			playerMoveNegX = false;
		}
		if (direction <0){
			playerMoveNegX = true;
			playerMovePosX = false;
		}
		if (direction >0){
			playerMoveNegX = false;
			playerMovePosX = true;
		}
	}

	public void moveEnemy(){
		for(int i =0; i< enemyList.getSize(); i++){
			mover.moveEnemy(gameOver, player, enemyList.getEnemy(i), playerMovePosX, playerMoveNegX);
			System.out.println(enemyList.getEnemy(i).getBounds());
			System.out.println(player.getBounds());
		}
	}
}




