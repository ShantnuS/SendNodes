package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sendnodes.GameController;
import com.sendnodes.entities.EntityManager;

import soundengine.SoundManager;

public class UIManager {
	private EntityManager em;
	private boolean clickedDown = false;
	
	public UIManager(EntityManager em){
		this.em = em;
	}
	public void update(){
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickedDown == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			em.registerClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDown = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}
}
