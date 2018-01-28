package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sendnodes.GameController;
import com.sendnodes.entities.EntityManager;

import soundengine.SoundManager;

public class UIManager {
	private EntityManager em;
	private boolean clickedDownLeft = false;
	private boolean clickedDownRight = false;
	
	public UIManager(EntityManager em){
		this.em = em;
	}
	
	public void update(){
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickedDownLeft == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			em.registerLeftClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDownLeft = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && clickedDownRight == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			em.registerRightClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDownRight = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
	}
}
