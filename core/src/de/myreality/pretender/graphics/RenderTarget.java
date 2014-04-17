package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

public interface RenderTarget extends Poolable {
	
	void draw(Batch batch, float delta);
	
	Rectangle getBody();
	
	float getX();
	float getY();
	
	float getOffsetX();
	float getOffsetY();
}
