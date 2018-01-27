package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;

public class GameController {
	
	private EntityManager entityManager;
	private final int map_size = 20;
	
	public GameController() {
		entityManager = new EntityManager(map_size);
	}
	
	public void update() {
		entityManager.update();
	}
	
	public void render(SpriteBatch batch){
		entityManager.render(batch);
	}
}
