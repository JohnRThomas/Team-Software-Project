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

import tsp.game.threads.Drawer;
import tsp.game.threads.Gamer;


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
	private Clip currentMusic;
	private static final String FIRST_SONG = new String("/music/empire.wav");
	private static final String END_SONG_DEATH = new String("/music/zanarkand.wav");

	
	
	public MainWindow() {
		super("TSP Game");
		drawer = new Drawer(canvas);
		gamer = new Gamer(this);
		drawer.start();
		this.setResizable(false);
		
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
		playMusic(FIRST_SONG);
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
			playMusic(END_SONG_DEATH);
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
	
	private void playMusic(String path){
		if (null != currentMusic){
			currentMusic.stop();
		}
		try{
	        AudioInputStream audio = AudioSystem.getAudioInputStream(MainWindow.class.getResource(path));
	        currentMusic = AudioSystem.getClip();
	        currentMusic.open(audio);
	        currentMusic.start();
	        currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
	    }catch(Exception ex){
	        System.out.println("Error with playing sound at: " + path);
	        ex.printStackTrace();
	    }
	}

	public void tick() {

		if (canvas.player.health <= 0) {
			canvas.end(true); // death
		}

		canvas.movePlayer();
		canvas.moveEnemy();

		if (canvas.player.hitTimer == 0) {
			canvas.player.color = Color.GREEN; // resets color to show hit invulnerability has worn off
			if (canvas.player.x+ canvas.player.width >= canvas.evilRedBox.getX() && canvas.player.x <= canvas.evilRedBox.getX()+100){
				if (canvas.player.y+ canvas.player.width >= canvas.evilRedBox.getY() && canvas.player.y <= canvas.evilRedBox.getY()+100){
					canvas.player.health = canvas.player.health - 25; //TODO add damage values for enemies
					System.out.println(canvas.player.health);
					canvas.player.hitTimer = 60;
				}
			}
		}
		
		if (canvas.player.x+ canvas.player.width  >= 700 && canvas.player.x <= 700+100){
				canvas.end(false);
		}
		
		//gravity code
		canvas.player.y -= canvas.player.gravity; // fall according to gravity
		
		if (canvas.player.y < 10) { // if at top of screen
			canvas.player.y = 10; // reset position
			canvas.player.gravity = -1; // reset gravity
		}
		
		if(canvas.player.y > canvas.getBounds().height - 169) { // if at bottom of screen
			canvas.player.y = canvas.getBounds().height - 170; // reset position
			canvas.player.gravity = -1; // reset gravity
			canvas.jumpCount = 0; // reset jumps
		}
		
		if (canvas.player.gravity > -10) { // if not at terminal velocity
			canvas.player.gravity = canvas.player.gravity - 1; // increase fall rate
		}
		
		if (canvas.player.hitTimer > 0) { // if in hit invulnerability
			canvas.player.color = Color.BLUE; // change color 
			canvas.player.hitTimer--; // decrease time remaining
			System.out.println("hit");
		}
		
		canvas.repaint();
	}
	
}


