package de.myreality.pretender;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import de.myreality.pretender.ai.MovementBehavior;
import de.myreality.pretender.graphics.RenderAnimationStrategy;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.graphics.TexturePool;
import de.myreality.pretender.graphics.VillagerTextureGenerator;
import de.myreality.pretender.util.EntityDetector;

public class EntitySpawner {
	
	public static final int TEXTURE_CAPACITY = 50;
	
	public static final int VILLAGER_WIDTH = 25;
	public static final int VILLAGER_HEIGHT = 32;
	
	private Pool<Entity> entityPool;
	
	private Renderer renderer;
	
	private TexturePool texturePool;
	
	private EntityDetector detector;
	
	private TweenManager tweenManager;

	public EntitySpawner(Renderer renderer, EntityDetector detector, TweenManager tweenManager) {
		entityPool = Pools.get(Entity.class);
		this.renderer = renderer;
		this.tweenManager = tweenManager;
		this.detector = detector;
		texturePool = new TexturePool(TEXTURE_CAPACITY, new VillagerTextureGenerator());
		texturePool.setTextureSize(VILLAGER_WIDTH * 2, VILLAGER_HEIGHT * 2);
	}
	
	public void spawnVillager(float x, float y, Entity parent) {
		Entity villager = entityPool.obtain();
		villager.setDimensions(VILLAGER_WIDTH, VILLAGER_HEIGHT);
		villager.setBody(new Rectangle(0, VILLAGER_HEIGHT - VILLAGER_HEIGHT / 2.2f, VILLAGER_WIDTH, VILLAGER_HEIGHT / 2.2f));
		villager.setPosition(x, y);
		villager.setTexture(texturePool.get());
		RenderAnimationStrategy str = new RenderAnimationStrategy(VILLAGER_WIDTH, VILLAGER_HEIGHT, 300);
		villager.setRenderStrategy(str);
		villager.setBehavior(new MovementBehavior(parent, detector, tweenManager));	
		renderer.add(villager);
	}
}
