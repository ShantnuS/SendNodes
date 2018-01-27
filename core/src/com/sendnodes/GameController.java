package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;
import com.sendnodes.ui.UIManager;

public class GameController {
	
	private EntityManager entityManager;
	private UIManager uiManager;
	
	public GameController(int map_size) {
		entityManager = new EntityManager(map_size);
		uiManager = new UIManager(entityManager);
	}
	
	public void update() {
		entityManager.update();
		uiManager.update();
	}
	
	public void render(SpriteBatch batch){
		entityManager.render(batch);
	}
}
