package com.dixon.krewrpg.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Deathknight extends Entity {
	private static float FRAME_DURATION = 0.3f;

	public Deathknight(Texture texture, int maxHp, float moveSpeed, Vector2 position, int size) {
		super(texture, maxHp, moveSpeed, position, size);
		
		walkLeftAnimation = new Animation(FRAME_DURATION, getFrames(texture, 1, 3, size));
		standFrontAnimation = new Animation(FRAME_DURATION, getFrames(texture, 7, 3, size));
	}

	 public TextureRegion[] getFrames(Texture texture, int row, int columns, int size) {
	        TextureRegion[][] tmp = TextureRegion.split(texture, size, size);
	        TextureRegion[] frames = new TextureRegion[columns];
	        int index = 0;
	        for (int i = 0; i < columns; i++) {
	            frames[index++] = tmp[row][i];
	        }
	        return frames;
	    }

}
