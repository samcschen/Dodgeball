package com.dodgeball.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by samuel on 6/11/16.
 */
public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;

    public GameObject (float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }
}
