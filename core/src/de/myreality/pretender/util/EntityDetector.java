package de.myreality.pretender.util;

import de.myreality.pretender.Entity;

public interface EntityDetector {

	boolean hasEntity(float x, float y);
	Entity getEntity(float x, float y);
}
