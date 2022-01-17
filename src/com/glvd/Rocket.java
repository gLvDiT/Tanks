package com.glvd;
public class Rocket extends Actor {

	Tank owner;
	
	public Rocket(Tank t) {
		super((int)t.getX(), (int)t.getY(), Map.BLOCK_SIZE * 7 , t.getGunAngle() , 41, 41);
		owner = t;
		
		collider.setPaddingX(8);
		collider.setPaddingY(12);
		
		collider.addToBlackList(Map.BRICK);
		collider.addToBlackList(Map.BROKEN_BRICK);
		collider.addToBlackList(Map.WALL);
	}
	
	@Override
	public void collisionEvent() {
		super.collisionEvent();
		deactivate();
		
		//Sound.playSound("destroy.wav");
		
		int [] info = collider.getCollisionInfo();
		int row = info[0];
		int col = info[1];
		int tile = info[2];
		if (tile == Map.BRICK) {
			Map.setTile(row, col, Map.BROKEN_BRICK);
		}
		else if (tile == Map.BROKEN_BRICK) {
			Map.setTile(row, col, Map.GROUND);
		}
		
		
	}
	
	public boolean checkActorCollision(Actor act) {
		
		if (owner != act) {
			Collider rC; // Коллайдер ракеты
			Collider aC; // Коллайдер Актера
			
			rC = collider;
			aC = act.collider;
			
			return rC.checkCollision(aC) && isActive() && act.isActive();
		}
		else {
			return false;
		}
		
	
	}
	
	
	

}
