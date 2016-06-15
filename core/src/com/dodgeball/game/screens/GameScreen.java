package com.dodgeball.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    public OrthographicCamera cam;
    public Viewport viewport;
    public float SCREEN_WIDTH;
    public float SCREEN_HEIGHT;

    public GameScreen(Dodgeball game){
        this.game = game;
        world = new World(this);
        renderer = new WorldRenderer(game.batch,game.shapeRenderer,world);

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        viewport = new ExtendViewport(1920,1080,cam);
        viewport.apply();

        cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2,0);
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
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.shapeRenderer.setProjectionMatrix(world.gameScreen.cam.combined);

        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
    }
}
