package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sendnodes.entities.EntityManager;

public class UIManager {
	private EntityManager em;
	
	public UIManager(EntityManager em){
		this.em = em;
	}
	public void update(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			em.registerClick(x, y);
		}

	}
}
