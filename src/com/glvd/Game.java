package com.glvd;

public class Game {

	public static void main(String[] args) {
		
		Sound.playSound("sountrack.wav");
		
		
		Map.generate(150, 150);
		ImageHelper.loadTexture("sprites7.png");
		ImageHelper.cropImage(0, 2, Map.GROUND);
		ImageHelper.cropImage(0, 3, Map.WATER);
		ImageHelper.cropImage(1, 0, Map.WALL);
		ImageHelper.cropImage(1, 1, Map.BRICK);
		ImageHelper.cropImage(1, 6, Map.BROKEN_BRICK);
		ImageHelper.cropImage(0, 4,Map.BUSH);
		ImageHelper.cropImage(6, 3,Map.TREE);
		ImageHelper.cropImage(9, 0,Map.EMPTY);
		ImageHelper.cropImage(8, 0,Map.DESTROYED_TANK);


		// ��������� ������� ���������
		// 10 - 19
		for (int i = 0; i <= 9; i++) {
			ImageHelper.cropImage(2, i, 19-i);
		}
		// ������� �����
		ImageHelper.cropImage(3, 1, 20);
		
		// ��������� ����� ���������
		// 30 - 39
		for (int i = 0; i <= 9; i++) {
			ImageHelper.cropImage(10, i, 39-i);
		}
		// ����� ����� 40
		ImageHelper.cropImage(3, 3, 40);
		
		// �������� ������
		ImageHelper.cropImage(4, 1, 41);
		
		
		Window w = new Window();
		
		

	}

}


