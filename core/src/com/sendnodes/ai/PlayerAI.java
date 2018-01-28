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
		if (player.getTargets().size() < 2 && player.getOwnedNodes().size() > 0) {
			System.out.println("===============");
			int numberOfNodes = player.getOwnedNodes().size();
			System.out.println(numberOfNodes);
			ArrayList<Node> ownedNodes = player.getOwnedNodes();
			Random r = new Random();
			Node randomNode = ownedNodes.get(r.nextInt(numberOfNodes));
			for (Connection conn : randomNode.getConnections()) {
				if (conn.getOtherNode(randomNode) != null && conn.getOtherNode(randomNode).getOwner() != player) {
					if (Network.isConnected(player, player.getNode(), conn.getOtherNode(randomNode))) {
						Attack nextAttack = new Attack(player, conn.getOtherNode(randomNode), -player.getCPU());
						if (!Attack.alreadyExists(player.getTargets(), nextAttack)) {
							player.addTarget(nextAttack);
						}
					}
				}
				if (player.getTargets().size() > 1) {
					for (Attack a : player.getTargets()) {
						System.out.println(a.getTarget().getHp());
					}
					System.out.println("-----------------");
					break;
				}
			}
		}
	}
}
