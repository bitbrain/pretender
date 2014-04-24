package de.myreality.pretender.util;

import java.util.ArrayList;
import java.util.Collection;

import de.myreality.pretender.Entity;
import de.myreality.pretender.graphics.RenderTarget;


public class BruteForceEntityDetector implements EntityDetector {
	
	private Collection<RenderTarget> entities;
	
	private RenderTarget[] exceptions;
	
	public BruteForceEntityDetector(Collection<RenderTarget> entities, RenderTarget ... exceptions) {
		this.entities = entities;
		this.exceptions = exceptions;
	}

	@Override
	public boolean hasEntity(float x, float y) {		
		return getEntity(x, y) != null;
	}
	
	@Override
	public Entity getEntity(float x, float y) {
		
		boolean skip = false;
		
		for (RenderTarget entity : entities) {
			
			for (RenderTarget ex : exceptions) {
				if (ex.equals(entity)) {
					skip = true;
					break;
				}
			}
			
			if (skip) {
				skip = false;
				continue;
			}
			
			boolean xCheck = entity.getX() + entity.getBody().x < x &&
					 		  entity.getX() + entity.getBody().x + entity.getBody().width > x;
			boolean yCheck = entity.getY() + entity.getBody().y < y &&
			 		          entity.getY() + entity.getBody().y + entity.getBody().height > y;
			 		          
			if (xCheck && yCheck && entity instanceof Entity) {
				return (Entity)entity;
			}
		}
		
		return null;
	}

	@Override
	public Collection<Entity> getEntities(Entity area) {
		
		Collection<Entity> result = new ArrayList<Entity>();
		
		for (RenderTarget target : entities) {
			
			if (target instanceof Entity) {
				
				if (Collision.check(area, (Entity)target)) {
					result.add((Entity)target);
				}
				
			}
		}
		
		return result;
	}

}
