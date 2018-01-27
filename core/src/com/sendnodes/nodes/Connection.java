package com.sendnodes.nodes;

public class Connection {
	private Node[] vertices = new Node[2];
	private int bandwidth;
	
	public Connection(Node conn1, Node conn2, int bandwidth) {
		vertices[0] = conn1;
		vertices[1] = conn2;
		this.bandwidth = bandwidth;
	}
	public Node[] getConnectedNodes() {
		return vertices;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	
	public Node getOtherNode(Node node){
		if(vertices[0] == node) {
			return vertices[1];
		}else if(vertices[1] == node){
			return vertices[0];
		}
		return null;
	}
}
