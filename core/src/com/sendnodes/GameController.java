package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;
import com.sendnodes.ui.UIManager;

import soundengine.SoundManager;

public class GameController {
	
	private EntityManager entityManager;
	
	//ALL THE GETTERS!
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}


	private SoundManager soundManager;
	private UIManager uiManager;
	
	private static final GameController instance = new GameController(Properties.DEFAULT_MAP_SIZE);
	
	public GameController(int map_size) {
		entityManager = new EntityManager(map_size);
		soundManager = new SoundManager();
		uiManager = new UIManager(entityManager);
	}
	
	public static GameController getInstance() {
		return instance;
	}
	
	public void update() {
		entityManager.update();
		uiManager.update();
	}
	
	
	public void render(SpriteBatch batch){
		entityManager.render(batch);
	}
	
	public SoundManager getSoundManager(){
		return soundManager;
	}
}
