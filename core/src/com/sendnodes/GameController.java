package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;

public class GameController {
	
	private EntityManager entityManager;
	
	public GameController(int map_size) {
		entityManager = new EntityManager(map_size);
	}
	
	public void update() {
		entityManager.update();
	}
	
	public void render(SpriteBatch batch){
		entityManager.render(batch);
	}
}
