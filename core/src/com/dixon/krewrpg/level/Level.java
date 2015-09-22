package com.dixon.krewrpg.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.dixon.krewrpg.entity.Entity;

public class Level {
	
	protected TiledMap map;
    protected Array<Entity> entities;
    
    public Level() {
        entities = new Array<Entity>();
    }

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public Array<Entity> getEntities() {
		return entities;
	}

	public void setEntities(Array<Entity> sprites) {
		this.entities = sprites;
	}
    
    

}
