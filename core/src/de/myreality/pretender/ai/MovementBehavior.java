package de.myreality.pretender.ai;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.myreality.pretender.Entity;
import de.myreality.pretender.tweens.EntityTween;
import de.myreality.pretender.util.EntityDetector;

public class MovementBehavior implements EntityBehavior {
	
	private float delay;
	
	private float time;
	
	private float waitTime;
	
	private boolean left;
	
	private Entity parent;
	
	private TweenManager tweenManager;
	
	private EntityDetector entityDetector;
	
	public MovementBehavior(boolean left, Entity parent, EntityDetector entityDetector, TweenManager tweenManager) {
		time = delay;
		this.left = left;
		this.parent = parent;
		this.tweenManager = tweenManager;
		this.entityDetector = entityDetector;
		waitTime = (float) (1f + Math.random() * 4f);
	}
	
	public MovementBehavior(Entity parent, EntityDetector entityDetector, TweenManager tweenManager) {
		this(true, parent, entityDetector, tweenManager);
	}
	
	private float determineX(Entity entity) {
		float xSpeed = (float) (10.0f + Math.random() * 5f);			
		return left ? entity.getX() - xSpeed : entity.getX() + xSpeed;
	}
	
	private float determineY(Entity entity) {

		float speed = 18.0f;
		
		float factor = Math.random() > 0.5f ? speed : -speed;
		float newPosY = entity.getY() + factor;
		
		if (newPosY + entity.getBody().getY() <= parent.getY()) {
			newPosY = entity.getY() + speed;
		}
		
		if (newPosY + entity.getHeight() >= parent.getY() + parent.getHeight()) {
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
			
			if (!entityDetector.hasEntity(newPosX + entity.getBody().getX(), newPosY + entity.getBody().getY())) {
				
				time = delay;
				waitTime = (float) (1f + Math.random() * 4f);
				delay = 0f;
				Tween.to(entity, EntityTween.POS_X, 500 * 2)
					.target(newPosX)
					.ease(TweenEquations.easeInOutCubic)
					.start(tweenManager);
				
				
				Tween.to(entity, EntityTween.POS_Y, 500 * 2)
				.target(newPosY)
				.ease(TweenEquations.easeInOutCubic)
				.start(tweenManager);
			}
		}
	}
	
}

