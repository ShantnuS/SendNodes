package com.sendnodes.entities;

import java.util.ArrayList;

import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class EntityManager {
	ArrayList<Node> nodes;
	ArrayList<Player> players;

	public void update() {
		for (Player player:players){
			player.update();
		}
	}
}
