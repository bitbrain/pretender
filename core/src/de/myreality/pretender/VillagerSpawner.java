package de.myreality.pretender;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import de.myreality.pretender.graphics.RenderAnimationStrategy;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.graphics.TexturePool;
import de.myreality.pretender.graphics.VillagerTextureGenerator;
import de.myreality.pretender.tweens.EntityTween;
import de.myreality.pretender.util.BruteForceEntityDetector;
import de.myreality.pretender.util.EntityDetector;

public class VillagerSpawner {
	
	public static final float SPAWN_RATE = 0.5f;
	public static final float FRAME_DURATION = 0.5f;
	
	public static final int INITIAL_RATE = 800;
	
	private static final int TEXTURE_CAPACITY = 50;
	
	private static final int VILLAGER_WIDTH = 25;
	private static final int VILLAGER_HEIGHT = 32;
	
	private Entity street;
	
	private Renderer renderer;
	
	private Pool<Entity> entityPool;
	
	private TexturePool texturePool;
	
	private float time;
	
	private TweenManager tweenManager;
	
	private EntityDetector detector;
	
	public VillagerSpawner(Entity street, Renderer renderer, TweenManager tweenManager) {
		this.street = street;
		this.renderer = renderer;
		
		texturePool = new TexturePool(TEXTURE_CAPACITY, new VillagerTextureGenerator());
		texturePool.setTextureSize(VILLAGER_WIDTH * 2, VILLAGER_HEIGHT * 2);
		this.tweenManager = tweenManager;
		
		entityPool = Pools.get(Entity.class);
		detector = new BruteForceEntityDetector(renderer.getRenderTargets(), street);
		
		int spawned = 0;
		int tries = 1000;
		
		while (tries > 0 && spawned < INITIAL_RATE) {
			float x = (float)(street.getX() + street.getWidth() / 2f + (street.getWidth() / 2f) * Math.random());
			float y = (float) (street.getY() + street.getHeight() * Math.random());
			
			if (!detector.hasEntity(x, y)) {
				spawn(x, y);
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
		float x = street.getX() + street.getWidth() - VILLAGER_WIDTH;
		float  y = (float) (street.getY() + street.getHeight() * Math.random());
		
		if (!detector.hasEntity(x, y)) {
			spawn(x, y);
		}
	}
	
	void spawn(float x, float y) {
		Entity villager = entityPool.obtain();
		villager.setDimensions(VILLAGER_WIDTH, VILLAGER_HEIGHT);
		villager.setBody(new Rectangle(0, VILLAGER_HEIGHT - VILLAGER_HEIGHT / 2.2f, VILLAGER_WIDTH, VILLAGER_HEIGHT / 2.2f));
		villager.setPosition(x, y);
		villager.setTexture(texturePool.get());
		RenderAnimationStrategy str = new RenderAnimationStrategy(VILLAGER_WIDTH, VILLAGER_HEIGHT, FRAME_DURATION);
		villager.setRenderStrategy(str);
		villager.setBehavior(new VillagerBehavior(str.getInitialDelay()));		
		renderer.add(villager);
	}
	
	
	class VillagerBehavior implements EntityBehavior {
		
		private float delay;
		
		private float time;
		
		private float waitTime;
		
		public VillagerBehavior(float delay) {
			this.delay = delay;
			time = delay;
			waitTime = (float) (1f + Math.random() * 4f);
		}
		
		private float determineX(Entity entity) {
			float xSpeed = (float) (10.0f + Math.random() * 5f);
			return entity.getX() - xSpeed;
		}
		
		private float determineY(Entity entity) {

			float speed = 8.0f;
			
			float factor = Math.random() > 0.5f ? speed : -speed;
			float newPosY = entity.getY() + factor;
			
			if (newPosY + entity.getBody().getY() <= street.getY()) {
				newPosY = entity.getY() + speed;
			}
			
			if (newPosY + entity.getHeight() >= street.getY() + street.getHeight()) {
				newPosY = entity.getY() - speed;
			}
			
			return newPosY;
		}

		@Override
		public void behave(float delta, Entity entity) {
			
			time += delta;
			
			if (!tweenManager.containsTarget(entity) && time >= waitTime) {
				
				
				float newPosX = determineX(entity);
				float newPosY = determineY(entity);	
				
				if (!detector.hasEntity(newPosX + entity.getBody().getX(), newPosY + entity.getBody().getY())) {
					time = delay;
					waitTime = (float) (1f + Math.random() * 4f);
					delay = 0f;
					
					Tween.to(entity, EntityTween.POS_X, FRAME_DURATION * 2)
						.target(newPosX)
						.ease(TweenEquations.easeInOutCubic)
						.start(tweenManager);
					
					
					Tween.to(entity, EntityTween.POS_Y, FRAME_DURATION * 2)
					.target(newPosY)
					.ease(TweenEquations.easeInOutCubic)
					.start(tweenManager);
				}
			}
		}
		
	}
	
	
}
