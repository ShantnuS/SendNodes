package com.sendnodes.ai;

import java.util.ArrayList;
import java.util.Random;

import com.sendnodes.entities.Attack;
import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class PlayerAI {
	public static void AIMove(Player player) {
		if (player.getTargets().size() == 0) {
			int numberOfNodes = player.getOwnedNodes().size();
			ArrayList<Node> ownedNodes = player.getOwnedNodes();
			Random r = new Random();
			Node randomNode = ownedNodes.get(r.nextInt(numberOfNodes));
			for (Connection conn : randomNode.getConnections()) {
				if (conn.getOtherNode(randomNode) != null && conn.getOtherNode(randomNode).getOwner() != player) {
					player.addTarget(new Attack(player, conn.getOtherNode(randomNode), -1));
					break;
				}
			}
		}
	}
}
