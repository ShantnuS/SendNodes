package com.sendnodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Network {

	private ArrayList<Node> nodes;
	private Node[][] node_grid;
	private int size;
	private Random r = new Random();
	
	public Network(int size) {
		this.size = size;
		nodes = new ArrayList<Node>();
		
		node_grid = new Node[size][size];
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				node_grid[x][y] = new Node(x, y);
			}
		}
		
		
		Connection conn;
		
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				// for each default node that is not on the outside, generate adjacent connections 66% of the time.
				if (x > 1 && y > 1 && x < size-1 && y < size-1) {
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x+1][y], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x+1][y].addConnection(conn);
					}
					
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x][y+1], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x][y+1].addConnection(conn);
					}
					
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x+1][y+1], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x+1][y+1].addConnection(conn);
					}
					
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x-1][y], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x-1][y].addConnection(conn);
					}
					
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x][y-1], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x][y-1].addConnection(conn);
					}
					
					if (r.nextInt(3) != 1) {
						conn = new Connection(node_grid[x][y], node_grid[x-1][y-1], 1);
						node_grid[x][y].addConnection(conn);
						node_grid[x-1][y-1].addConnection(conn);
					}				
				}
				
			}
		}
		
		// Now all nodes are interconnected, prune connections and nodes to generate the map.
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				if (r.nextInt(2) == 1) {
					// 50% chance to remove a node and its connections.
					for (Connection null_conn : node_grid[x][y].getConnections())
						null_conn.getOtherNode(node_grid[x][y]).removeConnection(null_conn);
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
	
	public boolean isConnected(Player player, Node node1, Node node2) {
        List<Node> queue = new ArrayList<Node>();
        
        queue.add( node1 ); // start from here
        boolean pathExists = false;
        
        while (!queue.isEmpty()) {
            Node current = queue.remove(0);
            
            if (current == node2) {
                pathExists = true;
                break;
            }
            
            for (Connection conn : current.getConnections()) {
            	Node other = conn.getOtherNode(current);
            	if (other.getOwner() == player) {
            		other.getPathBuilder().addAll(current.getPathBuilder());
            		other.getPathBuilder().add(current);
            		queue.add(other);
            	}
            	else if (other == node2) {
            		pathExists = true;
                    break;
            	}
            }
        }
        
        return pathExists;
	}
	
	public boolean pipeProblem(Player player, Node node1, Node node2) {
		if (!isConnected(player, node1, node2))
			return false;
		
		
		return true;
	}
	
	public int getMapSize() {
		return size;
	}

	public Node[][] getMap() {
		return node_grid;
	}
	
	public ArrayList<Node> getMapNodesList() {
		return nodes;
	}
	
}
