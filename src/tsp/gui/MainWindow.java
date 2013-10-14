package tsp.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainWindow extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private final JPanel container = new JPanel();
	private final JMenuBar menu = new JMenuBar();
	private final Canvas canvas = new Canvas();
	
	public MainWindow() {
		super("TSP Game");
		makeMenu();
		this.setLayout(new BorderLayout());
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.getContentPane().add(menu, BorderLayout.NORTH);
		container.setLayout(new GridLayout(1,1));
		container.add(canvas);
		this.addKeyListener(this);
		container.addKeyListener(this);
		setSize(800,600);
		setLocationRelativeTo(this.getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

}
