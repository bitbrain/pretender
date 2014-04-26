package de.myreality.pretender.util;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Expo;
import de.myreality.pretender.Entity;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.tweens.EntityTween;

public class SimpleEntityKiller implements EntityKiller {
	
	private Renderer renderer;
	
	private TweenManager tweenManager;
	
	public SimpleEntityKiller(Renderer renderer, TweenManager tweenManager) {
		this.renderer = renderer;
		this.tweenManager = tweenManager;
	}

	@Override
	public void kill(final Entity entity) {
		
		if (tweenManager.containsTarget(entity)) {
			return;
		}
		
		Expo eq = TweenEquations.easeOutExpo;
		float time = 5f;
		
		// Up in the sky
		Tween.to(entity, EntityTween.OFFSET_Y, time)
			 .target(entity.getOffsetY() -220f)
			 .ease(eq)
			 .start(tweenManager);
		Tween.to(entity, EntityTween.SCALE_Y, time)
			.target(2.0f)
			.ease(eq)
			.start(tweenManager);
		
		// Reduce alpha!
		Tween.to(entity, EntityTween.ALPHA, time)
		 .target(0f)
		 .ease(eq)
		 .setCallbackTriggers(TweenCallback.COMPLETE)
		 .setCallback(new TweenCallback() {
			 
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				renderer.remove(entity);
			}			 
			
		 })
		 .start(tweenManager);
		
		entity.setDead(true);
	}

}
