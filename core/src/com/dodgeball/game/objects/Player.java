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
    public static final int RELOAD_TIME = 45;
    public static final int MAX_SPEED = 400;
    public int reloadTimer = 0;

    public World world;


    public Player(float x, float y, World world){

        super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
        acceleration.x = 50;
        acceleration.y = 50;
        this.world = world;

    }

    public void update(float deltaTime){
        updateLocation(deltaTime);
        control();
        slowDown();
        orient();
    }
    public void updateLocation(float deltaTime){
        position.add(velocity.x*deltaTime, velocity.y*deltaTime,0);
        center.x = position.x+PLAYER_WIDTH/2;
        center.y = position.y+PLAYER_HEIGHT/2;
        bounds.setPosition(position.x, position.y);
    }
    public void control(){
        reloadTimer++;
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
        if(Gdx.input.isTouched()&&reloadTimer%RELOAD_TIME==0){

            world.playerBullets.add(new Bullet(position.x,position.y,360-rotation,world));
            reloadTimer = 0;

        }

    }

    public void slowDown(){
        if (velocity.x>MAX_SPEED){
            velocity.x = MAX_SPEED;
        }
        if (velocity.x<-MAX_SPEED){
            velocity.x=-MAX_SPEED;
        }
        if (velocity.y>MAX_SPEED){
            velocity.y=MAX_SPEED;
        }
        if (velocity.y<-MAX_SPEED){
            velocity.y=-MAX_SPEED;
        }
        if(center.x<0&&velocity.x<0){
            velocity.x=0;
        }
    }

    public void orient(){

        double angle = world.mathUtils.atan2(world.crosshair.center.y-this.center.y,world.crosshair.center.x-this.center.x);
        angle = angle * (180/Math.PI);
        angle = angle-90;
        if(angle < 0)
        {
            angle = 360 + angle;
        }
        rotation = (float)(angle);
        bounds.setRotation(rotation);
    }
}
