package de.myreality.pretender.util;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import de.myreality.pretender.Entity;
import de.myreality.pretender.ai.MovementBehavior;
import de.myreality.pretender.graphics.RenderAnimationStrategy;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.graphics.TexturePool;
import de.myreality.pretender.graphics.VillagerTextureGenerator;

public class EntityFactory {
	
	public static final int TEXTURE_CAPACITY = 50;
	
	public static final int VILLAGER_WIDTH = 49;
	public static final int VILLAGER_HEIGHT = 60;
	
	private Pool<Entity> entityPool;
	
	private Renderer renderer;
	
	private TexturePool villagerTexturePool, policeTexturePool;
	
	private EntityDetector detector;
	
	private TweenManager tweenManager;

	public EntityFactory(Renderer renderer, EntityDetector detector, TweenManager tweenManager) {
		entityPool = Pools.get(Entity.class);
		this.renderer = renderer;
		this.tweenManager = tweenManager;
		this.detector = detector;
		villagerTexturePool = new TexturePool(TEXTURE_CAPACITY, new VillagerTextureGenerator());
		villagerTexturePool.setTextureSize(VILLAGER_WIDTH * 2, VILLAGER_HEIGHT * 2);
		policeTexturePool = new TexturePool(TEXTURE_CAPACITY, new VillagerTextureGenerator());
		policeTexturePool.setTextureSize(VILLAGER_WIDTH * 2, VILLAGER_HEIGHT * 2);
	}
	
	public boolean spawnVillager(float x, float y, Entity parent) {
		Entity entity = spawnEntity(x, y, villagerTexturePool);
		
		if (entity != null) {
			entity.setDimensions(VILLAGER_WIDTH, VILLAGER_HEIGHT);
			entity.setBody(new Rectangle(0, VILLAGER_HEIGHT - VILLAGER_HEIGHT / 2.2f, VILLAGER_WIDTH, VILLAGER_HEIGHT / 2.2f));
			entity.setBehavior(new MovementBehavior(parent, detector, tweenManager));
		}
		
		return false;
	}
	
	public boolean spawnPolice(float x, float y, Entity parent) {
		
		Entity entity = spawnEntity(x, y, policeTexturePool);
		
		if (entity != null) {
			entity.setDimensions(VILLAGER_WIDTH, VILLAGER_HEIGHT);
			entity.setBody(new Rectangle(0, VILLAGER_HEIGHT - VILLAGER_HEIGHT / 2.2f, VILLAGER_WIDTH, VILLAGER_HEIGHT / 2.2f));
			entity.setBehavior(new MovementBehavior(false, parent, detector, tweenManager));
		}
		
		return false;
	}
	
	private Entity spawnEntity(float x, float y, TexturePool texturePool) {
		if (detector.hasEntity(x,  y)) {
			return null;
		}
		
		Entity entity = entityPool.obtain();
		
		entity.setPosition(x, y);
		entity.setTexture(texturePool.get());
		RenderAnimationStrategy str = new RenderAnimationStrategy(VILLAGER_WIDTH, VILLAGER_HEIGHT, 300);
		entity.setRenderStrategy(str);	
		renderer.add(entity);
		return entity;
	}
}
