package de.myreality.pretender.util;

import java.util.Collection;

import de.myreality.pretender.graphics.RenderTarget;


public class BruteForceEntityDetector implements EntityDetector {
	
	private Collection<RenderTarget> entities;
	
	public BruteForceEntityDetector(Collection<RenderTarget> entities) {
		this.entities = entities;
	}

	@Override
	public boolean hasEntity(float x, float y) {
		for (RenderTarget entity : entities) {			
			boolean xCheck = entity.getX() + entity.getBody().x <= x &&
					 		  entity.getX() + entity.getBody().x + entity.getBody().width >= x;
			boolean yCheck = entity.getY() + entity.getBody().y <= y &&
			 		          entity.getY() + entity.getBody().y + entity.getBody().height >= y;
			if (xCheck && yCheck) {
				return true;
			}
		}
		
		return false;
	}

}
