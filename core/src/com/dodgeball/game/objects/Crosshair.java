package com.dodgeball.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.dodgeball.game.utils.World;

/**
 * Created by samuel on 6/12/16.
 */
public class Crosshair extends GameObject{
    public static final float CROSSHAIR_WIDTH = 64;
    public static final float CROSSHAIR_HEIGHT = 64;
    public World world;

    public Crosshair(float x, float y, World world){

        super(x,y,CROSSHAIR_WIDTH,CROSSHAIR_HEIGHT);
        this.world = world;

    }

    public void update(float deltaTime){
        position = getMousePosition();
        position.x = position.x-CROSSHAIR_WIDTH/2;
        position.y = position.y-CROSSHAIR_WIDTH/2;

        center.x = position.x+CROSSHAIR_WIDTH/2;
        center.y = position.y+CROSSHAIR_HEIGHT/2;
    }

    public Vector3 getMousePosition(){
        return world.gameScreen.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }
}
