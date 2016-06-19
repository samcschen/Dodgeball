package com.dodgeball.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.objects.Bullet;

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
        //renderDebug();
        batch.begin();
        renderShips();
        renderInterface();
        renderBullets(world.shipOneBullets);
        renderBullets(world.shipTwoBullets);
        batch.end();
    }

    public void renderShips(){

        batch.draw(Assets.ship, world.shipOne.position.x, world.shipOne.position.y, world.shipOne.SHIP_WIDTH/2, world.shipOne.SHIP_HEIGHT/2,world.shipOne.SHIP_WIDTH, world.shipOne.SHIP_HEIGHT, 1f,1f,world.shipOne.rotation,0,0,64,64,false,false);
        batch.draw(Assets.ship, world.shipTwo.position.x, world.shipTwo.position.y, world.shipTwo.SHIP_WIDTH/2, world.shipTwo.SHIP_HEIGHT/2,world.shipTwo.SHIP_WIDTH, world.shipTwo.SHIP_HEIGHT, 1f,1f,world.shipTwo.rotation,0,0,64,64,false,false);

    }
    public void renderInterface(){
        batch.draw(Assets.crosshairOne, world.crosshair.position.x, world.crosshair.position.y);
    }
    public void renderBullets(Array<Bullet> bullets){
        for(int i = 0;i<bullets.size;i++){
            batch.draw(Assets.bullet,bullets.get(i).position.x,bullets.get(i).position.y,bullets.get(i).BULLET_WIDTH/2, bullets.get(i).BULLET_HEIGHT/2,bullets.get(i).BULLET_WIDTH, bullets.get(i).BULLET_HEIGHT, 1f,1f,bullets.get(i).rotation,0,0,13,54,false,false);
        }
    }

    public void renderDebug(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(world.shipOne.bounds.getTransformedVertices());
        shapeRenderer.polygon(world.shipOne.sensorOne.getTransformedVertices());
        shapeRenderer.polygon(world.shipOne.sensorTwo.getTransformedVertices());
        shapeRenderer.polygon(world.shipOne.sensorThree.getTransformedVertices());
        shapeRenderer.polygon(world.shipOne.sensorFour.getTransformedVertices());
        shapeRenderer.polygon(world.shipTwo.bounds.getTransformedVertices());
        for(int i = 0;i<world.shipOneBullets.size;i++){
            shapeRenderer.polygon(world.shipOneBullets.get(i).bounds.getTransformedVertices());
        }
        for(int i = 0;i<world.shipTwoBullets.size;i++){
            shapeRenderer.polygon(world.shipTwoBullets.get(i).bounds.getTransformedVertices());
        }
        shapeRenderer.end();
    }
}
