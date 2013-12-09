package tsp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tsp.game.Player;

public class EndScreen extends JPanel{
	private static final long serialVersionUID = 1L;

	private JLabel gameOver;
	private JPanel buttons;
	private JLabel stats;
	
	protected boolean isLabelWhite;
//	private int red, green, blue;
	
	public EndScreen(final MainWindow mainWindow, boolean death, Player player){
		super();
		this.setLayout(new BorderLayout());
//		red = 0;
//		blue = 0;
//		green = 0;
		if (death){
			gameOver = new JLabel("GAME OVER", JLabel.CENTER);
			buttons = new JPanel();
			JButton home = new JButton("Main Menu");
			final EndScreen THIS = this;
			home.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.container.add(mainWindow.startScreen);
					mainWindow.container.remove(THIS);
					mainWindow.container.repaint();
				}
			});
			
			JButton reset = new JButton("Retry Level");
			reset.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.canvas.player.currentHealth = 100;
					mainWindow.canvas.player.setX(0);
					mainWindow.container.remove(THIS);
					mainWindow.add(mainWindow.canvas);
				}
			});
			
			buttons.add(home);
			buttons.add(reset);
			buttons.setOpaque(false);
		}else{
			gameOver = new JLabel("VICTORY", JLabel.CENTER);
			buttons = new JPanel();
			final EndScreen THIS = this;
			JButton home = new JButton("Main Menu");
			home.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.container.add(mainWindow.startScreen);
					mainWindow.container.remove(THIS);
					mainWindow.container.repaint();
					mainWindow.startScreen.startDrawing();
				}
			});
			buttons.add(home);
			buttons.setOpaque(false);
			
		}
		String data ="Death:" + player.deathCount + " Victory:" + player.victoryCount;
		stats = new JLabel(data, JLabel.CENTER);
		gameOver.setForeground(Color.WHITE);
		stats.setForeground(Color.WHITE);
		Font font = gameOver.getFont();
		gameOver.setFont(font.deriveFont((float)72.0));
		stats.setFont(font.deriveFont((float)24.0));
		this.add(gameOver, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.CENTER);
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
