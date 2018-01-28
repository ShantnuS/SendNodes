package com.sendnodes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import soundengine.MusicManager;

public class Core extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameController controller;

	@Override
	public void create() {
		batch = new SpriteBatch();
		controller = GameController.getInstance();
		controller.create();
	}

	@Override
	public void render() {
		controller.update();
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		controller.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
