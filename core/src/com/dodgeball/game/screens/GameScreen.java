package com.dodgeball.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.dodgeball.game.Dodgeball;
import com.dodgeball.game.utils.World;
import com.dodgeball.game.utils.WorldRenderer;

/**
 * Created by samuel on 6/11/16.
 */
public class GameScreen extends ScreenAdapter {

    Dodgeball game;

    World world;
    WorldRenderer renderer;

    public GameScreen(Dodgeball game){
        this.game = game;
        world = new World();
        renderer = new WorldRenderer(game.batch , world);

    }

    public void update (float deltaTime){
        world.update(deltaTime);
    }

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void render(float delta){
        update(delta);
        draw();
    }
}
