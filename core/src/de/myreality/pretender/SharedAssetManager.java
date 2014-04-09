package de.myreality.pretender;

import com.badlogic.gdx.assets.AssetManager;

public final class SharedAssetManager extends AssetManager {

	private static SharedAssetManager instance;
	
	static {
		instance = new SharedAssetManager();
	}
	
	private SharedAssetManager() { }
	
	public static SharedAssetManager getInstance() {
		return instance;
	}
}
