package com.dodgeball.game.utils;

import com.badlogic.gdx.math.MathUtils;
import com.dodgeball.game.objects.Crosshair;
import com.dodgeball.game.objects.Player;
import com.dodgeball.game.screens.GameScreen;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    public Player playerOne;
    public Crosshair crosshair;
    public MathUtils mathUtils;
    public GameScreen gameScreen;


    public World(GameScreen gameScreen){
        playerOne = new Player(0,0, this);
        crosshair = new Crosshair(100,100, this);

        mathUtils = new MathUtils();
        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime){
        updateCharacters(deltaTime);
        updateInterface(deltaTime);
    }

    public void updateCharacters(float deltaTime){
        playerOne.update(deltaTime);
    }

    public void updateInterface(float deltaTime){
        crosshair.update(deltaTime);
    }
}
