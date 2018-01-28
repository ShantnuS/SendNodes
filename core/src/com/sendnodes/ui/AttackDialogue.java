package com.sendnodes.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AttackDialogue {
	private int x, y;
	
	public AttackDialogue(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch batch, Texture texture){
		batch.draw(texture, x, y, 0.5f, 0.5f);
	}
}
