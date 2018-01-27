package com.sendnodes.nodes;

import com.sendnodes.entities.Player;

public class Node {
	private Connection[] connections;
	private int hp;
	private int shield;
	private Player player;
	
	public int getHp() {
		return hp;
	}
	
	public int getShield() {
		return shield;
	}
	
	public void adjustHealth(int health, Player from) {
		this.hp += health;
		if (hp<0) {
			this.player = from;
			hp = -hp;
		}
	}
	
	
}
