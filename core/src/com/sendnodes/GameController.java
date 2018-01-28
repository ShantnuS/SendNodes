package com.sendnodes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sendnodes.entities.EntityManager;
import com.sendnodes.ui.UIManager;

import screens.MenuScreen;
import soundengine.SoundManager;

public class GameController {
	
	private EntityManager entityManager;
	private MenuScreen menuScreen;
	public int screenNumber; 
	
	//ALL THE GETTERS!
	public EntityManager EM() {
		return entityManager;
	}
	
	public UIManager UI() {
		return uiManager;
	}

	
	
	private SoundManager soundManager;
	private UIManager uiManager;
	
	private Stage stage;
	
	public static final GameController instance = new GameController(Properties.DEFAULT_MAP_SIZE);
	
	public GameController(int map_size) {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		System.out.println("initialising");
		menuScreen = new MenuScreen(stage);
		uiManager = new UIManager(stage);
		entityManager = new EntityManager(map_size);
		soundManager = new SoundManager();
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
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			screenNumber = 0;
		}
	
		switch(screenNumber){
		case 1:
			uiManager.update();
			entityManager.update();
			break;
		}

	}
	
	
	public void render(SpriteBatch batch){
		switch(screenNumber) {
		case 0: menuScreen.render(batch);
				break;
		case 1: {
			entityManager.render(batch);
			uiManager.render(batch);
			break;
		}
				
		default: menuScreen.render(batch);
				break;
		}

	}
	
	public SoundManager getSoundManager(){
		return soundManager;
	}
}
