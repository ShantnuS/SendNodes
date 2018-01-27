package com.sendnodes.entities;

import java.util.ArrayList;

import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Player {
	private Node startingNode;
	private int ip;
	
	private ArrayList<Attack> targets;
	
	public boolean addTarget(Attack attack) {
		targets.add(attack);
		return true;
	}
	
	public boolean removeTarget(Node node) {
		targets.remove(node);
		return true;
	}
	
	public void update(){
		attackTargets();
	}
	
	private void attackTargets(){
		for (Attack target:getTargets()) {
			//--PLACE HOLDER-- ATTACKS NEED OWN OBJECT TO GET THIS VALUE
			int amountToDamageTarget = target.getDamage();
			for(Connection conn:target.getTarget().getConnections()) {
				if(conn.getOtherNode(target.getTarget()).getOwner() == this) {
					if(amountToDamageTarget<=conn.getBandwidth()) {
						target.getTarget().adjustHealth(amountToDamageTarget, this);
						amountToDamageTarget-=amountToDamageTarget;
					}else {
						target.getTarget().adjustHealth(conn.getBandwidth(), this);
						amountToDamageTarget-=conn.getBandwidth();
					}					
				}
			}
		}
	}
	
	
	public ArrayList<Attack> getTargets(){
		return targets;
	}
}
