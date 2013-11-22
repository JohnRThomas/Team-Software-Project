package tsp.imageMaker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import tsp.game.Player;

public class SavePlayer {
	public void playerSaver(Player player){
		String playerFile = "res/player/player1";
		PrintWriter out =null;
		try {
			out = new PrintWriter(playerFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(player.name);
		out.println(1 + " " + "player" + " " + ".png"
				+ " " + player.gravity + " " + 100 
				+ " " + player.speed + " " + 0
				+ " " + player.jumpMax + " " + player.jumpCount
				+ " " + player.victoryCount + " " + player.deathCount);

		out.close();
	}
}
