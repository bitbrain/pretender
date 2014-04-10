package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.myreality.pretender.Entity;

public interface RenderStrategy {

	void render(Texture texture, Batch batch, float delta, Entity entity);
}
