package de.myreality.pretender.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import de.myreality.pretender.Entity;

public class EntityTween implements TweenAccessor<Entity> {
	
	public static final int POS_X = 1;
	public static final int POS_Y = 2;
	public static final int OFFSET_X = 3;
	public static final int OFFSET_Y = 4;
	public static final int ALPHA = 5;
	public static final int SCALE_X = 6;
	public static final int SCALE_Y = 7;
	
	@Override
	public int getValues(Entity entity, int type, float[] values) {
		
		switch (type) {
			case POS_X:
				values[0] = entity.getX();
				return 1;
			case POS_Y:
				values[0] = entity.getY();
				return 1;
			case OFFSET_X:
				values[0] = entity.getOffsetX();
				return 1;
			case OFFSET_Y:
				values[0] = entity.getOffsetY();
				return 1;
			case ALPHA:
				values[0] = entity.getColor().a;
				return 1;
			case SCALE_X:
				values[0] = entity.getScaleX();
				return 1;
			case SCALE_Y:
				values[0] = entity.getScaleY();
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
			case OFFSET_X:
				entity.setOffset(values[0], entity.getOffsetY());
				break;
			case OFFSET_Y:
				entity.setOffset(entity.getOffsetX(), values[0]);
				break;
			case ALPHA:
				entity.getColor().a = values[0];
				break;
			case SCALE_X:
				entity.setScaleX(values[0]);
				break;
			case SCALE_Y:
				entity.setScaleY(values[0]);
				break;
		}
	}

}
