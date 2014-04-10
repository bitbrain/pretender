package de.myreality.pretender;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import de.myreality.pretender.graphics.RenderAnimationStrategy;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.graphics.TexturePool;
import de.myreality.pretender.graphics.VillagerTextureGenerator;

public class VillagerSpawner {
	
	public static final float SPAWN_RATE = 0.5f;
	public static final float FRAME_DURATION = 0.5f;
	
	public static final int INITIAL_RATE = 100;
	
	private static final int TEXTURE_CAPACITY = 50;
	
	private static final int VILLAGER_WIDTH = 25;
	private static final int VILLAGER_HEIGHT = 40;
	
	private Entity street;
	
	private Renderer renderer;
	
	private Pool<Entity> entityPool;
	
	private TexturePool texturePool;
	
	private float time, totalTime;
	
	private TweenManager tweenManager;
	
	public VillagerSpawner(Entity street, Renderer renderer, TweenManager tweenManager) {
		this.street = street;
		this.renderer = renderer;
		
		texturePool = new TexturePool(TEXTURE_CAPACITY, new VillagerTextureGenerator());
		texturePool.setTextureSize(VILLAGER_WIDTH * 2, VILLAGER_HEIGHT * 2);
		this.tweenManager = tweenManager;
		
		entityPool = Pools.get(Entity.class);
		
		for (int i = 0; i < INITIAL_RATE; ++i) {
			spawn((float)(street.getX() + street.getWidth() / 2f + (street.getWidth() / 2f) * Math.random()), (float) (street.getY() + street.getHeight() * Math.random()));
		}
	}

	public void update(float delta) {
		
		time += delta;
		totalTime += delta;
		
		if (time >= SPAWN_RATE) {
			time = time - SPAWN_RATE;
			spawn();
		}
	}
	
	void spawn() {
		spawn(street.getX() + street.getWidth() - VILLAGER_WIDTH, (float) (street.getY() + street.getHeight() * Math.random()));
	}
	
	void spawn(float x, float y) {
		Entity villager = entityPool.obtain();
		villager.setPosition(x, y);
		villager.setDimensions(VILLAGER_WIDTH, VILLAGER_HEIGHT);
		
		villager.setTexture(texturePool.get());
		villager.setRenderStrategy(new RenderAnimationStrategy(VILLAGER_WIDTH, VILLAGER_HEIGHT, FRAME_DURATION));
		villager.setBehavior(new VillagerBehavior());		
		renderer.add(villager);
	}
	
	
	class VillagerBehavior implements EntityBehavior {
		
		private float timeStamp = (float) (Math.random() * 1000.0f);

		@Override
		public void behave(float delta, Entity entity) {
			if (!tweenManager.containsTarget(entity)) {
				
				
			}
		}
		
	}
	
	
}
