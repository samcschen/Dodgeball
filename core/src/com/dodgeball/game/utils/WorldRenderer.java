package com.dodgeball.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by samuel on 6/11/16.
 */
public class WorldRenderer {

    SpriteBatch batch;
    World world;

    public WorldRenderer (SpriteBatch batch, World world){
        this.batch = batch;
        this.world = world;
    }

    public void render(){
        renderObjects();
    }

    public void renderObjects(){
        batch.begin();
        batch.draw(Assets.playerOne, world.playerOne.position.x, world.playerOne.position.y);
        batch.end();
    }
}
