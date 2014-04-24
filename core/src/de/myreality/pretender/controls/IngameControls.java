package de.myreality.pretender.controls;

import java.util.Collection;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.pretender.Entity;
import de.myreality.pretender.util.EntityDetector;
import de.myreality.pretender.util.EntityKiller;

public class IngameControls extends Stage {
	
	public float CURSOR_SIZE = 64;

	private EntityKiller entityKiller;
	
	private EntityDetector entityDetector;
	
	private Entity cursor;
	
	public IngameControls(EntityKiller entityKiller, EntityDetector detector) {
		super();
		this.entityKiller = entityKiller;
		this.entityDetector = detector;
		cursor = new Entity();
		cursor.setBody(new Rectangle(0, 0, CURSOR_SIZE, CURSOR_SIZE));
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
		
		// Apply cursor
		cursor.getBody().x = x - CURSOR_SIZE;
		cursor.getBody().y = y - CURSOR_SIZE;
		
		Collection<Entity> entities = entityDetector.getEntities(cursor);
		
		for (Entity entity : entities) {
			entityKiller.kill(entity);
		}
	}
}
