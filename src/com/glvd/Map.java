package com.glvd;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Map {
	
	
	public static final int BACKGROUND_LAYER = 0;
	public static final int FOREGROUND_LAYER = 1;
	
	public static final int BLOCK_SIZE = 32;
	
	public static final int GROUND = 0;
	public static final int WALL   = 1;
	public static final int WATER  = 2;
	public static final int BRICK  = 3;	
	public static final int BROKEN_BRICK  = 4;	
	public static final int BUSH   = 5;
	public static final int TREE   = 6;
	public static final int EMPTY   = 7;
	public static final int DESTROYED_TANK   = 8;
	
	private static int[][] matrix;
	
	public static int getTile (int row, int col) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) {
			return EMPTY;
		}
		return matrix[row][col];
	}
	
	public static int[] spawn() {
		
		Random r = new Random();
		
		while (true) {
			int row  = r.nextInt(matrix.length);
			int col  = r.nextInt(matrix[0].length);
			int tile = getTile(row, col);
			
			if (tile == Map.GROUND) {
				return new int[] {row, col};
			}
		}
		
		
	}
	
	public static void setTile (int row, int col, int tile) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) {
			return;
		}
		
		matrix[row][col] = tile;
	}
	
	public static int getWorldWidth() {
		return matrix[0].length * BLOCK_SIZE;
	}
	public static int getWorldHeight() {
		return matrix.length * BLOCK_SIZE;
	}
	
	
	public static void generate(int row, int col) {
		matrix = new int[row][col];
		
		for (int i = 0; i < col; i++) {
			matrix[0][i] 		= WALL;
			matrix[row - 1][i] 	= WALL;
		}
		for (int j = 1; j < row-1; j++) {
			matrix[j][0] 		= WALL;
			matrix[j][col-1] 	= WALL;
		}
		
		Random rand = new Random();
		
		for (int r = 1; r <= row-2; r++ ) {
			for (int c = 1; c <= col-2; c++) {
				if (rand.nextInt(7) == 0) {
					matrix[r][c] = rand.nextInt(7);
				}
				
			}
		}
		
	}
	
	public static int getColByX (int x) {
		return x / BLOCK_SIZE;
	}
	
	public static int getRowByY (int y) {
		return y / BLOCK_SIZE;
	}
	
	
	public static void paint(Graphics g, int layer) {
		
		int firstRow =   getRowByY( (int)Camera.getY() );
		int lastRow  = getRowByY( (int)Camera.getY2() );;
	    int firstCol = getColByX( (int)Camera.getX() );
	    int lastCol  = getColByX( (int)Camera.getX2() );
		
		
		for (int row = firstRow; row <= lastRow; row++) {
			for (int col = firstCol; col <= lastCol; col++) {
				int cell = matrix[row][col];
				
				if (layer == BACKGROUND_LAYER) {
					if (cell == BUSH || cell == TREE) {
						cell = GROUND;
					}
				}
				else {
					if (cell != BUSH && cell != TREE) {
						continue;
					}
				}
				
				ImageHelper.paint(g, cell, Camera.getScreenX(col * 32), Camera.getScreenY(row * 32));
				
			}
			
		}
		
		
	}

}
