package tsp.game;

public class Shoot {
	public boolean shoot(Player player, Projectile[] projectileList, int shotCount, boolean leftShoot, boolean rightShoot, boolean upShoot, boolean downShoot) {
		
		int speed = 15 ; //Probably shouldn't be hard-coded but w/e, it can be changed later
		if(shotCount < projectileList.length) {
			if(leftShoot) {
				projectileList[shotCount] = new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), -15, 0, "res/images/40x10greenLaser.png", false) ;
				return true ;
			}
			else if(rightShoot) {
				projectileList[shotCount] = new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 15, 0, "res/images/40x10greenLaser.png", false) ;
				return true ;
			}
			else if(upShoot) {
				projectileList[shotCount] = new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 0, -15, "res/images/40x10greenLaser.png", false) ;
				return true ;
			}
			else if(downShoot) {
				projectileList[shotCount] = new Projectile((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/4), 0, 15, "res/images/40x10greenLaser.png", false) ;
				return true ;
			}
		}
		return false ;
	}

}
