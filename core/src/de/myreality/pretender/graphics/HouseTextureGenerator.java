package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class HouseTextureGenerator implements TextureGenerator {
	
	private static final int WINDOW_WIDTH = 20;
	private static final int WINDOW_HEIGHT = 40;

	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		
		map.setColor(getRandomHouseColor());
		map.fill();
		
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
	}
	
	private Color getRandomHouseColor() {
		return new Color((float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 1f);
	}

}
