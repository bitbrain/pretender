package de.myreality.pretender.util;

import com.badlogic.gdx.math.Rectangle;

import de.myreality.pretender.Entity;

public final class Collision {

	public static boolean check(Entity entity1, Entity entity2) {
		
		Rectangle body1 = entity1.getBody();
		Rectangle body2 = entity2.getBody();
		
		float oldX1 = body1.x;
		float oldX2 = body2.x;
		float oldY1 = body1.y;
		float oldY2 = body2.y;
		
		body1.setPosition(oldX1 + entity1.getX(), oldY1 + entity1.getY());
		body2.setPosition(oldX2 + entity2.getX(), oldY2 + entity2.getY());
		
		boolean collision = body1.contains(body2) || body1.overlaps(body2);
		
		body1.setPosition(oldX1, oldY1);
		body2.setPosition(oldX2, oldY2);
		
		return collision;
	}
}
