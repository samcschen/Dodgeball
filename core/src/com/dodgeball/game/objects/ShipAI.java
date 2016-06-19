package com.dodgeball.game.objects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.utils.World;

/**
 * Created by samuel on 6/14/16.
 */
public class ShipAI extends DynamicGameObject{
    public World world;
    public static final float SHIP_WIDTH = 64;
    public static final float SHIP_HEIGHT = 64;
    public final int RELOAD_TIME = 10;
    public final int MAX_SPEED = 400;
    public int reloadTimer = 0;
    public int moveTimer=0;
    String genome;
    Array <Float>  splitGenome;

    public int fitness = 0;

    public float sensorWidth = 128;
    public Polygon sensorOne;//Top Left
    public Polygon sensorTwo;//Top Right
    public Polygon sensorThree;//Bottom Left
    public Polygon sensorFour;//Bottom Right

    /*
     First 9 digits rotation difference from actual target
     Next 16 digits describe acceleration.  Pairs of 2x2, with each 2 being x and y
     Next 4 digits are random movement outside sensors.  2x2, first 2 are x, next 2 are y
     Next 3 digits are width
     */

    DynamicGameObject target;

    public ShipAI(float x, float y, String genome,DynamicGameObject target, World world){

        super(x,y,SHIP_WIDTH,SHIP_HEIGHT);
        acceleration.x = 50;
        acceleration.y = 50;

        this.genome = genome;
        splitGenome = new Array<Float>();
        parseGenome();

        this.target=target;
        this.world = world;

        sensorWidth=splitGenome.get(13);
        sensorOne = new Polygon(new float[]{0,0,sensorWidth,0,sensorWidth,sensorWidth,0,sensorWidth});
        sensorTwo = new Polygon(new float[]{0,0,sensorWidth,0,sensorWidth,sensorWidth,0,sensorWidth});
        sensorThree = new Polygon(new float[]{0,0,sensorWidth,0,sensorWidth,sensorWidth,0,sensorWidth});
        sensorFour = new Polygon(new float[]{0,0,sensorWidth,0,sensorWidth,sensorWidth,0,sensorWidth});

        sensorOne.setOrigin(sensorWidth/2,sensorWidth/2);
        sensorTwo.setOrigin(sensorWidth/2,sensorWidth/2);
        sensorThree.setOrigin(sensorWidth/2,sensorWidth/2);
        sensorFour.setOrigin(sensorWidth/2,sensorWidth/2);
    }

    public void parseGenome(){
        splitGenome.add(Float.parseFloat(genome.substring(0,3)));//Substring is inclusive then exclusive
        splitGenome.add(Float.parseFloat(genome.substring(3,6)));
        splitGenome.add(Float.parseFloat(genome.substring(6,9)));
        splitGenome.add(Float.parseFloat(genome.substring(9,11)));
        splitGenome.add(Float.parseFloat(genome.substring(11,13)));
        splitGenome.add(Float.parseFloat(genome.substring(13,15)));
        splitGenome.add(Float.parseFloat(genome.substring(15,17)));
        splitGenome.add(Float.parseFloat(genome.substring(17,19)));
        splitGenome.add(Float.parseFloat(genome.substring(19,21)));
        splitGenome.add(Float.parseFloat(genome.substring(21,23)));
        splitGenome.add(Float.parseFloat(genome.substring(23,25)));
        splitGenome.add(Float.parseFloat(genome.substring(25,27)));
        splitGenome.add(Float.parseFloat(genome.substring(27,29)));
        splitGenome.add(Float.parseFloat(genome.substring(29,32)));
    }
    public void update(float deltaTime){
        updateLocation(deltaTime);
        movement();
        shoot();
    }
    public void updateLocation(float deltaTime){

        slowDown();
        stayInBounds(deltaTime);
        position.add(velocity.x*deltaTime*world.speed, velocity.y*deltaTime*world.speed,0);
        center.x = position.x+SHIP_WIDTH/2;
        center.y = position.y+SHIP_HEIGHT/2;
        bounds.setPosition(position.x, position.y);
        updateSensors();
        movement();
    }
    public void stayInBounds(float deltaTime){
        if (center.x<0&&velocity.x<0){
            velocity.x =MAX_SPEED;
            position.add(3*velocity.x*deltaTime*world.speed, 3*velocity.y*deltaTime*world.speed,0);
        }
        if (center.x>world.gameScreen.cam.viewportWidth&&velocity.x>0){
            velocity.x =-MAX_SPEED;
            position.add(3*velocity.x*deltaTime*world.speed, 3*velocity.y*deltaTime*world.speed,0);
        }
        if (center.y<0&&velocity.y<0){
            velocity.y =MAX_SPEED;
            position.add(3*velocity.x*deltaTime*world.speed, 3*velocity.y*deltaTime*world.speed,0);
        }
        if (center.y>world.gameScreen.cam.viewportHeight&&velocity.y>0){
            velocity.y =-MAX_SPEED;
            position.add(3*velocity.x*deltaTime*world.speed, 3*velocity.y*deltaTime*world.speed,0);
        }
    }
    public void updateSensors(){
        sensorOne.setPosition(center.x-sensorWidth,center.y);
        sensorTwo.setPosition(center.x,center.y);
        sensorThree.setPosition(center.x-sensorWidth,center.y-sensorWidth);
        sensorFour.setPosition(center.x,center.y-sensorWidth);
    }
    public void movement(){
        if(target==world.shipTwo) {
            for (int i = 0; i < world.shipTwoBullets.size; i++) {
                if (Intersector.overlapConvexPolygons(sensorOne, world.shipTwoBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(3)-50;
                    velocity.y=velocity.y+splitGenome.get(4)-50;
                }
                if (Intersector.overlapConvexPolygons(sensorTwo, world.shipTwoBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(5)-50;
                    velocity.y=velocity.y+splitGenome.get(6)-50;
                }
                if (Intersector.overlapConvexPolygons(sensorThree, world.shipTwoBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(7)-50;
                    velocity.y=velocity.y+splitGenome.get(8)-50;
                }
                if (Intersector.overlapConvexPolygons(sensorFour, world.shipTwoBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(9)-50;
                    velocity.y=velocity.y+splitGenome.get(10)-50;
                }
                if(!Intersector.overlapConvexPolygons(sensorOne, world.shipTwoBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorTwo, world.shipTwoBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorThree, world.shipTwoBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorFour, world.shipTwoBullets.get(i).bounds)){
                    moveTimer++;
                    if(moveTimer==30) {
                        velocity.x = velocity.x + world.mathUtils.random(-splitGenome.get(11), splitGenome.get(11)+1);
                        velocity.y = velocity.y + world.mathUtils.random(-splitGenome.get(12), splitGenome.get(12)+1);
                        moveTimer=0;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < world.shipOneBullets.size; i++) {
                if (Intersector.overlapConvexPolygons(sensorOne, world.shipOneBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(3)-5;
                    velocity.y=velocity.y+splitGenome.get(4)-5;
                }
                if (Intersector.overlapConvexPolygons(sensorOne, world.shipOneBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(5)-5;
                    velocity.y=velocity.y+splitGenome.get(6)-5;
                }
                if (Intersector.overlapConvexPolygons(sensorOne, world.shipOneBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(7)-5;
                    velocity.y=velocity.y+splitGenome.get(8)-5;
                }
                if (Intersector.overlapConvexPolygons(sensorOne, world.shipOneBullets.get(i).bounds)) {
                    velocity.x=velocity.x+splitGenome.get(9)-5;
                    velocity.y=velocity.y+splitGenome.get(10)-5;
                }
                if(!Intersector.overlapConvexPolygons(sensorOne, world.shipOneBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorTwo, world.shipOneBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorThree, world.shipOneBullets.get(i).bounds)&&!Intersector.overlapConvexPolygons(sensorFour, world.shipOneBullets.get(i).bounds)){
                    moveTimer++;
                    if(moveTimer==30) {
                        velocity.x = velocity.x + world.mathUtils.random(-splitGenome.get(11), splitGenome.get(11));
                        velocity.y = velocity.y + world.mathUtils.random(-splitGenome.get(12), splitGenome.get(12));
                        moveTimer=0;
                    }
                }
            }
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
        if(reloadTimer%(RELOAD_TIME/world.speed)==0){
            orient();
            if(target==world.shipTwo) {
                world.shipOneBullets.add(new Bullet(position.x, position.y, 360 - rotation, world));
            }else{
                world.shipTwoBullets.add(new Bullet(position.x, position.y, 360 - rotation, world));
            }
            reloadTimer = 0;

        }
    }

    public void getHit(){
        fitness = fitness-4;
    }
}
