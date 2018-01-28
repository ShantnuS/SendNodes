package com.sendnodes.ai;

import java.util.ArrayList;
import java.util.Random;

import com.sendnodes.Network;
import com.sendnodes.entities.Attack;
import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class PlayerAI {
	public static void AIMove(Player player) {
		if (player.getTargets().size() == 0 && player.getOwnedNodes().size()>0) {
			int numberOfNodes = player.getOwnedNodes().size();
			System.out.println(numberOfNodes);
			ArrayList<Node> ownedNodes = player.getOwnedNodes();
			Random r = new Random();
			Node randomNode = ownedNodes.get(r.nextInt(numberOfNodes));
			for (Connection conn : randomNode.getConnections()) {
				if (conn.getOtherNode(randomNode) != null && conn.getOtherNode(randomNode).getOwner() != player) {
					if(Network.isConnected(player, player.getNode(), conn.getOtherNode(randomNode))){
						System.out.println(player);
						player.addTarget(new Attack(player, conn.getOtherNode(randomNode), -player.getCPU()));
					}
					break;
				}
			}
		}
	}
}
