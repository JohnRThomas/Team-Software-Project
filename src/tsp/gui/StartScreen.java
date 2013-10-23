package tsp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScreen extends JPanel {
	private static final long serialVersionUID = 80085L;
	
	private MainWindow mainWindow;
	
	public StartScreen(MainWindow window){
		super();
		mainWindow = window;
		JButton start = new JButton("Start Game");
		this.setLayout(new BorderLayout());
		add(start, BorderLayout.CENTER);
		start.setFocusable(false);
		
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.startGame();
			}
		});
	}	
}
