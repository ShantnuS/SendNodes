package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.Iterator;

import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Player {
	private Node startingNode;
	private int ip = 500;
	
	private ArrayList<Attack> targets;
	
	public Player(Node startNode){
		startingNode = startNode;
		targets = new ArrayList<Attack>();
		System.out.println("x:"+startingNode.getX()+" y:"+startingNode.getY());
	}
	
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
	
	//TODO: Prioritise attacks??
	private void attackTargets(){
		int remainingIp = ip;
		Iterator<Attack> iterAttack = getTargets().iterator();
		while (iterAttack.hasNext()) {
			Attack attack = iterAttack.next();
			int amountToDamageTarget = attack.getDamage();
			if(remainingIp <= 0){
				break;
			}
			System.out.println("test2");
			for(Connection conn:attack.getTarget().getConnections()) {
				System.out.println("Amount to damage target " + amountToDamageTarget);
				
				System.out.println("Remaining IP " + remainingIp);
				System.out.println(conn.getOtherNode(attack.getTarget()));
				System.out.println(conn.getOtherNode(this.getNode()));
				System.out.println(this);
				
				if(amountToDamageTarget>remainingIp){
					System.out.println("test4");
					amountToDamageTarget = remainingIp;
				}
				if(conn.getOtherNode(attack.getTarget()).getOwner() == this) {
					if(amountToDamageTarget<=conn.getBandwidth()) {
						System.out.println("test5");
						boolean destroyed = attack.getTarget().adjustHealth(amountToDamageTarget, this);
						if(destroyed){
							iterAttack.remove();
							break;
						}
						amountToDamageTarget-=amountToDamageTarget;
						remainingIp-=amountToDamageTarget;
					}else {
						System.out.println("test6");
						boolean destroyed = attack.getTarget().adjustHealth(conn.getBandwidth(), this);
						if(destroyed){
							iterAttack.remove();
							break;
						}
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
	
	public Node getNode(){
		return startingNode;
	}
	
	public int getX(){
		return startingNode.getX();
	}
	public int getY(){
		return startingNode.getY();
	}
}
