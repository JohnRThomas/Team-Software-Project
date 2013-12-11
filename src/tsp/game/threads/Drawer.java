package tsp.game.threads;

import javax.swing.JPanel;

public class Drawer extends Thread{
	JPanel canvas;
	int speed = 0;

	public Drawer(JPanel canvas){
		this.canvas = canvas;
	}

	public Drawer(JPanel canvas, int speed){
		this.canvas = canvas;
		this.speed = speed;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			if(speed == 0){
				//Draw at most x60 per second
				long time = System.currentTimeMillis();
				if(canvas != null)canvas.repaint();
				if(System.currentTimeMillis() - time < 17){
					try {
						sleep(System.currentTimeMillis() - time);
					} catch (InterruptedException e) {}
				}
			}else{
				canvas.repaint();
				try {
					sleep(speed);
				} catch (InterruptedException e) {}
			}
		}
	}
}
