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
		int remainingIp = ip;
		for (Attack target:getTargets()) {
			int amountToDamageTarget = target.getDamage();
			if(remainingIp <= 0){
				return;
			}
			for(Connection conn:target.getTarget().getConnections()) {
				if(amountToDamageTarget>remainingIp){
					amountToDamageTarget = remainingIp;
				}
				if(conn.getOtherNode(target.getTarget()).getOwner() == this) {
					if(amountToDamageTarget<=conn.getBandwidth()) {
						target.getTarget().adjustHealth(amountToDamageTarget, this);
						amountToDamageTarget-=amountToDamageTarget;
						remainingIp-=amountToDamageTarget;
					}else {
						target.getTarget().adjustHealth(conn.getBandwidth(), this);
						amountToDamageTarget-=conn.getBandwidth();
						remainingIp-=conn.getBandwidth();
					}					
				}
			}
		}
	}
	
	
	public ArrayList<Attack> getTargets(){
		return targets;
	}
}
