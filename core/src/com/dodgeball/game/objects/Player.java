package com.dodgeball.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by samuel on 6/11/16.
 */
public class Player extends DynamicGameObject{

    public static final float PLAYER_WIDTH = 64;
    public static final float PLAYER_HEIGHT = 64;

    public Player(float x, float y){

        super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);

    }

    public void update(float deltaTime){
        position.add(velocity.x*deltaTime, velocity.y*deltaTime);
        control();
        slowDown();
    }

    public void control(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velocity.x = velocity.x-5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velocity.x = velocity.x+5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            velocity.y = velocity.y+5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            velocity.y = velocity.y-5;
        }
    }

    public void slowDown(){
        velocity.x = velocity.x/1.05f;
        velocity.y = velocity.y/1.05f;
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
}
