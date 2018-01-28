package com.sendnodes.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Node;

public class AttackDialogue {
	private int x, y;
	private Node query;
	private int nodeWidth, nodeHeight;
	private Player p;
	
	public AttackDialogue(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setQuery(Node n, int nodeWidth, int nodeHeight, Player player){
		query = n;
		this.nodeWidth = nodeWidth;
		this.nodeHeight = nodeHeight;
		this.p = player;
	}
	
	public Node getQuery(){
		return query;
	}
	public int getNodeWidth(){
		return nodeWidth;
	}
	public int getNodeHeight(){
		return nodeHeight;
	}
	public Player getPlayer(){
		return p;
	}
	
	public void render(SpriteBatch batch, Texture texture){
		System.out.println(x + " + " + y);
		System.out.println("x" + texture.getWidth());
		batch.draw(texture, x, y, 0.5f, 0.5f);
	}
}
