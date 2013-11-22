package tsp.game;

import java.util.ArrayList;

/**
 * A magical class that makes projectiles go PewPew! and stuff
 *
 */
public class Shoot {
	public boolean shoot(Player player, ArrayList<Projectile> projectileList, boolean leftShoot, boolean rightShoot, boolean upShoot, boolean downShoot) {

		if(leftShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/4), (player.getY()+player.getHeight()/4), -15, 0, false, "res/images/40x10greenLaserFlat.png", true)) ;
			return true ;
		}
		else if(rightShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 15, 0, false, "res/images/40x10greenLaserFlat.png", true)) ;
			return true ;
		}
		else if(upShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()-player.getHeight()/4), 0, -15, false, "res/images/10x40greenLaserVert.png", true)) ;
			return true ;
		}
		else if(downShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/2), 0, 15, false, "res/images/10x40greenLaserVert.png", true)) ;
			return true ;
		}
		return false ;
	}
}
