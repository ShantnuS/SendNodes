package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.Network;
import com.sendnodes.Properties;
import com.sendnodes.nodes.Node;

public class EntityManager {
	private HashMap<String, Texture> images;
	
	private Network map;
	private ArrayList<Player> players;
	private int[] node_size;
	private int tile_size;
	
	public EntityManager(int map_size) {
		images = new HashMap<String, Texture>();
		images.put("node_blue", new Texture("Node_blue.png"));
		
		map = new Network(map_size);
		players = new ArrayList<Player>();
		players.add(new Player(map.getRandomNode()));
		
		node_size = new int[2];
		node_size[0] = Properties.SCREEN_WIDTH/map_size;
		node_size[1] = Properties.SCREEN_HEIGHT/map_size;
		
		tile_size = images.get("node_blue").getWidth()*Properties.GRAPHICS_SCALE;
	}

	public void update() {
		for (Player player:players){
			player.update();
		}
		// map.update();
	}
	
	public void render(SpriteBatch batch){
		for (int x=0; x<map.getMap().length; x++){
			for (int y=0; y<map.getMap()[x].length; y++){
				if (map.getMap()[x][y] != null){
					batch.draw(images.get("node_blue"), x*node_size[0], y*node_size[1], tile_size, tile_size);
				}
			}
		}
	}
	
	public void registerClick(int x, int y){
		int xNode = (int) Math.floor(x/node_size[0]);
		int yNode = (int) Math.floor(y/node_size[1]);
		if(map.getMap()[x][y].getOwner()!=players.get(0) && map.isConnected(players.get(0), new Node(), map.getMap()[x][y])){
			players.get(0).addTarget(new Attack(players.get(0), map.getMap()[x][y], 1));
		}
		
		
	}
	
}
