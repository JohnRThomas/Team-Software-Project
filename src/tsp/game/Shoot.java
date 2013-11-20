package tsp.game;

import java.util.ArrayList;

public class Shoot {
	public boolean shoot(Player player, ArrayList<Projectile> projectileList, boolean leftShoot, boolean rightShoot, boolean upShoot, boolean downShoot) {

		if(leftShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), -15, 0, "res/images/40x10greenLaserFlat.png", false)) ;
			return true ;
		}
		else if(rightShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 15, 0, "res/images/40x10greenLaserFlat.png", false)) ;
			return true ;
		}
		else if(upShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 0, -15, "res/images/40x10greenLaserFlat.png", false)) ;
			return true ;
		}
		else if(downShoot) {
			projectileList.add(new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 0, 15, "res/images/40x10greenLaserFlat.png", false)) ;
			return true ;
		}
		return false ;
	}
}
