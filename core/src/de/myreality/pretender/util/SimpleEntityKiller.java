package de.myreality.pretender.util;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
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
		
		// Up in the sky
		Tween.to(entity, EntityTween.OFFSET_Y, 2f)
			 .target(-50f)
			 .ease(TweenEquations.easeInExpo)
			 .start(tweenManager);
		
		// Reduce alpha!
		Tween.to(entity, EntityTween.ALPHA, 2f)
		 .target(0f)
		 .ease(TweenEquations.easeInExpo)
		 .setCallbackTriggers(TweenCallback.COMPLETE)
		 .setCallback(new TweenCallback() {
			 
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				renderer.remove(entity);
			}			 
			
		 })
		 .start(tweenManager);
	}

}
