package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

import de.myreality.pretender.Resources;

public class StreetTextureGenerator implements TextureGenerator {
	
	private static final int LINE_HEIGHT = 10;
	private static final int LINE_WIDTH = 50;
	private static final int LINE_SPACING = 30;
	
	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		
		map.setColor(Color.valueOf(Resources.COLOR_STREET));
		map.fill();
		
		map.setColor(Color.valueOf(Resources.COLOR_STREET_LINES));
		
		int offset = (int) -(Math.random() * LINE_WIDTH) / 2;
		
		while (offset < width) {
			
			map.fillRectangle(offset, (height / 2 - LINE_HEIGHT / 2) - 20, LINE_WIDTH, LINE_HEIGHT);
			
			offset += LINE_WIDTH + LINE_SPACING;
		}
			 
		map.setColor(Color.valueOf("888888"));
		map.fillRectangle(0, height - height / 6, width, height / 6);
		
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
		
	}

}
