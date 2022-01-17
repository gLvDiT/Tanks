package com.glvd;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class ActorCollection {

	private static ArrayList<Actor> collection = new ArrayList<Actor>();
	
	private static ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	
	
	public static void addRocket (Tank owner) {
		
		Rocket r = new Rocket(owner);
		rockets.add(r);
		
	}
	
	
	public static void add(int team) {
		
		int[] pos = Map.spawn();
		
		Tank t = new Tank(Map.BLOCK_SIZE * pos[1], Map.BLOCK_SIZE * pos[0], team);
		
		if (collection.size()  < 2) {
			t.setBot(false);
		}
		
		
		collection.add(t);
		
 		
	}
	
	public static void update (int ms) {
		
		for (int i = collection.size() - 1 ; i >= 0 ; i--) {
			Actor act = collection.get(i);
			if ( ! act.isActive() ) {
				collection.remove(i);
			}
			else {
				act.update(ms);
			}
		}
		
		
		for (int i = rockets.size() - 1; i >= 0 ; i--) {
			Rocket act = rockets.get(i);
			
			if (!act.isActive()) {
				rockets.remove(i);
				continue;
			}
			
			act.update(ms);
			
			for (Actor t : collection ) {
				if (act.checkActorCollision(t)) {
					// Если пересекается ракета и танк 
					act.deactivate();
					t.deactivate();
					
					Sound.playSound("destroy.wav");
				}
			}
		}
		
	}
	
	public static void paint(Graphics g) {
		
		for (Rocket r : rockets ) {
			r.paint(g);
		}
		
		for (Actor act : collection ) {
			act.paint(g);
		}
		
		
	}

	public static Actor getPlayer() {
		return collection.get(0);
	}
	
	public static Actor getPlayer2() {
		return collection.get(1);
	}
	
	public static Actor getRandomActor() {
		return  collection.get( collection.size() - 1 );    
	}
	
	public static Actor getNearestActor(Actor predator) {
		
		Actor victim = null;
		double D = Double.MAX_VALUE;
		for (Actor act : collection) {
			if (act.getTeam() != predator.getTeam() ) {
				double d = Point.distance(act.getX(), act.getY(), predator.getX(), predator.getY());
				if (d < D) {
					D = d;
					victim = act;
				}
			}
		}
		return victim;
		  
	}
	
		
		
}
