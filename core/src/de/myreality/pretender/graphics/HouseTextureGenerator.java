package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class HouseTextureGenerator implements TextureGenerator {
	
	private static final int WINDOW_WIDTH = 5;
	private static final int WINDOW_HEIGHT = 10;
	private static final int PADDING_X = 10;
	private static final int PADDING_Y = 40;
	
	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		
		Color houseColor = getRandomHouseColor();
		map.setColor(houseColor);
		map.fill();
		
		// Calculate possible window amount
		final int WINDOWS_X = (int) (Math.floor(width / (WINDOW_WIDTH + PADDING_X)));
		final int WINDOWS_Y = (int) (Math.floor(height / WINDOW_HEIGHT + PADDING_Y));
		
		Color windowColor = getWindowColor(houseColor);
		map.setColor(windowColor);
		
		for (int y = 0; y < WINDOWS_Y; ++y) {
			for (int x = 0; x < WINDOWS_X; ++x) {
				map.fillRectangle(x * WINDOW_WIDTH, y * WINDOW_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT);
			}
		}
		
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
	}
	
	private Color getWindowColor(Color houseColor) {
		return new Color(houseColor.r / 2f, houseColor.g / 2, houseColor.b / 2, (float) 1.0);
	}
	
	private Color getRandomHouseColor() {
		return new Color((float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 1f);
	}

}
