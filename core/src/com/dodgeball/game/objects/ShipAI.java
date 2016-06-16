package com.dodgeball.game.objects;

import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.utils.World;

/**
 * Created by samuel on 6/14/16.
 */
public class ShipAI extends DynamicGameObject{
    public static final float SHIP_WIDTH = 64;
    public static final float SHIP_HEIGHT = 64;
    public static final int RELOAD_TIME = 45;
    public static final int MAX_SPEED = 400;
    public int reloadTimer = 0;
    String genome;
    Array <Float>  splitGenome;
    public int health = 3;
    /*
     First 9 digits rotation difference from actual target
     */

    DynamicGameObject target;
    public World world;

    public ShipAI(float x, float y, String genome,DynamicGameObject target, World world){

        super(x,y,SHIP_WIDTH,SHIP_HEIGHT);
        acceleration.x = 50;
        acceleration.y = 50;

        this.genome = genome;
        splitGenome = new Array<Float>();
        parseGenome();

        this.target=target;
        this.world = world;

    }

    public void parseGenome(){
        splitGenome.add(Float.parseFloat(genome.substring(0,3)));//Substring is inclusive then exclusive
        splitGenome.add(Float.parseFloat(genome.substring(3,6)));
        splitGenome.add(Float.parseFloat(genome.substring(6,9)));
    }
    public void update(float deltaTime){
        updateLocation(deltaTime);
        movement();
        shoot();
    }
    public void updateLocation(float deltaTime){
        position.add(velocity.x*deltaTime, velocity.y*deltaTime,0);
        center.x = position.x+SHIP_WIDTH/2;
        center.y = position.y+SHIP_HEIGHT/2;
        bounds.setPosition(position.x, position.y);
    }
    public void movement(){

    }
    public float getTargetAngle(DynamicGameObject object){
        double angle = world.mathUtils.atan2(object.center.y-this.center.y,object.center.x-this.center.x);
        angle = angle * (180/Math.PI);
        angle = angle-90;
        if(angle < 0)
        {
            angle = 360 + angle;
        }
        return (float)angle;
    }
    public void orient(){
        float angle = getTargetAngle(target);
        switch(world.mathUtils.random(1,3)){
            case 1:
                angle = angle+splitGenome.get(0)-499;
                break;
            case 2:
                angle = angle+splitGenome.get(1)-499;
                break;
            case 3:
                angle = angle+splitGenome.get(2)-499;
                break;
        }
        rotation = angle;
        bounds.setRotation(rotation);
    }
    public void shoot(){
        reloadTimer++;
        if(reloadTimer%RELOAD_TIME==0){
            orient();
            if(target==world.shipTwo) {
                world.shipOneBullets.add(new Bullet(position.x, position.y, 360 - rotation, world));
            }else{
                world.shipTwoBullets.add(new Bullet(position.x, position.y, 360 - rotation, world));
            }
            reloadTimer = 0;

        }
    }

    public void reduceHealth(){
        health = health -1;
    }
}
