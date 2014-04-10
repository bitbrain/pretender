package de.myreality.pretender.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.myreality.pretender.Entity;
import de.myreality.pretender.Entity.Direction;

public class RenderAnimationStrategy implements RenderStrategy {
	
	private Map<Direction, Animation> movingAnimations;
	private Map<Direction, Animation> idleAnimations;
	
	private float time;
	
	private DefaultRenderStrategy defaultRenderStrategy;
	
	private int tileWidth, tileHeight;
	
	private float frameDuration;
	
	public RenderAnimationStrategy(int tileWidth, int tileHeight, float frameDuration) {
		movingAnimations = new HashMap<Direction, Animation>();
		idleAnimations = new HashMap<Direction, Animation>();
		defaultRenderStrategy = new DefaultRenderStrategy();
		time = (float) (Math.random() * frameDuration);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.frameDuration = frameDuration;
	}

	@Override
	public void render(Texture texture, Batch batch, float delta, Entity entity) {
		
		time += delta + 1;
		
		Animation animation = null;
		
		if (entity.isMoving()) {
			
			if (movingAnimations.isEmpty()) {
				fillAnimations(texture, movingAnimations, 1);
			}
			
			animation = movingAnimations.get(entity.getDirection());
		} else {
			
			if (idleAnimations.isEmpty()) {
				fillAnimations(texture, idleAnimations, 0);
			}
			
			animation = idleAnimations.get(entity.getDirection());
		}
		
		batch.draw(animation.getKeyFrame(time), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	}
	
	private void fillAnimations(Texture texture, Map<Direction, Animation> map, int row) {
		TextureRegion[][] regions = TextureRegion.split(texture, tileWidth, tileHeight);
		map.put(Direction.LEFT, new Animation(frameDuration, regions[row][0], regions[row][1]));
		map.put(Direction.RIGHT, new Animation(frameDuration, regions[row][2], regions[row][3]));
		map.get(Direction.LEFT).setPlayMode(PlayMode.LOOP);
		map.get(Direction.RIGHT).setPlayMode(PlayMode.LOOP);
	}

}
