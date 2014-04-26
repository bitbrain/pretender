package de.myreality.pretender;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

import de.myreality.pretender.screens.IngameScreen;
import de.myreality.pretender.tweens.ColorTween;
import de.myreality.pretender.tweens.EntityTween;

public class PretenderGame extends Game {
	
	private AssetManager manager;
	
	public PretenderGame() {
		manager = SharedAssetManager.getInstance();
	}
	
	@Override
	public void create () {	
		loadResources();
	
		setScreen(new IngameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
	}
	
	void loadResources() {
		Tween.registerAccessor(Entity.class, new EntityTween());
		Tween.registerAccessor(Color.class, new ColorTween());
		
		manager.load(Resources.MUSIC_AMBIENT, Music.class);
		manager.load(Resources.SOUND_DIE, Sound.class);
		manager.load(Resources.SOUND_HIT, Sound.class);
		
		manager.finishLoading();
	}
}
