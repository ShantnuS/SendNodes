package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class ButtonMaker {


    
	public static TextButton getBasicButton(final String text) {
		
		TextButtonStyle textButtonStyle;
		BitmapFont font;
		Skin skin;
		TextureAtlas buttonAtlas;

		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("UIAtlas/UI.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("UI_menu_button_up");
		textButtonStyle.down = skin.getDrawable("UI_menu_button_down");

		TextButton button = new TextButton(text, textButtonStyle);

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed " + text);
            }
        });    
		
		return button;
	}
	
	public static TextButton getTexturedButton(final String text, final String upIcon, final String downIcon) {
		
		TextButtonStyle textButtonStyle;
		BitmapFont font;
		Skin skin;
		TextureAtlas buttonAtlas;

		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("powerup.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable(upIcon);
		textButtonStyle.down = skin.getDrawable(downIcon);

		TextButton button = new TextButton(text, textButtonStyle);

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed " + text);
            }
        });    
		
		return button;
	}
	
	public static TextButton getLabel(final String text) {
		
		TextButtonStyle textButtonStyle;
		BitmapFont font;
		Skin skin;
		TextureAtlas buttonAtlas;

		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("UIAtlas/UI.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("UI_menu_button_up");
		textButtonStyle.down = skin.getDrawable("UI_menu_button_up");

		TextButton button = new TextButton(text, textButtonStyle);
		
		return button;
	}

}
