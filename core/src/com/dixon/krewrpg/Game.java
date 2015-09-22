package com.dixon.krewrpg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
		entities = currentLevel.getSprites();
		viewPool.free(view);
		viewPool.clear();
		view = viewPool.obtain();
		view.getInput();
		view.render();
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
