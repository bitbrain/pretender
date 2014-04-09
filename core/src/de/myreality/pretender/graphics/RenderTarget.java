package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface RenderTarget {
	
	void draw(Batch batch, float delta);
	
	float getX();
	
	float getY();
}
