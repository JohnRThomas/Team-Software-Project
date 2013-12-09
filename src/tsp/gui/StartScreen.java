package tsp.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import tsp.game.Player;
import tsp.game.threads.Drawer;

public class StartScreen extends JPanel {
	private static final long serialVersionUID = 80085L;
	private MainWindow mainWindow;
	final Drawer drawer;
	BufferedImage title;
	BufferedImage playerSprite;

	public StartScreen(MainWindow window){
		super();
		try {
			playerSprite = ImageIO.read(new File("./res/images/player.png"));
			title = ImageIO.read(new File("./res/images/title.png"));
		} catch (IOException e1) {
		}
		mainWindow = window;
		JButton start = new JButton("Start Game");
		JPanel titleLabel = new JPanel(){
			private static final long serialVersionUID = 717507158886199567L;
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(title, 0,0, null);
			}
		};
		drawer = new Drawer(this, 30);
		this.setLayout(null);		
		start.setFocusable(false);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//drawer.interrupt();
				mainWindow.startGame();
			}
		});


		add(titleLabel);
		titleLabel.setBounds(0,0,800,100);

		add(start);
		start.setBounds(275,150,250,25);
		startDrawing();
	}
	public void startDrawing() {
		drawer.start();		
	}
	
	Star[] stars = new Star[200];
	float animCount = 0f;
	Point loc = new Point();
	int mod = -1;
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for(int i = 0; i < stars.length; i++){
			if(stars[i] == null){
				stars[i] = new Star(new Point((int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight())), (int)(Math.random()*9) + 1);
			}else{
				if(stars[i].pt.x + stars[i].speed <= this.getWidth())stars[i].pt.x += stars[i].speed;
				else{
					stars[i].pt.y = (int)(Math.random()*this.getHeight());
					stars[i].pt.x = -(int)(Math.random()*this.getWidth());
				}
			}
			g.fillRect(stars[i].pt.x-1, stars[i].pt.y-1, 2, 2);
		}
		
		
		if(mod > 0 && animCount >= 255){
			mod = -mod;
		}else if(mod < 0 && animCount <= 0){
			loc = new Point((int)(Math.random()*(this.getHeight()-100)), (int)(Math.random()*(this.getWidth()-300)));
			mod = -mod;
		}
		
		animCount += mod;

		Graphics2D g2d = (Graphics2D)g.create();
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animCount/255.0f);
		g2d.setComposite(ac);
		g2d.drawImage(playerSprite, loc.x, loc.y, null);
	}

	private class Star{
		int speed;
		Point pt;
		public Star(Point pt, int speed){
			this.pt = pt;
			this.speed = speed;
		}
	}
}
