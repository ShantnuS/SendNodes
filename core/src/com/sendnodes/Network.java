package com.sendnodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Network {

	private ArrayList<Node> nodes;
	private ArrayList<Connection> connections;

	private Node[][] node_grid;
	private int size;
	private Random r = new Random();

	public void addConnection(int x1, int y1, int x2, int y2) {
		Connection conn = new Connection(node_grid[x1][y1], node_grid[x2][y2], 1);
		node_grid[x1][y1].addConnection(conn);
		node_grid[x2][y2].addConnection(conn);
		connections.add(conn);
	}

	private Object getRandomSetValue(Set set) {
		int noNodesAdded = set.size();
		int item = r.nextInt(noNodesAdded); // In real life, the Random
												// object should be rather more
												// shared than this
		int j = 0;
		for (Object obj : set) {
			if (j == item)
				return obj;
			j++;
		}
		return null;
	}

	public Network(int size) {
		this.size = size;
		node_grid = new Node[size][size];
		nodes = new ArrayList<Node>();
		connections = new ArrayList<Connection>();

		int treeInitialX = r.nextInt(size);
		int treeInitialY = r.nextInt(size);
		System.out.println(treeInitialX);
		System.out.println(treeInitialY);

		int numberOfNodes = (int) (size * 1.0 * size * 0.8);

		HashSet<Node> nodesAdded = new HashSet<Node>();

		node_grid[treeInitialX][treeInitialY] = new Node(treeInitialX, treeInitialY);
		nodesAdded.add(node_grid[treeInitialX][treeInitialY]);
		HashSet<String> connectionTracker = new HashSet<String>();
		for (int i = 1; i < numberOfNodes; i++) {
			if(nodesAdded.size()== size * size){
				break;
			}
			Node currentNode = (Node) getRandomSetValue(nodesAdded);
			int tempX = 0;
			int tempY = 0;
			while (tempX == tempY) {
				tempX = r.nextInt(3) - 1;
				tempY = r.nextInt(3) - 1;
			}

			tempX += currentNode.getXPos();
			tempY += currentNode.getYPos();

			if (!(tempX < 0 || tempY < 0 || tempY > size - 1 || tempX > size - 1 || nodesAdded.contains(node_grid[tempX][tempY]))) {
				node_grid[tempX][tempY] = new Node(tempX, tempY);
				addConnection(currentNode.getXPos(), currentNode.getYPos(), tempX, tempY);
				connectionTracker.add(currentNode.getXPos() + "" + currentNode.getYPos() + "" + tempX + "" + tempY);
				nodesAdded.add(node_grid[tempX][tempY]);
			}else{
				numberOfNodes += 1; 
			}
		}
		
		nodes.addAll(nodesAdded);
	}

	// use for starting node
	public Node getRandomNode() {
		return nodes.get(r.nextInt(nodes.size()));
	}

	public boolean isConnected(Player player, Node node1, Node target) {

		Set<Node> explored = new HashSet<Node>();
		List<Node> exploreQueue = new ArrayList<Node>();

		exploreQueue.add(node1); // start from here

		boolean pathExists = false;

		while (!exploreQueue.isEmpty() && !pathExists) {
			// Get next item in queue and mark it as explored
			Node current = exploreQueue.remove(0);

			if (!explored.contains(current)) {
				explored.add(current);

				// If our current node is our target to find, exit with success
				if (current == target) {
					pathExists = true;
					break;
				}

				// Go through each connected neighbour and add it to the
				// exploration queue
				for (Connection conn : current.getConnections()) {

					Node neighbour = conn.getOtherNode(current);

					// If the neighbour is ours, explore it
					if (neighbour.getOwner() == player || player == null) {
						neighbour.getPathBuilder().add(current);
						exploreQueue.add(neighbour);
					}
					// If it's the target, we found it!
					else if (neighbour == target) {
						pathExists = true;
						break;
					}
				}
			}
		}

		return pathExists;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
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
