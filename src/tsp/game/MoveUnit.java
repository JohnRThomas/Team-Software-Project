package tsp.game;

import java.awt.Point;

import imageBase.BaseImage;
import tsp.imageMaker.MakeImages;

public class MoveUnit {

	public int movePlayer(Player player, MakeImages imageList, boolean left, boolean right, int boundsWidth ){ // TODO Commit this when convenient
		int playerDirection = 0; //if 0 not in center, if 1 center right, if -1 center left
		System.out.println(player.getX());
		if (left){

			if ((player.getX() + player.getWidth()/2+imageList.getBaseX() < boundsWidth/2)
					&& (imageList.getBaseX() < 0) ){
				imageList.setBaseX(imageList.getBaseX() +player.speed);
				if(player.getX() >= player.speed)player.setX(player.getX() - player.speed);
				else player.setX(0);
				playerDirection = -1;
			}
			else{
				if(player.getX() >= player.speed)player.setX(player.getX() - player.speed);
				else player.setX(0);
			}
		}

		if (right){

			if ((player.getX()+player.getWidth()/2 > boundsWidth/2 )
					&& (imageList.getBaseX() > -800) ){
				imageList.setBaseX(imageList.getBaseX() -player.speed);
				if(player.getX() + player.getWidth() + player.speed <= 1600)player.setX(player.getX() + player.speed ); // player width is 20
				playerDirection = 1;
			}
			else{ 
				if(player.getX() + player.getWidth() + player.speed <= 1600)player.setX(player.getX() + player.speed); // player width is 20
			}
		}
		
		return playerDirection;
	}

	public void moveEnemy(boolean gameOver, Player player, Enemy evilRedBox, boolean playerMovePosX, boolean playerMoveNegX){
		//		counter += 1;
		if (gameOver) return;
		if (playerMovePosX){
			evilRedBox.setX(evilRedBox.getX() - player.speed) ;
			playerMovePosX =false;
		}
		else if (playerMoveNegX){
			evilRedBox.setX(evilRedBox.getX() + player.speed) ;
			playerMoveNegX =false;
		}
		evilRedBox.move(new Point(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2));
	}

	public void moveProjectile(boolean gameOver, Projectile projectile) {
		if (gameOver) return;
		
		projectile.setX(projectile.getX() + projectile.getXSpeed()) ;
		projectile.setY(projectile.getY() + projectile.getYSpeed()) ;
		
		//Check if out of bounds... Not sure if its correct bounds but its pretty close
		if(projectile.getX() < 0 || projectile.getX() > 1600) {
			projectile.setHit(true) ;
		}
		if(projectile.getY() < 0 || projectile.getY() > 600) {
			projectile.setHit(true) ;
		}
	}
}
