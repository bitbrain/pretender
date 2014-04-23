package de.myreality.pretender;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

import de.myreality.pretender.ai.EntityBehavior;
import de.myreality.pretender.graphics.DefaultRenderStrategy;
import de.myreality.pretender.graphics.RenderStrategy;
import de.myreality.pretender.graphics.RenderTarget;

public class Entity implements RenderTarget, Poolable {
	
	public static enum Direction {
		LEFT, RIGHT, NONE
	}
	
	private Vector2 pos, lastPos, offset;
	
	private int width, height;
	
	private Texture texture;
	
	private Rectangle body;
	
	private RenderStrategy renderStrategy;
	
	private EntityBehavior behavior;
	
	private Direction direction;
	
	private Color color;
	
	private boolean dead;
	
	private float scaleX, scaleY;
	
	public Entity() {
		pos = new Vector2();
		lastPos = new Vector2();
		offset = new Vector2();
		reset();
	}
	
	public void setBody(Rectangle rectangle) {
		this.body = rectangle;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean collidesWith(Entity other) {
		
		float right = getX() + body.x + body.width;
		float bottom = getY() + body.y + body.height;

		float otherRight = other.getX() + other.body.x + other.body.width;
		float otherBottom = other.getY() + other.body.y + other.body.height;

		boolean collisionX = otherRight >= (getX() + body.x) && (other.getX() + other.body.x) <= right;
		boolean collisionY = otherBottom >= (getY() + body.y) && (other.getY() + other.body.y) <= bottom;

		return collisionX && collisionY;
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
	
	public void setOffset(float offsetX, float offsetY) {
		this.offset.x = offsetX;
		this.offset.y = offsetY;
	}
	
	public float getOffsetX() {
		return offset.x;
	}
	
	public float getOffsetY() {
		return offset.y;
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
	
	public Rectangle getBody() {
		return body;
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
		
		lastPos.x = pos.x;
		lastPos.y = pos.y;
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
		offset.x = 0;
		offset.y = 0;
		width = 0;
		height = 0;
		scaleX = 1f;
		scaleY = 1f;
		texture = null;
		body = new Rectangle();
		behavior = null;
		renderStrategy = new DefaultRenderStrategy();
		direction = Direction.NONE;
		color = new Color(1f, 1f, 1f, 1f);
		dead = false;
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public void setScaleX(float scale) {
		this.scaleX = scale;
	}
	
	public float getScaleY() {
		return scaleY;
	}
	
	public void setScaleY(float scale) {
		this.scaleY = scale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + height;
		result = prime * result + ((lastPos == null) ? 0 : lastPos.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + width;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (direction != other.direction)
			return false;
		if (height != other.height)
			return false;
		if (lastPos == null) {
			if (other.lastPos != null)
				return false;
		} else if (!lastPos.equals(other.lastPos))
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
	
	
	
	
}
