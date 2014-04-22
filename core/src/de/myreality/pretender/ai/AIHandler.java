package de.myreality.pretender.ai;

import de.myreality.pretender.Entity;
import de.myreality.pretender.util.EntityFactory;

public class AIHandler {
	
	public static final float SPAWN_RATE = 0.5f;
	public static final float FRAME_DURATION = 0.5f;
	
	public static final int INITIAL_RATE = 510;
	
	private Entity parent;
	
	private float time;
	
	private EntityFactory spawner;
	
	public AIHandler(Entity parent, EntityFactory spawner) {
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
		
		/*for (int i = 0; i < 60; ++i) {
			float x = parent.getX();
			float y = parent.getY() + i * 10;
			
			spawner.spawnPolice(x, y, parent);
		}*/
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
		float x = parent.getX() + parent.getWidth() - EntityFactory.VILLAGER_WIDTH;
		float  y = (float) (parent.getY() + parent.getHeight() * Math.random());
		return spawner.spawnVillager(x, y, parent);
	}
	
}
