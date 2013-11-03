package tsp.imageMaker;

import tsp.game.Player;

public class AllObjects {
	MakeEnemies enemies;
	MakeImages images;
	Player player;
	
	AllObjects(){	}
	AllObjects(MakeEnemies enemy,MakeImages image,Player players){
		enemies = enemy;
		images = image;
		player = players;
	}
	
	public void setEnemies(MakeEnemies enemy){
		enemies = enemy;
	}
	public void setImages(MakeImages image){
		images = image;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public MakeEnemies getEnemies(){
		return enemies;
	}
	public MakeImages getImages(){
		return images;
	}
	public Player getPlayer(){
		return player;
	}
}
