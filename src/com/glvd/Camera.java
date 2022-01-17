package com.glvd;
public class Camera {

	private static double x = 0, y = 5 * Map.BLOCK_SIZE;
	private static int    w = 800, h = 600;
	
	
	public static void setWidthAndHeight(int width, int height) {
		w = width;
		h = height;
	}

	
	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}
	
	public static double getX2() {
		return x + w - 1;
	}

	public static double getY2() {
		return y + h - 1;
	}




	public static void move (double deltaX, double deltaY) {
		
		setPosition( x + deltaX   , y + deltaY  );
		
	}
	
	public static void setPosition (double x, double y) {
		Camera.x = x;
		Camera.y = y;
		
		if (Camera.x < 0) {
			Camera.x = 0;
		}
		if (Camera.y < 0) {
			Camera.y = 0;
		}
		if (Camera.x > Map.getWorldWidth() - w) {
			Camera.x = Map.getWorldWidth() - w;
		}
		
		if (Camera.y > Map.getWorldHeight() - h) {
			Camera.y = Map.getWorldHeight() - h;
		}
		
	}
	
	public static int getScreenX (double X) {
		return (int)(X - Camera.x);
	}
	public static int getScreenY (double Y) {
		return (int)(Y - Camera.y);
	}
	
}
