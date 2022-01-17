package com.glvd;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener{

	Tank t ;
	
	long time2;
	
	public Panel () {
		time2 = System.currentTimeMillis();
		
		
		for (int i = 0; i < 100; i++) {
			ActorCollection.add(i%2);
		}
		
		t = (Tank) ActorCollection.getPlayer();
		
		
	}
	
	
	private void update () {
		long time = System.currentTimeMillis();
		int ms = (int)(time - time2);
		time2 = time;
		
		Camera.setWidthAndHeight(getWidth(), getHeight());
		
		// ѕрив€зать координаты камеры к координатам танка
		Camera.setPosition( 
				t.getX() - getWidth() / 2 + Map.BLOCK_SIZE / 2,
				t.getY() - getHeight()/2 + Map.BLOCK_SIZE / 2 
				);
		
		
		ActorCollection.update(ms);
	}
	
	private void control() {
		
		if (KeyState.isKeyDown( KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		
		//t.stop();
		
		if (KeyState.isKeyDown(KeyEvent.VK_W)) {
			t.up();
		}
		if (KeyState.isKeyDown(KeyEvent.VK_A)) {
			t.left();
	  	}
		if (KeyState.isKeyDown( KeyEvent.VK_D)) {
			t.right();
		}
		if (KeyState.isKeyDown( KeyEvent.VK_S)) {
			t.down();
		}
		
		
		if (KeyState.isKeyDown( KeyEvent.VK_RIGHT )) {
			Camera.move(0.5, 0);
		}
		
		if (KeyState.isKeyDown( KeyEvent.VK_DOWN )) {
			Camera.move(0, 0.5);
		}
		
		if (KeyState.isKeyDown( KeyEvent.VK_UP )) {
			Camera.move(0, -0.5);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		control();
		update();
		
		Map.paint(g, Map.BACKGROUND_LAYER);
		
		ActorCollection.paint(g);
		Map.paint(g, Map.FOREGROUND_LAYER);
		
		repaint();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		KeyState.keyDown( e.getKeyCode()   );
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			t.fire();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		KeyState.keyUp( e.getKeyCode()   );
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
		int xM = e.getX();
		int yM = e.getY();
		
		int x = Camera.getScreenX(t.getX()) + Map.BLOCK_SIZE / 2;
		int y = Camera.getScreenY(t.getY()) + Map.BLOCK_SIZE / 2;
		
		int dx = xM - x;
		int dy = yM - y;
		double angle = Math.atan2(dy, dx);
		
		t.setGunAngle(angle);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			t.fire();
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
