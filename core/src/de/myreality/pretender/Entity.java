package de.myreality.pretender;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

import de.myreality.pretender.graphics.DefaultRenderStrategy;
import de.myreality.pretender.graphics.RenderStrategy;
import de.myreality.pretender.graphics.RenderTarget;

public class Entity implements RenderTarget, Poolable {
	
	public static enum Direction {
		LEFT, RIGHT, NONE
	}
	
	private Vector2 pos, lastPos;
	
	private int width, height;
	
	private Texture texture;
	
	private Rectangle body;
	
	private RenderStrategy renderStrategy;
	
	private EntityBehavior behavior;
	
	private Direction direction;
	
	public Entity() {
		pos = new Vector2();
		lastPos = new Vector2();
		reset();
	}
	
	public void setBody(Rectangle rectangle) {
		this.body = rectangle;
	}
	
	public boolean collidesWith(Entity other) {
		return body.contains(other.body) || body.overlaps(other.body);
	}
	
	public void setRenderStrategy(RenderStrategy strategy) {
		this.renderStrategy = strategy;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setBehavior(EntityBehavior behavior) {
		this.behavior = behavior;
	}
	
	public void setTexture(Texture texture) {
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
		
		if (x < pos.x) {
			direction = Direction.LEFT;
		} else if (x > pos.x) {
			direction = Direction.RIGHT;
		} else {
			direction = Direction.NONE;
		}
		
		lastPos.x = pos.x;
		pos.x = x;
	}
	
	public void setY(float y) {
		lastPos.y = pos.y;
		pos.y = y;
	}

	@Override
	public void draw(Batch batch, float delta) {
		
		if (behavior != null) {
			behavior.behave(delta, this);
		}
		
		if (texture != null && renderStrategy != null) {
			renderStrategy.render(texture, batch, delta, this);
		}
	}
	
	public boolean isMoving() {
		return lastPos.x != pos.x || lastPos.y != pos.y;
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
		lastPos.x = 0;
		lastPos.y = 0;
		width = 0;
		height = 0;
		texture = null;
		body = new Rectangle();
		behavior = null;
		renderStrategy = new DefaultRenderStrategy();
		direction = Direction.NONE;
	}
}
