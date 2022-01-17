package com.glvd;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Collider {
	
	private int[] collisionInfo = new int[3];

	private  double x;
	private  double y;
	private  int width;
	private  int height;
	private  int paddingX;
	private  int paddingY;
	 
	private ArrayList<Integer> blackList = new ArrayList<Integer>();
	
	public int[] getCollisionInfo() {
		return collisionInfo;
	}

	private Rectangle getRect() {
		Rectangle r = new Rectangle((int)x + paddingX, (int)y + paddingY, width - paddingX*2, height - paddingY*2 );
		return r;		
	}
	
	public boolean checkCollision(Collider r) {
		
		return getRect().intersects( r.getRect() ); 
		
	}
	

	public void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}

	public void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}

	public void addToBlackList(int object) {
		blackList.add(object);
	}
	
	public void removeFromBlackList(int object) {
		blackList.remove(new Integer(object));
	}
	
	public boolean contains(int object) {
		return blackList.contains(object);
	}
	
	public Collider (double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width  = width;
		this.height = height;
	}

	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean checkMapCollision() {
		Point [] array = new Point[4];
		array[0] = new Point((int)x + paddingX, (int)y + paddingY);
		array[1] = new Point((int)x + width - paddingX, (int)y + paddingY);
		array[2] = new Point((int)x + width - paddingX, (int)y + height - paddingY);
		array[3] = new Point((int)x + paddingX, (int)y + height - paddingY);
		
		for (Point p : array ) {
			
			int row = Map.getRowByY(p.y);
			int col = Map.getColByX(p.x);
			
			int tile = Map.getTile(row, col);
			System.out.println(row + " " + col);
			
			if (contains(tile)) {
				collisionInfo[0] = row;
				collisionInfo[1] = col;
				collisionInfo[2] = tile;
				
				return true;
				
			}
		}
		
		return false;
	}
	
	
	
	
}
