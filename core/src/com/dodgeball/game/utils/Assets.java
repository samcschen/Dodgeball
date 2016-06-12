package com.dodgeball.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by samuel on 6/11/16.
 */
public class Assets {

    public static Texture playerOne;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){
        playerOne = loadTexture("soldier.png");
    }

    public static void playSound(Sound sound){
        sound.play(1);
    }
}
