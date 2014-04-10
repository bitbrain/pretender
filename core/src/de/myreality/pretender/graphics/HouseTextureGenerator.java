package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class HouseTextureGenerator implements TextureGenerator {
	
	private static final int WINDOW_WIDTH = 15;
	private static final int WINDOW_HEIGHT = 22;
	private static final int PADDING_X = 15;
	private static final int PADDING_Y = 22;
	private static final int ROOF_HEIGHT = 40;
	
	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		
		Color houseColor = getRandomHouseColor();
		map.setColor(houseColor);
		map.fill();
		
		// Calculate possible window amount
		final int WINDOWS_X = (int) (Math.floor(width / (float)(WINDOW_WIDTH + PADDING_X)));
		final int WINDOWS_Y = (int) (Math.floor((height - ROOF_HEIGHT) / (float)(WINDOW_HEIGHT + PADDING_Y)));
		
		
		
		final int INTERN_OFFSET_X = Math.round((width - (WINDOWS_X * (WINDOW_WIDTH + PADDING_X)) + PADDING_X) / 2f);
		final int INTERN_OFFSET_Y = Math.round(((height - ROOF_HEIGHT) - (WINDOWS_Y * (WINDOW_HEIGHT + PADDING_Y)) + PADDING_Y) / 2f);
		for (int y = 0; y < WINDOWS_Y; ++y) {
			for (int x = 0; x < WINDOWS_X; ++x) {
				Color windowColor = getWindowColor(houseColor);
				map.setColor(windowColor);
				map.fillRectangle(INTERN_OFFSET_X + x * (WINDOW_WIDTH + PADDING_X), (ROOF_HEIGHT) + INTERN_OFFSET_Y + y * (WINDOW_HEIGHT + PADDING_Y), WINDOW_WIDTH, WINDOW_HEIGHT);
			}
		}
		
		Color roofColor = getRoofColor(houseColor);
		map.setColor(roofColor);
		map.fillRectangle(0, 0, width, ROOF_HEIGHT);
		
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
	}
	
	private Color getWindowColor(Color houseColor) {
		float yellow = (float) (0.4f + Math.random() * 0.2f);
		return new Color(houseColor.r + yellow, houseColor.g + yellow, houseColor.b, (float) 1.0);
	}
	
	private Color getRoofColor(Color houseColor) {
		return new Color(houseColor.r / 2f, houseColor.g / 2f, houseColor.b / 2f, (float) 1.0);
	}
	
	private Color getRandomHouseColor() {
		return new Color((float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 
						  (float)(Math.random() * 0.3f) + 0.4f, 1f);
	}

}
