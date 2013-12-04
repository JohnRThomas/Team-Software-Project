package tsp.imageMaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tsp.game.Player;

public class BuildPlayer {
	public Player playerBuilder(Player player, int playerNumber){
		String	fileName = "res/player/player"+playerNumber;
		Scanner wordScanner = null;
		try {
			wordScanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int imageCount = 0;

		int j = 0;
		int x[];
		int y[];
		int damage[];
		int health[];
		int gravity[];
		String playerName;
		String playerImage;
		boolean collide[];
		while(wordScanner.hasNext()){

			String word = wordScanner.nextLine();
			if(j ==0){
				playerName = word;
			}
			if(j ==1){
				playerImage = word;
			}
			if(j ==2){
				playerImage = word;
			}
			if(j ==3){
				playerImage = word;
			}
			if(j ==4){
				playerImage = word;
			}
			if(j ==5){
				playerImage = word;
			}
			j+=1;

		}// end of scanner

		wordScanner.close();
		return player;	


	}
}
