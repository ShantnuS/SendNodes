package com.sendnodes.nodes;

import java.util.ArrayList;
import java.util.List;

import com.sendnodes.entities.Player;

import powerups.NodePowerUp;

public class Node {
	private ArrayList<Connection> connections;
	private int hp;
	private int shield;
	private Player player;
	private List<Node> latestPipePath;
	private int x,y;
	private NodePowerUp powerup;
	
	public Node(int x, int y){
		connections = new ArrayList<Connection>();
		latestPipePath = new ArrayList<Node>();
		
		this.x = x;
		this.y = y;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setPowerUp(NodePowerUp powerup) {
		this.powerup = powerup;
	}
	
	public NodePowerUp getPowerUp() {
		return this.powerup;
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
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
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
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
