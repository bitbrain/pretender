package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.myreality.pretender.Entity;




public class DefaultRenderStrategy implements RenderStrategy {

	@Override
	public void render(Texture texture, Batch batch, float delta,
			Entity entity) {
		batch.draw(texture, entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	}
}
