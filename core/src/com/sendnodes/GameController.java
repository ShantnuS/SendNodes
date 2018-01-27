package com.sendnodes;

import com.sendnodes.entities.EntityManager;

public class GameController {
	
	private static final GameController controller = new GameController();
	
	private EntityManager manager;
	private final int map_size = 20;
	
	public GameController() {
		manager = new EntityManager(map_size);
	}
	
	public static GameController getInstance() {
		return controller;
	}
	
	public void tick() {
		manager.update();
	}
	
}
