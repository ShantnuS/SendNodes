package com.sendnodes.ui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sendnodes.Properties;

public class Container {

	ArrayList<TextButton> buttons;
	int posX;
	int posY;
	Texture texture;
	int sizeX;
	int sizeY;
	Stage stage;
	int bSizeX;
	int bSizeY;
	
	public Container(int posX, int posY, int bSizeX, int bSizeY, Stage stage) {
		buttons = new ArrayList<TextButton>();
		this.stage = stage;
		this.posX = posX;
		this.posY = posY;
		this.bSizeX = bSizeX;
		this.bSizeY = bSizeY;		
	}
	
	// If you want to just move the container
	public void move(int posX, int posY, boolean isLandscape){
		this.posX = posX;
		this.posY = posY;
		resizeActors(isLandscape);
	}
	
	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		stage.draw();
	}
	
	public void resizeActors(boolean isLandscape) {
		int bCount = buttons.size();
		int offsetX = posX;
		int offsetY = posY;
		int distance = 0;
		

		//offsetY -= (bSizeY*bCount)/2;
		offsetX += (bSizeX*bCount)/2;
		
		if(isLandscape) {
			distance = -bSizeX-10;
		}
		else {
			distance = -bSizeY-10;
		}
		
		int counter = 1;
		for(TextButton b: buttons) {
			b.setSize(bSizeX,bSizeY);
			if(isLandscape) {
				b.setPosition(offsetX+(distance*counter), offsetY );
			}
			else {
				b.setPosition(offsetX, offsetY+(distance*counter));
			}
			counter++;
			stage.addActor(b);
		}
	}
	
	public void addButton(TextButton button) {
		buttons.add(button);
		sizeX+=button.getWidth();
		sizeY+=button.getHeight();
	}
	
	public ArrayList<TextButton> getButtons() {
		return buttons;
	}
	public void setButtons(ArrayList<TextButton> buttons) {
		this.buttons = buttons;
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
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
}
