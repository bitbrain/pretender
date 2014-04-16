package de.myreality.pretender.ai;

import de.myreality.pretender.Entity;
import de.myreality.pretender.EntitySpawner;
import de.myreality.pretender.util.EntityDetector;

public class AIHandler {
	
	public static final float SPAWN_RATE = 0.5f;
	public static final float FRAME_DURATION = 0.5f;
	
	public static final int INITIAL_RATE = 3;
	
	private Entity parent;
	
	private float time;
	
	private EntityDetector detector;
	
	private EntitySpawner spawner;
	
	public AIHandler(Entity parent, EntitySpawner spawner, EntityDetector detector) {
		this.parent = parent;		
		this.spawner = spawner;
		this.detector = detector;
		
		int spawned = 0;
		int tries = 1000;
		
		while (tries > 0 && spawned < INITIAL_RATE) {
			float x = (float)(parent.getX() + parent.getWidth() / 2f + (parent.getWidth() / 2f) * Math.random());
			float y = (float) (parent.getY() + parent.getHeight() * Math.random());
			
			if (!detector.hasEntity(x, y)) {
				spawner.spawnVillager(x, y, parent);
				spawned++;
			}
			
			tries--;
		}
	}

	public void update(float delta) {
		
		time += delta;
		
		if (time >= SPAWN_RATE) {
			time = time - SPAWN_RATE;
			spawn();
		}
	}
	
	void spawn() {
		float x = parent.getX() + parent.getWidth() - EntitySpawner.VILLAGER_WIDTH;
		float  y = (float) (parent.getY() + parent.getHeight() * Math.random());
		
		if (!detector.hasEntity(x, y)) {
			spawner.spawnVillager(x, y, parent);
		}
	}
	
}
