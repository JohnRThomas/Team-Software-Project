package tsp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScreen extends JPanel {

	private MainWindow mainWindow;
	
	public StartScreen(MainWindow mainWindow){
		super();
		this.mainWindow = mainWindow;
		JButton start = new JButton("Start Game");
		this.setLayout(new BorderLayout());
		add(start, BorderLayout.CENTER);
		start.setFocusable(false);
		addStartListener(start);
	}
	

	private void addStartListener(JButton start){
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.startGame();
			}
		});
	}
	
	
}
