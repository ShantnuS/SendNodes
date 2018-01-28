package com.sendnodes;

public class Statistics {

	//Contains user stats over a game
	int nodesCaptured; //number of nodes captured
	long timeAlive; //time alive in seconds 
	
	public Statistics() {
		nodesCaptured=0;
		timeAlive=0;
	}
	
	public int getNodesCaptured() {
		return nodesCaptured;
	}
	public void setNodesCaptured(int nodesCaptured) {
		this.nodesCaptured = nodesCaptured;
	}
	public long getTimeAlive() {
		return timeAlive;
	}
	public void setTimeAlive(long timeAlive) {
		this.timeAlive = timeAlive;
	} 
	
	public void incrementNodesCaptured() {
		nodesCaptured++;
	}
	
}
