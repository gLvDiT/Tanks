package com.glvd;
import java.util.ArrayList;

public class Target {
	
	private ArrayList<Actor> collection = new ArrayList<Actor>();
	
	public Actor getTarget(Actor predator) {
		
		return ActorCollection.getNearestActor(predator);
	}
	
	public void update (int ms) {
		
	}
	

}
