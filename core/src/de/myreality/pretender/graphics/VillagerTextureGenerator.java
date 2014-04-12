package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class VillagerTextureGenerator implements TextureGenerator {
	
	private Color[] SKIN_COLORS = new Color[] {
			
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
		
		generate(map, skinColor, hairColor, shoeColor, trousesColor, shirtColor);

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
		return Color.valueOf("5b7aa9");
	}
	
	private Color getShirtColor() {
		return new Color((float)(Math.random() * 0.3f) + 0.7f, 
				  (float)(Math.random() * 0.2f) + 0.3f, 
				  (float)(Math.random() * 0.2f) + 0.3f, 1f);
	}

	private void generate(Pixmap map, Color skinColor, Color hairColor, Color shoeColor, Color trousesColor, Color shirtColor) {		
		
		final int w = map.getWidth() / 2;
		final int h = map.getHeight() / 2;
		
		for (int y = 0; y < map.getHeight(); y += h) {
			for (int x = 0; x < map.getWidth(); x += w) {
				 
				if (y == 0) { // IDLE
					drawBody(map, x + w / 3, y, w, h, skinColor, hairColor, shoeColor, trousesColor, shirtColor);
				} else { // MOVING
					drawBody(map, x + w / 3, y, w, h, skinColor, hairColor, shoeColor, trousesColor, shirtColor);
				
					if (x == w) {
						map.setColor(shirtColor);
						map.fillRectangle(x, y + h / 2 - 2, 10, 3);
					}
				}
			}
		}
	}
	
	private void drawBody(Pixmap map, int x, int y, int width, int height, Color skinColor, Color hairColor, Color shoeColor, Color trousesColor, Color shirtColor) {
		
		final int HEAD_SIZE = width / 2;
		drawHead(map, x, y + height - HEAD_SIZE, HEAD_SIZE, skinColor, hairColor);
		drawTorso(map, x, (int) (y + height - HEAD_SIZE * 2), HEAD_SIZE, height, shirtColor);
		drawTrousers(map, x, y, height, HEAD_SIZE, trousesColor);
	}
	
	private void drawTrousers(Pixmap map, int x, int y, int height, int headSize,  Color trousersColor) {
		map.setColor(trousersColor);
		map.fillRectangle((int) (x + ((headSize / 2 - (headSize / 2f) / 2))), y, (int) (headSize / 2f), height - (int) (headSize * 2));
	}
	
	private void drawTorso(Pixmap map, int x, int y, int headSize, int height, Color shirtColor) {
		map.setColor(shirtColor);
		map.fillRectangle((int) (x + ((headSize / 2 - (headSize / 1.5f) / 2))), y, (int) (headSize / 1.5f), (int) (headSize));
	}
	
	private void drawHead(Pixmap map, int x, int y, int size, Color skinColor, Color hairColor) {
		map.setColor(skinColor);
		map.fillRectangle(x, y, size, size);
		map.setColor(hairColor);
		map.fillRectangle(x, y + size - (int)(size / 2.0f), size, (int) (size / 2.0f));
		map.setColor(Color.BLACK);
		map.fillRectangle(x + 2, y, 3, 4);
		map.fillRectangle(x + size - 5, y, 3, 4);
	}
}
