package com.glvd;
import javax.swing.JFrame;

public class Window extends JFrame  {

	public Window () {
		
		setExtendedState(MAXIMIZED_BOTH);
		
		setUndecorated(true);
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Panel p = new Panel();
		
		add(p);
		addKeyListener(p);
		addMouseListener(p);
		addMouseMotionListener(p);
		
		revalidate();
		
	}

}
