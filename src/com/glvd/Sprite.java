package com.glvd;
import java.awt.Graphics;

public class Sprite {

	private double x, y;
	private double _x, _y;
	
	private double speed = Map.BLOCK_SIZE * 2;
	private double _speed;
	
	private double angle = Math.PI / 4;
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	private boolean animationStopped = false;
	
	// номер текущего кадра
	private int frame = 10;
	private int frameFirst = 10;
	private int frameLast  = 19;
	private int frameTime  = 80;
	private int currentFrameTime = 0;
	
	private boolean active = true;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getAngle() {
		return angle;
	}
	
	public void setFirstFrame(int frame) {
		frameFirst = frame;
		this.frame = frame;
		
	}
	public void setLastFrame(int frame) {
		frameLast = frame;
	}
	
	

	public void setAnimationStopped(boolean animationStopped) {
		this.animationStopped = animationStopped;
	}


	public Sprite (int x, int y, double speed, double angle, int frameFirst, int frameLast) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.frameFirst = frameFirst;
		this.frameLast = frameLast;
		this.frame = frameFirst;
		
		this._speed = speed;
	}
	
	
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void paint (Graphics g) {
		if (active) {
			ImageHelper.paint(g, angle, frame, Camera.getScreenX(x), Camera.getScreenY(y));
		}
	}
	
	private void frameUpdate(int ms) {
		currentFrameTime += ms;
		if (currentFrameTime >= frameTime && !animationStopped ) {
			nextFrame();
			currentFrameTime = 0;
		}
	}
	
	private void movingUpdate(int ms) {
		double deltaX, deltaY;

		deltaX = speed * Math.cos(angle) * ms / 1000.0;
		deltaY = speed * Math.sin(angle) * ms / 1000.0;
		
		x += deltaX;
		y += deltaY;
		
		//angle += 0.001;
	}
	
	public void stop () {
		speed = 0;
		setAnimationStopped(true);
	}
	
	public void right() {
		angle = 0;
		speed = _speed;
		setAnimationStopped(false);
	}
	public void down() {
		angle = Math.PI / 2;
		speed = _speed;
		setAnimationStopped(false);
	}
	public void left() {
		angle = Math.PI ;
		speed = _speed;
		setAnimationStopped(false);
	}
	public void up() {
		angle = -Math.PI / 2 ;
		speed = _speed;
		setAnimationStopped(false);
	}
	
	
	public void update ( int ms  ) {
		if (active) {
			_x = x;
			_y = y;
			frameUpdate(ms);
			movingUpdate(ms);
		}
		
	}
	
	
	public void nextFrame() {
		frame++;
		
		if (frame > frameLast) {
			frame = frameFirst;
		}
	}

	public void undoMoving() {
		x = _x;
		y = _y;
	}



	public void setX(double x2) {
		x = x2;
	}

	public void setY(double y2) {
		y = y2;
		
	}
	
	
}
