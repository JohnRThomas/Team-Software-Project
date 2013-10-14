package tsp.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tsp.game.Player;

public class Canvas extends JPanel implements KeyListener{
	Player player = new Player();
	public Canvas(){
		super();
		this.addKeyListener(this);
	}
	boolean up = true;
	@Override
	public void keyTyped(KeyEvent e) {
		up = false;
		while(!up){
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				if(player.x > 10)player.x -= 10;
				else player.x = 10;
				break;
			case KeyEvent.VK_RIGHT:
				if(player.x <= getBounds().width)player.x += 10;
				else player.x = getBounds().width -20;
				break;
			case KeyEvent.VK_DOWN:
				if(player.y <= this.getBounds().height)player.y += 10;
				else player.y = getBounds().height - 20;
				break;
			case KeyEvent.VK_UP:
				if(player.y > 10)player.y -= 10;
				else player.y = 10;
				break;	
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			if(player.x > 10)player.x -= 10;
			else player.x = 10;
			break;
		case KeyEvent.VK_RIGHT:
			if(player.x <= getBounds().width)player.x += 10;
			else player.x = getBounds().width -20;
			break;
		case KeyEvent.VK_DOWN:
			if(player.y <= this.getBounds().height)player.y += 10;
			else player.y = getBounds().height - 20;
			break;
		case KeyEvent.VK_UP:
			if(player.y > 10)player.y -= 10;
			else player.y = 10;
			break;	
		}
		repaint();*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		up = true;
	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.GREEN);
		g.fillOval(player.x, player.y, 20, 20);

	}
}
