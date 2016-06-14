package com.dodgeball.game.objects;

import com.dodgeball.game.utils.World;

/**
 * Created by samuel on 6/13/16.
 */
public class Bullet extends DynamicGameObject{
    World world;
    public final int SPEED = 1500;
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 54;

    public Bullet(float x, float y,float rotation, World world){

        super(x,y,BULLET_WIDTH,BULLET_HEIGHT);
        velocity.x=world.mathUtils.sinDeg(rotation)*SPEED;
        velocity.y=world.mathUtils.cosDeg(rotation)*SPEED;
        this.world = world;
        this.rotation = 360-rotation;

    }
    public void update(float deltaTime){
        position.add(velocity.x*deltaTime, velocity.y*deltaTime,0);
        bounds.setPosition(position.x, position.y);
        bounds.setRotation(rotation);
    }
}
