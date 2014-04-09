package de.myreality.pretender.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.FitViewport;

import de.myreality.pretender.Entity;
import de.myreality.pretender.PretenderGame;
import de.myreality.pretender.graphics.Renderer;
import de.myreality.pretender.graphics.StreetTextureGenerator;
import de.myreality.pretender.graphics.TextureGenerator;

public class IngameScreen implements Screen {
	
	private PretenderGame game;
	
	private OrthographicCamera camera;
	
	private Stage stage;
	
	private Renderer renderer;
	
	private Batch batch;
	
	private Pool<Entity> pool;
	
	public IngameScreen(PretenderGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
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
		
		pool = Pools.get(Entity.class);
		renderer = new Renderer(pool);
		batch = new SpriteBatch();		
		
		Entity street = pool.obtain();
		renderer.add(street);
		TextureGenerator strTexGenerator = new StreetTextureGenerator();
		street.setTexture(strTexGenerator.create(Gdx.graphics.getWidth(), 300));
		street.setDimensions(Gdx.graphics.getWidth(), 300);
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
