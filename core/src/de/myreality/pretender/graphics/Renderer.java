package de.myreality.pretender.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;

import de.myreality.pretender.Entity;

public class Renderer {
	
	private static final float SORT_INTERVAL = 0.1f;
	
	private Queue<RenderTarget> removeQueue;
	
	private List<RenderTarget> targets;
	
	private RenderTargetComperator comperator;
	
	private Pool<Entity> pool;
	
	private float time;
	
	public Renderer(Pool<Entity> pool) {
		targets = new ArrayList<RenderTarget>();
		removeQueue = new LinkedList<RenderTarget>();
		comperator = new RenderTargetComperator();
		this.pool = pool;
		time = 0;
	}
	
	public void add(RenderTarget target) {
		targets.add(target);
		Collections.sort(targets, comperator);
	}
	
	public void remove(RenderTarget target) {
		removeQueue.add(target);
	}

	public void render(Batch batch, float delta) {
		
		time += delta;
		
		if (time >= SORT_INTERVAL) {
			time = time - SORT_INTERVAL;
			Collections.sort(targets, comperator);
		}
		
		for (RenderTarget target : targets) {
			
			if (target.getX() + target.getBody().getWidth() < 0 || target.getY() + target.getBody().getHeight() < 0 ||
				target.getX() > Gdx.graphics.getWidth() ||
				target.getY() > Gdx.graphics.getHeight()) {
				remove(target);
			} else {
				target.draw(batch, delta);
			}
		}
		
		System.out.println(targets.size() + ", " + removeQueue.size());
		
		while (!removeQueue.isEmpty()) {
			RenderTarget target = removeQueue.poll();
			targets.remove(target);
			
			if (target instanceof Entity) {
				pool.free((Entity)target);
			}
		}
	}

	
	class RenderTargetComperator implements Comparator<RenderTarget> {

		@Override
		public int compare(RenderTarget targetA, RenderTarget targetB) {		
			
			float value = (targetA.getY() + targetA.getBody().getY()) - (targetB.getY() + targetB.getBody().getY());
			
			if (value < 0) {
				return -1;
			} else if (value > 0) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
}
