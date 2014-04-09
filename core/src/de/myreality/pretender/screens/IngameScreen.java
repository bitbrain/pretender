package de.myreality.pretender.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import de.myreality.pretender.PretenderGame;
import de.myreality.pretender.graphics.Renderer;

public class IngameScreen implements Screen {
	
	private PretenderGame game;
	
	private OrthographicCamera camera;
	
	private Stage stage;
	
	private Renderer renderer;
	
	private Batch batch;
	
	public IngameScreen(PretenderGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
			renderer.render(batch, delta);
		batch.end();
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		if (stage == null) {
			camera = new OrthographicCamera();			
			camera.setToOrtho(true);
			stage = new Stage();
			Gdx.input.setInputProcessor(stage);
		}
		
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		stage.setViewport(new FitViewport(width, height));
	}

	@Override
	public void show() {
		renderer = new Renderer();
		batch = new SpriteBatch();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
