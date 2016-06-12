package com.dodgeball.game.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by samuel on 6/11/16.
 */
public class DynamicGameObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 acceleration;

    public DynamicGameObject (float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        acceleration = new Vector2();
    }
}
