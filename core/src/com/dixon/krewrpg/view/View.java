package com.dixon.krewrpg.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.dixon.krewrpg.entity.Entity;
import com.dixon.krewrpg.entity.EntityUpComparator;
import com.dixon.krewrpg.level.Level;

public class View {

	private Array<Entity> entities;
	private Array<Entity> sortedUpEntities;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private SpriteBatch spriteBatch;

	private Pool<Array<Entity>> entityArrayPool = new Pool<Array<Entity>>() {
		@Override
		protected Array<Entity> newObject() {
			return new Array<Entity>();
		}
	};

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

		sortedUpEntities = entityArrayPool.obtain();
		sortedUpEntities.addAll(entities);
		sortedUpEntities.sort(new EntityUpComparator());

		spriteBatch.begin();
		renderSprites();
		spriteBatch.end();
	}

	private void renderSprites() {
		for (Entity entity : sortedUpEntities) {
			entity.setAnimationTime(Gdx.graphics.getDeltaTime());

			switch (entity.getState()) {
			case standFront:
				entity.setCurrentFrame(entity.getStandFrontFrame());
				break;
			case standBack:
				entity.setCurrentFrame(entity.getStandBackFrame());
				break;
			case standLeft:
				entity.setCurrentFrame(entity.getStandLeftFrame());
				break;
			case standRight:
				entity.setCurrentFrame(entity.getStandRightFrame());
				break;
			case walkFront:
				entity.setCurrentFrame(entity.getWalkFrontFrame());
				break;
			case walkBack:
				entity.setCurrentFrame(entity.getWalkBackFrame());
				break;
			case walkLeft:
				entity.setCurrentFrame(entity.getWalkLeftFrame());
				break;
			case walkRight:
				entity.setCurrentFrame(entity.getWalkRightFrame());
				break;
			case attackFront:
				entity.setCurrentFrame(entity.getAttackFrontFrame());
				break;
			case attackBack:
				entity.setCurrentFrame(entity.getAttackBackFrame());
				break;
			case attackLeft:
				entity.setCurrentFrame(entity.getAttackLeftFrame());
				break;
			case attackRight:
				entity.setCurrentFrame(entity.getAttackRightFrame());
				break;
			case death:
				entity.setCurrentFrame(entity.getDeathFrame());
				break;
			}

			if (!entity.isFacingLeft()) {
				spriteBatch.draw(entity.getCurrentFrame(), entity.getX(), entity.getY(), entity.getSize(),
						entity.getSize());
			} else {
				spriteBatch.draw(entity.getCurrentFrame(), entity.getX() + entity.getSize(), entity.getY(),
						-entity.getSize(), entity.getSize());
			}
		}
	}

	public void getInput() {
		Entity player = entities.get(0);
		 if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			 entities.get(0).setState(Entity.State.walkBack);
			 entities.get(0).setDY(player.getMoveSpeed());
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
	        	entities.get(0).setState(Entity.State.walkFront);
	        	entities.get(0).setDY(-player.getMoveSpeed());
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
	        	entities.get(0).setFacingLeft(true);
	        	entities.get(0).setState(Entity.State.walkLeft);
	        	entities.get(0).setDX(-player.getMoveSpeed());
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
	        	entities.get(0).setFacingLeft(false);
	        	entities.get(0).setState(Entity.State.walkRight);
	        	entities.get(0).setDX(player.getMoveSpeed());
	        }
	}
}
