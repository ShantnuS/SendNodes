package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.Iterator;

import com.sendnodes.Network;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

import powerups.PlayerPowerUp;

public class Player {
	private Node startingNode;
	private int initial_ip = 5;
	private int current_ip = 5;

	private ArrayList<Attack> attacks;
	private ArrayList<PlayerPowerUp> powerups;
	private ArrayList<Node> ownedNodes = new ArrayList<Node>();
	
	private String playerTextureName, nodeTextureName;

	public Player(Node startNode, String playerTextureName, String nodeTextureName) {
		startingNode = startNode;
		ownedNodes.add(startNode);
		attacks = new ArrayList<Attack>();
		powerups = new ArrayList<PlayerPowerUp>();
		System.out.println("x:" + startingNode.getXPos() + " y:" + startingNode.getYPos());
		
		this.playerTextureName = playerTextureName;
		this.nodeTextureName = nodeTextureName;
	}
	
	public void gainedNode(Node node){
		ownedNodes.add(node);
	}
	
	public void lostNode(Node node){
		ownedNodes.remove(node);
	}
	
	public ArrayList<Node> getOwnedNodes(){
		return ownedNodes;
	}

	public boolean addTarget(Attack attack) {
		attacks.add(attack);
		return true;
	}

	public boolean removeTarget(Node node) {
		attacks.remove(node);
		return true;
	}

	public void update() {
		attackTargets();
	}

	public void recalculateNodeData() {
		current_ip = Network.calculateInfluence(this) + initial_ip;
		attacks = Network.pruneAttacks(this, attacks);
	}
	
	public int getCPU(){
		return current_ip;
	}

	// TODO: Prioritise attacks??
	private void attackTargets() {
		int remainingIp = initial_ip;
		ArrayList<Player> killedVictims = new ArrayList<Player>();
		ArrayList<Attack> attacksToRemove = new ArrayList<Attack>();

		// Go through every target
		for (Attack attack : getTargets()) {

			// Get wanted damage for this attack on the target
			int maxPossibleDamage = attack.getDamage();
			if (remainingIp <= 0) {
				break;
			}
			
			System.out.println("test2");

			// Go through every connection to the target
			for (Connection conn : attack.getTarget().getConnections()) {

				// Cap damage if over AD points
				if (maxPossibleDamage > remainingIp) {
					maxPossibleDamage = remainingIp;
				}

				// Check if this node is part of our attack or some other node
				if (conn.getOtherNode(attack.getTarget()).getOwner() == this) {
					Player victim = attack.getTarget().getOwner();
					// Do all damage possible
					if (maxPossibleDamage <= conn.getBandwidth()) {

						boolean destroyed = attack.getTarget().adjustHealth(maxPossibleDamage, this);
						if (destroyed) {
							attacksToRemove.add(attack);
							killedVictims.add(victim);
						}

						remainingIp -= maxPossibleDamage;
						break;

					}
					// Throttle damage
					else {
						boolean destroyed = attack.getTarget().adjustHealth(conn.getBandwidth(), this);
						remainingIp -= conn.getBandwidth();

						if (destroyed) {
							attacksToRemove.add(attack);
							killedVictims.add(victim);
							break;
						}
						maxPossibleDamage -= conn.getBandwidth();
					}
				}
			}
		}
		

		// Recalculate influence points, and prune networks for all affected players
		for (int i=0; i<attacksToRemove.size(); i++){
			attacksToRemove.get(i).getAttacker().recalculateNodeData();
			if (killedVictims.get(i)!=null)
				killedVictims.get(i).recalculateNodeData();
		}
		
		// Remove all of the destroyed nodes
		getTargets().removeAll(attacksToRemove);

	}

	public ArrayList<Attack> getTargets() {
		return attacks;
	}

	public Node getNode() {
		return startingNode;
	}

	public int getX() {
		return startingNode.getXPos();
	}

	public int getY() {
		return startingNode.getYPos();
	}

	public int getInitialInfluence() {
		return initial_ip;
	}
	
	public String getPlayerTextureName(){
		return playerTextureName;
	}
	
	public String getNodeTextureName(){
		return nodeTextureName;
	}
}
