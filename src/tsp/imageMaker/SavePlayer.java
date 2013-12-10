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
		out.println("PlayerName: "+player.name);
		out.println("Animations: "+ player.totalAnimations + " ImageName: " + "player" + " FileType: " + ".png"
				+ " Gravity: " + player.gravity + " Health: " + 100 + " MaxHealth: " + 100
				+ " Speed: " + player.speed + " HitTimer: " + 0
				+ " JumpMax: " + player.jumpMax + " JumpCount: " + player.jumpCount
				+ " DeathCount: " + player.victoryCount + " VictoryCount: " + player.deathCount);

		out.close();
	}
}
