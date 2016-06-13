package com.dodgeball.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by samuel on 6/11/16.
 */
public class GameObject {
    public Vector3 position;
    public final Vector2 center;
    public final Rectangle bounds;

    public GameObject (float x, float y, float width, float height) {
        this.position = new Vector3(x, y, 0);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        center = new Vector2(position.x-width/2, y-height/2);
    }
}
