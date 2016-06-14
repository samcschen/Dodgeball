package com.dodgeball.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.objects.Bullet;
import com.dodgeball.game.objects.Crosshair;
import com.dodgeball.game.objects.Player;
import com.dodgeball.game.screens.GameScreen;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    public Player playerOne;
    public Array<Bullet> bullets;

    public Crosshair crosshair;
    public MathUtils mathUtils;
    public GameScreen gameScreen;


    public World(GameScreen gameScreen){
        playerOne = new Player(0,0, this);
        crosshair = new Crosshair(100,100, this);
        bullets = new Array<Bullet>();
        mathUtils = new MathUtils();
        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime){
        updateCharacters(deltaTime);
        updateBullets(deltaTime);
        updateInterface(deltaTime);
    }

    public void updateCharacters(float deltaTime){
        playerOne.update(deltaTime);
    }

    public void updateBullets(float deltaTime){
        for(int i = 0;i<bullets.size;i++){
            bullets.get(i).update(deltaTime);
            if(bullets.get(i).position.x> gameScreen.cam.viewportWidth||bullets.get(i).position.y>gameScreen.cam.viewportHeight||bullets.get(i).position.x<0 ||bullets.get(i).position.y<0){
                bullets.removeIndex(i);
            }
        }
    }

    public void updateInterface(float deltaTime){
        crosshair.update(deltaTime);
    }
}
