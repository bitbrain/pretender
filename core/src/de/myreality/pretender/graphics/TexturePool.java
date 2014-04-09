package de.myreality.pretender.graphics;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class TexturePool {
	
	private TextureGenerator generator;
	
	private Map<Integer, Texture> textures;
	
	private int textureWidth, textureHeight;
	
	public TexturePool(TextureGenerator generator) {
		this.generator = generator;
		this.textureWidth = 64;
		this.textureHeight = 64;
	}
	
	public void setTextureSize(int width, int height) {
		this.textureWidth = width;
		this.textureHeight = height;
	}
	
	Texture get(Integer ID) {
		
		if (textures.containsKey(ID)) {
			return textures.get(ID);
		} else {
			Texture texture = generator.create(textureWidth, textureHeight);
			textures.put(ID, texture);
			return texture;
		}
	}
	
}
