package de.myreality.pretender;

import de.myreality.pretender.graphics.Renderer;

public class VillagerSpawner {
	
	public static final int SPAWN_RATE = 2000;
	
	public static final int INITIAL_RATE = 100;
	
	private static final int VILLAGER_WIDTH = 30;
	private static final int VILLAGER_HEIGHT = 10;
	
	private Entity street;
	
	private Renderer renderer;
	
	private long time;
	
	public VillagerSpawner(Entity street, Renderer renderer) {
		this.street = street;
		this.renderer = renderer;
		
		for (int i = 0; i < INITIAL_RATE; ++i) {
			spawn((float)(street.getX() + street.getWidth() / 2f + (street.getWidth() / 2f) * Math.random()), (float) (street.getY() + street.getHeight() * Math.random()));
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
		spawn(street.getX() + street.getWidth() - VILLAGER_WIDTH, (float) (street.getY() + street.getHeight() * Math.random()));
	}
	
	void spawn(float x, float y) {
		System.out.println("SPAWN!");
	}
}
