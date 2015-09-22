package com.dixon.krewrpg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.dixon.krewrpg.entity.Entity;
import com.dixon.krewrpg.level.Level;
import com.dixon.krewrpg.level.Level01;
import com.dixon.krewrpg.view.View;

public class Game implements ApplicationListener {
	private SpriteBatch batch;
	private Array<Level> levels;
	private View view;
	private Level currentLevel;
	private TiledMap map;
	private Array<Entity> entities;
	private Array<Rectangle> tiles = new Array<Rectangle>();

	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};

	private Pool<View> viewPool = new Pool<View>() {
		@Override
		protected View newObject() {
			return new View(currentLevel);
		}
	};

	@Override
	public void create() {
		Level01 level01 = new Level01();
		currentLevel = level01;
		view = viewPool.obtain();
	}

	@Override
	public void render() {
		map = currentLevel.getMap();
		entities = currentLevel.getEntities();
		viewPool.free(view);
		viewPool.clear();
		view = viewPool.obtain();
		view.getInput();
		detectCollisions(1);
		view.render();
	}

	public void detectCollisions(int layerIndex) {
		for (Entity entity : entities) {
			Rectangle entityRect = rectPool.obtain();
			entityRect.set(entity.getX() + 0.3f, entity.getY() + 0.3f, entity.getWidth(), entity.getHeight());
			int startX, startY, endX, endY;

			startX = endX = (int) entity.getX();
			startY = (int) entity.getY();
			endY = (int) (entity.getY() + entity.getHeight());
			setTiles(startX, startY, endX, endY, tiles, layerIndex);
			// Tile collision on the x-axis.
			for (Rectangle tile : tiles) {
				if (entityRect.overlaps(tile)) {
					entity.setX(startX);
					break;
				}
			}
		}
	}

	public void setTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles, int layerIndex) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerIndex);
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				TiledMapTileLayer.Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x + 0.2f, y - 0.2f, 0.8f, 0.8f);
					tiles.add(rect);
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
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
		// TODO Auto-generated method stub

	}
}
