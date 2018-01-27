package com.sendnodes.entities;

import java.util.ArrayList;

import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Player {
	private Node startingNode;
	private int ip;
	
	private ArrayList<Node> targets;
	
	public boolean addTarget(Node node) {
		targets.add(node);
		return true;
	}
	
	public boolean removeTarget(Node node) {
		targets.remove(node);
		return true;
	}
	
	public void update(){
		for (Node target:getTargets()) {
			//--PLACE HOLDER-- ATTACKS NEED OWN OBJECT TO GET THIS VALUE
			int amountToDamageTarget = 2;
			for(Connection conn:target.getConnections()) {
				if(conn.getOtherNode(target).getOwner() == this) {
					if(amountToDamageTarget<=conn.getBandwidth()) {
						target.adjustHealth(amountToDamageTarget, this);
						amountToDamageTarget-=amountToDamageTarget;
					}else {
						target.adjustHealth(conn.getBandwidth(), this);
						amountToDamageTarget-=conn.getBandwidth();
					}					
				}
			}
		}
	}
	
	
	public ArrayList<Node> getTargets(){
		return targets;
	}
}
