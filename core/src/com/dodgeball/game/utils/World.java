package com.dodgeball.game.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.objects.Bullet;
import com.dodgeball.game.objects.Crosshair;
import com.dodgeball.game.objects.Player;
import com.dodgeball.game.objects.ShipAI;
import com.dodgeball.game.screens.GameScreen;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    public Player playerOne;
    public ShipAI shipOne;
    public ShipAI shipTwo;

    public Array<Bullet> playerBullets;
    public Array<Bullet> shipOneBullets;
    public Array<Bullet> shipTwoBullets;

    public Crosshair crosshair;
    public MathUtils mathUtils;
    public GameScreen gameScreen;


    public World(GameScreen gameScreen){
        playerOne = new Player(0,0, this);
        shipOne = new ShipAI(100,100,"000000000",shipTwo,this);
        shipTwo = new ShipAI(400,400,"000000000",shipOne,this);
        shipOne = new ShipAI(100,100,"499499499",playerOne,this);

        playerBullets = new Array<Bullet>();
        shipOneBullets = new Array<Bullet>();
        shipTwoBullets = new Array<Bullet>();

        crosshair = new Crosshair(100,100, this);

        mathUtils = new MathUtils();
        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime){
        updateCharacters(deltaTime);
        updateShips(deltaTime);
        updateBullets(playerBullets,deltaTime);
        updateBullets(shipOneBullets,deltaTime);
        updateBullets(shipTwoBullets,deltaTime);
        updateInterface(deltaTime);
    }

    public void updateCharacters(float deltaTime){
        playerOne.update(deltaTime);
    }

    public void updateShips(float deltaTime){
        shipOne.update(deltaTime);
        shipTwo.update(deltaTime);
    }

    public void updateBullets(Array<Bullet> bullets,float deltaTime){
        for(int i = 0;i<bullets.size;i++){
            bullets.get(i).update(deltaTime);
            if(bullets.get(i).timer==300){
                bullets.removeIndex(i);
            }
        }
    }

    public void updateInterface(float deltaTime){
        crosshair.update(deltaTime);
    }
}
