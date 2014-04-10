package de.myreality.pretender.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import de.myreality.pretender.Entity;

public class EntityTween implements TweenAccessor<Entity> {
	
	public static final int POS_X = 1;
	public static final int POS_Y = 2;
	
	@Override
	public int getValues(Entity entity, int type, float[] values) {
		
		switch (type) {
			case POS_X:
				values[0] = entity.getX();
				return 1;
			case POS_Y:
				values[0] = entity.getY();
				return 1;
		}
		
		return 0;
	}

	@Override
	public void setValues(Entity entity, int type, float[] values) {
		
		switch (type) {
			case POS_X:
				entity.setX(values[0]);
				break;
			case POS_Y:
				entity.setY(values[0]);
				break;
		}
	}

}
