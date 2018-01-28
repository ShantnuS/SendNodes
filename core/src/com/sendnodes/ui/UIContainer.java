package com.sendnodes.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIContainer {
	ArrayList<UIObject> containers;
	int posX;
	int posY;
	Texture texture;
	boolean isLandscape =false;
	
	public UIContainer(int posX, int posY) {
		containers = new ArrayList<UIObject>();
		this.posX = posX;
		this.posY = posY;	
	}
	
	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		for(UIObject b: containers)
		{	
			if(b instanceof ButtonContainer)
			{
				((ButtonContainer) b).render(batch);
			}
			if(b instanceof Incrementor)
			{
				((Incrementor) b).render(batch);
			}
		}
		
	}
	
	public void resizeActors() {
		
		int d = 0;
		for(UIObject b: containers) {

			if(isLandscape) {
				if(b instanceof ButtonContainer)
				{
					ButtonContainer i =((ButtonContainer) b);
					i.setPosition(posX + d,posY-i.getSizeY()/2);
					i.resizeActors();
					d += i.getSizeX();
				}
				if(b instanceof Incrementor)
				{
					Incrementor i =((Incrementor) b);
					i.setPosition(posX + d,posY-i.getSizeY()/2);
					i.resize();
					d += i.getSizeX();
				}
			}
			else {
				if(b instanceof ButtonContainer)
				{
					ButtonContainer i =((ButtonContainer) b);
					i.setPosition(posX -i.getSizeX()/2,posY+d);
					i.resizeActors();
					d += i.getSizeX();
				}
				if(b instanceof Incrementor)
				{
					Incrementor i =((Incrementor) b);
					i.setPosition(posX -i.getSizeX()/2,posY+d);
					i.resize();
					d += i.getSizeX();
				}
			}
		}
	}
	
	public void setIsLandscape(boolean x) {
		this.isLandscape =x;
	}
	
	public void addContainer(UIObject o) {
		containers.add(o);
	}
	
	public ArrayList<UIObject> getButtons() {
		return containers;
	}

	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
