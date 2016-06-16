package com.dodgeball.game.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.objects.*;
import com.dodgeball.game.screens.GameScreen;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    public ShipAI shipOne;
    public ShipAI shipTwo;

    public Array<Bullet> shipOneBullets;
    public Array<Bullet> shipTwoBullets;

    public int generation = 0;
    public int group = 1;
    public Array<String> genomes;

    public Crosshair crosshair;
    public MathUtils mathUtils;
    public GameScreen gameScreen;


    public World(GameScreen gameScreen){
        generateGenomes();
        generateShips();

        shipOneBullets = new Array<Bullet>();
        shipTwoBullets = new Array<Bullet>();

        crosshair = new Crosshair(100,100, this);

        mathUtils = new MathUtils();
        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime){
        updateCharacters(deltaTime);
        updateShips(deltaTime);
        updateBullets(shipOneBullets,shipTwo,deltaTime);
        updateBullets(shipTwoBullets,shipOne,deltaTime);
        updateInterface(deltaTime);
    }
    public void generateGenomes(){

        genomes = new Array<String>();
        for(int i =0;i<16;i++){
            genomes.add("");
            for(int j=0;j<9;j++){
                genomes.set(i,genomes.get(i)+String.valueOf(MathUtils.random(0,9)));
            }
        }
        System.out.println("First genome: "+genomes.get(0));
        System.out.println("Second genome: "+genomes.get(1));
    }
    public void generateShips(){
        shipOne = new ShipAI(100,100,genomes.get(0),shipTwo,this);
        shipTwo = new ShipAI(400,400,genomes.get(1),shipOne,this);
        shipOne = new ShipAI(100,100,genomes.get(0),shipTwo,this);
    }
    public void updateCharacters(float deltaTime){
       // playerOne.update(deltaTime);
    }

    public void updateShips(float deltaTime){
        shipOne.update(deltaTime);
        shipTwo.update(deltaTime);
    }

    public void updateBullets(Array<Bullet> bullets,ShipAI target, float deltaTime){
        for(int i = 0;i<bullets.size;i++){
            bullets.get(i).update(deltaTime);

            if(bullets.get(i).timer==300||bullets.get(i).isColliding(target.bounds)){
                bullets.removeIndex(i);
                target.reduceHealth();
            }
        }
    }

    public void updateInterface(float deltaTime){
        crosshair.update(deltaTime);
    }
}
