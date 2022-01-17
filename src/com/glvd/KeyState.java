package com.glvd;
public class KeyState {

	private static boolean [] states = new boolean[256];
	
	public static boolean isKeyDown (int code) {
		return states[code];
	}
	
	public static void keyDown (int code) {
		states[code] = true;
	}
	
	public static void keyUp (int code) {
		states[code] = false;
	}
	
	
}
