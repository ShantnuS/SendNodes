package com.sendnodes.entities;

import com.sendnodes.nodes.Node;

public class Attack {
	private Player attacker;
	private Node target;
	private int damageAmount;
	
	public Attack(Player attacker, Node target, int damageAmount){
		this.damageAmount = damageAmount;
		this.target = target;
		this.attacker = attacker;
	}
	
	public int getDamage(){
		return damageAmount;
	}
	
	public Player getAttacker(){
		return attacker;
	}
	
	public Node getTarget(){
		return target;
	}
	
}
