package tsp.game;

import imageBase.BaseImage;
import tsp.imageMaker.MakeImages;

public class MoveUnit {

	public int movePlayer(Player player, MakeImages imageList, boolean left, boolean right, int boundsWidth ){ // TODO Commit this when convenient
		int playerDirection = 0; //if 0 not in center, if 1 center right, if -1 center left
		if (left){

			if ((player.getX() + player.getWidth()/2+imageList.getBaseBackground().getX() < boundsWidth/2)
					&& (imageList.getBaseBackground().getX() < 0) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() +player.speed);
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
			//System.out.println(imageX +" " + player.getX() + " " +getBounds().width/2);


			if ((player.getX()+player.getWidth()/2 > boundsWidth/2 )
					&& (imageList.getBaseBackground().getX() > -800) ){
				imageList.getBaseBackground().setX(imageList.getBaseBackground().getX() -player.speed);
				if(player.getX() + player.getWidth() + player.speed <= 1600)player.setX(player.getX() + player.speed ); // player width is 20
				playerDirection = 1;
			}
			else{ 
				if(player.getX() + player.getWidth() + player.speed <= 1600)player.setX(player.getX() + player.speed); // player width is 20
				else player.setX(boundsWidth - player.getWidth());
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
		else{
		if (player.getX()+10 >= evilRedBox.getX() + 50){
			evilRedBox.setX(evilRedBox.getX() + 1) ;
		}
		else{
			evilRedBox.setX(evilRedBox.getX() - 1) ;
		}
		if (player.getY()+10 >= evilRedBox.getY() + 50){
			evilRedBox.setY(evilRedBox.getY() + 1) ;
		}
		else{
			evilRedBox.setY(evilRedBox.getY() - 1) ;
		}
		}
		
		// TODO Hey, look, I can make the enemy grow
		//		if (counter > 120){
		//			counter = 0;
		//			evilRedBox.width += 10;
		//			evilRedBox.height += 10;
		//		}

		//		if (evilRedBox.x > 700){
		//			evilRedBox.x = 700;
		//		}
		//		if (evilRedBox.x < 0){
		//			evilRedBox.x = 0;
		//		}
		//		if (evilRedBox.y > 500){
		//			evilRedBox.y = 500;
		//		}
		//		if (evilRedBox.y < 0){
		//			evilRedBox.y = 0;
		//		}
	}

	public void moveProjectile(boolean gameOver, Projectile projectile) {
		if (gameOver) return;
		
		projectile.setX(projectile.getX() + projectile.getXSpeed()) ;
	}
	
	
}
