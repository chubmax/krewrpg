package com.dixon.krewrpg.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.dixon.krewrpg.entity.Entity;
import com.dixon.krewrpg.level.Level;

public class View {

	private Array<Entity> entities;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private SpriteBatch spriteBatch;

	public View(Level level) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();

		TiledMap map = level.getMap();
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		
		spriteBatch = new SpriteBatch();
		entities = level.getEntities();

	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(entities.get(0).getX() + 1, entities.get(0).getY(), 0);
		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);

		renderer.setView(camera);
		renderer.render();
		
		spriteBatch.begin();
		renderSprites();
		spriteBatch.end();
	}

	private void renderSprites() {
		for (Entity entity : entities) {
			spriteBatch.draw(entity.getTexture(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		}
	}
	
	public void getInput() {
		Entity player = entities.get(0);
		if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
			player.setY(player.getY() + (player.getMoveSpeed() * Gdx.graphics.getDeltaTime()));
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			player.setY(player.getY() - (player.getMoveSpeed() * Gdx.graphics.getDeltaTime()));
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			player.setX(player.getX() - (player.getMoveSpeed() * Gdx.graphics.getDeltaTime()));
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			player.setX(player.getX() + (player.getMoveSpeed() * Gdx.graphics.getDeltaTime()));
		}
	}
}
