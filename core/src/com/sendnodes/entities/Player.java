package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.Iterator;

import com.sendnodes.Network;
import com.sendnodes.Statistics;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

import powerups.PlayerPowerUp;

public class Player {
	private Node startingNode;
	private boolean shield = false;
	private int initial_ip = 5;
	private int current_ip = 5;
	private int loot = 0;
	private int passiveLootGen = 0;

	private ArrayList<Attack> attacks;
	private ArrayList<Attack> defences;
	private ArrayList<PlayerPowerUp> powerups;
	private ArrayList<Node> ownedNodes = new ArrayList<Node>();
	
	private String playerTextureName, nodeTextureName;
	
	Statistics stats;

	public Player(Node startNode, String playerTextureName, String nodeTextureName) {
		startingNode = startNode;
		ownedNodes.add(startNode);
		attacks = new ArrayList<Attack>();
		defences = new ArrayList<Attack>();
		powerups = new ArrayList<PlayerPowerUp>();
		
		this.playerTextureName = playerTextureName;
		this.nodeTextureName = nodeTextureName;
		stats = new Statistics();
	}
	
	public Statistics getStats() {
		return stats;
	}
	
	public void toggleShield(){
		shield  = !shield;
	} 
	
	public void increasePassiveLootGen(int amount){
		passiveLootGen += amount;
	}
	
	public boolean getShieldStatus(){
		return shield;
	}
	
	public void gainedNode(Node node){
		System.out.println("Add owned node");
		ownedNodes.add(node);
	}
	
	public void giveLoot(int loot){
		this.loot += loot;
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
	//	attackTargets(attacks);
	//	defendTargets(defences);
		
		applyCPUToTargets(defences);
		applyCPUToTargets(attacks);
	}

	public void recalculateNodeData() {
		current_ip = Network.calculateInfluence(this) + initial_ip;
		attacks = Network.pruneAttacks(this, attacks);
	}
	
	public int getCPU(){
		return current_ip;
	}

	// TODO: Prioritise attacks??
	private void applyCPUToTargets(ArrayList<Attack> targets) {
		loot+=passiveLootGen;
		
		int remainingIp = initial_ip;
		ArrayList<Player> killedVictims = new ArrayList<Player>();
		ArrayList<Attack> attacksToRemove = new ArrayList<Attack>();

		// Go through every target
		for (Attack attack : targets) {

			// Get wanted damage for this attack on the target
			int maxPossibleDamage = attack.getDamage();
			if (remainingIp <= 0) {
				break;
			}
			
			// Go through every connection to the target
			for (Connection conn: attack.getTarget().getConnections()) {

				// Cap damage if over AD points
				if (Math.abs(maxPossibleDamage) > remainingIp) {
					maxPossibleDamage = Integer.signum(maxPossibleDamage) * remainingIp;
				}

				// Check if this node is part of our attack or some other node
				if (conn.getOtherNode(attack.getTarget()).getOwner() == this && Network.isConnected(this, startingNode, conn.getOtherNode(attack.getTarget()))) {
					Player victim = attack.getTarget().getOwner();
					// Do all damage possible
					System.out.println(maxPossibleDamage);
					System.out.println(conn.getBandwidth());
					
					if (Math.abs(maxPossibleDamage) <= conn.getBandwidth()) {

						boolean destroyed = attack.getTarget().adjustHealth(maxPossibleDamage, this);
						if (destroyed) {
							attacksToRemove.add(attack);
							killedVictims.add(victim);
						}

						remainingIp -= Math.abs(maxPossibleDamage);
						break;

					}
					// Throttle damage
					else {
						System.out.println("throttle damage");
						boolean destroyed = attack.getTarget().adjustHealth(Integer.signum(maxPossibleDamage) * conn.getBandwidth(), this);
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
