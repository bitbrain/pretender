package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.myreality.pretender.Entity;
import de.myreality.pretender.Entity.Direction;

public class RenderAnimationStrategy implements RenderStrategy {
	
	private Animation movingAnimation, idleAnimation;
	
	private float time;
	
	private float initialDelay;
	
	private int tileWidth, tileHeight;
	
	private float frameDuration;
	
	public RenderAnimationStrategy(int tileWidth, int tileHeight, float frameDuration) {
		initialDelay = (float) (Math.random() * frameDuration);
		time = initialDelay;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.frameDuration = frameDuration;
	}
	
	public float getInitialDelay() {
		return initialDelay;
	}

	@Override
	public void render(Texture texture, Batch batch, float delta, Entity entity) {
		
		time += delta + 1;
		
		if (movingAnimation == null || idleAnimation == null) {
			initAnimations(texture);
		}
		
		Animation animation = null;
		
		if (entity.isMoving()) {
			animation = movingAnimation;			
		} else {
			animation = idleAnimation;
		}
		
		batch.setColor(entity.getColor());
		
		float downScaleX = entity.getWidth() - (entity.getWidth() * entity.getScaleX()) / 2;
		float downScaleY = entity.getHeight() - (entity.getHeight() * entity.getScaleY()) / 2;
		
		if (entity.getDirection() == Direction.RIGHT) {
			batch.draw(animation.getKeyFrame(time), 
					entity.getX() + entity.getOffsetX() + entity.getWidth() + downScaleX, 
					entity.getY() + entity.getOffsetY() + downScaleY, 
					-(entity.getWidth() * entity.getScaleX()), 
					entity.getHeight() * entity.getScaleY());
		} else {
			batch.draw(animation.getKeyFrame(time), 
					entity.getX() + entity.getOffsetX() + entity.getWidth() + downScaleX, 
					entity.getY() + entity.getOffsetY() + downScaleY, 
					entity.getWidth() * entity.getScaleX(), 
					entity.getHeight() * entity.getScaleY());
		}
	}
	
	private void initAnimations(Texture texture) {
		TextureRegion[][] regions = TextureRegion.split(texture, tileWidth, tileHeight);
		idleAnimation = new Animation(frameDuration, regions[0][0], regions[0][1]);
		movingAnimation = new Animation(frameDuration, regions[1][0], regions[1][1]);
		idleAnimation.setPlayMode(PlayMode.LOOP);
		movingAnimation.setPlayMode(PlayMode.LOOP);
	}

}
