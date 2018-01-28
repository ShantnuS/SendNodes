package com.sendnodes.entities;

import java.util.ArrayList;

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
	
	public void incrementDamage(){
		damageAmount--;
	}
	
	public void decrementDamage(){
		damageAmount++;
	}
	
	public static boolean alreadyExists(ArrayList<Attack> attacks, Attack attack){
		for(Attack alreadyAttacking:attacks){
			if(alreadyAttacking.getTarget() == attack.getTarget() && alreadyAttacking.getAttacker()==attack.getAttacker()){
				return true;
			}
		}
		return false;
	}
	
}
