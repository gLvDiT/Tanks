package com.glvd;

import java.awt.Graphics;

public class Actor {
	
	protected boolean  collisionDetected = false;
	protected Sprite   body;
	protected Collider collider;
	protected double   hp;
	protected int team;
	
	public int getTeam() {
		return team;
	}
	
	public void deactivate() {
		body.setActive(false);
	}
	
	public boolean isActive() {
		return body.isActive();
	}
	
	
	public void setFirstFrame(int frame) {
		body.setFirstFrame(frame);
	}
	public void setLastFrame(int frame) {
		body.setLastFrame(frame);
	}
	
	
	public double getX() {
		return body.getX();
	}
	
	public void stop () {
		body.stop();
	}

	public double getY() {
		return body.getY();
	}

	public void right() {
		body.right();
	}

	public void down() {
		body.down();
	}

	public void left() {
		body.left();
	}

	public void up() {
		body.up();
	}

	public Actor(int x, int y, double speed, double angle, int frameFirst, int frameLast) {
		body = new Sprite(x, y, speed, angle, frameFirst, frameLast);
		collider = new Collider(x, y, Map.BLOCK_SIZE, Map.BLOCK_SIZE);
	}
	
	public void paint(Graphics g) {
		body.paint(g);
	}
	
	public void collisionEvent () {
			body.undoMoving();
			body.stop();
	}
	
	public void update (int ms) {
		body.update(ms);
		collider.setX( body.getX() );
		collider.setY( body.getY() );
		
		collisionDetected = false;
		if (collider.checkMapCollision() ) {
			collisionDetected = true;
			collisionEvent();
		}
		
	}
	
	
	
}
