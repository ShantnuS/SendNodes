package com.sendnodes.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.sendnodes.GameController;
import com.sendnodes.entities.Player;

import powerups.NodePowerUp;

public class Node {
	private ArrayList<Connection> connections;
	private Random r = new Random();
	private int hp = r.nextInt(70) + 140;
	private int maxhp = hp;
	private int shield;
	private Player player;
	private List<Node> latestPipePath;
	private int x, y;
	private NodePowerUp powerup;
	private int ipBoost = r.nextInt(3);
	private int loot = (r.nextDouble() > 0.6) ? r.nextInt(80) + 20 : 0;

	public Node(int x, int y) {
		connections = new ArrayList<Connection>();
		latestPipePath = new ArrayList<Node>();

		this.x = x;
		this.y = y;
	}

	public int getHp() {
		return hp;
	}

	public void buffMaxhp(float multiplier) {
		this.maxhp *= multiplier;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setPowerUp(NodePowerUp powerup) {
		this.powerup = powerup;
	}

	public NodePowerUp getPowerUp() {
		return this.powerup;
	}

	public void setupRoot(Player player) {
		this.player = player;
		this.ipBoost = 0;
		this.hp = 3000;
	}

	public int getIpBoost() {
		return ipBoost;
	}

	public int getShield() {
		return shield;
	}

	public Player getOwner() {
		return player;
	}

	public boolean adjustHealth(int health, Player from) {
		if (!(player.getShieldStatus() == true && health < 0)) {
			this.hp += health;
			if (hp < 0) {
				Player oldPlayer = this.getOwner();
				if (this.getOwner() != null) {
					this.getOwner().lostNode(this);
				}
				this.setOwner(from);
				if (loot > 0) {
					from.giveLoot(loot);
					loot = 0;
				}
				if (from != null) {
					from.gainedNode(this);
				}
				hp = -hp;
				hp = (int) (hp * 1.0 / 2);
				hp += 20;

				if (hp > maxhp) {
					hp = maxhp;
				}
				return true;
			}
		}
		return false;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public void addConnection(Connection conn) {
		connections.add(conn);
	}

	public void removeConnection(Connection conn) {
		connections.remove(conn);
	}

	public List<Node> getPathBuilder() {
		return latestPipePath;
	}

	public void setOwner(Player player) {
		Player olderPlayer = this.getOwner();
		this.player = player;

		if (olderPlayer != null) {
			olderPlayer.lostNode(this);
			olderPlayer.recalculateNodeData();
		}
		if (this.player != null) {
			this.player.gainedNode(this);
			this.player.recalculateNodeData();
		}
	}

	public int getXPos() {
		return x;
	}

	public int getYPos() {
		return y;
	}
}
