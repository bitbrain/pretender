package de.myreality.pretender.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;

public class ColorTween implements TweenAccessor<Color> {


	public static final int R = 1;

	public static final int G = 2;

	public static final int B = 3;

	public static final int A = 4;

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public int getValues(Color target, int tweenType, float[] returnValues) {

		switch (tweenType) {
			case R:
				returnValues[0] = target.r;
				return 1;
			case G:
				returnValues[0] = target.g;
				return 1;
			case B:
				returnValues[0] = target.b;
				return 1;
			case A:
				returnValues[0] = target.a;
				return 1;
		}

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public void setValues(Color target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case R:
				target.r = newValues[0];
				break;
			case G:
				target.g = newValues[0];
				break;
			case B:
				target.b = newValues[0];
				break;
			case A:
				target.a = newValues[0];
				break;
		}
	}

}