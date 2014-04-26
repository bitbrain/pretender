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

		this.left = left;
		this.parent = parent;
		this.tweenManager = tweenManager;
		this.entityDetector = entityDetector;
		resetWaitTime();
		delay = waitTime;
		time = 0;
	}
	
	public MovementBehavior(Entity parent, EntityDetector entityDetector, TweenManager tweenManager) {
		this(true, parent, entityDetector, tweenManager);
	}
	
	private float determineX(Entity entity) {
		float xSpeed = (float) (10.0f + Math.random() * 5f);			
		return left ? entity.getX() - xSpeed : entity.getX() + xSpeed;
	}
	
	private float determineY(Entity entity) {

		float speed = 15.0f;
		
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
		
		if (time >= waitTime) {
			
			float newPosX = determineX(entity);
			float newPosY = determineY(entity);	
			float deltaX = newPosX - entity.getX();
			float deltaY = newPosY - entity.getY();
			
			Entity other = entityDetector.getEntity(newPosX + entity.getBody().getX(), newPosY + entity.getBody().getY());
			
			if (other == null || other.isDead()) {
				
				time = delay;
				resetWaitTime();
				delay = 0f;
				
				if (entity.isDead()) {
					return;
				}
				
				entity.setX(newPosX);
				entity.setY(newPosY);
				
				entity.setOffset(-deltaX, -deltaY);
				
				if (!tweenManager.containsTarget(entity)) {
					Tween.to(entity, EntityTween.OFFSET_X, 0.3f)
						.target(0f)
						.ease(TweenEquations.easeInOutCubic)
						.start(tweenManager);
					Tween.to(entity, EntityTween.OFFSET_Y, 0.3f)
					.target(0f)
					.ease(TweenEquations.easeInOutCubic)
					.start(tweenManager);
				}
			}
		}
	}
	
	private void resetWaitTime() {
		waitTime = (float) (0.5f + Math.random() * 0.5f);
	}
	
}

