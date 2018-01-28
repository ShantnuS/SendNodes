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
//		System.out.println("      =");
//		System.out.println("     ===");
//		System.out.println("    =====");
//		System.out.println("   =======");
//		System.out.println("  =========");
//		System.out.println(" ===========");
//		System.out.println("=============");
//		System.out.println("    /:''|    ");
//		System.out.println("   |: 66|_   ");
//		System.out.println("   C     _)  ");
//		System.out.println("    \\ ._|      ");
//		System.out.println("     ) /       ");
//		System.out.println("   /`\\       ");
//		System.out.println("   || |Y|       ");
//		System.out.println("   || |#|       ");
//		System.out.println("   || |#|       ");
//		System.out.println("   || |#|       ");
//		System.out.println("   :| |=:       ");
//		System.out.println("   ||_|,|      ");
//		System.out.println("   \\)))||     ");
//		System.out.println("|~~~`-`~~~|   ");
//		System.out.println("|         |    ");
//		System.out.println("|_________|    ");
//		System.out.println("|_________|    ");
//		System.out.println("    | ||       ");
//		System.out.println("    |_||__        ");
//		System.out.println("    (____))    ");
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
