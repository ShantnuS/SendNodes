package com.sendnodes.nodes;

import java.util.ArrayList;
import java.util.List;

import com.sendnodes.entities.Player;

public class Node {
	private ArrayList<Connection> connections;
	private int hp;
	private int shield;
	private Player player;
	private List<Node> latestPipePath;
	
	public Node(){
		connections = new ArrayList<Connection>();
		latestPipePath = new ArrayList<Node>();
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getShield() {
		return shield;
	}
	
	public Player getOwner(){
		return player;
	}
	
	public void adjustHealth(int health, Player from) {
		this.hp += health;
		if (hp<0) {
			this.player = from;
			hp = -hp;
		}
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public void addConnection(Connection conn) {
		connections.add(conn);
	}
	
	public void removeConnection(Connection conn) {
		connections.remove(conn);
	}
	
	public List<Node> getPathBuilder() {
		return latestPipePath;
	}
}
