package com.dodgeball.game.objects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by samuel on 6/11/16.
 */
public class GameObject {
    public Vector3 position;
    public final Vector2 center;
    public Polygon bounds;

    public GameObject (float x, float y, float width, float height) {
        this.position = new Vector3(x, y, 0);
        this.bounds = new Polygon(new float[]{0,0,width,0,width,height,0,height});
        this.bounds.setOrigin(width/2, height/2);
        center = new Vector2(position.x+(width/2), y+height/2);
    }
}
