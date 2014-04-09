package de.myreality.pretender.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class TexturePool {
	
	private TextureGenerator generator;
	
	private Map<Integer, Texture> textures;
	
	private int textureWidth, textureHeight;
	
	private int capacity;
	
	public TexturePool(int capacity, TextureGenerator generator) {
		textures = new HashMap<Integer, Texture>();
		this.generator = generator;
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.capacity = capacity;
	}
	
	public void setTextureSize(int width, int height) {
		this.textureWidth = width;
		this.textureHeight = height;
	}
	
	public Texture get() {
		
		Integer ID = (int) (capacity * Math.random());
		
		if (textures.containsKey(ID)) {
			return textures.get(ID);
		} else {
			Texture texture = generator.create(textureWidth, textureHeight);
			textures.put(ID, texture);
			return texture;
		}
	}
	
}
