package de.myreality.pretender.ai;

import de.myreality.pretender.Entity;
import de.myreality.pretender.EntitySpawner;

public class AIHandler {
	
	public static final float SPAWN_RATE = 0.5f;
	public static final float FRAME_DURATION = 0.5f;
	
	public static final int INITIAL_RATE = 510;
	
	private Entity parent;
	
	private float time;
	
	private EntitySpawner spawner;
	
	public AIHandler(Entity parent, EntitySpawner spawner) {
		this.parent = parent;		
		this.spawner = spawner;
		
		int spawned = 0;
		int tries = 1000;
		
		while (tries > 0 && spawned < INITIAL_RATE) {
			float x = (float)(parent.getX() + parent.getWidth() / 1.5f + (parent.getWidth() / 1.5f) * Math.random());
			float y = (float) (parent.getY() + parent.getHeight() * Math.random());
	
				if (spawner.spawnVillager(x, y, parent)) {
					spawned++;
				}
			
			tries--;
		}
	}

	public void update(float delta) {
		
		time += delta;
		
		if (time >= SPAWN_RATE) {
			if (spawn()) {
				time = time - SPAWN_RATE;
			}
		}
	}
	
	boolean spawn() {
		float x = parent.getX() + parent.getWidth() - EntitySpawner.VILLAGER_WIDTH;
		float  y = (float) (parent.getY() + parent.getHeight() * Math.random());
		return spawner.spawnVillager(x, y, parent);
	}
	
}
