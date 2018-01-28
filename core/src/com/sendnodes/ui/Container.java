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
	
	public Container(int posX, int posY, int sizeX, int sizeY, int bSizeX, int bSizeY) {
		buttons = new ArrayList<TextButton>();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.bSizeX = bSizeX;
		this.bSizeY = bSizeY;		
	}
	
	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		stage.draw();
	}
	
	public void resizeActors(boolean isLandscape) {
		int bCount = buttons.size();
		int offsetX = sizeX /2;
		int offsetY = sizeY /2;
		int distance = 0;
		
		if(isLandscape) {
			offsetY -= bSizeY/2;
			distance = -(sizeX) / (bCount + 2);
		}
		else {
			offsetX -= bSizeX/2;
			distance = -(sizeY) / (bCount + 2);
		}
		
		int counter = 1;
		for(TextButton b: buttons) {
			b.setSize(bSizeX,bSizeY);
			if(isLandscape) {
				b.setPosition(posX+distance*counter, (posY+offsetY) );
			}
			else {
				b.setPosition(posX+offsetX, (posY+distance*counter));
			}
			counter++;
			stage.addActor(b);
		}
	}
	
	public void addButton(TextButton button) {
		buttons.add(button);
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
