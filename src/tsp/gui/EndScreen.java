package tsp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tsp.game.Player;

public class EndScreen extends JPanel{
	private static final long serialVersionUID = 1L;

	private JLabel gameOver;
	private JLabel stats;
	
	protected boolean isLabelWhite;
//	private int red, green, blue;
	
	public EndScreen(MainWindow mainWindow, boolean death,Player player){
		super();
		this.setLayout(new BorderLayout());
//		red = 0;
//		blue = 0;
//		green = 0;
		if (death){
			gameOver = new JLabel("GAME OVER", JLabel.CENTER);
		}
		else{
			gameOver = new JLabel("VICTORY", JLabel.CENTER);
		}
		String data ="Death:" + player.deathCount + " Victory:" + player.victoryCount;
		stats = new JLabel(data, JLabel.CENTER);
		gameOver.setForeground(Color.WHITE);
		stats.setForeground(Color.WHITE);
		Font font = gameOver.getFont();
		gameOver.setFont(font.deriveFont((float)72.0));
		stats.setFont(font.deriveFont((float)24.0));
		this.add(gameOver, BorderLayout.NORTH);
		this.add(stats, BorderLayout.SOUTH);
		this.setBackground(Color.BLACK);
	}
	
//	protected void lighterLabel(long startTime, long currentTime){
//		long difference = (currentTime-startTime)/1000000;
//		if (difference < 0 || difference > 250){
//			difference = 250;
//		}
//		gameOver.setForeground(new Color(red+difference, green+difference, blue+difference));
//		if (difference > 250) isLabelWhite = true;
//	}
	
}
