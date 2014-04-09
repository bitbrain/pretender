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
		
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		
		Color skinColor = getSkinColor();
		
		map.setColor(skinColor);
		map.fill();
		
		Texture texture = new Texture(map);
		map.dispose();
		
		return texture;
	}
	
	private Color getSkinColor() {		
		return SKIN_COLORS[(int) (Math.random() * SKIN_COLORS.length)];
	}

}
