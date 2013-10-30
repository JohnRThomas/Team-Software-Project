package tsp.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import tsp.game.threads.Drawer;

public class StartScreen extends JPanel {
	private static final long serialVersionUID = 80085L;

	private MainWindow mainWindow;
	final Drawer drawer;
	BufferedImage title;

	public StartScreen(MainWindow window){
		super();
		try {
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

		this.setLayout(null);		
		start.setFocusable(false);

		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				drawer.interrupt();
				mainWindow.startGame();
			}
		});

		drawer = new Drawer(this, 30);
		drawer.start();

		add(titleLabel);
		titleLabel.setBounds(0,0,800,100);

		add(start);
		start.setBounds(275,150,250,25);
	}

	Star[] stars = new Star[200];

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
