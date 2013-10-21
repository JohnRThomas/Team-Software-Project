package tsp.game.threads;

import tsp.gui.MainWindow;

public class Gamer extends Thread{
	MainWindow window;

	public Gamer(MainWindow mainWindow) {
		window = mainWindow;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			//Call the tick method at most x60 per second
			long time = System.currentTimeMillis();
			System.out.println("tick");
			window.tick();
			long difference = System.currentTimeMillis() - time;
			if(difference < 17){
				try {
					sleep(17-difference);
				} catch (InterruptedException e) {}
			}
		}
	}

}
