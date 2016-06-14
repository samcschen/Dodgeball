package com.dodgeball.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by samuel on 6/11/16.
 */
public class Assets {

    public static Texture playerOne;
    public static Texture bullet;
    public static Texture crosshairs;
    public static TextureRegion crosshairOne;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){

        playerOne = loadTexture("player.png");
        bullet = loadTexture("bullet.png");
        crosshairs = loadTexture("crosshairs.png");

        crosshairOne = new TextureRegion(crosshairs, 0.0f, 0.0f, .125f, .125f);

    }

    public static void playSound(Sound sound){
        sound.play(1);
    }
}
