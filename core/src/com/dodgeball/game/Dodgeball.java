package com.dodgeball.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dodgeball.game.screens.GameScreen;
import com.dodgeball.game.utils.Assets;

public class Dodgeball extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
