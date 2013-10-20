package tsp.game.threads;

import tsp.gui.Canvas;

public class Drawer extends Thread{
	Canvas canvas;
	
	public Drawer(Canvas canvas){
			this.canvas = canvas;
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			//Draw at most x60 per second
			long time = System.currentTimeMillis();
			canvas.repaint();
			if(System.currentTimeMillis() - time < 17){
				try {
					sleep(System.currentTimeMillis() - time);
				} catch (InterruptedException e) {}
			}
		}
	}
}
