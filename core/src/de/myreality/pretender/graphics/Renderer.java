package de.myreality.pretender.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Renderer {
	
	private Queue<RenderTarget> removeQueue;
	
	private List<RenderTarget> targets;
	
	private RenderTargetComperator comperator;
	
	public Renderer() {
		targets = new ArrayList<RenderTarget>();
		removeQueue = new LinkedList<RenderTarget>();
		comperator = new RenderTargetComperator();
	}
	
	public void add(RenderTarget target) {
		targets.add(target);
	}
	
	public void remove(RenderTarget target) {
		removeQueue.add(target);
	}

	public void render(Batch batch, float delta) {
		
		Collections.sort(targets, comperator);
		
		for (RenderTarget target : targets) {
			target.draw(batch, delta);
		}
		
		while (!removeQueue.isEmpty()) {
			RenderTarget target = removeQueue.poll();
			targets.remove(target);
		}
	}

	
	class RenderTargetComperator implements Comparator<RenderTarget> {

		@Override
		public int compare(RenderTarget targetA, RenderTarget targetB) {			
			return Math.round(targetB.getY() - targetB.getY());
		}
		
	}
}
