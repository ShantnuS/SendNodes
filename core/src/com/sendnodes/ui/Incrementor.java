package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class Incrementor extends UIObject {

	Integer value;
	int delta;
	TextButton plus;
	TextButton minus;
	TextButton label;
	Stage stage;
	
	int posX;
	int posY;



	int sizeX;
	int sizeY;
	
	boolean isLandscape = true;
	public Incrementor(int posX, int posY, int sizeX, int sizeY, Integer value) {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		delta = 1;
		this.value=value;
		
	}
	
	public void resize() {
		minus = ButtonMaker.getBasicButton("-");
		plus = ButtonMaker.getBasicButton("+");
		label = ButtonMaker.getLabel("N/A");
		
        minus.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	decrement();
            }
        });   
        
        plus.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	increment();
            }
        });  
        
        int bSizeX = 0;
        int bSizeY = 0;
        
        if(isLandscape) {
            plus.setPosition(posX + sizeX*2/3,posY+sizeY);
            minus.setPosition(posX, posY+sizeY);
            label.setPosition(posX+ sizeX*1/3, posY+sizeY);
            bSizeX = sizeX/3;
            bSizeY = sizeY;
            
        }
        else {
            plus.setPosition(posX+sizeX,posY + sizeY*2/3);
            minus.setPosition(posX+sizeX, posY);
            label.setPosition(posX+sizeX, posY+ sizeY*1/3);
            bSizeX = sizeX;
            bSizeY = sizeY/3;
        }
        plus.setSize(bSizeX, bSizeY);
        minus.setSize(bSizeX, bSizeY);
        label.setSize(bSizeX, bSizeY);
        
        stage.addActor(plus);
        stage.addActor(minus);
        stage.addActor(label);
	}
	
	
	public void increment() {
		value+= delta;
		updateLabel();
	}
	
	public void decrement() {
		value -= delta;
		updateLabel();
	}
	
	public void setDelta(int x) {
		this.delta=x;
	}
	
	public void updateLabel() {
		label.setText(Integer.toString(value));
	}


	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		stage.draw();
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
	
	public void setPosition(int x, int y)
	{
		posX = x;
		posY = y;
	}
	
	
}
