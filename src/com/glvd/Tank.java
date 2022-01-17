package com.glvd;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Tank extends Actor {

	private Target target = new Target();
	
	private boolean bot = true;
	
	
	private int fireTime 		= 1000;
	private int currentFireTime = 0;
	
	private Sprite gun;

	private int currentTimeDirection = 0;
	private int timeDirection        = 0;
	
	
	private void AIFire () {
		Actor act = target.getTarget(this);
		
		if (act == null) {
			return;
		}
	
		double xM = act.getX();
		double yM = act.getY();
		
		double x = getX();
		double y = getY();
		
		double dx = xM - x;
		double dy = yM - y;
		double angle = Math.atan2(dy, dx);
		
		double d = Point.distance(x, y, xM, yM);
		if (d < Map.BLOCK_SIZE * 15) {
			setGunAngle(angle);
			
			if (d < Map.BLOCK_SIZE * 8 && Math.random() < 0.01 ) {
				fire();
			}
			
		}
		
	}
	
	
	public void setGunAngle (double angle) {
		gun.setAngle(angle);
	}
	
	
	@Override
	public void deactivate() {
		super.deactivate();
		gun.setFirstFrame(Map.DESTROYED_TANK);
		gun.setLastFrame(Map.DESTROYED_TANK);
		
		gun.setAngle( Math.random() * Math.PI * 2  );
		
	}
	
	
	public double getGunAngle() {
		return gun.getAngle();
	}
	
	
	public void fire() {
		if (currentFireTime >= fireTime) {
			Sound.playSound("fire.wav");
			currentFireTime = 0;
			
			ActorCollection.addRocket(this);
		}
		
	}
	
	
	
	private void AI () {
		
		AIFire ();
		
		if (collisionDetected) {
			changeDirection();
		}
		
		if (currentTimeDirection >= timeDirection) {	
			  currentTimeDirection = 0;
			  timeDirection = (int)(Math.random() * 5000); 
					  
				if (Math.random() >= 0.8 ) {
					AI1direction ();
				}
				else {
					AI2direction ();
				}
		  }
		
		
	}
	
	private void AI2direction () {
		
		Actor victim = target.getTarget(this);
		if (victim == null) {
			return;
		}
		
		// —ближение по горизонтали
		if (Math.random() < 0.5) {
			
			if (getX() > victim.getX()) {
				left();
			}
			else {
				right();
			}
			
		}
		// —ближение по вертикали
		else {
			
			if (victim.getY() < getY()) {
				up();
			}
			else {
				down();
			}
			
		}
		
		
		
	}
	
	
	
	public void setBot (boolean b) {
		bot = b;
	}
	
	
	private void AI1direction () {
		
		changeDirection();
	
	}
	
	
	private void changeDirection() {
		Random r = new Random();
		int n = r.nextInt(4);
		if (n == 0) {
			up();
		}
		else if (n == 1) {
			down();
		}
		else if (n == 2) {
			right();
		}
		else {
			left();
		}
	}
	
	
	
	
	public Tank(int x, int y, int team) {
		super(x, y, Map.BLOCK_SIZE * 2, 0, 10, 19);
		
		this.team = team;
		
		if (team == 1) {
			setFirstFrame(30);
			setLastFrame(39);
		}
		
		collider.addToBlackList(Map.BRICK);
		collider.addToBlackList(Map.WALL);
		collider.addToBlackList(Map.WATER);
		collider.addToBlackList(Map.BROKEN_BRICK);
		
		collider.setPaddingX(3);
		collider.setPaddingY(3);
		
		gun = new Sprite(x, y, 0, 0, 20, 20);
		if (team == 1) {
			gun.setFirstFrame(40);
			gun.setLastFrame(40);
		}
		
	}
	
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		gun.paint(g);
		
	   Actor victim =	 target.getTarget(this);
	   
	 /*  g.drawLine( Camera.getScreenX( getX() )  , Camera.getScreenY( getY() ) , 
			   
			   Camera.getScreenX( victim.getX() )  , Camera.getScreenY( victim.getY() ));*/
		
	}	
	
	@Override
	public void update(int ms) {
		super.update(ms);
		
		currentFireTime += ms;
		currentTimeDirection += ms;
		
		gun.setX( getX()  );
		gun.setY( getY()  );
		
		if (bot) {
			AI();			
		}
	}
	

}
