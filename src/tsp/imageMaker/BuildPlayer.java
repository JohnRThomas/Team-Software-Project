package tsp.imageMaker;

import java.awt.Color;
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
		int damage;
		int health = 0;
		int max = 0;
		int gravity = 0;
		int speed = 0;
		int hitTimer = 0;
		int animations =0;
		int victoryCount =0;
		int deathCount =0;
		int jumpMax =0;
		int jumpCount = 0;
		String playerName =null;
		String partImageName = null;
		String playerImage[] = null;
		boolean collide[];
		while(wordScanner.hasNext()){

			String word = wordScanner.next();
			if(j ==0){
				playerName = word;
			}
			if (j == 1){
				animations = Integer.parseInt(word);
				if (playerImage ==null){
					playerImage = new String[animations];
				}

			}
			else if (j == 2){
				partImageName = word;
			}
			else if (j == 3){
				for (int k =0; k<animations; k++){
					playerImage[k] = (partImageName + k + word);

				}
			}
			if(j ==4){
				gravity = Integer.parseInt(word);
			}
			if(j ==5){
				health = Integer.parseInt(word);
			}
			if (j ==6){
				max = Integer.parseInt(word);
			}
			if(j ==7){
				speed = Integer.parseInt(word);
			}
			if(j ==8){
				hitTimer = Integer.parseInt(word);
			}
			if(j ==9){
				jumpMax = Integer.parseInt(word);
			}
			if(j ==10){
				jumpCount = Integer.parseInt(word);
			}
			if(j ==11){
				victoryCount = Integer.parseInt(word);
			}
			if(j ==12){
				deathCount = Integer.parseInt(word);
			}
			
			j+=1;
		}// end of scanner
		player = new Player(0, 400 ,gravity,health,max,speed,hitTimer,jumpCount,jumpMax, victoryCount, deathCount,playerName);
		wordScanner.close();
		return player;	


	}
}
