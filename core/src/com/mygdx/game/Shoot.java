package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Shoot extends Sprite {
    private Vector2 velocity;

    public Shoot(Texture texture) {
        super(texture);
        velocity = new Vector2();
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void update(float deltaTime) {
        setPosition(getX() + velocity.x * deltaTime, getY() + velocity.y * deltaTime);
    }
}
