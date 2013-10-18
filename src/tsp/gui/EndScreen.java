package tsp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndScreen extends JPanel{
	
	private JLabel gameOver;
	
	protected boolean isLabelWhite;
//	private int red, green, blue;
	
	public EndScreen(MainWindow mainWindow, boolean death){
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
		gameOver.setForeground(Color.WHITE);
		Font font = gameOver.getFont();
		gameOver.setFont(font.deriveFont((float)72.0));
		this.add(gameOver, BorderLayout.CENTER);
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
