package de.myreality.pretender.util;

import aurelienribon.tweenengine.Tween;
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
	public void kill(Entity entity) {
		
		Tween.to(entity, EntityTween.OFFSET_Y, 2f)
			 .target(-50f)
			 .ease(TweenEquations.easeInExpo)
			 .start(tweenManager);
		Tween.to(entity, EntityTween.OFFSET_Y, 2f)
		 .target(-50f)
		 .ease(TweenEquations.easeInExpo)
		 .start(tweenManager);
	}

}
