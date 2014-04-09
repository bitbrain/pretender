package de.myreality.pretender;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import de.myreality.pretender.screens.IngameScreen;

public class PretenderGame extends Game {
	
	private AssetManager manager;
	
	public PretenderGame() {
		manager = new AssetManager();
	}
	
	@Override
	public void create () {	
		loadResources();
		setScreen(new IngameScreen(this));
	}
	
	public AssetManager getAssetManager() {
		return manager;
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
	}
	
	void loadResources() {
		
	}
}