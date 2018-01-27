package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;

import soundengine.SoundManager;

public class GameController {
	
	private EntityManager entityManager;
	private SoundManager soundManager;
	
	public GameController(int map_size) {
		entityManager = new EntityManager(map_size);
		soundManager = new SoundManager();
	}
	
	public void update() {
		entityManager.update();
	}
	
	public void render(SpriteBatch batch){
		entityManager.render(batch);
	}
}
