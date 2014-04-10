package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class VillagerTextureGenerator implements TextureGenerator {
	
	private Color[] SKIN_COLORS = new Color[] {
			
			Color.valueOf("642d00"),
			Color.valueOf("f8e8cb"),
			Color.valueOf("bda684"),
			Color.valueOf("f1e8dc")
	};

	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		
		Color skinColor = getSkinColor();
		Color hairColor = getHairColor();
		Color shoeColor = getShoeColor();
		Color trousesColor = getTrousesColor();
		Color shirtColor = getShirtColor();
		
		generateIdle(map, skinColor, hairColor, shoeColor, trousesColor, shirtColor);

		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
	}
	
	private Color getSkinColor() {		
		return SKIN_COLORS[(int) (Math.random() * SKIN_COLORS.length)];
	}
	
	private Color getHairColor() {
		return Color.BLACK;
	}
	
	private Color getShoeColor() {
		return Color.BLACK;
	}
	
	private Color getTrousesColor() {
		return Color.BLUE;
	}
	
	private Color getShirtColor() {
		return Color.RED;
	}

	private void generateIdle(Pixmap map, Color skinColor, Color hairColor, Color shoeColor, Color trousesColor, Color shirtColor) {		
		
		final int w = map.getWidth() / 4;
		final int h = map.getHeight() / 2;
		
		for (int y = 0; y < map.getHeight(); y += h) {
			for (int x = 0; x < map.getWidth(); x += w) {
				map.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(),1f));
				map.fillRectangle(x, y, w, h);
			}
		}
	}
}
