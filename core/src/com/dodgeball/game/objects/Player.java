package com.dodgeball.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dodgeball.game.utils.World;

/**
 * Created by samuel on 6/11/16.
 */
public class Player extends DynamicGameObject{

    public static final float PLAYER_WIDTH = 64;
    public static final float PLAYER_HEIGHT = 64;
    public static final float FRICTION = 1.5f;
    public float rotation = 0;

    public World world;


    public Player(float x, float y, World world){

        super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
        acceleration.x = 115;
        acceleration.y = 115;
        this.world = world;

    }

    public void update(float deltaTime){
        position.add(velocity.x*deltaTime, velocity.y*deltaTime,0);
        center.x = position.x-PLAYER_WIDTH/2;
        center.y = position.y-PLAYER_HEIGHT/2;
        control();
        slowDown();
        orient();
    }

    public void control(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velocity.x = velocity.x-acceleration.x;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velocity.x = velocity.x+acceleration.x;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            velocity.y = velocity.y+acceleration.y;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            velocity.y = velocity.y-acceleration.y;
        }
    }

    public void slowDown(){
        velocity.x = velocity.x/FRICTION;
        velocity.y = velocity.y/FRICTION;
        if (velocity.x>-1&&velocity.x<0){
            velocity.x=0;
        }
        if (velocity.x<1&&velocity.x>0){
            velocity.x=0;
        }
        if (velocity.y>-1&&velocity.y<0){
            velocity.y=0;
        }
        if (velocity.y<1&&velocity.y>0){
            velocity.y=0;
        }
    }

    public void orient(){

        double angle = world.mathUtils.atan2(world.crosshair.center.y-this.center.y,world.crosshair.center.x-this.center.x);
        angle = angle * (180/Math.PI);
        angle = angle-90;
        if(angle < 0)
        {
            angle = 360 - (-angle);
        }
        rotation = (float)(angle);

    }
}
