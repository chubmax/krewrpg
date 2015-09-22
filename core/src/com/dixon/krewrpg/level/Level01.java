package com.dixon.krewrpg.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.dixon.krewrpg.entity.Nova;

public class Level01 extends Level {
	
	public Level01() {
		map = new TmxMapLoader().load("maps/cave01_test.tmx");
		
		Nova nova = new Nova(new Texture("sprites/nova/Tom_1.png"), 100, 15f, new Vector2(20, 20));
		
		entities.add(nova);
	}

}
