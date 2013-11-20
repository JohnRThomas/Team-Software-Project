package tsp.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tsp.game.Collisions;
import tsp.game.threads.Drawer;
import tsp.game.threads.Gamer;
import tsp.sound.MusicDirector;
import tsp.sound.SoundConstants;


public class MainWindow extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;

	//GUI components
	private final JPanel container = new JPanel();
	private final JMenuBar menu = new JMenuBar();
	private final Canvas canvas = new Canvas(this);

	//Game threads
	private Drawer drawer;
	private Gamer gamer;

	//Audio
	private MusicDirector music;

	private int counter = 0;
	Collisions collider = new Collisions();

	public MainWindow() {
		super("TSP Game");
		drawer = new Drawer(canvas);
		gamer = new Gamer(this);
		drawer.start();
		this.setResizable(false);
		music = new MusicDirector(this);
		makeMenu();
		this.setLayout(new BorderLayout());
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.getContentPane().add(menu, BorderLayout.NORTH);
		container.setLayout(new GridLayout(1,1));
		container.add(new StartScreen(this));
		this.addKeyListener(this);
		setSize(800,600);
		setLocationRelativeTo(this.getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowListener(){
			@Override
			public void windowClosed(WindowEvent e) {
				drawer.interrupt();
				gamer.interrupt();

			}
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}

		});
	}

	private void makeMenu() {
		JMenu file = new JMenu("Options");
		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		file.add(close);
		menu.add(file);
	}

	protected void startGame(){
		container.remove(0);
		container.add(canvas);
		revalidate();
		music.playMusic(SoundConstants.SONG_01);
		gamer.start();
	}	
	
	protected void endGame(boolean death){
		container.remove(0);
		gamer.interrupt();
		final EndScreen endScreen = new EndScreen(this, death);
		container.add(endScreen);
		revalidate();
		endScreen.repaint();
		if (death){
			music.playMusic(SoundConstants.END_SONG_DEATH);
		}
		//		long startTime = System.currentTimeMillis();
		//		while(!endScreen.isLabelWhite){
		//			endScreen.lighterLabel(startTime, System.currentTimeMillis());
		//			endScreen.repaint();
		//		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		canvas.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		canvas.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		canvas.keyTyped(e);
	}

	public void tick() {
		counter +=1;
		if (canvas.player.health <= 0) {
			canvas.end(true); // death
		}
		if (counter >25){
			counter =0;
			for(int i =0; i < canvas.enemyList.getSize();i +=1){
			canvas.enemyList.getEnemy(i).incrementImage();
			}
		}
		canvas.movePlayer();
		canvas.moveEnemy();
		canvas.moveProjectile();
		
		canvas.shoot() ;
		
		//test code for movement
		//System.out.println("platform" + canvas.imageList.getImageBase(0).getX());
		
		
		Collisions.runCollisions(canvas.player);
		

		if (canvas.player.getX()+ canvas.player.getWidth()  >= 1500 && canvas.player.getX() <= 1500+100){
			canvas.end(false);
		}

		//gravity code
		canvas.player.setY(canvas.player.getY()-canvas.player.gravity); // fall according to gravity

		if (canvas.player.getY() < 10) { // if at top of screen
			canvas.player.setY(10); // reset position
			canvas.player.gravity = -1; // reset gravity
		}

		if(canvas.player.getY() > canvas.getBounds().height - 51) { // if at bottom of screen
			canvas.player.setY(canvas.getBounds().height - 50); // reset position
			canvas.player.gravity = -1; // reset gravity
			canvas.player.jumpCount = 0; // reset jumps
		}

		if (canvas.player.gravity > -10) { // if not at terminal velocity
			canvas.player.gravity = canvas.player.gravity - 1; // increase fall rate
		}

		if (canvas.player.hitTimer > 0) { // if in hit invulnerability
			canvas.player.hitTimer--; // decrease time remaining
		}

		canvas.repaint();
	}

}


