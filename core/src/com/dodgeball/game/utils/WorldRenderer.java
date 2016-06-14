package com.dodgeball.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by samuel on 6/11/16.
 */
public class WorldRenderer {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    World world;

    public WorldRenderer (SpriteBatch batch,ShapeRenderer shapeRenderer, World world){
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.world = world;
    }

    public void render(){
        renderDebug();
        renderObjects();
    }

    public void renderObjects(){
        batch.begin();
        batch.draw(Assets.playerOne, world.playerOne.position.x, world.playerOne.position.y, world.playerOne.PLAYER_WIDTH/2, world.playerOne.PLAYER_HEIGHT/2,world.playerOne.PLAYER_WIDTH, world.playerOne.PLAYER_HEIGHT, 1f,1f,world.playerOne.rotation,0,0,64,64,false,false);
        batch.draw(Assets.crosshairOne, world.crosshair.position.x, world.crosshair.position.y);
        for(int i = 0;i<world.bullets.size;i++){
            batch.draw(Assets.bullet,world.bullets.get(i).position.x,world.bullets.get(i).position.y,world.bullets.get(i).BULLET_WIDTH/2, world.bullets.get(i).BULLET_HEIGHT/2,world.bullets.get(i).BULLET_WIDTH, world.bullets.get(i).BULLET_HEIGHT, 1f,1f,world.bullets.get(i).rotation,0,0,13,54,false,false);
        }
        batch.end();
    }

    public void renderDebug(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(world.playerOne.bounds.getTransformedVertices());
        for(int i = 0;i<world.bullets.size;i++){
            shapeRenderer.polygon(world.bullets.get(i).bounds.getTransformedVertices());
        }
        shapeRenderer.end();
    }
}
