package com.dixon.krewrpg.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	private int hp;
	private int maxHp;
	private Texture texture;
	private float moveSpeed;
	private Vector2 position;
	
	public Entity(Texture sprite, int maxHp, float moveSpeed, Vector2 position) {
		this.texture = sprite;
		this.maxHp = maxHp;
		this.moveSpeed = moveSpeed;
		this.position = position;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public void setX(float x) {
		position.x = x;
	}
	
	public void setY(float y) {
		position.y = y;
	}
	
	public float getWidth() {
		return 1 / 32f * texture.getWidth() ;
	}
	
	public float getHeight() {
		return 1 / 40f * texture.getHeight();
	}

}
