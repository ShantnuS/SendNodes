package com.sendnodes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.EntityManager;
import com.sendnodes.ui.UIManager;


import soundengine.MusicManager;

import screens.MenuScreen;

import soundengine.SoundManager;

public class GameController {
	
	private EntityManager entityManager;
	private MenuScreen menuScreen;
	private int screenNumber; 
	
	//ALL THE GETTERS!
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}

	
	
	private SoundManager soundManager;
	private MusicManager musicManager;
	private UIManager uiManager;
	private Statistics stats;
	
	public Statistics getStats() {
		return stats;
	}

	private static final GameController instance = new GameController(Properties.DEFAULT_MAP_SIZE);
	
	public GameController(int map_size) {
		entityManager = new EntityManager(map_size);
		menuScreen = new MenuScreen();
		soundManager = new SoundManager();
		uiManager = new UIManager(entityManager);
		musicManager = new MusicManager();
		new Thread(musicManager).start();
		stats = new Statistics();
		screenNumber = 0;
	}
	
	public void setScreenNumber(int num) {
		this.screenNumber = num;

	}
	
	public static GameController getInstance() {
		return instance;
	}
	
	public void create() {
		entityManager.create();
		menuScreen.create();
	}
	
	public void update() {
		entityManager.update();
		uiManager.update();
	}
	 
	
	public void render(SpriteBatch batch){
		switch(screenNumber) {
		case 0: menuScreen.render(batch);
				break;
		case 1: entityManager.render(batch);
				break;
		default: menuScreen.render(batch);
				break;
		}

	}
	
	public SoundManager getSoundManager(){
		return soundManager;
	}
	public MusicManager getMusicManager(){
		return musicManager;
	}
}
