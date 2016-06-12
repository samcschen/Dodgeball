package com.dodgeball.game.utils;

import com.dodgeball.game.objects.Player;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    Player playerOne;

    public World(){
        playerOne = new Player(120,120);
    }

    public void update(float deltaTime){
        updateCharacters(deltaTime);
    }

    public void updateCharacters(float deltaTime){
        playerOne.update(deltaTime);
    }
}
