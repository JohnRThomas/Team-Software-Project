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
import tsp.game.Projectile;
import tsp.imageMaker.AllObjects;
import tsp.imageMaker.BuildImages;
import tsp.imageMaker.MakeEnemies;
import tsp.imageMaker.MakeImages;

public class Canvas extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;

	Player player = new Player(0, 400, 20, 20, -1, 100, 5, 60, Color.GREEN);

	private boolean /*up = false, down = false,*/ left = false, right = false, shoot = false; // should be taken care of in Player
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

	private int currentStage;
	private int totalStages;
	
	BuildImages stageMaker;
	MakeImages imageList;
	MakeEnemies enemyList;
	Projectile[] projectileList;
	AllObjects objectMaker;
	ChangeStage stageChanger;
	
	Image offScreenImage;
	
	//int enemyX = -40, enemyY = -40;
	int gravCounter = 0;
	int jumpCount = 0;
	int shotCount = 0;
	public Canvas(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;
		currentStage=1;
		stageChanger = new ChangeStage();
		totalStages = stageChanger.getKeyInfo();
		objectMaker = new AllObjects();
		stageMaker = new BuildImages();
		objectMaker = stageMaker.getFile("stage1",objectMaker);
		imageList = objectMaker.getImages();
		enemyList = objectMaker.getEnemies();
		projectileList = new Projectile[20];
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (shotCount < projectileList.length) {
				projectileList[shotCount] = new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 15, 0, "res/images/40x10greenLaser.png", false) ;
				//background.drawImage(projectileList[shotCount].getImage(), projectileList[shotCount].getX(), projectileList[shotCount].getY(), this);
				shotCount++;
			}
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			//shoot = false ;
		}
	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//adds background image
		if (offScreenImage == null) {
			offScreenImage = createImage(imageList.getImageBase(0).getWidth(), imageList.getImageBase(0).getHeight());
			background = offScreenImage.getGraphics();
		}
		background.clearRect(0, 0,imageList.getImageBase(0).getWidth(), imageList.getImageBase(0).getHeight() + 1);

		//draws images
		for(int i =0; i< imageList.getSize(); i++){
			background.drawImage(imageList.getImageBase(i).getImage(), imageList.getImageBase(i).getX(), imageList.getImageBase(i).getY(), this);

		}
		for(int i =0; i< enemyList.getSize(); i++){
			background.drawImage(enemyList.getEnemyImage(i), enemyList.getEnemy(i).getX(), enemyList.getEnemy(i).getY(), this);
		}
		
		background.drawImage(player.getImage(), player.getX(), player.getY(), this);
		
		for(int i =0; i< shotCount; i++){
			background.drawImage(projectileList[i].getImage(), projectileList[i].getX(), projectileList[i].getY(), this);
		}

		g.drawImage(offScreenImage, imageList.getBaseBackground().getX(), imageList.getBaseBackground().getY(),this); 

	
		g.setColor(Color.black);
		g.fillRect(1500+imageList.getBaseBackground().getX(), 300, 100, 100);

		// draw health value
		// TODO make more visible
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString(player.health.toString(), this.getWidth()-40, 20);
		
		HUD.draw(g, this);
	}

	public void end(boolean death){
		
		if (death == true){
			gameOver = true;
			mainWindow.endGame(death);
		}
		String stageName = null;
		stageName = stageChanger.getNextStage(currentStage, totalStages);
		if (stageName == null){
			gameOver = true;
			mainWindow.endGame(death);
		}
		currentStage +=1;
		if(stageName != null){
		objectMaker = stageMaker.getFile(stageName,objectMaker);
		imageList = objectMaker.getImages();
		enemyList = objectMaker.getEnemies();
		}
		player.setX(0);
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
		}
	}

	public void moveProjectile() {
		for(int i =0; i < shotCount; i++){
			mover.moveProjectile(gameOver, projectileList[i]);
		}
	}
}




