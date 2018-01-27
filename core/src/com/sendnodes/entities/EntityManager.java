package com.sendnodes.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.Network;

public class EntityManager {
	
	private Network map;
	private ArrayList<Player> players;
	
	public EntityManager(int map_size) {
		map = new Network(map_size);
		players = new ArrayList<Player>();
		players.add(new Player(map.getRandomNode()));
	}

	public void update() {
		for (Player player:players){
			player.update();
		}
		// map.update();
	}
	
	public void render(SpriteBatch batch){
		
	}
	
}
