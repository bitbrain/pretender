package de.myreality.pretender.controls;

import java.util.Collection;

import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.pretender.Entity;
import de.myreality.pretender.graphics.RenderTarget;
import de.myreality.pretender.util.EntityKiller;
import de.myreality.pretender.util.RenderTargetProvider;

public class IngameControls extends Stage {

	private EntityKiller entityKiller;
	
	private RenderTargetProvider renderTargetProvider;
	
	public IngameControls(EntityKiller entityKiller, RenderTargetProvider renderTargetProvider) {
		super();
		this.entityKiller = entityKiller;
		this.renderTargetProvider = renderTargetProvider;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		
		Collection<RenderTarget> targets = renderTargetProvider.getRenderTargets();
		
		for (RenderTarget t : targets) {
			if (t instanceof Entity) {				
				entityKiller.kill((Entity)t);				
			}
		}
		
		return true;
	}

		
}
