package com.dixon.krewrpg.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity implements Comparable<Entity> {

	public enum State {
		standFront, standBack, standLeft, standRight,

		walkFront, walkBack, walkLeft, walkRight,

		attackFront, attackBack, attackLeft, attackRight,

		death
	}

	private int hp;
	private int maxHp;
	private int size;
	private Texture texture;
	private float moveSpeed;
	private Vector2 position;
	private Vector2 velocity;
	protected float animationTime;
    private State state;
    private boolean facingLeft;
	
	private TextureRegion currentFrame;
	private TextureRegion currentFrameTop;

	protected Animation standFrontAnimation;
	protected Animation standBackAnimation;
	protected Animation standLeftAnimation;
	protected Animation standRightAnimation;
	protected Animation walkFrontAnimation;
	protected Animation walkBackAnimation;
	protected Animation walkLeftAnimation;
	protected Animation walkRightAnimation;
	protected Animation attackFrontAnimation;
	protected Animation attackBackAnimation;
	protected Animation attackLeftAnimation;
	protected Animation attackRightAnimation;
	protected Animation deathAnimation;

	public Entity(Texture sprite, int maxHp, float moveSpeed, Vector2 position, int size) {
		this.texture = sprite;
		this.maxHp = maxHp;
		this.moveSpeed = moveSpeed;
		this.position = position;
		this.animationTime = 0f;
		this.size = size;
		this.state = State.standFront;
		this.velocity = new Vector2();
	}
	
	public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public TextureRegion getCurrentFrameTop() {
        return currentFrameTop;
    }

    public void setCurrentFrameTop(TextureRegion currentFrame) {
        this.currentFrameTop = new TextureRegion(currentFrame,
                                                 currentFrame.getRegionX(), currentFrame.getRegionY() + 48,
                                                 currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	public void setX(float x) {
		position.x = x;
	}

	public float getY() {
		return position.y;
	}

	public void setY(float y) {
		position.y = y;
	}

	public void setDX(float x) {
		velocity.x = x;
	}

	public float getDX() {
		return velocity.x;
	}

	public void setDY(float y) {
		velocity.y = y;
	}

	public float getDY() {
		return velocity.y;
	}

	public float getWidth() {
		return 1 / 32f * texture.getWidth();
	}

	public float getHeight() {
		return 1 / 40f * texture.getHeight();
	}
	
    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime += animationTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public TextureRegion getStandFrontFrame() {
        return standFrontAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getStandBackFrame() {
        return standBackAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getStandLeftFrame() {
        return standLeftAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getStandRightFrame() {
        return standRightAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getWalkFrontFrame() {
        return walkFrontAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getWalkBackFrame() {
        return walkBackAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getWalkLeftFrame() {
        return walkLeftAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getWalkRightFrame() {
        return walkRightAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getAttackFrontFrame() {
        return attackFrontAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getAttackBackFrame() {
        return attackBackAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getAttackLeftFrame() {
        return attackLeftAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getAttackRightFrame() {
        return attackRightAnimation.getKeyFrame(animationTime, true);
    }

    public TextureRegion getDeathFrame() {
        return deathAnimation.getKeyFrame(animationTime, true);
    }
	
	
    public String comparableY() {
        return Float.toString(getY());
    }

	@Override
	public int compareTo(Entity other) {
		return other.comparableY().compareTo(comparableY());
	}

}
