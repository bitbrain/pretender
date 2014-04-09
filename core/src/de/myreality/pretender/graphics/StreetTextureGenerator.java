package de.myreality.pretender.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

import de.myreality.pretender.Resources;

public class StreetTextureGenerator implements TextureGenerator {

	@Override
	public Texture create(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		
		map.setColor(Color.valueOf(Resources.COLOR_STREET));
		map.fill();
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
		
	}

}
