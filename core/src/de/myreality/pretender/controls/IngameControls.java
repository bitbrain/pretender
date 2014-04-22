package de.myreality.pretender.controls;

import java.util.Collection;

import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.pretender.Entity;
import de.myreality.pretender.graphics.RenderTarget;
import de.myreality.pretender.util.EntityDetector;
import de.myreality.pretender.util.EntityKiller;

public class IngameControls extends Stage {

	private EntityKiller entityKiller;
	
	private EntityDetector entityDetector;
	
	public IngameControls(EntityKiller entityKiller, EntityDetector detector) {
		super();
		this.entityKiller = entityKiller;
		this.entityDetector = detector;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		makeKill(screenX, screenY);		
		
		return true;
	}
	
	

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		super.touchDragged(screenX, screenY, pointer);
		
		makeKill(screenX, screenY);
		
		return true;
	}

	private void makeKill(int x, int y) {
		Entity entity = entityDetector.getEntity(x, y);
		
		if (entity != null) {
			entityKiller.kill(entity);
		}
	}
}
