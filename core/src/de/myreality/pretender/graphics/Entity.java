package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Entity implements RenderTarget, Poolable {
	
	private Vector2 pos;
	
	private int width, height;
	
	private Texture texture;
	
	public Entity() {
		pos = new Vector2();
		reset();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setDimensions(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public void setX(float x) {
		pos.x = x;
	}
	
	public void setY(float y) {
		pos.x = y;
	}

	@Override
	public void draw(Batch batch, float delta) {
		if (texture != null) {
			batch.draw(texture, pos.x, pos.y, width, height);
		}
	}

	@Override
	public float getX() {
		return pos.x;
	}

	@Override
	public float getY() {
		return pos.y;
	}

	@Override
	public void reset() {
		pos.x = 0;
		pos.y = 0;
		width = 0;
		height = 0;
		texture = null;
	}

}
