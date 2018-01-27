package com.sendnodes;

import java.util.ArrayList;
import java.util.Random;

import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Network {

	private ArrayList<Node> nodes;
	private int size;
	private Random r = new Random();
	
	public Network(int size) {
		this.size = size;
		nodes = new ArrayList<Node>();
		
		Node[][] node_grid = new Node[size][size];
		
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				// for each default node that is not on the outside, generate adjacent connections.
				if (x > 1 && y > 1 && x < size-1 && y < size-1) {
					Connection conn = new Connection(node_grid[x][y], node_grid[x+1][y], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x+1][y].addConnection(conn);
					
					conn = new Connection(node_grid[x][y], node_grid[x][y+1], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x][y+1].addConnection(conn);
					
					conn = new Connection(node_grid[x][y], node_grid[x+1][y+1], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x+1][y+1].addConnection(conn);
					
					conn = new Connection(node_grid[x][y], node_grid[x-1][y], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x-1][y].addConnection(conn);
					
					conn = new Connection(node_grid[x][y], node_grid[x][y-1], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x][y-1].addConnection(conn);
					
					conn = new Connection(node_grid[x][y], node_grid[x-1][y-1], 1);
					node_grid[x][y].addConnection(conn);
					node_grid[x-1][y-1].addConnection(conn);
				}
				
			}
		}
		
		// Now all nodes are interconnected, prune connections and nodes to generate the map.
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				if (r.nextInt(2) == 1) {
					// 50% chance
					for (Connection conn : node_grid[x][y].getConnections()) {
						// remove connection from other node
						conn.getOtherNode(node_grid[x][y]);
					}
					node_grid[x][y] = null;
				}
			}
		}
			
		
		// Empty the nodes generated into the ArrayList.
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				if (node_grid[x][y] != null)
					nodes.add(node_grid[x][y]);
			}
		}
	}
	
	// use for starting node
	public Node getRandomNode() {
		return nodes.get(r.nextInt(nodes.size()));
	}
	
	public int getMapSize() {
		return size;
	}

}
